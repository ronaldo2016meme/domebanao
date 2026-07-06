<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa nhân viên</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

<h2>Sửa nhân viên</h2>

<form action="editEmployee" method="post">

<input type="hidden" name="maNV" value="${nv.maNV}">

<label>Họ tên</label>
<input type="text" name="hoTen" value="${nv.hoTen}">

<label>SĐT</label>
<input type="text" name="sdt" value="${nv.sdt}">

<label>Email</label>
<input type="text" name="email" value="${nv.email}">

<label>CCCD</label>
<input type="text" name="cccd" value="${nv.cccd}">

<label>Trạng thái</label>

<select name="maTrangThai">

<option value="TTNV01">Đang làm</option>

<option value="TTNV02">Nghỉ việc</option>

</select>

<button class="btn">Cập nhật</button>

</form>

</div>

</body>
</html>