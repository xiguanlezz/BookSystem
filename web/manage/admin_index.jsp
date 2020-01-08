<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/25
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

</head>
<body>
<%@include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font">&#xe06b;</i><span>欢迎使用书城系统管理员端。</span></div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <h1>快捷操作</h1>
        </div>
        <div class="result-content">
            <div class="short-wrap">
                <a href="${pageContext.request.contextPath}/douserselect.do"><i class="icon-font">&#xe008;</i>用户管理</a>
                <a href="${pageContext.request.contextPath}/docateselect.do"><i class="icon-font">&#xe005;</i>分类管理</a>
                <a href="${pageContext.request.contextPath}/doproductselect.do"><i class="icon-font">&#xe006;</i>图书管理</a>
                <a href="${pageContext.request.contextPath}/doorderselect.do"><i class="icon-font">&#xe004;</i>订单管理</a>
                <a href="${pageContext.request.contextPath}/doevaluationselect.do"><i class="icon-font">&#xe012;</i>评价管理</a>
            </div>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <h1>系统基本信息</h1>
        </div>
        <div class="result-content">
            <ul class="sys-info-list">
                <li>
                    <label class="res-lab">操作系统</label><span class="res-info">WIN10</span>
                </li>
                <li>
                    <label class="res-lab">运行环境</label><span class="res-info">Tomcat v8.5.41</span>
                </li>
                <li>
                    <label class="res-lab">java运行方式</label><span class="res-info">jdk1.8</span>
                </li>
                <li>
                    <label class="res-lab">静静设计-版本</label><span class="res-info">v-0.1</span>
                </li>
                <li>
                    <label class="res-lab">上传附件限制</label><span class="res-info">2M</span>
                </li>
                <li>
                    <label class="res-lab">北京时间</label>
                    <span class="res-info">
                            <%
                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
                                String currentTime = format.format(date);
                                request.setAttribute("currentTime", currentTime);
                            %>
                            ${requestScope.currentTime}
                        </span>
                </li>
                <li>
                    <label class="res-lab">服务器域名/IP</label><span class="res-info">localhost [ 127.0.0.1 ]</span>
                </li>
                <li>
                    <label class="res-lab">Host</label><span class="res-info">127.0.0.1</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-title">
            <h1>使用帮助</h1>
        </div>
        <div class="result-content">
            <ul class="sys-info-list">
                <li>
                    <label class="res-lab">更多模板：</label><span class="res-info"><a href="http://www.lmonkey.com/"
                                                                                  target="_blank">学习猿地</a></span>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--/main-->
</div>
</body>
</html>
