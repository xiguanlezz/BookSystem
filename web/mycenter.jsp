<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/27
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</head>
<body><!------------------------------head------------------------------>
<%@include file="my_header.jsp"%>
        <div class="you fl">
            <div class="tx clearfix">
                <div class="fl clearfix">
                    <a href="#" class="fl">
                        <img src="img/tx.png"/>
                    </a>
                    <p class="fl">
                        <span>${user.USER_NAME}</span>
                        <a href="${pageContext.request.contextPath}/myinformation.jsp">修改个人信息></a>
                    </p>
                </div>
                <div class="fr">绑定邮箱：${user.USER_EMAIL}</div>
            </div>
            <div class="bott">
                <div class="clearfix">
                    <a href="#" class="fl"><img src="img/gxin1.jpg"/></a>
                    <p class="fl">
                        <span>待支付的订单：
                            <strong>
                                <c:if test="${empty waitPayNumber}">
                                    0
                                </c:if>
                                <c:if test="${not empty waitPayNumber}">
                                    ${waitPayNumber}
                                </c:if>
                            </strong>
                        </span>
                        <a href="${pageContext.request.contextPath}/myorder.jsp">查看待支付订单></a>
                    </p>
                </div>
                <div class="clearfix">
                    <a href="#" class="fl"><img src="img/gxin2.jpg"/></a>
                    <p class="fl">
                        <span>待收货的订单：
                            <strong>
                                <c:if test="${empty waitCheckNumber}">
                                    0
                                </c:if>
                                <c:if test="${not empty waitCheckNumber}">
                                    ${waitCheckNumber}
                                </c:if>
                            </strong>
                        </span>
                        <a href="${pageContext.request.contextPath}/myorder.jsp">查看待收货订单></a>
                    </p>
                </div>
                <div class="clearfix">
                    <a href="#" class="fl"><img src="img/gxin3.jpg"/></a>
                    <p class="fl">
                        <span>待评价的订单：
                            <strong>
                                <c:if test="${empty waitEvaluateNumber}">
                                    0
                                </c:if>
                                <c:if test="${not empty waitEvaluateNumber}">
                                    ${waitEvaluateNumber}
                                </c:if>
                            </strong>
                        </span>
                        <a href="${pageContext.request.contextPath}/myorder.jsp">查看待评价订单></a>
                    </p>
                </div>
                <div class="clearfix">
                    <a href="#" class="fl"><img src="img/gxin4.jpg"/></a>
                    <p class="fl">
                        <span>喜欢的商品：<strong>0</strong></span>
                        <a href="#">查看喜欢的商品></a>
                    </p>
                </div>
            </div>
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
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试
    </p>
</div>
</body>
</html>
