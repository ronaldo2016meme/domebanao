<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Hóa đơn</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=600">

</head>

<body>


<div class="invoice">

    <!-- ================= HEADER ================= -->

    <h2>CỬA HÀNG BÁN ÁO</h2>


    <div class="invoice-line"></div>


    <!-- ================= THÔNG TIN HÓA ĐƠN ================= -->

    <c:if test="${not empty list}">

        <div class="invoice-info">

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

                        Khách lẻ

                    </c:when>

                    <c:otherwise>

                        <c:out value="${list[0].tenKH}"/>

                    </c:otherwise>

                </c:choose>

            </p>

        </div>

    </c:if>


    <div class="invoice-line"></div>


    <!-- ================= SẢN PHẨM ================= -->

    <c:forEach items="${list}"
               var="hd"
               varStatus="st">


        <div class="receipt-item">


            <div class="receipt-name">

                ${st.count}.
                <c:out value="${hd.tenSP}"/>

            </div>


            <div class="receipt-info">

                <span>
                    Màu:
                    <b>
                        <c:out value="${hd.tenMau}"/>
                    </b>
                </span>


                <span>
                    Size:
                    <b>
                        <c:out value="${hd.tenSize}"/>
                    </b>
                </span>

            </div>


            <div class="receipt-price">


                <span>

                    <c:out value="${hd.soLuong}"/>

                    ×

                    <fmt:formatNumber
                            value="${hd.donGia}"
                            pattern="#,##0"/>

                </span>


                <strong>

                    <fmt:formatNumber
                            value="${hd.thanhTien}"
                            pattern="#,##0"/>

                </strong>


            </div>


        </div>


    </c:forEach>


    <div class="invoice-line"></div>


    <!-- ================= TỔNG TIỀN ================= -->

    <c:if test="${not empty list}">


        <div class="receipt-summary">


            <div class="receipt-total-line">

                <span>
                    Giảm giá
                </span>


                <strong>

                    <fmt:formatNumber
                            value="${list[0].tienGiam}"
                            pattern="#,##0"/>

                    VNĐ

                </strong>

            </div>


            <div class="receipt-total-line total">

                <span>
                    TỔNG TIỀN
                </span>


                <strong>

                    <fmt:formatNumber
                            value="${list[0].tongTien}"
                            pattern="#,##0"/>

                    VNĐ

                </strong>

            </div>


            <div class="receipt-total-line">

                <span>
                    Tiền khách đưa
                </span>


                <strong>

                    <fmt:formatNumber
                            value="${list[0].tienKhachDua}"
                            pattern="#,##0"/>

                    VNĐ

                </strong>

            </div>


            <div class="receipt-total-line">

                <span>
                    Tiền thừa
                </span>


                <strong>

                    <fmt:formatNumber
                            value="${list[0].tienThua}"
                            pattern="#,##0"/>

                    VNĐ

                </strong>

            </div>


        </div>


    </c:if>


    <div class="invoice-line"></div>


    <!-- ================= FOOTER ================= -->

    <div class="receipt-footer">

        <strong>
            CẢM ƠN QUÝ KHÁCH
        </strong>

        <p>
            Hẹn gặp lại!
        </p>

    </div>


    <!-- ================= BUTTON ================= -->

    <div class="invoice-action">

        <button type="button"
                onclick="window.print()">

            In hóa đơn

        </button>


        <a href="${pageContext.request.contextPath}/banhang">

            Quay lại bán hàng

        </a>

    </div>


</div>


</body>

</html>