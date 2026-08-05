<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Sửa sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=24">

</head>

<body>

<div class="container">

    <h2>Sửa sản phẩm</h2>

    <c:if test="${not empty errorCode}">

        <div class="error">

            <c:choose>

                <c:when test="${errorCode == 'THIEU_THONG_TIN'}">
                    Vui lòng nhập đầy đủ thông tin bắt buộc.
                </c:when>

                <c:when test="${errorCode == 'MA_SAN_PHAM_KHONG_HOP_LE'}">
                    Mã sản phẩm không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'KHONG_TIM_THAY_SAN_PHAM'}">
                    Không tìm thấy sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'GIA_BAN_KHONG_HOP_LE'}">
                    Giá bán phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'SAI_DINH_DANG_ANH'}">
                    Vui lòng chỉ chọn ảnh PNG, JPG, JPEG hoặc WEBP.
                </c:when>

                <c:when test="${errorCode == 'ANH_QUA_LON'}">
                    Dung lượng ảnh không được vượt quá 5 MB.
                </c:when>

                <c:when test="${errorCode == 'KHONG_TAO_DUOC_THU_MUC_ANH'}">
                    Không thể tạo thư mục lưu ảnh.
                </c:when>

                <c:when test="${errorCode == 'DU_LIEU_KHONG_HOP_LE'}">
                    Dữ liệu nhập không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'CAP_NHAT_SAN_PHAM_THAT_BAI'}">
                    Cập nhật sản phẩm thất bại.
                </c:when>

                <c:when test="${errorCode == 'LOI_CAP_NHAT_SAN_PHAM'}">
                    Có lỗi xảy ra khi cập nhật sản phẩm.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra. Vui lòng thử lại.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <form action="${pageContext.request.contextPath}/editsanpham"
          method="post"
          enctype="multipart/form-data">

        <input type="hidden"
               name="maSP"
               value="${sp.maSP}">

        <label for="tenSP">
            Tên sản phẩm
        </label>

        <input type="text"
               id="tenSP"
               name="tenSP"
               value="${sp.tenSP}"
               required>

        <label for="maDanhMuc">
            Danh mục
        </label>

        <select id="maDanhMuc"
                name="maDanhMuc"
                required>

            <option value="DM01"
                    ${sp.maDanhMuc == 'DM01' ? 'selected' : ''}>
                Áo thun
            </option>

            <option value="DM02"
                    ${sp.maDanhMuc == 'DM02' ? 'selected' : ''}>
                Áo polo
            </option>

            <option value="DM03"
                    ${sp.maDanhMuc == 'DM03' ? 'selected' : ''}>
                Áo sơ mi
            </option>

            <option value="DM04"
                    ${sp.maDanhMuc == 'DM04' ? 'selected' : ''}>
                Hoodie
            </option>

            <option value="DM05"
                    ${sp.maDanhMuc == 'DM05' ? 'selected' : ''}>
                Áo khoác
            </option>

        </select>

        <label for="maNCC">
            Nhà cung cấp
        </label>

        <select id="maNCC"
                name="maNCC"
                required>

            <option value="NCC01"
                    ${sp.maNCC == 'NCC01' ? 'selected' : ''}>
                Routine
            </option>

            <option value="NCC02"
                    ${sp.maNCC == 'NCC02' ? 'selected' : ''}>
                YODY
            </option>

            <option value="NCC03"
                    ${sp.maNCC == 'NCC03' ? 'selected' : ''}>
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
                    ${sp.maTrangThaiSP == 'TTSP01' ? 'selected' : ''}>
                Đang bán
            </option>

            <option value="TTSP02"
                    ${sp.maTrangThaiSP == 'TTSP02' ? 'selected' : ''}>
                Ngừng bán
            </option>

            <option value="TTSP03"
                    ${sp.maTrangThaiSP == 'TTSP03' ? 'selected' : ''}>
                Hết hàng
            </option>

        </select>

        <label for="giaBan">
            Giá bán
        </label>

        <input type="number"
               id="giaBan"
               name="giaBan"
               value="${sp.giaBan}"
               min="0"
               step="0.01"
               required>

        <label for="moTa">
            Mô tả
        </label>

        <textarea id="moTa"
                  name="moTa"
                  rows="4"><c:out value="${sp.moTa}"/></textarea>

        <label for="ngayTao">
            Ngày tạo
        </label>

        <input type="date"
               id="ngayTao"
               name="ngayTao"
               value="${sp.ngayTao}">

        <label for="ngayCapNhat">
            Ngày cập nhật
        </label>

        <input type="date"
               id="ngayCapNhat"
               name="ngayCapNhat"
               value="${sp.ngayCapNhat}">

        <label>
            Ảnh hiện tại
        </label>

        <c:choose>

            <c:when test="${not empty sp.anh}">

                <div class="current-image-wrap">

                    <img src="${pageContext.request.contextPath}/image/${sp.anh}"
                         alt="${sp.tenSP}"
                         class="current-product-image">

                </div>

            </c:when>

            <c:otherwise>

                <p class="no-image-text">
                    Sản phẩm chưa có ảnh.
                </p>

            </c:otherwise>

        </c:choose>

        <input type="hidden"
               name="anhCu"
               value="${sp.anh}">

        <label for="anh">
            Chọn ảnh mới
        </label>

        <input type="file"
               id="anh"
               name="anh"
               accept=".png,.jpg,.jpeg,.webp,image/png,image/jpeg,image/webp">

        <small class="image-note">
            Để trống nếu muốn giữ nguyên ảnh hiện tại. Ảnh tối đa 5 MB.
        </small>

        <div class="form-actions">

            <button type="submit"
                    class="btn">
                Cập nhật
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