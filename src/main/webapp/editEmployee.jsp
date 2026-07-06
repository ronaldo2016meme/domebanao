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
<input type="text" name="hoTen" value="${nv.hoTen}" required>

<label>Ngày sinh</label>
<input type="date" name="ngaySinh" value="${nv.ngaySinh}" required>

<label>Giới tính</label>
<select name="gioiTinh">
    <option value="Nam" ${nv.gioiTinh=='Nam'?'selected':''}>Nam</option>
    <option value="Nữ" ${nv.gioiTinh=='Nữ'?'selected':''}>Nữ</option>
</select>

<label>Quốc tịch</label>
<input type="text" name="quocTich" value="${nv.quocTich}">

<label>Quê quán</label>
<input type="text" name="queQuan" value="${nv.queQuan}">

<label>Nơi thường trú</label>
<input type="text" name="noiThuongTru" value="${nv.noiThuongTru}">

<label>SĐT</label>
<input type="text" name="sdt" value="${nv.sdt}" required>

<label>Email</label>
<input type="email" name="email" value="${nv.email}" required>

<label>CCCD</label>
<input type="text" name="cccd" value="${nv.cccd}" required>

<label>Chức vụ</label>
<select name="maRole">
    <option value="R01" ${nv.maRole=='R01'?'selected':''}>Quản lý</option>
    <option value="R02" ${nv.maRole=='R02'?'selected':''}>Nhân viên</option>
</select>

<label>Trạng thái</label>
<select name="maTrangThai">
    <option value="TTNV01" ${nv.maTrangThai=='TTNV01'?'selected':''}>Đang làm</option>
    <option value="TTNV02" ${nv.maTrangThai=='TTNV02'?'selected':''}>Nghỉ việc</option>
</select>

<button type="submit" class="btn">Cập nhật</button>

</form>

</div>

</body>
</html>