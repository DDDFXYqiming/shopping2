<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/register_style.css">
    <meta charset="UTF-8">
    <title>添加商品</title>
</head>
<body>
<div class="register">
    <form class="register_form" action="/shopping2_war/product_add" method="POST" onsubmit="return validateForm()" name="registerForm">
    <h1>添加商品</h1>
        <label>名称</label><input type="text" name="PRODUCT_NAME" placeholder="请输入商品名称"/><br/>
        <label>描述</label><input type="text" name="PRODUCT_DESCRIPTION" placeholder="请输入商品描述"/><br/>
        <label>价格</label><input type="text" name="PRODUCT_PRICE" placeholder="请输入商品价格" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"/><br/>
        <label>库存</label><input type="text" name="PRODUCT_STOCK" placeholder="请输入商品库存" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"/><br/>
        <input type="submit" value="添加"/>
        <a href="/shopping2_war/main_.jsp">返回主页</a>
    </form>
</div>
</body>
</html>