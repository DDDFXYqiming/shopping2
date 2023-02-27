<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="css/login_style.css">
  <meta charset="UTF-8">
  <title>用户登录</title>
</head>
<body>
<div class="login_in">
  <form class="login_form" action="/shopping2_war/user_login_" method="POST">
    <h1>欢迎登录</h1>
    <label for="username">用户名:</label>
    <input type="text" name="username" id="username">
    <label for="password">密码:</label>
    <input type="password" name="password" id="password">
    <input type="submit" value="登录">
    <a href="/shopping2_war/register.jsp">注册</a>
    <a href="/shopping2_war/main_.jsp">返回</a>
  </form>
</div>
</body>
</html>
