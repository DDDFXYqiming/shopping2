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
    <title>购物车</title>
</head>
<body>
<h1>购物车</h1>
<table>
    <tr>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>操作</th>
    </tr>
</table>
<form action="main_.jsp" method="get">
    <input type="submit" value="返回">
</form>
<form action="cart_pay" method="get">
    <input type="submit" value="商品结算">
</form>
<div id = "cartList">
    <c:forEach var="cart" items="${cart_list}">
        <div>
            <p>名称：${cart.cart_product_name}</p>
            <p>价格：${cart.cart_product_price}</p>
            <p>已选数量：${cart.cart_product_nums}</p>
            <form action="/shopping2_war/cart_del_one" method="post">
                <input type="hidden" name="cartId" value="${cart.cart_id}">
                <input type="hidden" name="cartNums" value="${cart.cart_product_nums}">
                <input type="hidden" name="userId" value="${sessionScope.user.id}">
                <button type="submit">移出</button>
            </form>
            <form action="/shopping2_war/cart_add_one" method="post">
                <input type="hidden" name="cartId" value="${cart.cart_id}">
                <input type="hidden" name="cartNums" value="${cart.cart_product_nums}">
                <input type="hidden" name="userId" value="${sessionScope.user.id}">
                <button type="submit">添加</button>
            </form>
        </div>
    </c:forEach>

</div>
</body>
</html>
