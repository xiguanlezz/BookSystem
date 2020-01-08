<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/3
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygrxx.css"/>
    <style>
        .err {
            display: inline-block;
            /*border: 1px solid red;*/
            background-color: #ffffff;
            font-size: 20px;
            height: 25px;
            margin-left: 20px;
            line-height: 25px;
            color: red;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

    <script>
        $(function () {
            $("input[name='validWay']:radio").change(function () {
                var value = $(this).val();
                if (value == 'p') {
                    location.href = "${pageContext.request.contextPath}/changebypwd.jsp";
                } else {
                    location.href = "${pageContext.request.contextPath}/changebyemail.jsp";
                }
            });

            $("#sendBtn").click(function () {

            });

        });
    </script>

    <script>

        function SendEmail(obj) {
            var email = $("input[name='email']").val();
            var url = "${pageContext.request.contextPath}/usersendemail?email=" + email;
            $.get(url, function (data) {

            });
            //$(obj).style.backgroundColor = "blue";
            alert("邮件已发送")
        }

        var flag_email = true;
        var flag_repwd = true;
        var flag_validcode = true;

        function FocusEmail(obj) {
            $(obj).next('span').html(" ").removeClass('err');
        }

        function CheckEmail(obj) {
            var email = $(obj).val();
            var url = "${pageContext.request.contextPath}/usercheck?email=" + email;
            var msgBox = $(obj).next('span');
            $.get(url, function (data) {
                if (data == 'emailFalse') {
                    msgBox.html("邮箱错误").addClass('err');
                    flag_email = false;
                } else {
                    flag_email = true;
                }
            });
        }

        function FocusValidCode(obj) {
            var msgBox = $(obj).next('span');
            msgBox.html("").removeClass('err');
        }

        function CheckValidCode(obj) {
            var code = $(obj).val();
            var url = "${pageContext.request.contextPath}/checkcode?validCode=" + code;
            var msgBox = $(obj).next('span');
            $.get(url, function (data) {
                if (data == 'false') {
                    msgBox.html("验证码有误").addClass('err');
                    flag_validcode = false;
                } else {
                    flag_validcode = true;
                }
            });
        }

        function CheckRePwd(obj) {
            var newPwd = $("input[name='newPwd']").val();
            var renewPwd = $("input[name='renewPwd']").val();
            var msgBox = $(obj).next('span');
            if (newPwd != renewPwd) {
                msgBox.html("两次密码不一致").addClass("err");
                flag_renewPwd = false;
            } else {
                flag_renewPwd = true;
            }
        }

        function FocusRePwd(obj) {
            $(obj).next('span').html(" ").removeClass('err');
        }

        function CheckThisForm(obj) {
            if (flag_email && flag_repwd && flag_validcode) {
                return true;
            } else {
                alert("修改内容不符合要求");
            }
        }
    </script>

</head>
<body><!------------------------------head------------------------------>
<%@include file="my_header.jsp" %>
<div class="you fl">
    <h2>修改密码</h2>
    <form action="${pageContext.request.contextPath}/userchangepwd" method="post" class="remima" onsubmit="CheckThisForm(this)">
        <p><span>选择验证身份方式：</span>
            <input type="radio" name="validWay" value="p"/>密码验证 <input type="radio" name="validWay" value="e" checked/>邮箱验证
        </p>
        <p>
            <span>邮箱：</span>
            <input type="text" name="email" onfocus="FocusEmail(this)" onblur="CheckEmail(this)"/> <span
                class="err"></span>
            <button type="button" style="background-color: firebrick" onclick="javascript:SendEmail(this)">发送验证码
            </button>
        </p>
        <p class="op">请输入邮箱</p>

        <p>
            <span>收到的验证码：</span>
            <input type="text" name="validCode" onfocus="FocusValidCode(this)" onblur="CheckValidCode(this)"/> <span
                class="err"></span>
        </p>

        <p>
            <span>新密码：</span>
            <input type="text" name="newPwd"/>
        </p>

        <p>
            <span>重复新密码：</span>
            <input type="text" name="renewPwd" onfocus="FocusRePwd(this)" onblur="CheckRePwd(this)"/> <span
                class="err"></span>
        </p>
        <p class="op">请再次输入密码</p>

        <input type="submit" value="提交"/>
    </form>
</div>
</div>
</div><!--返回顶部-->
<div class="gotop">
    <a href="${pageContext.request.contextPath}/showcart">
        <dl>
            <dt><img src="img/gt1.png"/></dt>
            <dd>去购<br/>物车</dd>
        </dl>
    </a>
    <a href="#" class="dh">
        <dl>
            <dt><img src="img/gt2.png"/></dt>
            <dd>联系<br/>客服</dd>
        </dl>
    </a>
    <a href="${pageContext.request.contextPath}/ordersquerynum">
        <dl>
            <dt><img src="img/gt3.png"/></dt>
            <dd>个人<br/>中心</dd>
        </dl>
    </a>
    <a href="#" class="toptop" style="display: none">
        <dl>
            <dt><img src="img/gt4.png"/></dt>
            <dd>返回<br/>顶部</dd>
        </dl>
    </a>
    <p>400-800-8200</p>
</div>
<div class="footer">
    <div class="top">
        <div class="wrapper">
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span class="fl">7天无理由退货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span class="fl">15天免费换货</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span class="fl">满599包邮</span>
            </div>
            <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span class="fl">手机特色服务</span>
            </div>
        </div>
    </div>
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
</body>
</html>

