<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%
    String isLogin = (String) session.getAttribute("islogin");
%>
<%
    List<product> allProductList = new product_dao().selectAll(); // 获取所有商品列表
    request.setAttribute("allProductList", allProductList); // 将所有商品列表放入 request 中
%>
<%@ page import="dao.product_dao" %>
<%@ page import="entity.product" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.user" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/main_style.css">
    <meta charset="UTF-8">
    <title>网上购物商城</title>
</head>
<body>
<header>
    <h1>网上购物商城</h1>
    <div>
        <% if (isLogin == "0"||isLogin == null) { %>
        <button onclick="location.href='/shopping2_war/login_in.jsp'">登录</button>
        <button onclick="location.href='/shopping2_war/register.jsp'">注册</button>
        <% } else { %>
        <button onclick="location.href='/shopping2_war/cart_show'">购物车</button>
        <button onclick="location.href='/shopping2_war/user_login_out'">注销</button>
        <% } %>
    </div>
</header>
<c:if test="${sessionScope.user.status == 0}">
    <div class="main">
        <h2>当前界面为管理员界面！！！</h2>
    </div>
</c:if>
<div class="main">
    <h2>欢迎光临！</h2>
    <p>这里是我们的网上购物商城，欢迎您浏览我们的商品。</p>
</div>
<form action="product_show" method="post">
    <input type="text" name="searchText" placeholder="请输入查询条件">
    <button type="submit">查询</button>
</form>

<div id="productList">
    <!-- 商品展示区域 -->
<c:if test="${sessionScope.user.status == 0}">
    <form action="product_add_main.jsp" method="post">
        <button type="submit">添加商品</button>
    </form>
    <form action="cart_purchased" method="post">
        <button type="submit">销售统计报表界面</button>
    </form>
    <form action="cart_purchased_records" method="post">
        <button type="submit">客户的浏览/购买日志记录</button>
    </form>
</c:if>

<c:choose>
    <c:when test="${not empty productList_old}">
        <c:forEach var="product" items="${productList_old}">
            <div>
                <h3>${product.PRODUCT_NAME}</h3>
                <p>${product.PRODUCT_DESCRIPTION}</p>
                <p>价格：${product.PRODUCT_PRICE}</p>
                <p>库存：${product.PRODUCT_STOCK}</p>
                <form action="/shopping2_war/cart_add" method="post">
                    <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                    <input type="hidden" name="productName" value="${product.PRODUCT_NAME}">
                    <input type="hidden" name="productPrice" value="${product.PRODUCT_PRICE}">
                    <input type="hidden" name="productStock" value="${product.PRODUCT_STOCK}">
                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                    <button type="submit">添加到购物车</button>
                </form>
                <c:if test="${sessionScope.user.status == 0}">
                    <form action="/shopping2_war/product_delete" method="post">
                        <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                        <button type="submit">删除商品</button>
                    </form>
                    <form action="/shopping2_war/product_modify_main.jsp" method="post">
                        <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                        <button type="submit">修改商品信息</button>
                    </form>
                </c:if>
            </div>

        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:if test="${empty productList}">
            <c:forEach var="product" items="${allProductList}">
                <div>
                    <h3>${product.PRODUCT_NAME}</h3>
                    <p>${product.PRODUCT_DESCRIPTION}</p>
                    <p>价格：${product.PRODUCT_PRICE}</p>
                    <p>库存：${product.PRODUCT_STOCK}</p>
                    <form action="/shopping2_war/cart_add" method="post">
                        <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                        <input type="hidden" name="productName" value="${product.PRODUCT_NAME}">
                        <input type="hidden" name="productPrice" value="${product.PRODUCT_PRICE}">
                        <input type="hidden" name="productStock" value="${product.PRODUCT_STOCK}">
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">
                        <button type="submit">添加到购物车</button>
                    </form>
                    <c:if test="${sessionScope.user.status == 0}">
                        <form action="/shopping2_war/product_delete" method="post">
                            <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                            <button type="submit">删除商品</button>
                        </form>
                        <form action="/shopping2_war/product_modify_main.jsp" method="post">
                            <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                            <button type="submit">修改商品信息</button>
                        </form>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${not empty productList}">
            <c:forEach var="product" items="${productList}">
                <div>
                    <h3>${product.PRODUCT_NAME}</h3>
                    <p>${product.PRODUCT_DESCRIPTION}</p>
                    <p>价格：${product.PRODUCT_PRICE}</p>
                    <p>库存：${product.PRODUCT_STOCK}</p>
                    <form action="/shopping2_war/cart_add" method="post">
                        <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                        <input type="hidden" name="productName" value="${product.PRODUCT_NAME}">
                        <input type="hidden" name="productPrice" value="${product.PRODUCT_PRICE}">
                        <input type="hidden" name="productStock" value="${product.PRODUCT_STOCK}">
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">
                        <button type="submit">添加到购物车</button>
                    </form>
                    <c:if test="${sessionScope.user.status == 0}">
                        <form action="/shopping2_war/product_delete" method="post">
                            <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                            <button type="submit">删除商品</button>
                        </form>
                        <form action="/shopping2_war/product_modify_main.jsp" method="post">
                            <input type="hidden" name="productId" value="${product.PRODUCT_ID}">
                            <button type="submit">修改商品信息</button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
    </c:otherwise>
</c:choose>
</div>


</body>
</html>
