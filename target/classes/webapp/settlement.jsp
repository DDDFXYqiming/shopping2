<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8"%>
<%@ page import="entity.cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.user" %>
<%@ page import="entity.product" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.product_dao" %>
<%@ page import="dao.cart_dao" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>结算</title>
</head>
<body>
<h1>结算</h1>
<table>
  <tr>
    <th>商品名称</th>
    <th>商品价格</th>
    <th>商品数量</th>
    <th>操作</th>
  </tr>
</table>
<form action="main_.jsp" method="get">
  <input type="submit" value="返回主页">
</form>
<form action="cart_show" method="get">
  <input type="submit" value="返回购物车">
</form>
<div id = "cartList">
  <c:forEach var="cart" items="${cart_list}">
    <div>
      <p>名称：${cart.cart_product_name}</p>
      <p>价格：${cart.cart_product_price}</p>
      <p>已选数量：${cart.cart_product_nums}</p>
    </div>
  </c:forEach>
  <div>
    <h3>商品总价格：${totalPrice}</h3>
  </div>
</div>
<form action="cart_pay_over" method="get">
  <input type="submit" value="点击购买">
</form>
</body>
</html>