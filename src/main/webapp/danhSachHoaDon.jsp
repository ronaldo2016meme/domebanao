<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách hóa đơn</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=33">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="invoice-page">

    <div class="invoice-page-header">
        <div>
            <h2>DANH SÁCH HÓA ĐƠN</h2>
            <p>Danh sách hóa đơn đã thanh toán</p>
        </div>

        <a class="invoice-back-btn"
           href="${pageContext.request.contextPath}/banhang">
            Quay lại
        </a>
    </div>

    <div class="invoice-card">

        <c:choose>

            <c:when test="${empty listHoaDon}">
                <div class="invoice-empty">
                    Chưa có hóa đơn nào.
                </div>
            </c:when>

            <c:otherwise>

                <div class="invoice-table-wrap">

                    <table class="invoice-list-table">

                        <thead>
                        <tr>
                            <th>Mã hóa đơn</th>
                            <th>Ngày lập</th>
                            <th>Khách hàng</th>
                            <th>Nhân viên</th>
                            <th>Phương thức</th>
                            <th>Trạng thái</th>
                            <th>Tổng tiền</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach items="${listHoaDon}"
                                   var="hd">

                            <tr>
                                <td>
                                    #${hd.maHD}
                                </td>

                                <td>
                                    <fmt:formatDate
                                            value="${hd.ngayLap}"
                                            pattern="dd/MM/yyyy"/>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty hd.tenKH}">
                                            Khách lẻ
                                        </c:when>

                                        <c:otherwise>
                                            <c:out value="${hd.tenKH}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:out value="${hd.tenNV}"/>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${hd.phuongThucThanhToan == 'Tien mat'}">
                                            Tiền mặt
                                        </c:when>

                                        <c:when test="${hd.phuongThucThanhToan == 'Chuyen khoan'}">
                                            Chuyển khoản
                                        </c:when>

                                        <c:otherwise>
                                            <c:out value="${hd.phuongThucThanhToan}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <span class="invoice-status">
                                        <c:out value="${hd.tenTrangThai}"/>
                                    </span>
                                </td>

                                <td class="invoice-money">
                                    <fmt:formatNumber
                                            value="${hd.tongTien}"
                                            pattern="#,##0"/>
                                    VNĐ
                                </td>

                                <td>
                                    <a class="invoice-detail-btn"
                                       href="${pageContext.request.contextPath}/chitiethoadon?maHD=${hd.maHD}">
                                        Xem chi tiết
                                    </a>
                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</div>

</body>
</html>