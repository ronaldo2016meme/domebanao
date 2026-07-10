<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa sản phẩm chi tiết</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Sửa sản phẩm chi tiết</h2>

    <form action="editSanPhamChiTiet" method="post">

        <input type="hidden" name="maSPCT" value="${spct.maSPCT}">

        <label>Mã sản phẩm</label>
        <input type="number" name="maSP" value="${spct.maSP}" required>

        <label>Mã màu</label>
        <input type="text" name="maMau" value="${spct.maMau}" required>

        <label>Mã size</label>
        <input type="text" name="maSize" value="${spct.maSize}" required>

        <label>Số lượng tồn</label>
        <input type="number" name="soLuongTon" value="${spct.soLuongTon}" required>

        <label>Giá nhập</label>
        <input type="number" step="0.01" name="giaNhap"
               value="${spct.giaNhap}" required>

        <button type="submit" class="btn">
            Cập nhật
        </button>

    </form>

    <a href="sanphamchitiet" class="back">Quay lại</a>

</div>

</body>
</html>