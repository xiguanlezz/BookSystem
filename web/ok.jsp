<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/4
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"
            charset="utf-8"></script>
    <script type="text/javascript">
        jQuery(".bottom").slide({
            titCell: ".hd ul",
            mainCell: ".bd .likeList",
            autoPage: true,
            autoPlay: false,
            effect: "leftLoop",
            autoPlay: true,
            vis: 1
        });
    </script>
</head>
<body>
<%@include file="index_header.jsp" %>
<div class="order mt cart">
    <div class="site">
        <p class="wrapper clearfix">
            <span class="fl">支付成功</span>
            <img class="top" src="img/temp/cartTop03.png">
        </p>
    </div>
    <p class="ok">支付成功！剩余<span>5</span>秒<a href="${pageContext.request.contextPath}/ordersquerynum">返回订单页</a></p>
</div>
<div class="like"><h4>猜你喜欢</h4>
    <div class="bottom">
        <div class="hd">
            <span class="prev"><img src="img/temp/prev.png"></span>
            <span class="next"><img src="img/temp/next.png"></span>
        </div>
        <div class="imgCon wrapper bd">
            <div class="likeList clearfix">
                <div>
                    <c:forEach items="${requestScope.ps}" var="p">
                        <a href="${pageContext.request.contextPath}/selectproductview?id=${p.PRODUCT_ID}">
                            <dl>
                                <dt><img src="images/product/${p.PRODUCT_FILENAME}"></dt>
                                <dd>${p.PRODUCT_NAME}</dd>
                                <dd>￥${p.PRODUCT_PRICE}</dd>
                            </dl>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div><!--footer-->
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
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div>
</body>
</html>
