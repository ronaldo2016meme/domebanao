<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="editsanpham" method="post">

        <input type="hidden" name="maSP" value="${sp.maSP}">

        <label>Tên sản phẩm</label>
        <input type="text" name="tenSP" value="${sp.tenSP}" required>

        <label>Danh mục</label>
        <select name="maDanhMuc" required>
            <option value="DM01" ${sp.maDanhMuc=='DM01'?'selected':''}>Áo thun</option>
            <option value="DM02" ${sp.maDanhMuc=='DM02'?'selected':''}>Áo polo</option>
            <option value="DM03" ${sp.maDanhMuc=='DM03'?'selected':''}>Áo sơ mi</option>
            <option value="DM04" ${sp.maDanhMuc=='DM04'?'selected':''}>Hoodie</option>
            <option value="DM05" ${sp.maDanhMuc=='DM05'?'selected':''}>Áo khoác</option>
        </select>

        <label>Nhà cung cấp</label>
        <select name="maNCC" required>
            <option value="NCC01" ${sp.maNCC=='NCC01'?'selected':''}>Routine</option>
            <option value="NCC02" ${sp.maNCC=='NCC02'?'selected':''}>YODY</option>
            <option value="NCC03" ${sp.maNCC=='NCC03'?'selected':''}>Coolmate</option>
        </select>

        <label>Trạng thái</label>
        <select name="maTrangThaiSP" required>
            <option value="TTSP01" ${sp.maTrangThaiSP=='TT01'?'selected':''}>Đang bán</option>
            <option value="TTSP02" ${sp.maTrangThaiSP=='TT02'?'selected':''}>Ngừng bán</option>
            <option value="TTSP03" ${sp.maTrangThaiSP=='TT02'?'selected':''}>Hết hàng</option>
        </select>

        <label>Giá bán</label>
        <input type="number" step="0.01"  min="0" name="giaBan" value="${sp.giaBan}" required>

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

    <a href="sanpham" class="back">Quay lại</a>

</div>

</body>
</html>