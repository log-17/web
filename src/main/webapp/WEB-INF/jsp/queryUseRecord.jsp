<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/inc/tld.inc" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>使用记录</title>
    <link href="<c:url value='/static/css/queryUseRecord.css'/>" rel="stylesheet" type="text/css">
    <%@ include file="/static/inc/css-link.inc"%>
    <%@ include file="/static/inc/js-link.inc"%>
</head>

<body>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-md-12">
            <div class="ibox">
                <div class="ibox-content">
                    <form method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-1 control-label">服务编码</label>
                            <div class="col-md-2">
                                <input type="text" class="form-control input-md"
                                       id="serviceCode">
                            </div>
                            <label class="col-md-1 control-label">开始日期</label>
                            <div class="col-md-2">
                                <div class="input-group date" id="startDatepicker"
                                     data-provide="datepicker">
                                    <input type="text" class="form-control" id="startQDate"
                                           readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                            <label class="col-md-1 control-label">结束日期</label>
                            <div class="col-md-2">
                                <div class="input-group date" id="endDatepicker"
                                     data-provide="datepicker">
                                    <input type="text" class="form-control" id="endQDate" readonly>
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1">
                                <a class="btn btn-primary" type="button"
                                   href="javascript:search();">查 询</a>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered" id="useRecordQuery_table"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var oTable = new TableInit();
        oTable.Init();

        $("#startDatepicker").datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            startView: 'day'
        });
        $("#endDatepicker").datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            startView: 'day'
        });

    });

    function CompareDate(d1, d2) {
        return ((new Date(d1.replace(/-/g, "\/"))) > (new Date(d2.replace(
            /-/g, "\/"))));
    }

    function getThreeMonthsAfter(date) {
        var s = date.split("-");
        var yy = parseInt(s[0]);
        var mm = parseInt(s[1]);
        var dd = parseInt(s[2]);
        var dt = new Date(yy, mm + 2, dd);
        var dtArr = dt.toLocaleDateString().split("/");
        var dtStr = dtArr[0] + "-" + dtArr[1] + "-" + dtArr[2];
        return dtStr;
    }

    function search() {
        if (($("#startQDate").val() != '' && $("#endQDate").val() == '') || ($("#startQDate").val() == '' && $("#endQDate").val() != '')) {
            swal("请完善起止时间！");
            return;
        } else {
            if (CompareDate($("#startQDate").val(), $("#endQDate").val())) {
                swal("开始时间应小于结束时间!");
                return;
            } else {
                var requiredEndDate = getThreeMonthsAfter($("#startQDate")
                    .val());
                if (CompareDate($("#endQDate").val(), requiredEndDate)) {
                    swal("起止时间跨度只能3个月!")
                    return;
                }
            }
        }

        $('#useRecordQuery_table').bootstrapTable('refresh');
    }

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#useRecordQuery_table').bootstrapTable({
                url: "<c:url value='/useRecordSearch'/>",
                method: 'get',
                striped: true,
                cache: false,
                pagination: true,
                queryParams: oTableInit.queryParams,
                sidePagination: "server",
                pageNumber: 1,
                pageSize: 10,
                pageList: [10],
                contentType: "application/x-www-form-urlencoded",
                showColumns: false,
                showRefresh: false,
                minimumCountColumns: 2,
                clickToSelect: true,
                showToggle: false,
                cardView: false,
                detailView: false,
                columns: [{
                    field: 'serviceCode',
                    title: '服务编码',
                    align: 'center',
                    width: 500,
                }, {
                    field: 'createDate',
                    title: '创建日期',
                    align: 'center',
                }, {
                    field: 'timestampDifference',
                    title: '接口调用时间(ms)',
                    align: 'center',
                }, {
                    field: 'isSuc',
                    title: '是否成功',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value == 1) {
                            return '是';
                        }
                        if (value == 0) {
                            return '否';
                        }
                        return "--";
                    }
                }],
            });

        };
        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var temp = {
                limit: params.limit,
                offset: params.offset,
                serviceCode: $("input[id=serviceCode]").val(),
                startDateStr: $("input[id=startQDate]").val(),
                endDateStr: $("input[id=endQDate]").val()
            };
            return temp;
        };

        return oTableInit;
    };
</script>
</body>
</html>