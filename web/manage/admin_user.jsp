<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/25
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#pageSetting").change(function () {
                var pageSize = $("#pageSetting").val();
                //alert(pageSize);
                var keyWord = $("input[name='keyWord']").val();
                var url = "${pageContext.request.contextPath}/douserselect.do?pageSize=" + pageSize + "&keyWord=" + keyWord;
                location.href = url;

                //Ajax还是不适合在jsp动态显示???
            });
        });
    </script>

    <script>
        function DeleteUser(msg, url) {
            if (confirm(msg)) {     //点击确认
                location.href = url;
            }
        }

        function SelectAll(obj) {
            var input = document.getElementsByName("id[]");
            for (var i = 0; i < input.length; i++) {
                input[i].checked = obj.checked;
            }
        }

        function DeleteMoreUsers(msg, id) {
            var form = document.getElementById(id);
            if (confirm(msg)) {
                //只有被选中的checkbox才会提交value到后端
                form.submit();
            }
        }

    </script>
</head>
<body>
<%@include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">
    <div class="crumb-wrap">
        <div class="crumb-list">
            <i class="icon-font"></i>
            <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt</span>
            <span class="crumb-name">用户管理</span>
        </div>
    </div>
    <div class="search-wrap">
        <div class="search-content">
            <form action="${pageContext.request.contextPath}/douserselect.do" method="get">
                <table class="search-tab">
                    <tbody>
                        <tr>
                            <th width="70">关键字：</th>
                            <td>
                                <input class="common-text" placeholder="关键字" name="keyWord" type="text"
                                       value="${requestScope.keyWord}">
                            </td>
                            <td>
                                <input class="btn btn-primary btn2" name="sub" value="查询" type="submit">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
    <div class="result-wrap">
        <form method="post" action="${pageContext.request.contextPath}/douserdelete.do" id="delForm">
            <div class="result-title">
                <div class="result-list">
                    <a href="${pageContext.request.contextPath}/manage/admin_useradd.jsp">
                        <i class="icon-font"></i>新增用户
                    </a>
                    <%--                    写个隐藏域, 删除用户时保存当前页, request域只能保存一次请求的数据--%>
                    <input type="hidden" value="${currentPage}" name="currentPage">
                    <input type="hidden" value="${pageSize}" name="pageSize">

                    <a id="batchDel" href="javascript:DeleteMoreUsers('你确定删除这些用户吗?', 'delForm')">
                        <i class="icon-font"></i>批量删除
                    </a>
                </div>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <thead>
                        <tr>
                            <th class="tc" width="5%">
                                <input class="allChoose" type="checkbox" onclick="SelectAll(this)">
                            </th>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>EMAIL</th>
                            <th>手机</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr>
                                <td class="tc"><input name="id[]" value="${user.USER_ID}" type="checkbox"></td>
                                <td>${user.USER_ID}</td>
                                <td>${user.USER_NAME}</td>
                                <td>${user.USER_SEX}</td>
                                <td>${user.USER_EMAIL}</td>
                                <td>${user.USER_MOBILE}</td>

                                <td>
                                    <a class="link-update"
                                       href="${pageContext.request.contextPath}/touserupdate.do?id=${user.USER_ID}&currentPage=${currentPage}&pageSize=${pageSize}">修改</a>

                                    <c:if test="${user.USER_STATUS == 1}">
                                        <a class="link-del"
                                           href="javascript:DeleteUser('你确定要删除用户${user.USER_NAME}吗?','${pageContext.request.contextPath}/douserdelete.do?id=${user.USER_ID}&currentPage=${currentPage}&pageSize=${pageSize}')">删除
                                        </a>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="list-page">
                    共${totalCount}条记录, 当前是${currentPage}/${totalPage}页
                    <a href="/E_CommerceSystem/douserselect.do?keyWord=${requestScope.keyWord}&pageSize=${pageSize}">首页</a>
                    <a href="${pageContext.request.contextPath}/douserselect.do?currentPage=${(currentPage-1)<1?1:(currentPage-1)}&keyWord=${keyWord}&pageSize=${pageSize}">上一页</a>
                    <a href="${pageContext.request.contextPath}/douserselect.do?currentPage=${(currentPage+1)>totalPage?totalPage:(currentPage+1)}&keyWord=${keyWord}&pageSize=${pageSize}">下一页</a>
                    <a href="${pageContext.request.contextPath}/douserselect.do?currentPage=${totalPage}&keyWord=${keyWord}&pageSize=${pageSize}">尾页</a>

                    每页显示
                    <select name="pageSize" id="pageSetting">
                        <c:if test="${requestScope.pageSize==3}">
                            <option value="3" selected>3</option>
                            <option value="5">5</option>
                            <option value="7">7</option>
                        </c:if>
                        <c:if test="${requestScope.pageSize==5}">
                            <option value="3">3</option>
                            <option value="5" selected>5</option>
                            <option value="7">7</option>
                        </c:if>
                        <c:if test="${requestScope.pageSize==7}">
                            <option value="3">3</option>
                            <option value="5">5</option>
                            <option value="7" selected>7</option>
                        </c:if>

<%--                        <option value="3"--%>
<%--                                <c:if test="${requestScope.pageSize==3}"> selected="selected" </c:if> >3--%>
<%--                        </option>--%>
<%--                        <option value="5"--%>
<%--                                <c:if test="${requestScope.pageSize==5}"> selected="selected" </c:if> >5--%>
<%--                        </option>--%>
<%--                        <option value="7"--%>
<%--                                <c:if test="${requestScope.pageSize==7}"> selected="selected" </c:if> >7--%>
<%--                        </option>--%>
                    </select>
                    条记录
                </div>
            </div>
        </form>
    </div>
</div>

<%--闭合admin_menu.js--%>
</div>

</body>
</html>
