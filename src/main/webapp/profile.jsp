<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin cá nhân</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?v=2">

</head>
<body>


<div class="profile-container">

    <h2 class="profile-title">Thông tin cá nhân</h2>

    <table class="profile-table">
        <tr>
            <th>Mã nhân viên</th>
            <td>${nv.maNV}</td>
        </tr>

        <tr>
            <th>Họ tên</th>
            <td>${nv.hoTen}</td>
        </tr>

        <tr>
            <th>Ngày sinh</th>
            <td>${nv.ngaySinh}</td>
        </tr>

        <tr>
            <th>Giới tính</th>
            <td>${nv.gioiTinh}</td>
        </tr>

        <tr>
            <th>Quốc tịch</th>
            <td>${nv.quocTich}</td>
        </tr>

        <tr>
            <th>Quê quán</th>
            <td>${nv.queQuan}</td>
        </tr>

        <tr>
            <th>Nơi thường trú</th>
            <td>${nv.noiThuongTru}</td>
        </tr>

        <tr>
            <th>Số điện thoại</th>
            <td>${nv.sdt}</td>
        </tr>

        <tr>
            <th>Email</th>
            <td>${nv.email}</td>
        </tr>

        <tr>
            <th>CCCD</th>
            <td>${nv.cccd}</td>
        </tr>

        <tr>
            <th>Trạng thái</th>
            <td>
                <c:choose>
                    <c:when test="${nv.maTrangThai == 'TTNV01'}">
                        Đang làm việc
                    </c:when>
                    <c:when test="${nv.maTrangThai == 'TTNV02'}">
                        Đã nghỉ việc
                    </c:when>
                    <c:otherwise>
                        Không xác định
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <a href="home" class="profile-btn">Quay lại</a>

</div>

</body>
</html>