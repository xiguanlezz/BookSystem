<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/29
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>

    <script>
        function toOrder() {
            var str = "";
            $("input[name='ck']:checked").each(function (index, item) {
                if ($("input[name='ck']:checked").length - 1 == index) {
                    str += $(this).val();
                } else {
                    str += $(this).val() + ",";
                }
            });
            location.href = "orderselect?eids=" + str;
            alert(str)
        }

        //单选删除和批量删除都写在cart.js文件中

    </script>
</head>
<body>
<%@include file="index_header.jsp" %>
<div class="cart mt">
    <!-----------------site------------------->
    <div class="site">
        <p class=" wrapper clearfix"><span class="fl">购物车</span>
            <img class="top" src="img/temp/cartTop01.png">
            <a href="${pageContext.request.contextPath}/indexselect" class="fr">继续购物&gt;</a>
        </p>
    </div>
    <!-----------------table------------------->
    <form action="${pageContext.request.contextPath}/cartshopdelete" method="post" id="mycartForm">
        <div class="table wrapper">
            <div class="tr">
                <div>商品</div>
                <div style="text-align: left;">单价</div>
                <div>数量</div>
                <div style="text-align: left;">小计</div>
                <div style="text-align: left;">操作</div>
            </div>
            <c:forEach items="${requestScope.carts}" var="cart">
                <c:if test="${cart.CART_VALID==1}">
                    <div class="th">
                        <div class="pro clearfix">
                            <label class="fl">
                                <input name="ck" type="checkbox" value="${cart.CART_ID}"><span></span>
                            </label>
                            <a class="fl" href="#">
                                <dl class="clearfix">
                                    <a href="${pageContext.request.contextPath}/selectproductview?id=${cart.CART_P_ID}&uid=${cart.CART_U_ID}">
                                        <dt class="fl">
                                            <img src="images/product/${cart.CART_P_FILENAME}"
                                                 style="height: 100px;width: 100px">
                                        </dt>
                                        <dd class="fl">
                                            <p>${cart.CART_P_NAME}</p>
                                            <p>精品好书</p>
                                            <p>便宜的正版图书</p>
                                        </dd>
                                    </a>
                                </dl>
                            </a>
                        </div>
                        <div class="price">￥${cart.CART_P_PRICE}</div>
                        <div class="number">
                            <p class="num clearfix">
                                <img class="fl sub" src="img/temp/sub.jpg">
                                <span class="fl" datasrc="${cart.CART_ID}">${cart.CART_QUANTITY}</span>
                                <img class="fl add" src="img/temp/add.jpg">
                            </p>
                        </div>
                        <div class="price sAll">￥${cart.CART_QUANTITY*cart.CART_P_PRICE}</div>
                        <div class="price">
                            <a class="del" href="#2" data-src="${cart.CART_ID}">删除</a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>

            <div class="goOn">空空如也~
                <a href="${pageContext.request.contextPath}/indexselect">去逛逛</a>
            </div>
            <div class="tr clearfix">
                <label class="fl">
                    <input class="checkAll" type="checkbox"/><span></span>
                </label>
                <p class="fl">
                    <a href="#">全选</a>

                    <a href="#" class="del">删除</a>
                </p>
                <p class="fr">
                    <span>共<small id="sl">0</small>件商品</span>
                    <span>合计:&nbsp;<small id="all">￥0.00</small></span>
                    <a href="javascript:toOrder()" class="count">结算</a>
                </p>
            </div>
        </div>
    </form>
</div>
<div class="mask"></div>
<div class="tipDel">
    <p>确定要删除该商品吗？</p>
    <p class="clearfix">
        <a class="fl cer" href="#">确定</a>
        <a class="fr cancel" href="#">取消</a>
    </p>
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
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p>
</div><!----------------mask------------------->
<div class="mask"></div><!-------------------mask内容------------------->
<div class="proDets">
    <img class="off" src="img/temp/off.jpg"/>
    <div class="proCon clearfix">
        <div class="proImg fr">
            <img class="list" src="img/temp/proDet.jpg"/>
            <div class="smallImg clearfix">
                <img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg">
                <img src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg">
                <img src="img/temp/proDet03.jpg" data-src="img/temp/proDet03_big.jpg">
                <img src="img/temp/proDet04.jpg" data-src="img/temp/proDet04_big.jpg">
            </div>
        </div>
        <div class="fl">
            <div class="proIntro change"><p>颜色分类</p>
                <div class="smallImg clearfix">
                    <p class="fl on">
                        <img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花" data-src="img/temp/proBig01.jpg">
                    </p>
                    <p class="fl">
                        <img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草" data-src="img/temp/proBig02.jpg">
                    </p>
                    <p class="fl">
                        <img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg">
                    </p>
                    <p class="fl">
                        <img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg">
                    </p>
                </div>
            </div>
            <div class="changeBtn clearfix">
                <a href="#2" class="fl">
                    <p class="buy">确认</p>
                </a>
                <a href="#2" class="fr">
                    <p class="cart">取消</p>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="pleaseC">
    <p>请选择宝贝</p>
    <img class="off" src="img/temp/off.jpg"/>
</div>
</div>
</body>
</html>
