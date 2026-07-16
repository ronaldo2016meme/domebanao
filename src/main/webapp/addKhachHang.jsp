<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm khách hàng</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Thêm khách hàng</h2>

    <form action="addKhachHang" method="post">
    <c:if test="${not empty error}">
        <div style="color:red; margin-bottom:10px;">
            ${error}
        </div>
    </c:if>

        <label>Họ tên</label>
        <input type="text"
               name="hoTen"
               required>

        <label>Số điện thoại</label>
        <input type="text"
               name="sdt"
               maxlength="10"
               required>

        <label>Địa chỉ</label>
        <input type="text"
               name="diaChi"
               required>

        <button type="submit" class="btn">
            Thêm khách hàng
        </button>

    </form>

    <a href="khachhang" class="back">
        Quay lại
    </a>

</div>

</body>
</html>