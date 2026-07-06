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

<form action="addProduct" method="post" enctype="multipart/form-data">

<label>Tên sản phẩm</label>
<input type="text" name="tenSP" required>

<label>Danh mục</label>
<select name="maDanhMuc">
    <c:forEach items="${listDanhMuc}" var="dm">
        <option value="${dm.maDanhMuc}">
            ${dm.tenDanhMuc}
        </option>
    </c:forEach>
</select>

<label>Nhà cung cấp</label>
<select name="maNCC">
    <c:forEach items="${listNCC}" var="ncc">
        <option value="${ncc.maNCC}">
            ${ncc.tenNCC}
        </option>
    </c:forEach>
</select>

<label>Giá bán</label>
<input type="number" name="giaBan" required>

<label>Mô tả</label>
<textarea name="moTa" rows="4"></textarea>

<label>Ngày tạo</label>
<input type="date" name="ngayTao">

<label>Ngày cập nhật</label>
<input type="date" name="ngayCapNhat">

<label>Ảnh sản phẩm</label>
<input type="file" name="hinhAnh">

<br><br>

<button class="btn" type="submit">Lưu</button>

<a href="product">
    <button type="button" class="btn-delete">Hủy</button>
</a>

</form>

</div>

</body>
</html>