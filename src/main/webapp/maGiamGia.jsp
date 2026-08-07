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

    <title>Quản lý mã giảm giá</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=101">

</head>

<body>

<%@ include file="menu.jsp" %>


<div class="mgg-container">

    <h2>QUẢN LÝ MÃ GIẢM GIÁ</h2>


    <div class="mgg-header-buttons">

        <a href="${pageContext.request.contextPath}/addMaGiamGia"
           class="mgg-add-btn">

            + Thêm mã giảm giá

        </a>


        <a href="${pageContext.request.contextPath}/home"
           class="back">

            Quay lại

        </a>

    </div>


    <!-- ================= THÔNG BÁO ================= -->

    <c:if test="${not empty message}">

        <div class="alert alert-success">
            ${message}
        </div>

    </c:if>


    <c:if test="${not empty error}">

        <div class="alert alert-error">
            ${error}
        </div>

    </c:if>


    <!-- ================= BẢNG ================= -->

    <table class="mgg-table">

        <thead>

        <tr>

            <th>Mã</th>

            <th>Code</th>

            <th>Tên mã</th>

            <th>Giảm</th>

            <th>Điểm cần</th>

            <th>Bắt đầu</th>

            <th>Kết thúc</th>

            <th>Số lượng</th>

            <th>Trạng thái</th>

            <th>Thao tác</th>

        </tr>

        </thead>


        <tbody>

        <c:choose>

            <c:when test="${not empty listMaGiamGia}">

                <c:forEach items="${listMaGiamGia}"
                           var="m">

                    <tr>

                        <td>
                            ${m.maMGG}
                        </td>


                        <td class="mgg-code">

                            <strong>
                                ${m.maCode}
                            </strong>

                        </td>


                        <td class="mgg-name">

                            ${m.tenMGG}

                        </td>


                        <td class="mgg-percent">

                            <strong>
                                ${m.phanTramGiam}%
                            </strong>

                        </td>


                        <td>

                            ${m.diemCan}

                        </td>


                        <td class="mgg-date">

                            <fmt:formatDate
                                    value="${m.ngayBatDau}"
                                    pattern="dd/MM/yyyy"/>

                        </td>


                        <td class="mgg-date">

                            <fmt:formatDate
                                    value="${m.ngayKetThuc}"
                                    pattern="dd/MM/yyyy"/>

                        </td>


                        <td>

                            ${m.soLuong}

                        </td>


                        <td class="mgg-status">

                            <c:choose>

                                <c:when test="${m.trangThai}">

                                    <span class="status-active">
                                        Đang hoạt động
                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="status-inactive">
                                        Ngừng hoạt động
                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>


                        <td class="mgg-action">

                            <a href="${pageContext.request.contextPath}/editMaGiamGia?maMGG=${m.maMGG}"
                               class="mgg-edit-btn">

                                Sửa

                            </a>

                        </td>

                    </tr>

                </c:forEach>

            </c:when>


            <c:otherwise>

                <tr>

                    <td colspan="10"
                        class="mgg-empty">

                        Chưa có mã giảm giá nào.

                    </td>

                </tr>

            </c:otherwise>

        </c:choose>

        </tbody>

    </table>

</div>

</body>

</html>