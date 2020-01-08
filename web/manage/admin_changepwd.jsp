<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/26
  Time: 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>
    <style>
        .err {
            display: inline-block;
            /*border: 1px solid red;*/
            background-color: #ffe8e0;
            height: 25px;
            margin-left: 20px;
            line-height: 25px;
        }
    </style>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script>
        function CheckAndRemoveAdminPwd(obj) {
            $(obj).next('span').html('').removeClass('err');
        }

        function CheckAndShowAdminPwd(obj) {
            var msgBox = $(obj).next('span');
            var oldPwd = $("input[name='oldPwd']").val();
            var newPwd = $(obj).val();

            var url = "${pageContext.request.contextPath}/adminchangepwd.do?oldPwd=" + oldPwd + "&newPwd=" + newPwd;
            $.get(url, function (data) {
                if (data == "false") {
                    //alert(oldPwd + "--" + newPwd);
                    msgBox.addClass("err");
                    console.log(msgBox.val())
                    msgBox.html("新密码不能与近期使用过的密码相同");
                }
            })
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
            <a href="#">修改密码</a>
            <span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="#">管理员${user.USER_NAME}</a>
            <span class="crumb-step">&gt;</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/adminchangepwd.do" method="post">
                <table class="insert-tab" width="50%">
                    <tbody>
                    <tr>
                        <th><i class="require-red">*</i>用户名：</th>
                        <td>
                            <input class="common-text required" name="userName" size="50" type="text"
                                   value="${user.USER_ID}" readonly="readonly">
                        </td>
                    </tr>

                    <tr>
                        <th><i class="require-red">*</i>原始密码：</th>
                        <td>
                            <input class="common-text required" name="oldPwd" size="50" type="text"
                                   value="${user.USER_PASSWORD}">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>新密码：</th>
                        <td>
                            <input class="common-text required" name="newPwd" size="50" type="text"
                                   onfocus="CheckAndRemoveAdminPwd(this)" onblur="CheckAndShowAdminPwd(this)">
                            <span class="err"></span>
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
