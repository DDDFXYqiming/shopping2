<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ page import="entity.cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.user" %>
<%@ page import="entity.product" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.product_dao" %>
<%@ page import="dao.cart_dao" %>
<%
    // 在控制台输出该链表的长度，用于检查是否正确获取到了购物车商品列表
    System.out.println("cart_list size: " + ((ArrayList<cart>)request.getAttribute("cart_list")).size());
%>
<%
    List<cart> allCartList = new cart_dao().select_cart_All(); // 将所有商品列表放入 request 中
    System.out.println("allCartList size: " + allCartList.size());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>销售统计报表</title>
</head>
<body>
<h1>销售统计报表</h1>
<table>
    <tr>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品数量</th>
    </tr>
</table>
<form action="main_.jsp" method="get">
    <input type="submit" value="返回">
</form>
<div id = "cartList">
    <c:forEach var="cart" items="${cart_list}">
        <div>
            <p>名称：${cart.cart_product_name}</p>
            <p>价格：${cart.cart_product_price}</p>
            <p>已销售数量：${cart.cart_product_nums}</p>
        </div>
    </c:forEach>

</div>
</body>
</html>
