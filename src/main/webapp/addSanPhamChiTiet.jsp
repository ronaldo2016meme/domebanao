<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="container">

    <h2>Thêm sản phẩm chi tiết</h2>

    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/addSanPhamChiTiet"
          method="post">

        <label>Mã sản phẩm</label>
        <input type="number"
               name="maSP"
               value="${param.maSP}"
               min="1"
               required>

        <label>Màu sắc</label>
        <select name="maMau" required>
            <option value="">-- Chọn màu sắc --</option>

            <c:forEach items="${listMau}" var="mau">
                <option value="${mau.maMau}"
                    ${param.maMau eq mau.maMau ? 'selected' : ''}>
                    ${mau.tenMau}
                </option>
            </c:forEach>
        </select>

        <label>Size</label>
        <select name="maSize" required>
            <option value="">-- Chọn size --</option>

            <c:forEach items="${listSize}" var="size">
                <option value="${size.maSize}"
                    ${param.maSize eq size.maSize ? 'selected' : ''}>
                    ${size.tenSize}
                </option>
            </c:forEach>
        </select>

        <label>Số lượng tồn</label>
        <input type="number"
               name="soLuongTon"
               value="${param.soLuongTon}"
               min="0"
               required>

        <label>Giá nhập</label>
        <input type="number"
               name="giaNhap"
               value="${param.giaNhap}"
               min="0"
               step="0.01"
               required>

        <div class="form-actions">

            <button type="submit" class="btn">
                Cập nhật
            </button>

            <a href="${pageContext.request.contextPath}/sanphamchitiet"
                   class="back">
                    Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>