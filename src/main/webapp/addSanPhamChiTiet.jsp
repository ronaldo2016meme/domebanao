<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <title>Thêm sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=23">
</head>

<body>

<div class="container">

    <h2>Thêm sản phẩm chi tiết</h2>

    <!-- THÔNG BÁO LỖI -->
    <c:if test="${not empty errorCode}">

        <div class="error">

            <c:choose>

                <c:when test="${errorCode == 'THIEU_THONG_TIN'}">
                    Vui lòng nhập đầy đủ thông tin.
                </c:when>

                <c:when test="${errorCode == 'MA_SAN_PHAM_KHONG_HOP_LE'}">
                    Mã sản phẩm không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'SO_LUONG_KHONG_HOP_LE'}">
                    Số lượng tồn phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'GIA_NHAP_KHONG_HOP_LE'}">
                    Giá nhập phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'GIA_BAN_KHONG_HOP_LE'}">
                    Giá bán phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'GIA_BAN_NHO_HON_GIA_NHAP'}">
                    Giá bán không được nhỏ hơn giá nhập.
                </c:when>

                <c:when test="${errorCode == 'BIEN_THE_DA_TON_TAI'}">
                    Màu sắc và size này đã tồn tại.
                </c:when>

                <c:when test="${errorCode == 'THEM_SPCT_THAT_BAI'}">
                    Thêm sản phẩm chi tiết thất bại.
                </c:when>

                <c:when test="${errorCode == 'DU_LIEU_KHONG_HOP_LE'}">
                    Dữ liệu nhập không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'LOI_THEM_SPCT'}">
                    Có lỗi xảy ra khi thêm sản phẩm chi tiết.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <!-- THÔNG BÁO THÀNH CÔNG -->
    <c:if test="${not empty messageCode}">

        <div class="success">

            <c:choose>

                <c:when test="${messageCode == 'THEM_SPCT_THANH_CONG'}">
                    Thêm sản phẩm chi tiết thành công.
                </c:when>

                <c:when test="${messageCode == 'CAP_NHAT_SPCT_THANH_CONG'}">
                    Cập nhật sản phẩm chi tiết thành công.
                </c:when>

                <c:otherwise>
                    Thao tác thành công.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <form action="${pageContext.request.contextPath}/addSanPhamChiTiet"
          method="post">

        <label for="maSP">
            Mã sản phẩm
        </label>

        <input type="number"
               id="maSP"
               name="maSP"
               value="${not empty maSP ? maSP : param.maSP}"
               min="1"
               readonly
               required>

        <label for="maMau">
            Màu sắc
        </label>

        <select id="maMau"
                name="maMau"
                required>

            <option value="">
                -- Chọn màu sắc --
            </option>

            <c:forEach items="${listMau}"
                       var="mau">

                <option value="${mau.maMau}"
                    ${param.maMau eq mau.maMau ? 'selected' : ''}>

                    <c:out value="${mau.tenMau}"/>

                </option>

            </c:forEach>

        </select>

        <label for="maSize">
            Size
        </label>

        <select id="maSize"
                name="maSize"
                required>

            <option value="">
                -- Chọn size --
            </option>

            <c:forEach items="${listSize}"
                       var="size">

                <option value="${size.maSize}"
                    ${param.maSize eq size.maSize ? 'selected' : ''}>

                    <c:out value="${size.tenSize}"/>

                </option>

            </c:forEach>

        </select>

        <label for="soLuongTon">
            Số lượng tồn
        </label>

        <input type="number"
               id="soLuongTon"
               name="soLuongTon"
               value="${param.soLuongTon}"
               min="0"
               required>

        <label for="giaNhap">
            Giá nhập
        </label>

        <input type="number"
               id="giaNhap"
               name="giaNhap"
               value="${param.giaNhap}"
               min="0"
               step="0.01"
               required>

        <label for="giaBan">
            Giá bán
        </label>

        <input type="number"
               id="giaBan"
               name="giaBan"
               value="${param.giaBan}"
               min="0"
               step="0.01"
               required>

        <div class="form-actions">

            <button type="submit"
                    class="btn">

                Thêm sản phẩm chi tiết

            </button>

            <a href="${pageContext.request.contextPath}/sanphamchitiet?maSP=${not empty maSP ? maSP : param.maSP}"
               class="back">

                Quay lại

            </a>

        </div>

    </form>

</div>

</body>

</html>