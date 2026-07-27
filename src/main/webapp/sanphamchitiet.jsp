<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý sản phẩm chi tiết</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>
<body>

<div class="employee-container">

    <h2>Quản lý sản phẩm chi tiết</h2>

    <div class="top-action">
        <a href="${pageContext.request.contextPath}/addSanPhamChiTiet"
           class="btn-add">
            Thêm sản phẩm chi tiết
        </a>

        <a href="sanpham?maSP=${maSP}" class="back">
            Quay lại
        </a>
    </div>

    <table class="employee-table">

        <tr>
            <th>Mã SPCT</th>
            <th>Mã SP</th>
            <th>Màu</th>
            <th>Size</th>
            <th>Số lượng tồn</th>
            <th>Giá nhập</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach var="spct" items="${list}">

            <tr>

                <td>${spct.maSPCT}</td>
                <td>${spct.maSP}</td>
                <td>${spct.tenMau}</td>
                <td>${spct.tenSize}</td>
                <td>${spct.soLuongTon}</td>
                <td>
                     <fmt:formatNumber
                         value="${spct.giaNhap}"
                         pattern="#,##0"/> VNĐ
                 </td>

                <td>

                    <a href="editSanPhamChiTiet?id=${spct.maSPCT}">
                        <button class="btn-edit">Sửa</button>
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>