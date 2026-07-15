<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm chi tiết</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Thêm sản phẩm chi tiết</h2>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="addSanPhamChiTiet" method="post">

        <label>Mã sản phẩm</label>
        <input type="number" name="maSP" required>

        <label>Mã màu</label>
        <input type="text" name="maMau" required>

        <label>Mã size</label>
        <input type="text" name="maSize" required>

        <label>Số lượng tồn</label>
        <input type="number" name="soLuongTon" min="0" required>

        <label>Giá nhập</label>
        <input type="number" name="giaNhap" min="0" step="0.01" required>

        <button type="submit" class="btn">
            Thêm sản phẩm chi tiết
        </button>

    </form>

    <a href="sanphamchitiet" class="back">Quay lại</a>

</div>

</body>
</html>