<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/28
  Time: 9:02
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
            <span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="${pageContext.request.contextPath}/docateselect.do">分类管理</a>
            <span class="crumb-step">&gt;</span>
            <span>修改分类</span>
        </div>
    </div>

<%--    分类添加的时候, 为了能选择父分类, 需要先到Servlet跳转一下--%>
<%--    只写了二级分类--%>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/docateupdate.do" method="post" name="myform">
<%--                隐藏表单, 存放要修改的id值--%>
                <input type="hidden" value="${category.CATE_ID}" name="id">
                <table class="insert-tab" width="100%">
                    <tbody>
                        <tr>
                            <th><i class="require-red">*</i>父级分类：</th>
                            <td>
                                <select name="parentId" class="common-text required">
                                    <option value="0">根分类</option>
                                    <c:forEach items="${catelist}" var="cate">
                                        <c:if test="${cate.CATE_PARENT_ID==0}">
                                            <c:if test="${category.CATE_PARENT_ID==cate.CATE_ID}">
                                                <option value="${cate.CATE_ID}" selected>${cate.CATE_NAME}</option>
                                            </c:if>
                                            <c:if test="${category.CATE_PARENT_ID!=cate.CATE_ID}">
                                                <option value="${cate.CATE_ID}">${cate.CATE_NAME}</option>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <th><i class="require-red">*</i>分类名称：</th>
                            <td>
                                <input class="common-text required" name="className" size="50" type="text" value="${category.CATE_NAME}">
                            </td>
                        </tr>

                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="修改" type="submit">
                                <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

</div>
<!--/main-->
</div>
</body>
</html>
