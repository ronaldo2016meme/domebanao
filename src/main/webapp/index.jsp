<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>BIGGA</title>
    <link rel="stylesheet" href="css/style.css?v=2">
</head>
<body>

<!-- Header -->
<div class="header">

    <div class="logo">
        BIGGA
    </div>

    <div class="right">

        <!-- Nếu chưa đăng nhập -->
        <c:if test="${sessionScope.user == null}">
            <button onclick="location.href='login'">
                Đăng nhập
            </button>
        </c:if>

        <!-- Nếu đã đăng nhập -->
        <c:if test="${sessionScope.user != null}">
            <span>Xin chào,
                <b>${sessionScope.user.tenDangNhap}</b>
            </span>

            <button onclick="location.href='logout'">
                Đăng xuất
            </button>
        </c:if>

        <form action="search" method="get">
            <input type="text"
                   name="keyword"
                   placeholder="Search">

            <button type="submit">Tìm</button>
        </form>

    </div>

</div>

<!-- Menu -->
<div class="menu">

    <a href="product">Quản lý sản phẩm</a>

    <!-- Chỉ Quản lý mới thấy -->
    <c:if test="${sessionScope.user != null && sessionScope.user.maRole == 'R01'}">
        <a href="employee">Quản lý nhân viên</a>
    </c:if>

    <a href="revenue">Doanh thu</a>

    <a href="customer">Quản lý khách hàng</a>

    <a href="sale">Bán hàng</a>

    <a href="profile">Thông tin cá nhân</a>

</div>

<!-- Banner -->
<div class="banner">
    <img src="image/Banner.jpg" alt="Banner">
</div>

</body>
</html>