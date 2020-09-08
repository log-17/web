<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/inc/tld.inc" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <link href="<c:url value='/static/css/frame.css'/>" rel="stylesheet" type="text/css">
    <%@ include file="/static/inc/css-link.inc" %>
    <%@ include file="/static/inc/js-link.inc" %>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">欢迎</a>
        </div>
        <div>
            <ul class="nav navbar-nav">

                <c:forEach items="${menuMap}" var="m">
                    <%--如果没有子菜单--%>
                    <c:if test="${m.value.size()==0}">
                        <li>
                            <a class="menu-item" name="${m.key.menuURL}">${m.key.menuName}</a>
                        </li>
                    </c:if>
                    <%--如果有子菜单--%>
                    <c:if test="${m.value.size()!=0}">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">${m.key.menuName}
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <c:forEach items="${m.value}" var="menu">
                                    <li class="dropdown">
                                        <a class="dropdown-toggle" data-toggle="dropdown"
                                           name="${menu.menuURL}">${menu.menuName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>

                <li>
                    <a href="<c:url value='/logOut'/>">退出登录</a>
                </li>
            </ul>
        </div>
    </div>

</nav>
<div>
    <iframe class="iframe-content" name="iframe-content" scrolling="no" src="">

    </iframe>
</div>
<script>
    $(".menu-item").click(function () {
        var src = $(this).attr("name");
        $(".iframe-content").attr("src", src);
    });

    $(".dropdown-toggle").click(function () {
        var src = $(this).attr("name");
        $(".iframe-content").attr("src", src);
    });
</script>
</body>
</html>
