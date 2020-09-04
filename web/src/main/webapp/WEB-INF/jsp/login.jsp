<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/inc/tld.inc" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link href="<c:url value='/static/css/login.css'/>" rel="stylesheet" type="text/css">
    <%@ include file="/static/inc/css-link.inc"%>
    <%@ include file="/static/inc/js-link.inc"%>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <h2>登录</h2>
            <form id="loginForm" method="post" autocomplete="off" class="form-horizontal"
                  action="<c:url value='/verifyLogin'/>">
                <div class="form-group">
                    <label class="col-md-2 control-label">工号:</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="operatorCode"
                               id="operatorCode" placeholder="请输入工号" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">密码:</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" name="operatorPassword"
                               id="operatorPassword" placeholder="请输入密码" autocomplete="off">
                    </div>
                </div>
                <div style="display:none;">
                    <label id="msg">${msg}</label>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <button id="loginButton" onclick="login();" class="btn btn-primary btn-block">登
                            录
                        </button>
                    </div>
                </div>
            </form>
            <a href="<c:url value='/register'/>">去注册</a>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function () {
        var msg = $("#msg").html();
        if (null != msg && msg != "") {
            swal(msg);
        }
    });

    $().ready(function () {
        $("#loginForm").validate({
            rules: {
                operatorCode: "required",
                operatorPassword: "required",
            },
            messages: {
                operatorCode: "请输入工号",
                operatorPassword: "请输入密码",
            },
            submitHandler: function (form) {
                form.submit();
            }
        });

        $('#operatorCode').change(function () {
            $("#errorMsg").html("");
        });
        $('#operatorPassword').change(function () {
            $("#errorMsg").html("");
        });
    });

    function login() {
        $("#errorMsg").html("");
        $("#loginForm").submit();
    }

</script>

</body>
</html>
