<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Bán hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<h2>BÁN HÀNG</h2>

<a href="${pageContext.request.contextPath}/index.jsp">
    <button class="bh-btn" type="button">
        Quay lại trang chủ
    </button>
</a>

<c:if test="${not empty message}">
    <p style="color:green;text-align:center">${message}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red;text-align:center">${error}</p>
</c:if>

<div class="bh-container">

    <!-- ================= DANH SÁCH SẢN PHẨM ================= -->

    <div class="bh-left">

        <h3>Danh sách sản phẩm</h3>

        <table class="bh-table">

            <tr>

                <th>Mã</th>
                <th>Tên sản phẩm</th>
                <th>Màu</th>
                <th>Size</th>
                <th>Tồn</th>
                <th></th>

            </tr>

            <c:forEach items="${listSP}" var="sp">

                <tr>

                    <td>${sp.maSPCT}</td>

                    <td>${sp.tenSP}</td>

                    <td>${sp.tenMau}</td>

                    <td>${sp.tenSize}</td>

                    <td>${sp.soLuongTon}</td>

                    <td>

                        <form action="banhang" method="post">

                            <input
                                    type="hidden"
                                    name="maSPCT"
                                    value="${sp.maSPCT}">

                            <button
                                    class="bh-btn"
                                    type="submit">

                                Thêm

                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>

        </table>

    </div>

    <!-- ================= GIỎ HÀNG ================= -->

    <div class="bh-right">

        <h3>Giỏ hàng</h3>

        <table class="bh-table">

            <tr>

                <th>Sản phẩm</th>
                <th>SL</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>

            </tr>

            <c:set var="tongTien" value="0"/>

            <c:forEach items="${sessionScope.gioHang}" var="g">

                <tr>

                    <td>

                        ${g.tenSP}

                        <br>

                        ${g.tenMau} - ${g.tenSize}

                    </td>

                    <td>

                        <form action="capNhatGioHang"
                              method="post"
                              style="display:inline;">

                            <input
                                    type="hidden"
                                    name="maSPCT"
                                    value="${g.maSPCT}">

                            <input
                                    type="hidden"
                                    name="action"
                                    value="giam">

                            <button class="bh-btn">-</button>

                        </form>

                        <strong>

                            ${g.soLuong}

                        </strong>

                        <form action="capNhatGioHang"
                              method="post"
                              style="display:inline;">

                            <input
                                    type="hidden"
                                    name="maSPCT"
                                    value="${g.maSPCT}">

                            <input
                                    type="hidden"
                                    name="action"
                                    value="tang">

                            <button class="bh-btn">+</button>

                        </form>

                    </td>

                    <td>

                        ${g.donGia}

                    </td>

                    <td>

                        ${g.thanhTien}

                    </td>

                </tr>

                <c:set
                        var="tongTien"
                        value="${tongTien + g.thanhTien}"/>

            </c:forEach>

        </table>

        <div class="bh-total">

            Tổng tiền:
            ${tongTien}
            VNĐ

        </div>

        <br>

        <form action="thanhToan" method="post">

            <label>Khách hàng (Mã KH)</label>

            <input
                    type="number"
                    name="maKH"
                    required>

            <br>

            <label>Tiền khách đưa</label>

            <input
                    type="number"
                    name="tienKhachDua"
                    required>

            <input
                    type="hidden"
                    name="tongTien"
                    value="${tongTien}">

            <br>

            <button
                    class="bh-btn"
                    type="submit">

                Thanh toán

            </button>

        </form>

    </div>

</div>

</body>
</html>