<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">

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

        <p>
            <b>Mã hóa đơn:</b>
            <c:out value="${list[0].maHD}"/>
        </p>

        <p>
            <b>Ngày lập:</b>

            <fmt:formatDate
                    value="${list[0].ngayLap}"
                    pattern="dd/MM/yyyy"/>
        </p>

        <p>
            <b>Nhân viên:</b>
            <c:out value="${list[0].tenNV}"/>
        </p>

        <p>
            <b>Khách hàng:</b>

            <c:choose>

                <c:when test="${empty list[0].tenKH
                        or list[0].tenKH == 'KhÃ¡ch láº»'
                        or list[0].tenKH == 'Khách lẻ'}">

                    Kh&#225;ch l&#7867;

                </c:when>

                <c:otherwise>

                    <c:out value="${list[0].tenKH}"/>

                </c:otherwise>

            </c:choose>
        </p>

    </c:if>

    <table class="bh-table">

        <thead>

        <tr>
            <th>Sản phẩm</th>
            <th>Màu</th>
            <th>Size</th>
            <th>SL</th>
            <th>Đơn giá</th>
            <th>Thành tiền</th>
        </tr>

        </thead>

        <tbody>

        <c:forEach items="${list}" var="hd">

            <tr>

                <td>
                    <c:out value="${hd.tenSP}"/>
                </td>

                <td>
                    <c:out value="${hd.tenMau}"/>
                </td>

                <td>
                    <c:out value="${hd.tenSize}"/>
                </td>

                <td>
                    <c:out value="${hd.soLuong}"/>
                </td>

                <td>
                    <fmt:formatNumber
                            value="${hd.donGia}"
                            pattern="#,##0"/>
                    VNĐ
                </td>

                <td>
                    <fmt:formatNumber
                            value="${hd.thanhTien}"
                            pattern="#,##0"/>
                    VNĐ
                </td>

            </tr>

        </c:forEach>

        </tbody>

    </table>

    <br>

    <c:if test="${not empty list}">

        <p>
            <b>Giảm giá:</b>

            <fmt:formatNumber
                    value="${list[0].tienGiam}"
                    pattern="#,##0"/>

            VNĐ
        </p>

        <p>
            <b>Tổng tiền:</b>

            <fmt:formatNumber
                    value="${list[0].tongTien}"
                    pattern="#,##0"/>

            VNĐ
        </p>

        <p>
            <b>Tiền khách đưa:</b>

            <fmt:formatNumber
                    value="${list[0].tienKhachDua}"
                    pattern="#,##0"/>

            VNĐ
        </p>

        <p>
            <b>Tiền thừa:</b>

            <fmt:formatNumber
                    value="${list[0].tienThua}"
                    pattern="#,##0"/>

            VNĐ
        </p>

    </c:if>

    <br>

    <button type="button"
            class="bh-btn"
            onclick="window.print()">
        In hóa đơn
    </button>

    <a href="${pageContext.request.contextPath}/banhang"
       class="bh-btn">
        Quay lại bán hàng
    </a>

</div>

</body>
</html>