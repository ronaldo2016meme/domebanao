<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý nhân viên</title>

<link rel="stylesheet" href="./css/style.css">

</head>
<body>

<div class="employee-container">

<h2>Quản lý nhân viên</h2>

<div class="top-action">
    <a href="${pageContext.request.contextPath}/addEmployee" class="btn-add">
        Thêm nhân viên
    </a>
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
    <td>${nv.maTrangThai}</td>
    <td>${nv.chucVu}</td>

<td>

<a href="editEmployee?id=${nv.maNV}">
    <button class="btn-edit">Sửa</button>
</a>

<a href="deleteEmployee?id=${nv.maNV}">
    <button class="btn-delete">Xóa</button>
</a>

</td>

</tr>

</c:forEach>

</table>

</div>

</body>
</html>