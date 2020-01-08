<%--
  Created by IntelliJ IDEA.
  User: Chenjie
  Date: 2019/12/25
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8"/>
    <title>书城系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/manage/css/main.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

    <script>
        //Ajax
        $(function () {
            $("#FidSetting").change(function () {

                var Fid = parseInt($("#FidSetting").val());
                //var checkIndex=$("#FidSetting").get(0).selectedIndex;
                var url = "${pageContext.request.contextPath}/showproductcsort.do?id=" + Fid;
                //alert(Fid)
                //每次选择之前清空option
                $("#CIdSetting").empty();

                $.get(url, function (data) {
                    for (var i = 0; i < data.split(" ").length - 1; i++) {
                        var d1 = data.split(" ")[i];
                        var d2 = d1.substr(1, d1.length - 2);
                        var id = d2.split(",")[0];
                        var name = d2.split(",")[1];
                        //alert(id+"-"+name);
                        $("#CIdSetting").append("<option value=" + id + ">" + name + "</option>")
                    }
                });
            });


        });
    </script>

</head>
<body>
<%@include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list"><i class="icon-font"></i>
            <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt</span>
            <a class="crumb-name" href="${pageContext.request.contextPath}/doproductselect.do">图书管理</a>
            <span class="crumb-step">&gt</span>
            <span>修改图书</span>
        </div>
    </div>

    <div class="result-wrap">
        <div class="result-content">
            <form id="myform" method="post" action="${pageContext.request.contextPath}/doproductupdate.do"
                  enctype="multipart/form-data">
                <input type="hidden" name="productId" value="${requestScope.id}">
                <input type="text" value="${product.PRODUCT_FILENAME}" name="filename">
                <table class="insert-tab" width="100%">
                    <tbody>
                        <tr>
                            <th>图书名称：</th>
                            <td>
                                <input class="common-text required" name="bookName" size="50"
                                       value="${product.PRODUCT_NAME}">
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书一级分类：</th>
                            <td>
                                <select name="FId" id="FidSetting">
                                    <c:forEach items="${flist}" var="f">
                                        <c:if test="${fcate.CATE_ID==f.CATE_ID}">
                                            <option value="${f.CATE_ID}" selected>${f.CATE_NAME}</option>
                                        </c:if>
                                        <c:if test="${fcate.CATE_ID!=f.CATE_ID}">
                                            <option value="${f.CATE_ID}">${f.CATE_NAME}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>图书二级分类：</th>
                            <td>
                                <select name="CId" id="CIdSetting">
                                    <%--                                    默认要显示当前的二级分类--%>
                                    <c:forEach items="${clist}" var="c">
                                        <c:if test="${c.CATE_ID==product.PRODUCT_CID}">
                                            <option value="${c.CATE_ID}" selected>${c.CATE_NAME}</option>
                                        </c:if>
                                        <c:if test="${c.CATE_PARENT_ID==product.PRODUCT_FID&&c.CATE_ID!=product.PRODUCT_CID}">
                                            <option value="${c.CATE_ID}">${c.CATE_NAME}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>图书描述：</th>
                            <td>
                                <textarea class="required" name="bookDescription" rows="6" cols="40">${product.PRODUCT_DESCRIPTION}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th>图书价格：</th>
                            <td>
                                <input class="common-text required" name="bookPrice" size="50"
                                       value="${product.PRODUCT_PRICE}">
                            </td>
                        </tr>
                        <tr>
                            <th>图书库存：</th>
                            <td>
                                <input class="common-text required" name="bookStock" size="50"
                                       value="${product.PRODUCT_STOCK}">
                            </td>
                        </tr>
                        <tr>
                            <th>图书封面：</th>
                            <td>
                                <img src="images/product/${product.PRODUCT_FILENAME}" alt="..." width="200px"
                                     height="200px">
                                <input type="file" name="picture" size="50" style="height: 30px;">
                            </td>
                        </tr>

                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>
