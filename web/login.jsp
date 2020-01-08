<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/27
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body><!-------------------login-------------------------->
<div class="login">
    <form action="${pageContext.request.contextPath}/login" method="post" style="padding-top: 60px">
        <div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>
        <p><input type="text" name="userName" placeholder="昵称/邮箱/手机号" value="${userName}"></p>
        <p><input type="password" name="password" placeholder="密码" value="${Pwd}"></p>
        <p><input type="submit" value="登  录"></p>
        <p class="txt">
            <a href="register.jsp">免费注册</a>
            <a href="#">忘记密码？</a>
        </p>
    </form>
</div>
</body>
</html>
