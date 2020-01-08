<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/6
  Time: 17:43
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

    <script>
        var score=20;
       $(function () {
           $("#xin").each(function (a) {
               $("#xin").eq(a).children("a").click(function () {
                   var b = $(this).index();
                   var s=[20,40,60,80,100];
                   score=s[b];
                   //alert(b+"----"+score);
                   for (var c = 0; c < 5; c++) {
                       if (c <= b) {
                           $("#xin").eq(a).find("a").eq(c).find("img").attr("src", "img/hxin.png")
                       } else {
                           $("#xin").eq(a).find("a").eq(c).find("img").attr("src", "img/xin.png")
                       }
                   }
               })
           });

           $(".bc input[type='button']").click(function () {
               var words=$("textarea[name='words']").val();
               var url="${pageContext.request.contextPath}/evaluationadd?score="+score+"&evawords="+words+"&oid=${singleorder.ORDER_ID}";
               $.get(url,function (data) {
                   if(data=="evaAddFailure") {
                       alert("评论添加失败");
                   } else {
                        $(".flag").html('查看评价');
                   }
               });
           });
       })
    </script>

    <script>

    </script>
</head>
<body>
<%@include file="my_header.jsp"%>
        <div class="you fl">
            <div class="my clearfix"><h2 class="fl">商品评价</h2></div>
            <div class="dlist">
                <ul class="clearfix" id="pro">
                    <li class="on"><a href="#2">待评价商品</a></li>
                    <li><a href="#2">已评价商品</a></li>
                    <li><a href="#2">评价失效商品</a></li>
                </ul>
            </div>
            <div class="sx clearfix">
                <div class="clearfix">
                    <c:forEach items="${orderslist}" var="order">
                        <dl class="fl">
                            <c:forEach items="${requestScope.p_Indexmap}" var="pimp">
                                <c:if test="${order.ORDER_ID==pimp.value}">
                                <dt><a href="#"><img src="images/product/${pimp.key.PRODUCT_FILENAME}" style="height: 200px;width: 200px;"/></a></dt>
                                <dd><a href="#">${pimp.key.PRODUCT_NAME}</a></dd>
                                <dd>¥${pimp.key.PRODUCT_PRICE}
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${requestScope.i_Indexmap}" var="iimp">
                                <c:if test="${order.ORDER_ID==iimap.value}">
                                    ×${iimp.key.ITEM_P_QUANTITY}</dd>
                                </c:if>
                            </c:forEach>

                            <dd>${sessionScope.evaNum}人评价</dd>
                            <c:if test="${order.ORDER_STATUS=='3-1'}">
                                <dd><a href="#2"><span class="flag">评价</span></a></dd>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='3-2'}">
                                <dd><a href="#2"><span class="flag">查看评价</span></a></dd>
                            </c:if>
                            <c:if test="${order.ORDER_STATUS=='4'}">
                                <dd><a href="#2"><span class="flag">暂不能评价</span></a></dd>
                            </c:if>
                        </dl>
                    </c:forEach>

                    <dl class="fl">
                        <dt><a href="#"><img src="img/nav3.jpg"/></a></dt>
                        <dd><a href="#">家用创意壁挂 釉下彩复古</a></dd>
                        <dd>¥199.00</dd>
                        <dd>16000人评价</dd>
                        <dd><a href="#2">评价</a></dd>
                    </dl>
                </div>

                <div class="clearfix">
                    <dl class="fl">
                        <dt><a href="#"><img src="img/nav3.jpg"/></a></dt>
                        <dd><a href="#">家用创意壁挂 釉下彩复古</a></dd>
                        <dd>¥199.00</dd>
                        <dd>16000人评价</dd>
                        <dd><a href="#2">查看评价</a></dd>
                    </dl>
                </div>

                <div class="clearfix">
                    <dl class="fl">
                        <dt><a href="#"><img src="img/nav3.jpg"/></a></dt>
                        <dd><a href="#">家用创意壁挂 釉下彩复古</a></dd>
                        <dd>¥199.00</dd>
                        <dd>16000人评价</dd>
                        <dd><a href="#2">暂不能评价</a></dd>
                    </dl>
                </div>

            </div>
        </div>
    </div>
</div>
<!--<dl class="fl"><dt><a href="#"><img src="img/nav3.jpg"/></a></dt><dd><a href="#">家用创意壁挂  釉下彩复古</a></dd><dd>¥199.00</dd><dd>16000人评价</dd><dd><a href="#">待评价</a></dd></dl><dl class="fl"><dt><a href="#"><img src="img/nav3.jpg"/></a></dt><dd><a href="#">家用创意壁挂  釉下彩复古</a></dd><dd>¥199.00</dd><dd>16000人评价</dd><dd><a href="#">待评价</a></dd></dl>-->
<!--遮罩-->
<div class="mask"></div><!--评价弹框-->
<div class="pj">
    <div class="clearfix">
        <a href="#" class="fr gb"><img src="img/icon4.png"/></a>
    </div>
    <h3>商品评分</h3>
    <form action="#" method="get">
        <div class="clearfix">
            <p class="fl">请打分：</p>
            <p class="fl" id="xin">
                <a href="#2"><img src="img/xin.png"/></a>
                <a href="#2"><img src="img/xin.png"/></a>
                <a href="#2"><img src="img/xin.png"/></a>
                <a href="#2"><img src="img/xin.png"/></a>
                <a href="#2"><img src="img/xin.png"/></a>
            </p>
        </div>
        <textarea name="words" placeholder="请输入评价"></textarea>
        <div class="bc">
            <input type="button" value="保存"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div><!--查看评价-->
<div class="chak">
    <div class="clearfix">
        <a href="#" class="fr gb"><img src="img/icon4.png"/></a>
    </div>
    <h3>商品评分</h3>
    <form action="#" method="get">
        <div class="clearfix">
            <p class="fl">请打分：</p>
            <p class="fl">
                <c:if test="${evaluation.EVALUATION_SCORE==20}">
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                </c:if>
                <c:if test="${evaluation.EVALUATION_SCORE==40}">
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                </c:if>
                <c:if test="${evaluation.EVALUATION_SCORE==60}">
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                </c:if>
                <c:if test="${evaluation.EVALUATION_SCORE==80}">
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/xin.png"/></a>
                </c:if>
                <c:if test="${evaluation.EVALUATION_SCORE==100}">
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                    <a href="#2"><img src="img/hxin.png"/></a>
                </c:if>
            </p>
        </div>
        <textarea name="" rows="" cols="" placeholder="请输入评价">${evaluation.EVALUATION_WORDS}</textarea>
<%--        <div class="bc">--%>
<%--            <input type="button" value="保存"/>--%>
<%--            <input type="button" value="取消"/>--%>
<%--        </div>--%>
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