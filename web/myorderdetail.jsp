<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/5
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myorder.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%@include file="my_header.jsp"%>
        <div class="you fl">
            <div class="my clearfix">
                <h2>订单详情<a href="#">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></h2>
                <h3>订单号：<span>${requestScope.pay.PAY_ID}</span></h3>
            </div>
            <div class="orderList">
                <div class="orderList1"><h3>已收货</h3>
                    <c:forEach items="${requestScope.I_Pmap}" var="ipmap">
                        <div class="clearfix">
                            <a href="#" class="fl"><img src="images/product/${ipmap.value.PRODUCT_FILENAME}" style="height: 100px;width: 100px;"/></a>
                            <p class="fl">
                                <a href="#">${ipmap.value.PRODUCT_NAME}</a>
                                <a href="#">¥${ipmap.value.PRODUCT_PRICE}×${ipmap.key.ITEM_P_QUANTITY}</a>
                            </p>
                        </div>
                        <div style="height: 20px"></div>
                    </c:forEach>
                </div>
                <div class="orderList1">
                    <h3>收货信息</h3>
                    <p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${requestScope.address.ADDRESS_U_NAME}</span></p>
                    <p>联系电话：<span>${requestScope.address.ADDRESS_U_MOBILE}</span></p>
                    <p>收货地址：<span>${requestScope.address.ADDRESS_DETAILED_LOCATION}</span></p>
                </div>
                <div class="orderList1">
                    <h3>支付方式及送货时间</h3>
                    <p>支付方式：<span>${requestScope.pay.PAY_WAY}</span></p>
                    <p>送货时间：<span>${requestScope.pay.PAY_SEND_START_TIME}</span></p>
                </div>
                <div class="orderList1 hei">
                    <h3><strong>商品总价：</strong><span>¥${order.ORDER_TOTAL_PRICE}</span></h3>
                    <p><strong>运费：</strong><span>¥0</span></p>
                    <p><strong>订单金额：</strong><span>¥${order.ORDER_TOTAL_PRICE}</span></p>
                    <p><strong>实付金额：</strong><span>¥${order.ORDER_TOTAL_PRICE}</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
<<!--返回顶部-->
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
