<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý sản phẩm chi tiết</title>

<link rel="stylesheet" href="./css/style.css">

</head>
<body>

<div class="employee-container">

    <h2>Quản lý sản phẩm chi tiết</h2>

    <div class="top-action">
        <a href="${pageContext.request.contextPath}/addSanPhamChiTiet"
           class="btn-add">
            Thêm sản phẩm chi tiết
        </a>
    </div>

    <table class="employee-table">

        <tr>
            <th>Mã SPCT</th>
            <th>Mã SP</th>
            <th>Mã Màu</th>
            <th>Mã Size</th>
            <th>Số lượng tồn</th>
            <th>Giá nhập</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach var="spct" items="${list}">

            <tr>

                <td>${spct.maSPCT}</td>
                <td>${spct.maSP}</td>
                <td>${spct.maMau}</td>
                <td>${spct.maSize}</td>
                <td>${spct.soLuongTon}</td>
                <td>${spct.giaNhap}</td>

                <td>

                    <a href="editSanPhamChiTiet?id=${spct.maSPCT}">
                        <button class="btn-edit">Sửa</button>
                    </a>

                    <a href="deleteSanPhamChiTiet?id=${spct.maSPCT}"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">
                        <button class="btn-delete">Xóa</button>
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>