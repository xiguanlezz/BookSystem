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
        function DeleteCate(id) {
            var url = "${pageContext.request.contextPath}/docatedelete.do?id=" + id;
            if (confirm("你确定要删除这个分类吗?")) {
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
            <span class="crumb-name">分类管理</span>
        </div>
    </div>

    <div class="result-wrap">
        <form id="myform" method="post" action="#">
            <div class="result-title">
                <div class="result-list">
                    <a href="${pageContext.request.contextPath}/tocateadd.do"><i class="icon-font"></i>新增分类</a>
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="60%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>分类名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <%--                    只写了二级分类--%>
                    <tbody>
                    <c:forEach items="${requestScope.catelist}" var="rootcate">
                        <c:if test="${rootcate.CATE_PARENT_ID==0}">
                            <tr>
                                <td>${rootcate.CATE_ID}</td>
                                <td>${rootcate.CATE_NAME}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/tocateupdate.do?id=${rootcate.CATE_ID}">修改</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="javascript:DeleteCate(${rootcate.CATE_ID})">删除</a>
                                </td>
                            </tr>
                        </c:if>

                        <c:forEach items="${requestScope.catelist}" var="soncate">
                            <%--                            判断当前层的父ID是否等于上层的ID, 不能单纯的判断当前层的父ID是1, 这样会在每个根节点后面打印出所有的二级结点--%>
                            <c:if test="${soncate.CATE_PARENT_ID==rootcate.CATE_ID}">
                                <tr>
                                    <td>${soncate.CATE_ID}</td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${soncate.CATE_NAME}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/tocateupdate.do?id=${soncate.CATE_ID}">修改</a>
                                        &nbsp;&nbsp;&nbsp;
                                        <a href="javascript:DeleteCate(${soncate.CATE_ID})">删除</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
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
