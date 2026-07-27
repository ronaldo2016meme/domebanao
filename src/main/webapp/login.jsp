<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=20">
</head>
<body>

<div class="login-box">

    <div class="banner1">
        <img src="image/Banner1.jpg" alt="Banner">
    </div>

    <h2>Đăng nhập hệ thống BIGGA</h2>

    <form action="login" method="post">

        <label>Tên đăng nhập</label>
        <input type="text"
               name="username"
               placeholder="Nhập tên đăng nhập"
               required>

        <label>Mật khẩu</label>
        <input type="password"
               name="password"
               placeholder="Nhập mật khẩu"
               required>

        <div class="btn-login">
            <button type="submit">Đăng nhập</button>
        </div>

    </form>

    <p class="error">${error}</p>

</div>
</body>
</html>