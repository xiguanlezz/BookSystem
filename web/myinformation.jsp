<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/2
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygrxx.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

    <script>
        $(function () {
            $(".bc input[type='button']").click(function () {
                var name = $(".name").val();
                var birthday = $(".birthday").val();
                var sex = $("input:radio:checked").val();
                var email = $(".email").val();
                //alert(name+"-"+birthday+"-"+sex);
                var url = "${pageContext.request.contextPath}/userchange?name=" + name + "&bir=" + birthday + "&sex=" + sex + "&email=" + email;
                $.get(url, function (data) {
                    if (data == "success") {
                        alert("修改成功");
                        location.href = "${pageContext.request.contextPath}/userinformation";
                    } else if (data == "failure") {
                        alert("修改失败");
                        location.href = "${pageContext.request.contextPath}/myinformation.jsp";
                    }
                })
            });
        })
    </script>
</head>
<body><!------------------------------head------------------------------>
<%@include file="my_header.jsp" %>
<div class="you fl">
    <h2>个人信息</h2>
    <div class="gxin">
        <div class="tx">
            <a href="#"><img src="img/tx.png"/>
                <p id="avatar">修改头像</p></a>
        </div>
        <div class="xx">
            <h3 class="clearfix">
                <strong class="fl">基础资料</strong>
                <a href="#" class="fr" id="edit1">编辑</a>
            </h3>
            <div>姓名：${user.USER_NAME}</div>
            <div>生日：${user.USER_BIRTHDAY}</div>
            <c:if test="${user.USER_SEX=='T'}">
                <div>性别：男</div>
            </c:if>
            <c:if test="${user.USER_SEX=='F'}">
                <div>性别：女</div>
            </c:if>
            <div>邮箱：${user.USER_EMAIL}</div>
            <h3>高级设置</h3><!--<div><span class="fl">银行卡</span><a href="#" class="fr">管理</a></div>-->
            <div>
                <span class="fl">账号地区：中国</span>
                <%--                        <a href="#" class="fr" id="edit2">修改</a>--%>
            </div>
        </div>
    </div>
</div>
</div>
</div><!--遮罩-->
<div class="mask"></div><!--编辑弹框-->
<div class="bj">
    <div class="clearfix">
        <a href="#" class="fr gb">
            <img src="img/icon4.png"/>
        </a>
    </div>
    <h3>编辑基础资料</h3>
    <form action="#" method="get">
        <p>
            <label>姓名：</label>
            <input type="text" value="${user.USER_NAME}" class="name"/>
        </p>
        <p>
            <label>生日：</label>
            <input type="text" value="${user.USER_BIRTHDAY}" class="birthday"/>
        </p>
        <p>
            <label>性别：</label>
            <c:if test="${user.USER_SEX=='T'}">
                <span><input type="radio" checked value="T" name="gender"/>男</span>
                <span><input type="radio" class="sex" value="F" name="gender"/>女</span>
            </c:if>
            <c:if test="${user.USER_SEX=='F'}">
                <span><input type="radio" class="sex" value="T" name="gender"/>男</span>
                <span><input type="radio" checked value="F" name="gender"/>女</span>
            </c:if>
        </p>
        <p>
            <label>邮箱：</label>
            <input type="text" value="${user.USER_EMAIL}" class="email"/>
        </p>
        <div class="bc">
            <input type="button" value="保存"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div><!--高级设置修改-->
<div class="xg">
    <div class="clearfix">
        <a href="#" class="fr gb">
            <img src="img/icon4.png"/>
        </a>
    </div>
    <h3>切换账号地区</h3>
    <form action="#" method="get">
        <p>
            <label>姓名：</label>
            <input type="text" value="六六六"/>
        </p>
        <div class="bc">
            <input type="button" value="保存"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div><!--修改头像-->
<div class="avatar">
    <div class="clearfix">
        <a href="#" class="fr gb">
            <img src="img/icon4.png"/>
        </a>
    </div>
    <h3>修改头像</h3>
    <form action="#" method="get">
        <h4>请上传图片</h4>
        <input type="button" value="上传头像"/>
        <p>jpg或png，大小不超过2M</p>
        <input type="submit" value="提交"/>
    </form>
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
