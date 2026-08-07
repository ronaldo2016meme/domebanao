<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>BIGGA</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=2">
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

    <a href="${pageContext.request.contextPath}/profile">
        Thồng tin cá nhân
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        Sản phẩm
    </a>

    <a href="${pageContext.request.contextPath}/khachhang">
        Khách hàng
    </a>

    <a href="${pageContext.request.contextPath}/banhang">
        Bán hàng
    </a>

    <a href="${pageContext.request.contextPath}/danhSachHoaDon">
        Hóa đơn
    </a>

    <a href="${pageContext.request.contextPath}/maGiamGia">
        Giảm giá
    </a>

    <c:if test="${sessionScope.user != null
                 && sessionScope.user.maRole == 'R01'}">

        <div class="menu-dropdown">

            <button type="button"
                    class="menu-dropdown-btn">
                Quản trị
                <span>▾</span>
            </button>

            <div class="menu-dropdown-content">

                <a href="${pageContext.request.contextPath}/employee">
                    Nhân viên
                </a>

                <a href="${pageContext.request.contextPath}/thongke">
                    Doanh thu
                </a>

            </div>

        </div>

    </c:if>

</div>
<!-- Banner -->
<div class="banner">
    <img src="image/Banner.jpg" alt="Banner">
</div>

</body>
</html>