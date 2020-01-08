<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/26
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
    <style>
        .reg p .error {
            display: inline-block;
            /*border: 1px solid red;*/
            background-color: #ffe8e0;
            font-size: 20px;
            height: 25px;
            margin-left: 20px;
            line-height: 25px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/calendar.js"></script>

    <script>
        //每种要校验的都加上标志位, 其实只有用户名和验证码用了Ajax
        var flag_id = true;
        var flag_name = true;
        var flag_pwd = true;
        var flag_repwd = true;
        var flag_mobile = true;
        var flag_address = true;
        var flag_code = true;

        function FocusItem(obj) {
            if (obj.name == 'veryCode') {
                $(obj).next().next('span').html('').removeClass('error');
            } else {
                $(obj).next('span').html('').removeClass('error');
            }
        }

        function CheckItem(obj) {
            var msgBox = $(obj).next('span');
            switch ($(obj).attr('name')) {
                case 'userName':
                    if ($(obj).val() == "") {
                        msgBox.html('用户名不能为空');
                        msgBox.addClass('error');
                        flag_id = false;
                    } else {
                        //Ajax
                        //针对中文需要解码
                        var url = "${pageContext.request.contextPath}/usercheck?id=" + encodeURI($(obj).val()) + "&time=" + new Date().getTime();
                        $.get(url, function (data) {
                            if (data == "false") {
                                msgBox.html("用户名不能使用");
                                msgBox.addClass('error');
                                flag_id = false;
                            } else {
                                msgBox.removeClass('error');
                                flag_id = true;
                            }
                        });
                    }
                    break;

                case 'name':
                    if (obj.value == "") {
                        msgBox.html('用户姓名不能为空');
                        msgBox.addClass('error');
                        flag_name = false;
                    } else {
                        flag_name = true;
                    }
                    break;

                case 'password':
                    if ($(obj).val() == "") {
                        msgBox.html('密码不能为空');
                        msgBox.addClass('error');
                        flag_pwd = false;
                    } else {
                        flag_pwd = true;
                    }
                    break;

                case 'repassword':
                    if (obj.value == "") {
                        msgBox.html('确认密码不能为空');
                        msgBox.addClass('error');
                        flag_repwd = false;
                    } else if ($(obj).val() != $("input[name='password']").val()) {
                        msgBox.html('两次密码不一致');
                        msgBox.addClass('error');
                        flag_repwd = false;
                    } else {
                        flag_repwd = true;
                    }
                    break;

                case 'mobile':
                    if ($(obj).val() == "") {
                        msgBox.html('手机不能为空');
                        msgBox.addClass('error');
                        flag_mobile = false;
                    } else {
                        flag_mobile = true;
                    }
                    break;

                case 'address':
                    if (obj.value == "") {
                        msgBox.html('送货地址不能为空');
                        msgBox.addClass('error');
                        flag_address = false;
                    } else {
                        flag_address = true;
                    }
                    break;

                case 'veryCode':
                    var codeBox = $(obj).next().next('span');
                    if ($(obj).val() == '') {
                        codeBox.html('验证码不能为空');
                        codeBox.addClass('error');
                        flag_code = false;
                    } else {
                        var url = "${pageContext.request.contextPath}/checkcode?num=" + encodeURI($(obj).val()) + "&time=" + new Date().getTime();

                        $.get(url, function (data) {
                            if (data == "true") {
                                codeBox.removeClass('error');
                                flag_code = true;
                            } else {
                                codeBox.html('验证码输入有误, 请重试');
                                codeBox.addClass('error');
                                flag_code = false;
                            }
                        });
                        //$.ajaxSettings.async=false;
                    }
                    break;
            }
        }


        function ChangeCode(img) {
            //防止浏览器缓存, 加上时间参数改变请求地址
            img.src = "${pageContext.request.contextPath}/generatecode?time=" + new Date().getTime();
        }

        function checkForm(formObj) {
            var els = formObj.getElementsByTagName('input');
            for (var i = 0; i < els.length; i++) {
                if (els[i] != null) {
                    if (els[i].getAttribute("onblur") != null) {    //用了Ajax的才要检验
                        CheckItem(els[i]);
                    }
                }
            }
            return (flag_id && flag_name && flag_pwd && flag_repwd && flag_mobile && flag_address && flag_code);
        }
    </script>
</head>
<body>
<!-------------------reg-------------------------->
<div class="reg">
<%--    form表单返回true表示可以提交, false不可以提交--%>
    <form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm(this)">
        <h1><a href="index.html"><img src="img/temp/logo.png"></a></h1>
        <p>
            <h1 style="padding: 0px;margin: 0px;font-size: 40px;background-color: dodgerblue;text-align: center;line-height: 70px">用户注册</h1>
        </p>
        <p><input type="text" name="userName" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"> <span class="error"></span> </p>
        <p><input type="text" name="name" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户姓名"> <span class="error"></span> </p>
        <p><input type="text" name="password" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"> <span class="error"></span> </p>
        <p><input type="text" name="repassword" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"> <span class="error"></span> </p>
        <p><input type="text" name="email" placeholder="请输入邮箱"></p>
        <p>
            <input style="width: 15px;height :15px" type="radio" name="sex" value="T"checked>男
            <input style="width: 15px;height :15px" type="radio" name="sex" value="F">女
        </p>
        <p>
            <input type="text" name="birthday" value="" onfocus="c.show(this)" placeholder="请输入出生日期">
        </p>
        <p><input type="text" name="mobile" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入手机"> <span class="error"></span> </p>
        <p><input type="text" name="address" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入送货地址"> <span class="error"></span> </p>
        <p>
            <input class="code" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码">
            <img src="${pageContext.request.contextPath}/generatecode" onclick="ChangeCode(this)" alt="看不清, 换一张">
            <span class="error"></span>
        </p>
        <p><input type="submit" name="" value="注册"></p>
        <p class="txtL txt" style="">完成此注册，即表明您同意了我们的
            <a href="#">使用条款和隐私策略</a>
        </p>
        <p class="txt"><a href="#"><span></span>已有账号登录</a></p>
    </form>
</div>
</body>
</html>
