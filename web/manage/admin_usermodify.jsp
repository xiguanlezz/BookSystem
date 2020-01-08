<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/25
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>
</head>

<body>
<%@include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>
            <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt</span>
            <a class="crumb-name" href="${pageContext.request.contextPath}/douserselect.do">用户管理</a>
            <span class="crumb-step">&gt</span>
            <span>修改用户</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/douserupdate.do" method="post">

                <input type="hidden" value="${user.USER_STATUS}" name="userStatus">
                <input type="hidden" value="${currentPage}" name="currentPage">
                <input type="hidden" value="${pageSize}" name="pageSize">

                <table class="insert-tab" width="60%">
                    <tbody>
                    <tr>
                        <th><i class="require-red">*</i>用户名：</th>
                        <td>
                            <input class="common-text required" name="userName" size="50" type="text" value="${user.USER_ID}" readonly="true">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>用户姓名：</th>
                        <td>
                            <input class="common-text required" name="name" size="50" type="text" value="${user.USER_NAME}">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>登陆密码：</th>
                        <td>
                            <input class="common-text required" name="password" size="50" type="text" value="${user.USER_PASSWORD}">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>性别：</th>
                        <td>
                            <input class="common-text required" name="sex" size="50" value="T" type="radio" ${user.USER_SEX == 'T' ? 'checked' : ''}>男
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input class="common-text required" name="sex" size="50" value="F" type="radio" ${user.USER_SEX == 'F' ? 'checked' : ''}>女
                        </td>
                    </tr>
                    <tr>
                        <th>出生日期：</th>
                        <td>
                            <input class="common-text required" name="birthday" size="50" value="${user.USER_BIRTHDAY}">
                        </td>
                    </tr>
                    <tr>
                        <th>电子邮箱：</th>
                        <td>
                            <input class="common-text required" name="email" size="50" value="${user.USER_EMAIL}">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>手机号码：</th>
                        <td>
                            <input class="common-text required" name="mobile" size="50" value="${user.USER_MOBILE}">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>送货地址：</th>
                        <td>
                            <input class="common-text required" name="address" size="50" value="${user.USER_ADDRESS}">
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
