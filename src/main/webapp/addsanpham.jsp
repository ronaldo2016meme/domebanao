<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Thêm sản phẩm</h2>

    <form action="addsanpham" method="post" enctype="multipart/form-data">

        <label>Tên sản phẩm</label>
        <input type="text" name="tenSP" required>

        <label>Danh mục</label>
        <input type="text" name="danhMuc" required>

        <label>Nhà cung cấp</label>
        <input type="text" name="nhaCungCap" required>

        <label>Giá bán</label>
        <input type="number" step="0.01" name="giaBan" required>

        <label>Mô tả</label>
        <textarea name="moTa" rows="4"></textarea>

        <label>Ngày tạo</label>
        <input type="date" name="ngayTao">

        <label>Ngày cập nhật</label>
        <input type="date" name="ngayCapNhat">

        <label>Ảnh</label>
        <input type="file" name="anh" accept="image/*">

        <button type="submit" class="btn">Thêm sản phẩm</button>

    </form>

    <br>

    <a href="sanpham" class="back">← Quay lại</a>

</div>

</body>
</html>