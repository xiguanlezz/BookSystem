<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/29
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

    <script>
        function ChangeAddress(msg) {
            if (confirm(msg)) {
                var name = $(".readd #name").val();
                var mobile = $(".readd #mobile").val();
                var province = $(".readd #province").val();
                var city = $(".readd #city").val();
                var address = $(".readd #address").val();
                var zipcode = $(".readd #zipcode").val();
                var temp = "${pageContext.request.contextPath}/addressupdate?name=" + name + "&mobile=" + mobile;
                var url = temp + "&p=" + province + "&city=" + city + "&a=" + address + "&z=" + zipcode;
                $.get(url, function (data) {
                    if (data == "success") {
                        alert("修改成功");

                        //Ajax动态刷新局部页面
                        $(".showName").html(name);
                        $(".showAddress").html(address);
                        $(".showMobile").html(mobile);
                    } else if (data == "failure") {
                        alert("修改失败");
                    }
                });
            }
        }

        function DeleteAddress(msg) {
            var ds = [];
            <c:forEach items="${eids}" var="e">
            ds.push(${e});
            </c:forEach>
            //alert(ds)
            if (confirm(msg)) {
                var aid =${defaultAddress.ADDRESS_ID};
                location.href = "${pageContext.request.contextPath}/addressdelete?id=" + aid + "&eids=" + ds;
            }
        }

        function SaveAddress(formId) {
            var form = document.getElementById(formId);
            form.submit();
        }

        function PayAndShow(msg) {
            var des = [];
            <c:forEach items="${shoplist}" var="s">
            if (des.indexOf(${s.CART_P_ID}) == -1) {
                des.push(${s.CART_P_ID});
            }
            </c:forEach>

            alert(des)

            var send_way = $("span[class='on']").html();
            var pay_way = $("img[class='on']").next('input').val();
            var totalPrice = $("#totalPrice").html();
            alert(send_way + "-" + pay_way);
            var url = "${pageContext.request.contextPath}/orderadd?pids=" + des + "&aid="+${defaultAddress.ADDRESS_ID}+
            "&totalPrice=" + totalPrice + "&sendway=" + send_way + "&payway=" + pay_way;
            if (confirm(msg)) {

                location.href = url + "&pay_status=true";
            } else {
                location.href = url + "&pay_status=false";
            }
        }

    </script>
</head>
<body>
<!----------------------------------------order------------------>
<%@include file="index_header.jsp" %>
<div class="order cart mt">
    <!-----------------site------------------->
    <div class="site">
        <p class="wrapper clearfix">
            <span class="fl">订单确认</span>
            <img class="top" src="img/temp/cartTop02.png">
        </p>
    </div><!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <form action="${pageContext.request.contextPath}/orderadd" method="post" onsubmit="">

            <div class="orderL fl"><!--------h3---------------->
                <h3>收件信息<a href="#" class="fr" id="addxad">新增地址</a></h3>
                <!--------addres---------------->
                <div class="addres clearfix">
                    <div class="addre fl on">
                        <div class="tit clearfix">
                            <p class="fl" class="showName">${defaultAddress.ADDRESS_U_NAME}
                                <span class="default">[默认地址]</span>
                            </p>
                            <p class="fr">
                                <a href="#" onclick="javascript:DeleteAddress('你确定要删除这个地址吗?')">删除</a>
                                <span>|</span>
                                <a href="#" class="edit">编辑</a>
                            </p>
                        </div>
                        <div class="addCon">
                            <p class="showAddress">${defaultAddress.ADDRESS_DETAILED_LOCATION}</p>
                            <p class="showMobile">${defaultAddress.ADDRESS_U_MOBILE}</p>
                        </div>
                    </div>

                    <c:forEach items="${addresses}" var="address">
                        <div class="addre fl">
                            <div class="tit clearfix">
                                <p class="fl" class="showName">${address.ADDRESS_U_NAME}</p>
                                <p class="fr">
                                    <a href="#" class="setDefault">设为默认</a>
                                    <span>|</span>
                                    <a href="#" onclick="javascript:DeleteAddress('你确定要删除这个地址吗?')">删除</a>
                                    <span>|</span>
                                    <a href="#" class="edit">编辑</a>
                                </p>
                            </div>
                            <div class="addCon">
                                <p class="showAddress">${address.ADDRESS_DETAILED_LOCATION}</p>
                                <p class="showMobile">${address.ADDRESS_U_MOBILE}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <h3>支付方式</h3><!--------way---------------->
                <div class="way clearfix">
                    <img class="on" src="img/temp/way01.jpg"> <input type="hidden" value="支付宝支付">
                    <img src="img/temp/way02.jpg"> <input type="hidden" value="微信支付">
                    <img src="img/temp/way03.jpg"> <input type="hidden" value="银联支付">
                    <img src="img/temp/way04.jpg"> <input type="hidden" value="货到付款">
                </div>
                <h3>选择快递</h3><!--------dis---------------->
                <div class="dis clearfix" id="remark">
                    <span class="on">顺风快递</span>
                    <span>百世汇通</span>
                    <span>圆通快递</span>
                    <span>中通快递</span>
                </div>
            </div>
            <div class="orderR fr">
                <div class="msg">
                    <h3>订单内容<a href="showcart" class="fr">返回购物车</a></h3><!--------ul---------------->
                    <c:forEach items="${shoplist}" var="singlelist">
                        <ul class="clearfix">
                            <li class="fl">
                                <img src="images/product/${singlelist.CART_P_FILENAME}" height="100px" width="100px">
                            </li>
                            <li class="fl">
                                <p>${singlelist.CART_P_NAME}</p>
                                <p>精品好书 便宜的正版图书</p>
                                <p>数量：${singlelist.CART_QUANTITY}</p></li>
                            <li class="fr">￥${singlelist.CART_QUANTITY*singlelist.CART_P_PRICE}</li>
                        </ul>
                    </c:forEach>

                </div><!--------tips---------------->
                <div class="tips">
                    <p>
                        <span class="fl">商品金额：</span><span class="fr">￥${requestScope.totalPrice}</span>
                    </p>
                    <p>
                        <span class="fl">优惠金额：</span>
                        <span class="fr">￥0.00</span>
                    </p>
                    <p>
                        <span class="fl">运费：</span>
                        <span class="fr">免运费</span>
                    </p>
                </div><!--------tips count---------------->
                <div class="count tips">
                    <p>
                        <span class="fl">合计：</span>
                        <span class="fr" id="totalPrice">￥${requestScope.totalPrice}</span>
                    </p>
                </div>
                <%--                <input type="submit" class="pay" value="去支付">--%>
                <a href="#" onclick="javascript:PayAndShow('你确定要支付吗?')" class="pay">去支付</a>
            </div>
        </form>
    </div>
</div>

<div class="mask"></div>
<div class="adddz">
    <form action="addressadd" method="post" id="addForm">
        <input type="hidden" name="eids" value="${eids}">
        <input type="text" placeholder="姓名" class="on" name="name"/>
        <input type="text" placeholder="手机号" name="mobile"/>
        <div class="city">
            <select name="province">
                <option value="省份/自治区" selected disabled>省份/自治区</option>
                <option value="浙江省">浙江省</option>
                <option value="江苏省">江苏省</option>
                <option value="上海">上海</option>
                <option value="安徽省">安徽省</option>
            </select>
            <select name="city">
                <option value="城市/地区">城市/地区</option>
                <%--                <option value="浙江省"></option>--%>
                <%--                <option value="江苏省"></option>--%>
                <%--                <option value="上海"></option>--%>
                <%--                <option value="安徽省"></option>--%>
            </select>
        </div>
        <textarea name="detailedAddress" rows="" cols="" placeholder="详细地址"></textarea>
        <input type="text" placeholder="邮政编码" name="zipCode"/>
        <div class="bc">
            <input type="button" value="保存" onclick="javascript:SaveAddress('addForm')"/>
            <input type="button" value="取消"/>
        </div>
    </form>
</div>

<!--编辑弹框--><!--遮罩-->
<div class="mask"></div>
<div class="readd editAddre">
    <form action="#" method="post" id="changeForm">
        <input type="text" class="on" id="name" value="${defaultAddress.ADDRESS_U_NAME}"/>
        <input type="text" id="mobile" value="${defaultAddress.ADDRESS_U_MOBILE}"/>
        <div class="city">
            <select id="province">
                <c:if test="${defaultAddress.ADDRESS_PROVINCE=='浙江省'}">
                    <option value="浙江省" selected>浙江省</option>
                    <option value="江苏省">江苏省</option>
                    <option value="上海">上海</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${defaultAddress.ADDRESS_PROVINCE=='江苏省'}">
                    <option value="江苏省" selected>江苏省</option>
                    <option value="浙江省">浙江省</option>
                    <option value="上海">上海</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${defaultAddress.ADDRESS_PROVINCE=='上海'}">
                    <option value="上海" selected>上海</option>
                    <option value="江苏省">江苏省</option>
                    <option value="浙江省">浙江省</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${defaultAddress.ADDRESS_PROVINCE=='安徽省'}">
                    <option value="安徽省" selected>安徽省</option>
                    <option value="江苏省">江苏省</option>
                    <option value="上海">上海</option>
                    <option value="浙江省">浙江省</option>
                </c:if>
            </select>
            <select id="city">
                <option value="城市/地区">城市/地区</option>
            </select>
        </div>
        <textarea id="address" rows="20" cols="20"
                  placeholder="详细地址">${requestScope.defaultAddress.ADDRESS_DETAILED_LOCATION}</textarea>
        <input id="zipcode" type="text" placeholder="邮政编码" value="${defaultAddress.ADDRESS_ZIPCODE}"/>
        <div class="bc">
            <input type="button" value="保存" onclick="javascript:ChangeAddress('你确定要保存所作的修改吗?')"/>
            <input type="button" value="取消"/>
        </div>
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
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
</body>
</html>
