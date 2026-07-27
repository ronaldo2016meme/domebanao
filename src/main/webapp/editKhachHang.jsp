<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa khách hàng</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>
<body>

<div class="container">

    <h2>Sửa khách hàng</h2>
    <c:if test="${not empty error}">
        <div style="color:red; margin-bottom:10px;">
            ${error}
        </div>
    </c:if>

    <form action="editKhachHang" method="post">

        <input type="hidden"
               name="maKH"
               value="${kh.maKH}">

        <label>Họ tên</label>
        <input type="text"
               name="hoTen"
               value="${kh.hoTen}"
               required>

        <label>Số điện thoại</label>
        <input type="text"
               name="sdt"
               value="${kh.sdt}"
               maxlength="10"
               required>

        <label>Địa chỉ</label>
        <input type="text"
               name="diaChi"
               value="${kh.diaChi}"
               required>

        <button type="submit" class="btn">
            Cập nhật
        </button>

    </form>

    <a href="khachhang" class="back">
        Quay lại
    </a>

</div>

</body>
</html>