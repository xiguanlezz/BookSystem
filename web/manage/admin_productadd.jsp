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
            <a class="crumb-name" href="${pageContext.request.contextPath}/doproductselect.do">图书管理</a>
            <span class="crumb-step">&gt;</span>
            <span>新增图书</span>
        </div>
    </div>

<%--    分类添加的时候, 为了能选择父分类, 需要先到Servlet跳转一下--%>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/doproductadd.do" method="post" name="myform" enctype="multipart/form-data">
                <table class="insert-tab" width="40%">
                    <tbody>
                        <tr>
                            <th><i class="require-red">*</i>图书名称：</th>
                            <td>
                                <input class="common-text required" name="productName" size="50" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书分类：</th>
                            <td>
                                <select name="F_C_Id" class="common-text required">
                                    <c:forEach items="${flist}" var="f">
                                        <option disabled>${f.CATE_NAME}</option>
                                        <c:forEach items="${clist}" var="c">
                                            <c:if test="${c.CATE_PARENT_ID==f.CATE_ID}">
    <%--                                            将子ID和父ID拼到一起--%>
                                                <option value="${f.CATE_ID}+${c.CATE_ID}">&nbsp;&nbsp;&nbsp;${c.CATE_NAME}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>图书描述：</th>
                            <td>
                                <input class="common-text required" name="productDesc" size="50" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书价格：</th>
                            <td>
                                <input class="common-text required" name="productPrice" size="50" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书库存：</th>
                            <td>
                                <input class="common-text required" name="productStock" size="50" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书封面：</th>
                            <td>
                                <input class="common-text required" name="picture" size="50" type="file" style="height: 30px;">
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
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
