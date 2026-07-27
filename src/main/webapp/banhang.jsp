<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Bán hàng</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=30">

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

                    <td>${sp.maSP}</td>

                    <td>${sp.tenSP}</td>

                    <td>
                        <select name="maMau"
                                form="formThem${sp.maSP}"
                                required>

                            <option value="">
                                -- Chọn màu --
                            </option>

                            <c:forEach items="${listMau}" var="mau">
                                <option value="${mau.maMau}">
                                    ${mau.tenMau}
                                </option>
                            </c:forEach>

                        </select>
                    </td>

                    <td>
                        <select name="maSize"
                                form="formThem${sp.maSP}"
                                required>

                            <option value="">
                                -- Chọn size --
                            </option>

                            <c:forEach items="${listSize}" var="size">
                                <option value="${size.maSize}">
                                    ${size.tenSize}
                                </option>
                            </c:forEach>

                        </select>
                    </td>

                    <td>${sp.soLuongTon}</td>

                    <td>
                        <form id="formThem${sp.maSP}"
                              action="banhang"
                              method="post">

                            <input type="hidden"
                                   name="maSP"
                                   value="${sp.maSP}">

                            <button class="bh-btn"
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
                            <div class="qty-box">

                                <form action="capNhatGioHang" method="post">
                                    <input type="hidden" name="maSPCT" value="${g.maSPCT}">
                                    <input type="hidden" name="action" value="giam">
                                    <button type="submit" class="bh-btn">-</button>
                                </form>

                                <span>${g.soLuong}</span>

                                <form action="capNhatGioHang" method="post">
                                    <input type="hidden" name="maSPCT" value="${g.maSPCT}">
                                    <input type="hidden" name="action" value="tang">
                                    <button type="submit" class="bh-btn">+</button>
                                </form>

                            </div>
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

            <label>Số điện thoại khách hàng</label>

            <input
                    type="text"
                    name="soDienThoai"
                    placeholder="Nhập số điện thoại (để trống nếu khách lẻ)">

            <br>

            <label>Tiền khách đưa</label>

            <input
                    type="text"
                    name="tienKhachDua"
                    pattern="[0-9]+"
                    inputmode="numeric"
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