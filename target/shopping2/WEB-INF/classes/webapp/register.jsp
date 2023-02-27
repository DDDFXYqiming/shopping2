<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/register_style.css">
    <meta charset="UTF-8">
    <title>用户注册</title>
    <script type="text/javascript">
        function validateForm() {
            var name = document.forms["registerForm"]["name"].value;
            var password = document.forms["registerForm"]["password"].value;
            var email = document.forms["registerForm"]["email"].value;
            if (name == "" || password == "" || email == "") {
                alert("用户名，密码和邮箱不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="register">
    <form class="register_form" action="/shopping2_war/user_register_" method="POST" onsubmit="return validateForm()" name="registerForm">
    <h1>用户注册</h1>
        <label>用户名</label><input type="text" name="name" placeholder="请输入用户名"/><br/>
        <label>密码</label><input type="password" name="password" placeholder="请输入密码"/><br/>
        <label>邮箱</label><input type="text" name="email" placeholder="请输入邮箱"/><br/>
        <input type="submit" value="注册"/>
        <a href="/shopping2_war/login_in.jsp">已有账号？登录</a>
    </form>
</div>
</body>
</html>