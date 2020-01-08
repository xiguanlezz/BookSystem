<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/7
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<%@include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list">
            <i class="icon-font"></i>
            <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt</span>
            <span class="crumb-name">评价管理</span>
        </div>
    </div>

    <div class="result-wrap">
        <form id="myform" method="post" action="${pageContext.request.contextPath}/doproductselect.do">
            <div class="result-content">
                <table class="result-tab" width="60%">
                    <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>用户名</th>
                        <th>订单ID</th>
                        <th>评价分数</th>
                        <th>评价词</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.e_smap}" var="map">
                            <tr>
                                <td>${map.key.EVALUATION_U_ID}</td>
                                <td>${map.value}</td>
                                <td>${map.key.EVALUATION_O_ID}</td>
                                <td>${map.key.EVALUATION_SCORE}</td>
                                <td>${map.key.EVALUATION_WORDS}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>
