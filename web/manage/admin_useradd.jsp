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

    <script type="text/javascript">
        function CheckAndRemoveuserName(obj) {
            //console.log($(obj).attr("name"));
            $(obj).next('span').html('').removeClass('err');
        }

        function CheckAndShowuserName(obj) {
            var msgBox = $(obj).next('span');
            var url = "${pageContext.request.contextPath}/AdminUserAddCheck?";
            var value = $(obj).val();
            url += $(obj).attr("name") + "=" + value;
            console.log(url);
            $.get(url, function (data) {
                if (data == "already exist") {
                    msgBox.addClass("err");
                    msgBox.html("用户名已存在");
                } else if (data == "user-true") {

                } else if (data == "username-false") {
                    msgBox.addClass("err");
                    msgBox.html("用户名必须是6-12位, 且必须是字母开头");
                }
            });
        }

        function CheckAndRemovepassword(obj) {
            //console.log($(obj).attr("name"));
            $(obj).next('span').html('').removeClass('err');
        }

        function CheckAndShowpassword(obj) {
            var msgBox = $(obj).next('span');
            var url = "${pageContext.request.contextPath}/AdminUserAddCheck?";
            var value = $(obj).val();
            url += $(obj).attr("name") + "=" + value;
            console.log(url);
            $.get(url, function (data) {
                if (data == "password-true") {

                } else if (data == "password-false") {
                    msgBox.addClass("err");
                    msgBox.html("只能包含字母数字, 长度在6-16位之间");
                }
            });
        }

        function CheckAndRemoverepassword(obj) {
            //console.log($(obj).attr("name"));
            $(obj).next('span').html('').removeClass('err');
        }

        function CheckAndShowrepassword(obj) {
            var msgBox = $(obj).next('span');
            console.log($(obj).val() == $("input[name='password']").val());
            if($(obj).val() != $("input[name='password']").val()) {
                msgBox.addClass("err");
                msgBox.html("两次密码不一致");
            }
        }

        function CheckAndRemovemobile(obj) {
            //console.log($(obj).attr("name"));
            $(obj).next('span').html('').removeClass('err');
        }

        function CheckAndShowmobile(obj) {
            var msgBox = $(obj).next('span');
            var url = "${pageContext.request.contextPath}/AdminUserAddCheck?";
            var value = $(obj).val();
            url += $(obj).attr("name") + "=" + value;
            console.log(url);
            $.get(url, function (data) {
                if (data == "mobile-true") {

                } else if (data == "mobile-false") {
                    msgBox.addClass("err");
                    msgBox.html("手机号不存在");
                }
            });
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
            <span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="${pageContext.request.contextPath}/douserselect.do">用户管理</a>
            <span class="crumb-step">&gt;</span>
            <span>新增用户</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/douseradd.do" method="post">
                <table class="insert-tab" width="60%">
                    <tbody>
                        <tr>
                            <th><i class="require-red">*</i>用户名：</th>
                            <td>
                                <input class="common-text required" name="userName" size="50" type="text" onfocus="CheckAndRemoveuserName(this)" onblur="CheckAndShowuserName(this)">
                                <span class="err"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>用户姓名：</th>
                            <td>
                                <input class="common-text required" name="name" size="50" type="text">
                                <span class="err"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>登陆密码：</th>
                            <td>
                                <input class="common-text required" name="password" size="50" type="text" onfocus="CheckAndRemovepassword(this)" onblur="CheckAndShowpassword(this)">
                                <span class="err"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>确认密码：</th>
                            <td>
                                <input class="common-text required" name="repassword" size="50" type="text" onfocus="CheckAndRemoverepassword(this)" onblur="CheckAndShowrepassword(this)">
                                <span class="err"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>性别：</th>
                            <td>
                                <input class="common-text required" name="sex" size="50" value="T" type="radio" checked>男
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input class="common-text required" name="sex" size="50" value="F" type="radio">女
                            </td>
                        </tr>
                        <tr>
                            <th>出生日期：</th>
                            <td>
                                <input class="common-text required" name="birthday" size="50">
                            </td>
                        </tr>
                        <tr>
                            <th>电子邮箱：</th>
                            <td>
                                <input class="common-text required" name="email" size="50">
                                <span class="error"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>手机号码：</th>
                            <td>
                                <input class="common-text required" name="mobile" size="50" onfocus="CheckAndRemovemobile(this)" onblur="CheckAndShowmobile(this)">
                                <span class="error"></span>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>送货地址：</th>
                            <td>
                                <input class="common-text required" name="address" size="50" onfocus="CheckAndRemove(this)" onblur="CheckAndShow(this)">
                                <span class="error"></span>
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
