<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ page import="dao.product_dao" %>
<%@ page import="entity.product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/product_modify_style.css">
    <meta charset="UTF-8">
    <title>修改商品</title>
</head>
<body>
<%
    String productId = request.getParameter("productId");
    System.out.println("传递中...修改商品id"+productId);
%>
<%
    product_dao productDao = new product_dao();
    product pro = productDao.selectById(Integer.parseInt(productId));
%>
<div class="register">
    <form class="register_form" action="/shopping2_war/product_modify" method="POST" onsubmit="return validateForm()" name="registerForm">
        <h1>修改商品</h1>
        <label>名称</label><input type="text" name="PRODUCT_NAME" placeholder="请输入商品名称" value="<%= pro.getPRODUCT_NAME() %>"/><br/>
        <label>描述</label><textarea name="PRODUCT_DESCRIPTION" placeholder="请输入商品描述"><%= pro.getPRODUCT_DESCRIPTION() %></textarea>
        <label>价格</label><input type="text" name="PRODUCT_PRICE" placeholder="请输入商品价格" value="<%= pro.getPRODUCT_PRICE() %>" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"/><br/>
        <label>库存</label><input type="text" name="PRODUCT_STOCK" placeholder="请输入商品库存" value="<%= pro.getPRODUCT_STOCK() %>" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"/><br/>
        <input type="hidden" name="PRODUCT_ID" value="<%= productId %>">
        <input type="submit" value="添加"/>
        <a href="/shopping2_war/main_.jsp">返回主页</a>
    </form>
</div>
</body>
</html>
