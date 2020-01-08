<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/27
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top">
            <h1 class="fl">
                <a href="index.html">
                    <img src="img/logo.png"/>
                </a>
            </h1>
            <div class="fr clearfix" id="top1">
                <p class="fl">
                    <c:if test="${sessionScope.isLogin==1}">
                        <b>你好,</b> ${sessionScope.user.USER_NAME}
                    </c:if>
                    <c:if test="${sessionScope.isLogin!=1}">
                        <a href="login.jsp" id="login">登录</a>
                        <a href="register.jsp" id="reg">注册</a>
                    </c:if>
                    <c:if test="${isAdminLogin==1}">
                        <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">进入后台</a>
                    </c:if>
                </p>

                <form action="#" method="get" class="fl">
                    <input type="text" placeholder="热门搜索：干花花瓶"/>
                    <input type="button"/>
                </form>

                <div class="btn fl clearfix">
                    <a href="${pageContext.request.contextPath}/ordersquerynum"><img src="img/grzx.png"/></a>
                    <a href="#" class="er1"><img src="img/ewm.png"/></a>

                    <%--                    登陆后才显示购物车--%>
                    <c:if test="${isLogin==1}">
                        <a href="${pageContext.request.contextPath}/showcart">
                            <img src="img/gwc.png"/>
                        </a>
                    </c:if>

                    <p><a href="#"><img src="img/smewm.png"/></a></p>
                </div>
            </div>
        </div>

        <ul class="clearfix" id="bott">
            <li><a href="indexselect">首页</a></li>
            <c:forEach var="f" items="${requestScope.flist}">
                <li>
                    <a href="selectproductlist?id=${f.CATE_ID}">${f.CATE_NAME}</a>
                    <div class="sList2" style="display: none; height: 50px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
                        <div class="clearfix">
                            <c:forEach var="c" items="${requestScope.clist}">
                                <c:if test="${c.CATE_PARENT_ID==f.CATE_ID}">
                                    <a href="selectproductlist?cid=${c.CATE_ID}&fid=${f.CATE_ID}">${c.CATE_NAME}</a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

