<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <title>Sửa sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=23">
</head>

<body>

<div class="container">

    <h2>Sửa sản phẩm chi tiết</h2>

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
                    Sản phẩm với màu sắc và size này đã tồn tại.
                </c:when>

                <c:when test="${errorCode == 'DU_LIEU_KHONG_HOP_LE'}">
                    Mã sản phẩm, số lượng hoặc giá không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'CAP_NHAT_SPCT_THAT_BAI'}">
                    Cập nhật sản phẩm chi tiết thất bại.
                </c:when>

                <c:when test="${errorCode == 'LOI_CAP_NHAT_SPCT'}">
                    Có lỗi xảy ra khi cập nhật sản phẩm chi tiết.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra. Vui lòng thử lại.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <form action="${pageContext.request.contextPath}/editSanPhamChiTiet"
          method="post">

        <input type="hidden"
               name="maSPCT"
               value="${spct.maSPCT}">

        <label for="maSP">
            Mã sản phẩm
        </label>

        <input type="number"
               id="maSP"
               name="maSP"
               value="${spct.maSP}"
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
                    ${spct.maMau eq mau.maMau
                        ? 'selected'
                        : ''}>

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
                    ${spct.maSize eq size.maSize
                        ? 'selected'
                        : ''}>

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
               value="${spct.soLuongTon}"
               min="0"
               required>

        <label for="giaNhap">
            Giá nhập
        </label>

        <input type="number"
               id="giaNhap"
               name="giaNhap"
               value="${spct.giaNhap}"
               min="0"
               step="0.01"
               required>

        <label for="giaBan">
            Giá bán
        </label>

        <input type="number"
               id="giaBan"
               name="giaBan"
               value="${spct.giaBan}"
               min="0"
               step="0.01"
               required>

        <div class="form-actions">

            <button type="submit"
                    class="btn">
                Cập nhật
            </button>

            <a href="${pageContext.request.contextPath}/sanphamchitiet?maSP=${spct.maSP}"
               class="back">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>