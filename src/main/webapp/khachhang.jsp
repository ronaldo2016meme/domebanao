<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý khách hàng</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="customer-container">

    <h2>Quản lý khách hàng</h2>

    <div class="top-action">

        <form action="khachhang" method="get">
            <input type="text" name="keyword" placeholder="Search">
            <button type="submit">Tìm</button>
        </form>

        <a href="addKhachHang" class="btn">
            Thêm khách hàng
        </a>

        <a href="home" class="back">Quay lại</a>

    </div>

    <table class="customer-table">

        <tr>
            <th>Mã</th>
            <th>Tên khách hàng</th>
            <th>Số điện thoại</th>
            <th>Địa chỉ</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach var="kh" items="${list}">

        <tr>

            <td>${kh.maKH}</td>
            <td>${kh.hoTen}</td>
            <td>${kh.sdt}</td>
            <td>${kh.diaChi}</td>

            <td>

                <a href="editKhachHang?id=${kh.maKH}"
                   class="btn-edit">
                    Sửa
                </a>

            </td>

        </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>