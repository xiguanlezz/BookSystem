<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/3
  Time: 14:54
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
    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>

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
        });
    </script>
    <script>
        function ChangeCode(img) {
            //防止浏览器缓存, 加上时间参数改变请求地址
            img.src = "${pageContext.request.contextPath}/generatecode?time=" + new Date().getTime();
        }

        function FocusChangeItem(obj) {
            $(obj).next('span').html('').removeClass("err");
            $(obj).next().next('span').html('').removeClass("err");
        }

        var flag_oldPwd = true;
        var flag_renewPwd = true;
        var flag_code = true;

        function CheckChangeItem(obj) {
            var msgBox = $(obj).next("span");
            var name = obj.name;
            switch (name) {
                case 'oldPwd':
                    var url = "${pageContext.request.contextPath}/usercheck?oldPwd=" + $(obj).val();
                    $.get(url, function (data) {
                            if (data == "oldPwdFalse") {
                                msgBox.html("密码输入错误").addClass('err');
                                flag_oldPwd = false;
                            } else {
                                flag_oldPwd = true;
                            }
                        }
                    );
                    break;
                case 'renewPwd':
                    var newPwd = $("#newPwd").val();
                    var renewPwd = $(obj).val();
                    if (newPwd != renewPwd) {
                        msgBox.html("两次密码不一致").addClass('err');
                        flag_renewPwd = false;
                    } else {
                        flag_renewPwd = true;
                    }
                    break;
                case 'code':
                    var url = "${pageContext.request.contextPath}/checkcode?num=" + $(obj).val();
                    $.get(url, function (data) {
                            if (data == "false") {
                                msgBox = $(obj).next().next("span");
                                msgBox.html("验证码输入有误").addClass('err');
                                flag_code = false;
                            } else {
                                flag_code = true;
                            }
                        }
                    );
                    break;
            }
        }

        function CheckEveryItem(obj) {
            if (flag_oldPwd && flag_renewPwd && flag_code) {
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
    <form action="${pageContext.request.contextPath}/userchangepwd" method="post" class="remima" onsubmit="CheckEveryItem(this)">
        <p><span>选择验证身份方式：</span>
            <input type="radio" name="validWay" value="p" checked/>密码验证 <input type="radio" name="validWay" value="e"/>邮箱验证
        </p>

        <p>
            <span>原密码：</span>
            <input type="text" name="oldPwd" onfocus="FocusChangeItem(this)" onblur="CheckChangeItem(this)"/> <span
                class="err"></span>
        </p>
        <p class="op">输入原密码</p>

        <p>
            <span>新密码：</span>
            <input type="text" id="newPwd" onfocus="FocusChangeItem(this)" onblur="CheckChangeItem(this)"/>
        </p>

        <p>
            <span>重复新密码：</span>
            <input type="text" name="renewPwd" onfocus="FocusChangeItem(this)" onblur="CheckChangeItem(this)"/> <span
                class="err"></span>
        </p>
        <p class="op">请再次输入密码</p>
        <p>

            <span>验证码：</span>
            <input type="text" name="code" onfocus="FocusChangeItem(this)" onblur="CheckChangeItem(this)"/>
            <img src="${pageContext.request.contextPath}/generatecode" onclick="ChangeCode(this)" alt="..."
                 style="height: 40px;width: 100px"/>
            <span class="err"></span>
        </p>
        <p class="op">按右图输入验证码，不区分大小写</p>
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
