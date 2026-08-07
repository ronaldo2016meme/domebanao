<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa mã giảm giá</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=201">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="mgg-form-container">

    <h2>SỬA MÃ GIẢM GIÁ</h2>

    <c:if test="${not empty error}">
        <div class="error-box">
            ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/editMaGiamGia"
          method="post">

        <input type="hidden"
               name="maMGG"
               value="${mgg.maMGG}">

        <label>Mã code</label>
        <input type="text"
               name="maCode"
               value="${mgg.maCode}"
               required>

        <label>Tên mã giảm giá</label>
        <input type="text"
               name="tenMGG"
               value="${mgg.tenMGG}"
               required>

        <label>Phần trăm giảm</label>
        <input type="number"
               name="phanTramGiam"
               value="${mgg.phanTramGiam}"
               min="1"
               max="100"
               required>

        <label>Điểm cần đổi</label>
        <input type="number"
               name="diemCan"
               value="${mgg.diemCan}"
               min="0"
               required>

        <label>Ngày bắt đầu</label>
        <input type="date"
               name="ngayBatDau"
               value="${mgg.ngayBatDau}"
               required>

        <label>Ngày kết thúc</label>
        <input type="date"
               name="ngayKetThuc"
               value="${mgg.ngayKetThuc}"
               required>

        <label>Số lượng</label>
        <input type="number"
               name="soLuong"
               value="${mgg.soLuong}"
               min="0"
               required>

        <label>Trạng thái</label>

        <select name="trangThai">

            <option value="Hoạt động"
                ${mgg.trangThai == 'Hoạt động' ? 'selected' : ''}>
                Hoạt động
            </option>

            <option value="Ngừng hoạt động"
                ${mgg.trangThai == 'Ngừng hoạt động' ? 'selected' : ''}>
                Ngừng hoạt động
            </option>

        </select>

        <div class="mgg-form-buttons">

            <button type="submit" class="btn">
                Lưu thay đổi
            </button>

            <a href="${pageContext.request.contextPath}/maGiamGia"
               class="btn">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>