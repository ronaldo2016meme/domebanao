<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <title>Thêm sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=23">
</head>

<body>

<div class="container">

    <h2>Thêm sản phẩm</h2>

    <!-- THÔNG BÁO LỖI -->
    <c:if test="${not empty errorCode}">

        <div class="error">

            <c:choose>

                <c:when test="${errorCode == 'THIEU_THONG_TIN'}">
                    Vui lòng nhập đầy đủ thông tin bắt buộc.
                </c:when>

                <c:when test="${errorCode == 'TEN_SAN_PHAM_TRONG'}">
                    Tên sản phẩm không được để trống.
                </c:when>

                <c:when test="${errorCode == 'GIA_BAN_KHONG_HOP_LE'}">
                    Giá bán phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'CHUA_CHON_ANH'}">
                    Vui lòng chọn ảnh sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'SAI_DINH_DANG_ANH'}">
                    Vui lòng chỉ chọn tệp ảnh PNG, JPG, JPEG hoặc WEBP.
                </c:when>

                <c:when test="${errorCode == 'ANH_QUA_LON'}">
                    Dung lượng ảnh không được vượt quá 5 MB.
                </c:when>

                <c:when test="${errorCode == 'KHONG_TAO_DUOC_THU_MUC_ANH'}">
                    Không thể tạo thư mục lưu ảnh.
                </c:when>

                <c:when test="${errorCode == 'THEM_SAN_PHAM_THAT_BAI'}">
                    Thêm sản phẩm thất bại.
                </c:when>

                <c:when test="${errorCode == 'LOI_THEM_SAN_PHAM'}">
                    Có lỗi xảy ra khi thêm sản phẩm.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra. Vui lòng thử lại.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <!-- THÔNG BÁO THÀNH CÔNG -->
    <c:if test="${not empty messageCode}">

        <div class="success">

            <c:choose>

                <c:when test="${messageCode == 'THEM_SAN_PHAM_THANH_CONG'}">
                    Thêm sản phẩm thành công.
                </c:when>

                <c:otherwise>
                    Thao tác thành công.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <form action="${pageContext.request.contextPath}/addsanpham"
          method="post"
          enctype="multipart/form-data">

        <label for="tenSP">
            Tên sản phẩm
        </label>

        <input type="text"
               id="tenSP"
               name="tenSP"
               value="${param.tenSP}"
               placeholder="Nhập tên sản phẩm"
               required>

        <label for="maDanhMuc">
            Danh mục
        </label>

        <select id="maDanhMuc"
                name="maDanhMuc"
                required>

            <option value="DM01"
                    ${param.maDanhMuc == 'DM01' ? 'selected' : ''}>
                Áo Thun
            </option>

            <option value="DM02"
                    ${param.maDanhMuc == 'DM02' ? 'selected' : ''}>
                Áo Polo
            </option>

            <option value="DM03"
                    ${param.maDanhMuc == 'DM03' ? 'selected' : ''}>
                Áo Sơ Mi
            </option>

            <option value="DM04"
                    ${param.maDanhMuc == 'DM04' ? 'selected' : ''}>
                Áo Hoodie
            </option>

            <option value="DM05"
                    ${param.maDanhMuc == 'DM05' ? 'selected' : ''}>
                Áo Khoác
            </option>

        </select>

        <label for="maNCC">
            Nhà cung cấp
        </label>

        <select id="maNCC"
                name="maNCC"
                required>

            <option value="NCC01"
                    ${param.maNCC == 'NCC01' ? 'selected' : ''}>
                Routine
            </option>

            <option value="NCC02"
                    ${param.maNCC == 'NCC02' ? 'selected' : ''}>
                YODY
            </option>

            <option value="NCC03"
                    ${param.maNCC == 'NCC03' ? 'selected' : ''}>
                Coolmate
            </option>

        </select>

        <label for="maTrangThaiSP">
            Trạng thái
        </label>

        <select id="maTrangThaiSP"
                name="maTrangThaiSP"
                required>

            <option value="TTSP01"
                    ${param.maTrangThaiSP == 'TTSP01' ? 'selected' : ''}>
                Đang bán
            </option>

            <option value="TTSP02"
                    ${param.maTrangThaiSP == 'TTSP02' ? 'selected' : ''}>
                Ngừng bán
            </option>

            <option value="TTSP03"
                    ${param.maTrangThaiSP == 'TTSP03' ? 'selected' : ''}>
                Hết hàng
            </option>

        </select>

        <label for="giaBan">
            Giá bán
        </label>

        <input type="number"
               id="giaBan"
               name="giaBan"
               value="${param.giaBan}"
               min="0"
               step="0.01"
               placeholder="Nhập giá bán"
               required>

        <label for="moTa">
            Mô tả
        </label>

        <textarea id="moTa"
                  name="moTa"
                  rows="4"
                  placeholder="Nhập mô tả sản phẩm"><c:out value="${param.moTa}"/></textarea>

        <label for="ngayTao">
            Ngày tạo
        </label>

        <input type="date"
               id="ngayTao"
               name="ngayTao"
               value="${param.ngayTao}">

        <label for="ngayCapNhat">
            Ngày cập nhật
        </label>

        <input type="date"
               id="ngayCapNhat"
               name="ngayCapNhat"
               value="${param.ngayCapNhat}">

        <label for="anh">
            Ảnh
        </label>

        <input type="file"
               id="anh"
               name="anh"
               accept=".png,.jpg,.jpeg,.webp,image/png,image/jpeg,image/webp"
               required>

        <small class="image-note">
            Chỉ chấp nhận ảnh PNG, JPG, JPEG hoặc WEBP, tối đa 5 MB.
        </small>

        <div class="form-actions">

            <button type="submit"
                    class="btn">
                Thêm sản phẩm
            </button>

            <a href="${pageContext.request.contextPath}/sanpham"
               class="back">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>