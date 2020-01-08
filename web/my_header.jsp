<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/2
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="head ding">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl">
                <a href="index.html">
                    <img src="img/logo.png"/>
                </a>
            </h1>
        </div>
    </div>
</div>
<!------------------------------idea------------------------------>
<div class="address mt" id="add">
    <div class="wrapper clearfix">
        <a href="${pageContext.request.contextPath}/indexselect" class="fl">首页</a><span>/</span>
        <a href="#" class="on">个人中心</a>
    </div>
</div>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3>
                <a href="#"><img src="img/tx.png"/></a>
                <p class="clearfix">
                    <span class="fl">${user.USER_NAME}</span>
                    <span class="fr"><a href="${pageContext.request.contextPath}/logout">[退出登录]</a></span>
                </p>
            </h3>
            <div>
                <h4>我的交易</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/showcart">我的购物车</a></li>
                    <li><a href="${pageContext.request.contextPath}/orderquery">我的订单</a></li>
                    <li><a href="${pageContext.request.contextPath}/evaluationselect">评价晒单</a></li>
                </ul>
                <h4>个人中心</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/ordersquerynum">我的中心</a></li>
                    <li class="on"><a href="${pageContext.request.contextPath}/addressselect">地址管理</a></li>
                </ul>
                <h4>账户管理</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/myinformation.jsp">个人信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/changebypwd.jsp">修改密码</a></li>
                </ul>
            </div>
        </div>
