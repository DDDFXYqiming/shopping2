function validateForm() {
    var name = document.forms["registerForm"]["name"].value;
    var password = document.forms["registerForm"]["password"].value;
    var email = document.forms["registerForm"]["email"].value;

    if (name == "" || password == "" || email == "") {
        alert("请填写完整的注册信息！");
        return false;
    }

    // 邮箱格式验证
    var emailPattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    if (!emailPattern.test(email)) {
        alert("请输入正确的邮箱地址！");
        return false;
    }

    // 密码长度验证
    if (password.length < 2) {
        alert("密码长度不能少于2位！");
        return false;
    }

    return true;
}
