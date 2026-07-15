<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="addsanpham" method="post">

        <label>Tên sản phẩm</label>
        <input type="text" name="tenSP" required>

        <label>Danh mục</label>
        <select name="maDanhMuc" required>
            <option value="DM01">Áo Thun</option>
            <option value="DM02">Áo Polo</option>
            <option value="DM03">Áo Sơ Mi</option>
            <option value="DM04">Áo Hoodie</option>
            <option value="DM05">Áo Khoác</option>
        </select>

        <label>Nhà cung cấp</label>
        <select name="maNCC" required>
            <option value="NCC01">Routine</option>
            <option value="NCC02">YODY</option>
            <option value="NCC03">Coolmate</option>
        </select>

        <label>Trạng thái</label>
        <select name="maTrangThaiSP" required>
            <option value="TTSP01">Đang bán</option>
            <option value="TTSP02">Ngừng bán</option>
            <option value="TTSP03">Hết hàng</option>
        </select>

        <label>Giá bán</label>
        <input type="number" step="0.01"  min="0" name="giaBan" required>

        <label>Mô tả</label>
        <textarea name="moTa" rows="4"></textarea>

        <label>Ngày tạo</label>
        <input type="date" name="ngayTao">

        <label>Ngày cập nhật</label>
        <input type="date" name="ngayCapNhat">

        <label>Ảnh</label>
        <input type="text" name="anh" placeholder="Ví dụ: aothun.jpg">

        <button type="submit" class="btn">Thêm sản phẩm</button>

    </form>

    <br>

    <a href="sanpham" class="back">Quay lại</a>

</div>

</body>
</html>