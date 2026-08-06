<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý mã giảm giá</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>

<%@ include file="menu.jsp"%>

<div class="container">

    <h2>QUẢN LÝ MÃ GIẢM GIÁ</h2>

    <a href="addMaGiamGia" class="btn">+ Thêm mã giảm giá</a>

    <br><br>

    <table class="table">

        <tr>
            <th>Mã</th>
            <th>Tên</th>
            <th>Điểm đổi</th>
            <th>Giảm (%)</th>
            <th>Giảm tối đa</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Số lượng</th>
            <th>Trạng thái</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach items="${list}" var="m">

            <tr>

                <td>${m.maCode}</td>

                <td>${m.tenMa}</td>

                <td>${m.diemCan}</td>

                <td>${m.phanTramGiam}%</td>

                <td>${m.giamToiDa}</td>

                <td>${m.ngayBatDau}</td>

                <td>${m.ngayKetThuc}</td>

                <td>${m.soLuong}</td>

                <td>
                    <c:choose>
                        <c:when test="${m.trangThai}">
                            Hoạt động
                        </c:when>
                        <c:otherwise>
                            Ngừng
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <a class="btn"
                       href="editMaGiamGia?id=${m.maGiamGia}">
                        Sửa
                    </a>
                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>