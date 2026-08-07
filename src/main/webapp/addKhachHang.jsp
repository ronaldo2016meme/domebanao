<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Thêm khách hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="form-container">

    <h2>THÊM KHÁCH HÀNG</h2>

    <form action="${pageContext.request.contextPath}/addKhachHang"
          method="post">

        <!-- Trang quay lại -->
        <input type="hidden"
               name="returnUrl"
               value="${returnUrl}">

        <!-- Thông báo lỗi -->
        <c:if test="${not empty error}">

            <div class="error-message">
                ${error}
            </div>

        </c:if>

        <!-- Họ tên -->
        <label>Họ tên</label>

        <input type="text"
               name="hoTen"
               value="${kh.hoTen}"
               placeholder="Nhập họ tên"
               required>


        <!-- Số điện thoại -->
        <label>Số điện thoại</label>

        <input type="text"
               name="sdt"
               value="${kh.sdt}"
               maxlength="10"
               pattern="0[0-9]{9}"
               inputmode="numeric"
               placeholder="0981234567"
               required>


        <!-- Địa chỉ -->
        <label>Địa chỉ</label>

        <input type="text"
               name="diaChi"
               value="${kh.diaChi}"
               placeholder="Nhập địa chỉ"
               required>


        <div class="form-actions">

            <button type="submit" class="btn">
                Thêm khách hàng
            </button>

            <a href="${pageContext.request.contextPath}/${returnUrl}"
               class="back">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>