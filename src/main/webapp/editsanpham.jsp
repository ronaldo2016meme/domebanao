<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa sản phẩm</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Sửa sản phẩm</h2>

    <form action="editsansham" method="post">

        <input type="hidden" name="maSP" value="${sp.maSP}">

        <label>Tên sản phẩm</label>
        <input type="text" name="tenSP" value="${sp.tenSP}" required>

        <label>Danh mục</label>
        <input type="text" name="danhMuc" value="${sp.danhMuc}" required>

        <label>Nhà cung cấp</label>
        <input type="text" name="nhaCungCap" value="${sp.nhaCungCap}" required>

        <label>Giá bán</label>
        <input type="number" step="0.01" name="giaBan" value="${sp.giaBan}" required>

        <label>Mô tả</label>
        <textarea name="moTa">${sp.moTa}</textarea>

        <label>Ngày tạo</label>
        <input type="date" name="ngayTao" value="${sp.ngayTao}">

        <label>Ngày cập nhật</label>
        <input type="date" name="ngayCapNhat" value="${sp.ngayCapNhat}">

        <label>Ảnh</label>
        <input type="text" name="anh" value="${sp.anh}">

        <button type="submit" class="btn">Cập nhật</button>

    </form>

    <br>

    <a href="sanpham" class="back">← Quay lại</a>

</div>

</body>
</html>