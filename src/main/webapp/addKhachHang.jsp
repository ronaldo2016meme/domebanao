<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Thêm khách hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>

<body>

<div class="container">

    <h2>Thêm khách hàng</h2>

    <form action="${pageContext.request.contextPath}/addKhachHang"
          method="post">

        <!-- Giữ lại trang cần quay về -->
        <input type="hidden"
               name="returnUrl"
               value="${returnUrl}">

        <c:if test="${not empty error}">

            <div style="color: red; margin-bottom: 10px;">
                ${error}
            </div>

        </c:if>

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
               pattern="0[0-9]{9}"
               inputmode="numeric"
               required>

        <label>Địa chỉ</label>

        <input type="text"
               name="diaChi"
               value="${kh.diaChi}"
               required>

        <button type="submit"
                class="btn">
            Thêm khách hàng
        </button>

    </form>

    <!-- Chỉ có đúng một nút quay lại -->
    <a href="${pageContext.request.contextPath}/${returnUrl}"
       class="back">
        Quay lại
    </a>

</div>

</body>
</html>