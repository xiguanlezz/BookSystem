<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/6
  Time: 15:36
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

    <script>
        function DeleteProduct(id) {
            var url = "${pageContext.request.contextPath}/doproductdelete.do?id=" + id;
            if (confirm("你确定要删除这本图书吗?")) {
                location.href = url;
            }
        }

        function CloseOrder(msg, id) {
            var url = "${pageContext.request.contextPath}/doorderclose.do?oid=" + id;
            if (confirm(msg)) {
                location.href = url;
            }
        }
    </script>

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
            <span class="crumb-name">订单管理</span>
        </div>
    </div>

    <div class="result-wrap">
        <form id="myform" method="post" action="${pageContext.request.contextPath}/doorderselect.do">
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户姓名</th>
                        <th>下单时间</th>
                        <th>收货地址</th>
                        <th>订单备注</th>
                        <th>订单总价</th>
                        <th>订单状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${requestScope.orderslist}" var="order">
                        <tr>
                            <td>${order.ORDER_ID}</td>
                            <td>
                                <c:forEach items="${Index_Nmap}" var="inmap">
                                    <c:if test="${inmap.key==order.ORDER_ID}">
                                        ${inmap.value}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${order.ORDER_TIME}</td>
                            <td>
                                <c:forEach items="${Index_Amap}" var="iamap">
                                    <c:if test="${iamap.key==order.ORDER_ID}">
                                        ${iamap.value}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${order.ORDER_REMARK}</td>
                            <td>${order.ORDER_TOTAL_PRICE}</td>
                            <c:if test="${order.ORDER_STATUS=='1'}">
                                <td class="status">未付款</td>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='2'}">
                                <td class="status">未收货</td>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='3-1'}">
                                <td class="status">已收货但未评价</td>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='3-2'}">
                                <td class="status">已收货且已评价</td>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='4'}">
                                <td class="status">已关闭</td>
                            </c:if>
                            <td>
                                <a href="javascript:CloseOrder('你确定要关闭该订单吗?',${order.ORDER_ID})">
                                    <c:if test="${order.ORDER_STATUS=='4'}">
                                        <span style="color: red;">关闭该订单</span>
                                    </c:if>
                                    <c:if test="${order.ORDER_STATUS!='4'}">
                                        <span>关闭该订单</span>
                                    </c:if>
                                </a>
                            </td>
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
