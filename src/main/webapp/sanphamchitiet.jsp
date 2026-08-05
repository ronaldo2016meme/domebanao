<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Quản lý sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=41">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="employee-container">

    <h2>Quản lý sản phẩm chi tiết</h2>

    <!-- THÔNG BÁO THÀNH CÔNG / THÔNG TIN -->
    <c:if test="${not empty messageCode}">

        <div class="success">

            <c:choose>

                <c:when test="${messageCode == 'THEM_SPCT_THANH_CONG'}">
                    Thêm sản phẩm chi tiết thành công.
                </c:when>

                <c:when test="${messageCode == 'CAP_NHAT_SPCT_THANH_CONG'}">
                    Cập nhật sản phẩm chi tiết thành công.
                </c:when>

                <c:when test="${messageCode == 'CHUA_CO_SPCT'}">
                    Sản phẩm chưa có thông tin chi tiết.
                </c:when>

                <c:otherwise>
                    Thao tác thành công.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <!-- THÔNG BÁO LỖI -->
    <c:if test="${not empty errorCode}">

        <div class="error">

            <c:choose>

                <c:when test="${errorCode == 'CAP_NHAT_SPCT_THAT_BAI'}">
                    Cập nhật sản phẩm chi tiết thất bại.
                </c:when>

                <c:when test="${errorCode == 'THEM_SPCT_THAT_BAI'}">
                    Thêm sản phẩm chi tiết thất bại.
                </c:when>

                <c:when test="${errorCode == 'LOI_CAP_NHAT_SPCT'}">
                    Có lỗi xảy ra khi cập nhật sản phẩm chi tiết.
                </c:when>

                <c:when test="${errorCode == 'LOI_THEM_SPCT'}">
                    Có lỗi xảy ra khi thêm sản phẩm chi tiết.
                </c:when>

                <c:when test="${errorCode == 'LOI_TAI_DANH_SACH_SPCT'}">
                    Có lỗi xảy ra khi tải danh sách sản phẩm chi tiết.
                </c:when>

                <c:when test="${errorCode == 'MA_SAN_PHAM_KHONG_HOP_LE'}">
                    Mã sản phẩm không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'THIEU_MA_SAN_PHAM'}">
                    Không tìm thấy mã sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'KHONG_TIM_THAY_SPCT'}">
                    Không tìm thấy sản phẩm chi tiết.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra. Vui lòng thử lại.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <div class="top-action">

        <a href="${pageContext.request.contextPath}/addSanPhamChiTiet?maSP=${maSP}"
           class="btn-add">
            Thêm sản phẩm chi tiết
        </a>

        <a href="${pageContext.request.contextPath}/sanpham"
           class="back">
            Quay lại
        </a>

    </div>

    <div class="spct-filter">

        <div class="filter-group">

            <label for="filterMau">
                Lọc theo màu
            </label>

            <select id="filterMau">

                <option value="">
                    Tất cả màu
                </option>

                <c:forEach items="${listMau}"
                           var="mau">

                    <option value="${mau.maMau}">
                        <c:out value="${mau.tenMau}"/>
                    </option>

                </c:forEach>

            </select>

        </div>

        <div class="filter-group">

            <label for="filterSize">
                Lọc theo size
            </label>

            <select id="filterSize">

                <option value="">
                    Tất cả size
                </option>

                <c:forEach items="${listSize}"
                           var="size">

                    <option value="${size.maSize}">
                        <c:out value="${size.tenSize}"/>
                    </option>

                </c:forEach>

            </select>

        </div>

        <button type="button"
                id="resetFilter"
                class="btn-reset-filter">
            Bỏ lọc
        </button>

    </div>

    <div class="product-table-wrap">

        <table class="employee-table">

            <thead>

            <tr>
                <th>Màu</th>
                <th>Size</th>
                <th>Số lượng tồn</th>
                <th>Giá nhập</th>
                <th>Giá bán</th>
                <th>Chức năng</th>
            </tr>

            </thead>

            <tbody>

            <c:forEach var="spct"
                       items="${list}">

                <tr class="spct-row"
                    data-mamau="${spct.maMau}"
                    data-masize="${spct.maSize}">

                    <td>
                        <c:out value="${spct.tenMau}"/>
                    </td>

                    <td>
                        <c:out value="${spct.tenSize}"/>
                    </td>

                    <td>
                        <c:out value="${spct.soLuongTon}"/>
                    </td>

                    <td class="invoice-money">

                        <fmt:formatNumber
                                value="${spct.giaNhap}"
                                pattern="#,##0"/>

                        VNĐ

                    </td>

                    <td class="invoice-money">

                        <fmt:formatNumber
                                value="${spct.giaBan}"
                                pattern="#,##0"/>

                        VNĐ

                    </td>

                    <td>

                        <a href="${pageContext.request.contextPath}/editSanPhamChiTiet?id=${spct.maSPCT}"
                           class="btn-edit">
                            Sửa
                        </a>

                    </td>

                </tr>

            </c:forEach>

            <tr id="noFilterResult"
                style="display:none;">

                <td colspan="6"
                    style="
                        text-align:center;
                        padding:25px;
                        font-weight:bold;
                    ">

                    Không có sản phẩm chi tiết phù hợp.

                </td>

            </tr>

            </tbody>

        </table>

    </div>

</div>

<script>
document.addEventListener("DOMContentLoaded", function () {

    const filterMau =
        document.getElementById("filterMau");

    const filterSize =
        document.getElementById("filterSize");

    const resetFilter =
        document.getElementById("resetFilter");

    const rows =
        document.querySelectorAll(".spct-row");

    const noResult =
        document.getElementById("noFilterResult");

    function locSanPhamChiTiet() {

        const maMau =
            filterMau.value;

        const maSize =
            filterSize.value;

        let soDongHienThi = 0;

        rows.forEach(function (row) {

            const dungMau =
                maMau === ""
                || row.dataset.mamau === maMau;

            const dungSize =
                maSize === ""
                || row.dataset.masize === maSize;

            if (dungMau && dungSize) {

                row.style.display = "";

                soDongHienThi++;

            } else {

                row.style.display = "none";
            }
        });

        if (noResult) {

            noResult.style.display =
                soDongHienThi === 0
                    ? "table-row"
                    : "none";
        }
    }

    filterMau.addEventListener(
        "change",
        locSanPhamChiTiet
    );

    filterSize.addEventListener(
        "change",
        locSanPhamChiTiet
    );

    resetFilter.addEventListener(
        "click",
        function () {

            filterMau.value = "";
            filterSize.value = "";

            locSanPhamChiTiet();
        }
    );

});
</script>

</body>

</html>