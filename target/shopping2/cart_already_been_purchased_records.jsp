<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>客户日志记录</title>
</head>
<body>
<form action="/shopping2_war/cart_purchased_records_by_user_id" method="post">
    <input type="text" name="searchText" placeholder="请输入查询用户id" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')">
    <button type="submit">查询</button>
</form><form action="main_.jsp" method="post">
    <button type="submit">返回</button>
</form>
<h1>客户购买日志记录</h1>
<table>
    <tr>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>用户ID</th>
        <th>购物车状态</th>
        <th>创建时间</th>
        <th>更新时间</th>
    </tr>
    <c:forEach var="cart" items="${cart_list0}">
        <tr>
            <td>${cart.cart_product_name}</td>
            <td>${cart.cart_product_price}</td>
            <td>${cart.cart_product_nums}</td>
            <td>${cart.cart_user_id}</td>
            <td>${cart.cart_valid}</td>
            <td>${cart.getCart_created_at()}</td>
            <td>${cart.getCart_updated_at()}</td>
        </tr>
    </c:forEach>
</table>
<h1>客户浏览日志记录</h1>
<table>
    <tr>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>商品数量</th>
        <th>用户ID</th>
        <th>购物车状态</th>
        <th>创建时间</th>
        <th>更新时间</th>
    </tr>
    <c:forEach var="cart" items="${cart_list1}">
        <tr>
            <td>${cart.cart_product_name}</td>
            <td>${cart.cart_product_price}</td>
            <td>${cart.cart_product_nums}</td>
            <td>${cart.cart_user_id}</td>
            <td>${cart.cart_valid}</td>
            <td>${cart.getCart_created_at()}</td>
            <td>${cart.getCart_updated_at()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
