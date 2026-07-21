<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Hóa đơn</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="invoice">

    <h2>CỬA HÀNG BÁN ÁO</h2>

    <hr>

    <c:if test="${not empty list}">

        <p><b>Mã hóa đơn:</b> ${list[0].maHD}</p>

        <p><b>Ngày lập:</b> ${list[0].ngayLap}</p>

        <p><b>Nhân viên:</b> ${list[0].tenNV}</p>

        <p><b>Khách hàng:</b> ${list[0].tenKH}</p>

    </c:if>

    <table class="bh-table">

        <tr>

            <th>Sản phẩm</th>

            <th>Màu</th>

            <th>Size</th>

            <th>SL</th>

            <th>Đơn giá</th>

            <th>Thành tiền</th>

        </tr>

        <c:forEach items="${list}" var="hd">

            <tr>

                <td>${hd.tenSP}</td>

                <td>${hd.tenMau}</td>

                <td>${hd.tenSize}</td>

                <td>${hd.soLuong}</td>

                <td>${hd.donGia}</td>

                <td>${hd.thanhTien}</td>

            </tr>

        </c:forEach>

    </table>

    <br>

    <c:if test="${not empty list}">

        <p><b>Tổng tiền:</b>
            <fmt:formatNumber value="${list[0].tongTien}" pattern="#,##0"/> VNĐ
        </p>

        <p><b>Tiền khách đưa:</b>
            <fmt:formatNumber value="${list[0].tienKhachDua}" pattern="#,##0"/> VNĐ
        </p>

        <p><b>Tiền thừa:</b>
            <fmt:formatNumber value="${list[0].tienThua}" pattern="#,##0"/> VNĐ
        </p>

    </c:if>

    <br>

    <button class="bh-btn" onclick="window.print()">

        In hóa đơn

    </button>

    <a href="banhang">

        <button class="bh-btn">

            Quay lại bán hàng

        </button>

    </a>

</div>

</body>
</html>