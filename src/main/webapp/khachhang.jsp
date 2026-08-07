<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Quản lý khách hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=20">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="customer-container">

    <h2>QUẢN LÝ KHÁCH HÀNG</h2>

    <!-- Thanh chức năng -->
    <div class="top-action">

        <!-- Tìm kiếm -->
        <form action="${pageContext.request.contextPath}/khachhang"
              method="get"
              class="customer-search">

            <input type="text"
                   name="keyword"
                   value="${keyword}"
                   placeholder="Tìm khách hàng...">

            <button type="submit">
                Tìm
            </button>

        </form>

        </form>

        <!-- Nhóm nút -->
        <div class="customer-actions">

            <a href="${pageContext.request.contextPath}/addKhachHang?returnUrl=khachhang"
               class="btn-add">
                + Thêm khách hàng
            </a>

            <a href="${pageContext.request.contextPath}/home"
               class="back">
                Quay lại
            </a>

        </div>

    </div>


    <!-- Bảng khách hàng -->
    <div class="customer-table-wrap">

        <table class="customer-table">

            <thead>

            <tr>
                <th>Mã</th>
                <th>Tên khách hàng</th>
                <th>Số điện thoại</th>
                <th>Địa chỉ</th>
                <th>Điểm tích lũy</th>
                <th>Chức năng</th>
            </tr>

            </thead>

            <tbody>

            <c:choose>

                <c:when test="${empty list}">

                    <tr>

                        <td colspan="6"
                            class="customer-empty">

                            Không tìm thấy khách hàng.

                        </td>

                    </tr>

                </c:when>

                <c:otherwise>

                    <c:forEach var="kh"
                               items="${list}">

                        <tr>

                            <td>
                                <c:out value="${kh.maKH}"/>
                            </td>

                            <td class="customer-name">
                                <c:out value="${kh.hoTen}"/>
                            </td>

                            <td class="customer-phone">
                                <c:out value="${kh.sdt}"/>
                            </td>

                            <td>
                                <c:out value="${kh.diaChi}"/>
                            </td>

                            <td class="customer-point">

                                <strong>
                                    <c:out value="${kh.diemTichLuy}"/>
                                </strong>

                                điểm

                            </td>

                            <td>

                                <a href="${pageContext.request.contextPath}/editKhachHang?maKH=${kh.maKH}"
                                   class="btn-edit">
                                    Sửa
                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </c:otherwise>

            </c:choose>

            </tbody>

        </table>

    </div>

</div>

</body>

</html>