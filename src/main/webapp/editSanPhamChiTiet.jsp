<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="container">

    <h2>Sửa sản phẩm chi tiết</h2>

    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/editSanPhamChiTiet"
          method="post">

        <input type="hidden"
               name="maSPCT"
               value="${spct.maSPCT}">

        <label>Mã sản phẩm</label>
        <input type="number"
               name="maSP"
               value="${spct.maSP}"
               min="1"
               required>

        <label>Màu sắc</label>
        <select name="maMau" required>
            <option value="">-- Chọn màu sắc --</option>

            <c:forEach items="${listMau}" var="mau">
                <option value="${mau.maMau}"
                    ${spct.maMau eq mau.maMau ? 'selected' : ''}>
                    ${mau.tenMau}
                </option>
            </c:forEach>
        </select>

        <label>Size</label>
        <select name="maSize" required>
            <option value="">-- Chọn size --</option>

            <c:forEach items="${listSize}" var="size">
                <option value="${size.maSize}"
                    ${spct.maSize eq size.maSize ? 'selected' : ''}>
                    ${size.tenSize}
                </option>
            </c:forEach>
        </select>

        <label>Số lượng tồn</label>
        <input type="number"
               name="soLuongTon"
               value="${spct.soLuongTon}"
               min="0"
               required>

        <label>Giá nhập</label>
        <input type="number"
               name="giaNhap"
               value="${spct.giaNhap}"
               min="0"
               step="0.01"
               required>

        <button type="submit" class="btn">
            Cập nhật
        </button>

    </form>

    <a href="${pageContext.request.contextPath}/sanphamchitiet?maSP=${spct.maSP}"
       class="back">
        Quay lại
    </a>

</div>

</body>
</html>