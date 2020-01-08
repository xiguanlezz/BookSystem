<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/5
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myorder.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

    <script>
        function NowPay(msg, oid) {
            if (confirm(msg)) {
                location.href = "${pageContext.request.contextPath}/orderupdate?orderid=" + oid + "&flag=pay";
            }
        }

        function NowCheck(msg, oid) {
            if (confirm(msg)) {
                location.href = "${pageContext.request.contextPath}/orderupdate?orderid=" + oid + "&flag=check";
            }
        }
    </script>
</head>
<body>
<%@include file="my_header.jsp" %>
<div class="you fl">
    <div class="my clearfix"><h2 class="fl">我的订单</h2><a href="#" class="fl">请谨防钓鱼链接或诈骗电话，了解更多&gt;</a></div>
    <div class="dlist clearfix">
        <ul class="fl clearfix" id="wa">
            <li class="on"><a href="#2">全部有效订单</a></li>
            <li><a href="#2">待支付</a></li>
            <li><a href="#2l">待收货</a></li>
            <li><a href="#2">已关闭</a></li>
        </ul>
        <form action="#" method="get" class="fr clearfix">
            <input type="text" name="" value="" placeholder="请输入商品名称、订单号"/>
            <input type="button" name="" value=""/>
        </form>
    </div>
    <div class="dkuang deng">
        <p class="one">待支付</p>
        <div class="word clearfix">
            <ul class="fl clearfix">
                <li>2016年12月1日 18:53</li>
                <li>杨小黄</li>
                <li>订单号:5160513358520018</li>
                <li>在线支付</li>
            </ul>
            <p class="fr">订单金额：<span>99.00</span>元</p>
        </div>
        <div class="shohou clearfix">
            <a href="#" class="fl"><img src="img/g1.jpg"/></a>
            <p class="fl">
                <a href="#">家用创意菜盘子圆盘 釉下彩复古</a>
                <a href="#">¥99.00×1</a>
            </p>
            <p class="fr">
                <a href="#">立即支付</a>
                <a href="orderxq.html">订单详情</a>
            </p>
        </div>
    </div>


    <c:forEach items="${requestScope.O_Pmap}" var="opmap">
<%--        待支付--%>
    <c:if test="${opmap.key.ORDER_STATUS=='1'}">
    <div class="dkuang deng">
        <p class="one">待支付</p>
    </c:if>

    <c:if test="${opmap.key.ORDER_STATUS=='2'}">
    <div class="dkuang clearfix deng">
        <p class="one fl">待收货</p>
        <div class="clearfix">
           <div class="fl vewwl">
              <a href="#" class="ckwl">查看物流</a>
               <div class="wuliu">
                  <h4>${opmap.value.PAY_SEND_WAY}：858888888888888</h4>
                  <ul>
                     <li>
                        <p>妥投投递并签收，已签收。签收人：本人签收</p>
                        <p>2016-12-03 17:30:00</p>
                     </li>
                     <li>
                        <p>深圳市南油速递营销部安排投递（投递员姓名：六六六。联系电话：14243452522;</p>
                        <p>2016-12-03 08:50:00</p>
                     </li>
                     <li>
                        <p>到达广东省邮政速递物流有限公司深圳航空邮件处理中心处理中心（经转）</p>
                        <p>2016-12-03 02:20:00</p>
                     </li>
                     <li>
                        以上为最新跟踪信息<a href="wuliu.html">查看全部</a>
                     </li>
                  </ul>
                </div>
           </div>
        </div>
    </c:if>

    <c:if test="${opmap.key.ORDER_STATUS=='3-1'}">
        <div class="dkuang">
        <p class="one">已收货</p>
    </c:if>

    <c:if test="${opmap.key.ORDER_STATUS=='3-2'}">
        <div class="dkuang">
        <p class="one">已收货</p>
    </c:if>

    <c:if test="${opmap.key.ORDER_STATUS=='4'}">
        <div class="dkuang">
        <p class="one">已关闭</p>
    </c:if>

        <div class="word clearfix">
            <ul class="fl clearfix">
                <li>${opmap.key.ORDER_TIME}</li>
                <li>${user.USER_NAME}</li>
                <li>订单号:${opmap.key.ORDER_ID}</li>
                <li>${opmap.value.PAY_WAY}</li>
            </ul>
            <p class="fr">订单金额：<span>${opmap.key.ORDER_TOTAL_PRICE}</span>元</p>
        </div>

        <c:forEach items="${requestScope.P_Indexmap}" var="pimap" varStatus="ss">
            <c:if test="${pimap.value==opmap.key.ORDER_ID}">
        <div class="shohou clearfix">
            <a href="#" class="fl"><img src="images/product/${pimap.key.PRODUCT_FILENAME}" style="width: 80px;height: 80px;"/></a>
            <p class="fl">
                <a href="#">${pimap.key.PRODUCT_NAME}</a>
                <a href="#">¥${pimap.key.PRODUCT_PRICE}×
                        <c:forEach items="${requestScope.I_Indexmap}" var="iimap">
                            <c:if test="${pimap.key.PRODUCT_ID==iimap.key.ITEM_P_ID&&pimap.value==iimap.value}">
                                ${iimap.key.ITEM_P_QUANTITY}
                            </c:if>
                        </c:forEach>
                </a>
             </p>

             <p class="fr">
              <c:if test="${opmap.key.ORDER_STATUS=='1'}">
                <a href="javascript:NowPay('你确定要支付此订单吗?',${opmap.key.ORDER_ID})">立即支付</a>
              </c:if>
              <c:if test="${opmap.key.ORDER_STATUS=='2'}">
                <a href="javascript:NowCheck('你是否要确认收货' ,${opmap.key.ORDER_ID})">确认收货</a>
              </c:if>
              <c:if test="${opmap.key.ORDER_STATUS=='3-1'}">
                <a href="${pageContext.request.contextPath}/evaluationselect">待评价</a>
              </c:if>
              <c:if test="${opmap.key.ORDER_STATUS=='3-2'}">
                <a href="${pageContext.request.contextPath}/evaluationselect">已评价</a>
              </c:if>
              <c:if test="${opmap.key.ORDER_STATUS=='4'}">
                <a href="#">交易失败</a>
              </c:if>

              <c:forEach items="${requestScope.Index_Amap}" var="amap">
                <c:if test="${amap.key==opmap.key.ORDER_ID}">
                    <a href="${pageContext.request.contextPath}/orderdetailselect?addressid=${amap.value.ADDRESS_ID}&payid=${opmap.value.PAY_ID}&orderid=${opmap.key.ORDER_ID}">订单详情</a>
                </c:if>
              </c:forEach>
             </p>
        </div>
            </c:if>
        </c:forEach>

    </div>
    </c:forEach>
        </div>
        </div>
    </div>


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
                    <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span
                            class="fl">7天无理由退货</span>
                    </div>
                    <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span
                            class="fl">15天免费换货</span>
                    </div>
                    <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span
                            class="fl">满599包邮</span>
                    </div>
                    <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span
                            class="fl">手机特色服务</span>
                    </div>
                </div>
            </div>
            <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
                违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
</body>
</html>
