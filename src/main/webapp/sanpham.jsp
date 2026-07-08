<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý sản phẩm</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="product-container">

    <h2>Quản lý sản phẩm</h2>

    <div class="top-action">
        <a href="addsanpham" class="btn">Thêm sản phẩm</a>
    </div>

    <table class="product-table">

        <tr>
            <th>Mã SP</th>
            <th>Ảnh</th>
            <th>Tên sản phẩm</th>
            <th>Danh mục</th>
            <th>Nhà cung cấp</th>
            <th>Giá bán</th>
            <th>Mô tả</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach var="sp" items="${list}">
        <tr>

            <td>${sp.maSP}</td>

            <td>
                <img src="image/${sp.anh}"
                     width="60"
                     height="60">
            </td>

            <td>${sp.tenSP}</td>
            <td>${sp.danhMuc}</td>
            <td>${sp.nhaCungCap}</td>
            <td>${sp.giaBan}</td>
            <td>${sp.moTa}</td>
            <td>${sp.ngayTao}</td>
            <td>${sp.ngayCapNhat}</td>

            <td>
                <a href="editsanpham?id=${sp.maSP}" class="btn-edit">Sửa</a>

                <a href="deletesanpham?id=${sp.maSP}"
                   class="btn-delete"
                   onclick="return confirm('Bạn có chắc muốn xóa?')">
                    Xóa
                </a>
            </td>

        </tr>
        </c:forEach>

    </table>

</div>

</body>
</html>