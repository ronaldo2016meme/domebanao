<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Tạo tài khoản</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>

<body>

<div class="container">

    <h2>TẠO TÀI KHOẢN</h2>

    <form action="createAccount" method="post">

        <input type="hidden"
               name="maNV"
               value="${nhanVien.maNV}">

        <label>Mã nhân viên</label>

        <input
                value="${nhanVien.maNV}"
                readonly>

        <label>Họ tên</label>

        <input
                value="${nhanVien.hoTen}"
                readonly>

        <label>Tên đăng nhập</label>

        <input
                type="text"
                name="tenDangNhap"
                required>

        <label>Mật khẩu</label>

        <input
                type="password"
                name="matKhau"
                required>

        <label>Nhập lại mật khẩu</label>

        <input
                type="password"
                name="xacNhan"
                required>

        <button class="btn">
            Tạo tài khoản
        </button>

    </form>

    <br>

    <a href="employee" class="back">
        Quay lại
    </a>

    <p style="color:red;text-align:center">
        ${error}
    </p>

</div>

</body>
</html>