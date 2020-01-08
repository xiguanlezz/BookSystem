<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2020/1/2
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

    <%--    <script>--%>
    <%--        $("#deladd")--%>
    <%--    </script>--%>

    <script>
        function SaveAddress(formId) {
            //var form = $("#" + formId);
            var form = document.getElementById(formId);
            form.submit();
            //alert(0)
        }

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
                //alert(name + "-" + mobile);
                $.get(url, function (data) {
                    if (data == "success") {
                        alert("修改成功");
                    } else if (data == "failure") {
                        alert("修改失败");
                    }
                    location.href = "${pageContext.request.contextPath}/addressselect";
                });
            }
        }

        function DeleteAddress() {
            if (confirm('你确定要删除这个地址吗?')) {
                <c:if test="${not empty address}">
                    var aid =${address.ADDRESS_ID};
                    location.href = "${pageContext.request.contextPath}/addressdelete?aid=" + aid;
                </c:if>
            }
        }
    </script>
</head>
<body>
<%@include file="my_header.jsp" %>
<div class="you fl">
    <h2>收货地址</h2>
    <div class="add">
        <div>
            <a href="#2" id="addxad">
                <img src="img/jia.png"/>
            </a>
            <span>添加新地址</span>
        </div>
        <c:forEach items="${requestScope.addresses}" var="a">
            <div id="dizhi">
                <p>${a.ADDRESS_U_NAME}</p>
                <p>${a.ADDRESS_U_MOBILE}</p>
                <p>${a.ADDRESS_PROVINCE}</p>
                <p>${a.ADDRESS_DETAILED_LOCATION}(${a.ADDRESS_ZIPCODE})</p>
            </div>
        </c:forEach>

    </div>
</div>
</div>
</div><!--编辑弹框--><!--遮罩-->
<div class="mask"></div>
<div class="adddz">
    <form action="addressadd" method="post" id="addForm">
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

<%--这里只放一个地址, 模板不行--%>
<div class="readd">
    <form action="#" method="post" id="changeForm">
        <input type="text" class="on" id="name" value="${address.ADDRESS_U_NAME}"/>
        <input type="text" id="mobile" value="${address.ADDRESS_U_MOBILE}"/>
        <div class="city">
            <select id="province">
                <c:if test="${address.ADDRESS_PROVINCE=='浙江省'}">
                    <option value="浙江省" selected>浙江省</option>
                    <option value="江苏省">江苏省</option>
                    <option value="上海">上海</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${address.ADDRESS_PROVINCE=='江苏省'}">
                    <option value="江苏省" selected>江苏省</option>
                    <option value="浙江省">浙江省</option>
                    <option value="上海">上海</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${address.ADDRESS_PROVINCE=='上海'}">
                    <option value="上海" selected>上海</option>
                    <option value="江苏省">江苏省</option>
                    <option value="浙江省">浙江省</option>
                    <option value="安徽省">安徽省</option>
                </c:if>
                <c:if test="${address.ADDRESS_PROVINCE=='安徽省'}">
                    <option value="安徽省" selected>安徽省</option>
                    <option value="江苏省">江苏省</option>
                    <option value="上海">上海</option>
                    <option value="浙江省">浙江省</option>
                </c:if>
            </select>
            <select id="city">
                <option value="城市/地区">城市/地区</option>
            </select>
            <%--            <select>--%>
            <%--                <option value="区/县">路北区</option>--%>
            <%--            </select>--%>
            <%--            <select>--%>
            <%--                <option value="配送区域">火炬路</option>--%>
            <%--            </select>--%>
        </div>
        <textarea id="address" rows="20" cols="20"
                  placeholder="详细地址">${requestScope.address.ADDRESS_DETAILED_LOCATION}</textarea>
        <input id="zipcode" type="text" placeholder="邮政编码" value="${address.ADDRESS_ZIPCODE}"/>
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
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
</div>
</div>

</body>
</html>
