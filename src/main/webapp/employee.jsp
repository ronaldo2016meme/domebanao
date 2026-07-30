<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý nhân viên</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=30">

</head>
<body>
<%@ include file="menu.jsp" %>

<div class="employee-container">

<h2>Quản lý nhân viên</h2>

<div class="top-action">
    <a href="${pageContext.request.contextPath}/addEmployee" class="btn-add">
        Thêm nhân viên
    </a>

<form action="${pageContext.request.contextPath}/createAccount"
      method="get"
      class="create-account-form">

    <select name="maNV" class="select-employee" required>
        <option value="">-- Chọn nhân viên --</option>

        <c:forEach items="${list}" var="nv">
            <c:if test="${!nv.coTaiKhoan}">
                <option value="${nv.maNV}">
                    ${nv.maNV} - ${nv.hoTen}
                </option>
            </c:if>
        </c:forEach>
    </select>

    <button type="submit" class="btn-add">
        Tạo tài khoản
    </button>

</form>

    <a href="home" class="back">Quay lại</a>

</div>

<table class="employee-table">

<tr>
    <th>Mã NV</th>
    <th>Họ tên</th>
    <th>Giới tính</th>
    <th>SĐT</th>
    <th>Email</th>
    <th>CCCD</th>
    <th>Trạng thái</th>
    <th>Chức vụ</th>
    <th>Tài khoản</th>
    <th>Chức năng</th>
</tr>

<c:forEach items="${list}" var="nv">

<tr>

    <td>${nv.maNV}</td>
    <td>${nv.hoTen}</td>
    <td>${nv.gioiTinh}</td>
    <td>${nv.sdt}</td>
    <td>${nv.email}</td>
    <td>${nv.cccd}</td>
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
    <td>${nv.chucVu}</td>
    <td>
        <c:choose>
            <c:when test="${nv.coTaiKhoan}">
                Đã có
            </c:when>
            <c:otherwise>
                Chưa có
            </c:otherwise>
        </c:choose>
    </td>

<td>

<a href="editEmployee?id=${nv.maNV}">
    <button class="btn-edit">Sửa</button>
</a>

</td>

</tr>

</c:forEach>

</table>

</div>

</body>
</html>