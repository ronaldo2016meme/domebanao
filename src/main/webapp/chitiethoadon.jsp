<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">

    <title>Chi tiết hóa đơn</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=34">
</head>

<body>

<%@ include file="menu.jsp" %>

<div class="invoice-page">

    <div class="invoice-page-header">

        <div>
            <h2>CHI TIẾT HÓA ĐƠN #${hoaDon.maHD}</h2>
            <p>Thông tin hóa đơn và các sản phẩm đã bán</p>
        </div>

        <div class="invoice-header-actions">

            <button type="button"
                    class="invoice-print-btn"
                    onclick="window.print()">
                In hóa đơn
            </button>

            <a class="invoice-back-btn"
               href="${pageContext.request.contextPath}/danhSachHoaDon">
                Quay lại
            </a>

        </div>

    </div>

    <div class="invoice-summary-grid">

        <div class="invoice-summary-item">
            <span>Mã hóa đơn</span>
            <strong>#${hoaDon.maHD}</strong>
        </div>

        <div class="invoice-summary-item">
            <span>Ngày lập</span>

            <strong>
                <fmt:formatDate value="${hoaDon.ngayLap}"
                                pattern="dd/MM/yyyy"/>
            </strong>
        </div>

        <div class="invoice-summary-item">
            <span>Khách hàng</span>

            <strong>
                <c:out value="${hoaDon.tenKH}"/>
            </strong>
        </div>

        <div class="invoice-summary-item">
            <span>Nhân viên</span>

            <strong>
                <c:out value="${hoaDon.tenNV}"/>
            </strong>
        </div>

        <div class="invoice-summary-item">
            <span>Phương thức thanh toán</span>

            <strong>
                <c:choose>

                    <c:when test="${hoaDon.phuongThucThanhToan == 'Tien mat'}">
                        Tiền mặt
                    </c:when>

                    <c:when test="${hoaDon.phuongThucThanhToan == 'Chuyen khoan'}">
                        Chuyển khoản
                    </c:when>

                    <c:otherwise>
                        <c:out value="${hoaDon.phuongThucThanhToan}"/>
                    </c:otherwise>

                </c:choose>
            </strong>
        </div>

        <div class="invoice-summary-item">
            <span>Trạng thái</span>

            <strong>
                <c:out value="${hoaDon.tenTrangThai}"/>
            </strong>
        </div>

    </div>

    <div class="invoice-card">

        <c:choose>

            <c:when test="${empty listChiTiet}">

                <div class="invoice-empty">
                    Hóa đơn này chưa có sản phẩm.
                </div>

            </c:when>

            <c:otherwise>

                <div class="invoice-table-wrap">

                    <table class="invoice-list-table">

                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã SPCT</th>
                            <th>Sản phẩm</th>
                            <th>Màu sắc</th>
                            <th>Kích thước</th>
                            <th>Số lượng</th>
                            <th>Đơn giá</th>
                            <th>Thành tiền</th>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach items="${listChiTiet}"
                                   var="ct"
                                   varStatus="status">

                            <tr>

                                <td>${status.count}</td>

                                <td>#${ct.maSPCT}</td>

                                <td>
                                    <c:out value="${ct.tenSP}"/>
                                </td>

                                <td>
                                    <c:out value="${ct.tenMau}"/>
                                </td>

                                <td>
                                    <c:out value="${ct.tenSize}"/>
                                </td>

                                <td>${ct.soLuong}</td>

                                <td class="invoice-money">
                                    <fmt:formatNumber value="${ct.donGia}"
                                                      pattern="#,##0"/>
                                    VNĐ
                                </td>

                                <td class="invoice-money">
                                    <fmt:formatNumber value="${ct.thanhTien}"
                                                      pattern="#,##0"/>
                                    VNĐ
                                </td>

                            </tr>

                        </c:forEach>

                        </tbody>

                    </table>

                </div>

            </c:otherwise>

        </c:choose>

        <div class="invoice-totals">

            <div>
                <span>Tổng tiền</span>

                <strong>
                    <fmt:formatNumber value="${hoaDon.tongTien}"
                                      pattern="#,##0"/>
                    VNĐ
                </strong>
            </div>

            <div>
                <span>Tiền khách đưa</span>

                <strong>
                    <fmt:formatNumber value="${hoaDon.tienKhachDua}"
                                      pattern="#,##0"/>
                    VNĐ
                </strong>
            </div>

            <div class="invoice-grand-total">
                <span>Tiền thừa</span>

                <strong>
                    <fmt:formatNumber value="${hoaDon.tienThua}"
                                      pattern="#,##0"/>
                    VNĐ
                </strong>
            </div>

        </div>

    </div>

</div>

</body>
</html>