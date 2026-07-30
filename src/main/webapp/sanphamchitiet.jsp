<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Quản lý sản phẩm chi tiết</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=21">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="employee-container">

    <h2>Quản lý sản phẩm chi tiết</h2>

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

                <c:forEach items="${listMau}" var="mau">

                    <option value="${mau.maMau}">
                        ${mau.tenMau}
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

                <c:forEach items="${listSize}" var="size">

                    <option value="${size.maSize}">
                        ${size.tenSize}
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

    <table class="employee-table">

        <thead>
        <tr>
            <th>Màu</th>
            <th>Size</th>
            <th>Số lượng tồn</th>
            <th>Giá nhập</th>
            <th>Chức năng</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach var="spct" items="${list}">

            <tr class="spct-row"
                data-mamau="${spct.maMau}"
                data-masize="${spct.maSize}">

                <td>${spct.tenMau}</td>

                <td>${spct.tenSize}</td>

                <td>${spct.soLuongTon}</td>

                <td>
                    <fmt:formatNumber
                            value="${spct.giaNhap}"
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

            <td colspan="5"
                style="text-align:center;
                       padding:25px;
                       font-weight:bold;">

                Không có sản phẩm chi tiết phù hợp

            </td>

        </tr>

        </tbody>

    </table>

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