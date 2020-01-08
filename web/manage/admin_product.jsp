<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/28
  Time: 8:25
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
            <span class="crumb-name">图书管理</span>
        </div>
    </div>

    <div class="result-wrap">
        <form id="myform" method="post" action="${pageContext.request.contextPath}/doproductselect.do">
            <div class="result-title">
                <div class="result-list">
                    <a href="${pageContext.request.contextPath}/toproductadd.do"><i class="icon-font"></i>新增图书</a>
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="60%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>图书名称</th>
                            <th>操作</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.products}" var="p">
                            <tr>
                                <td>${p.PRODUCT_ID}</td>
                                <td>
    <%--                                                                       现在的目录是D:\IDEAproject\MyProject\E-CommerceSystem\out\artifacts\E_CommerceSystem_war_exploded--%>
                                    <img src="images/product/${p.PRODUCT_FILENAME}" alt="..."
                                         style="width: 100px;height: 100px;">
                                        ${p.PRODUCT_NAME}
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/toproductupdate.do?id=${p.PRODUCT_ID}">修改</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="javascript:DeleteProduct(${p.PRODUCT_ID})">删除</a>
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
