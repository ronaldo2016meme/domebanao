<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa nhân viên</title>

<link rel="stylesheet" href="css/style.css">

<script>
function checkAge() {

    let birth = new Date(document.getElementById("ngaySinh").value);
    let today = new Date();

    let age = today.getFullYear() - birth.getFullYear();

    if (today.getMonth() < birth.getMonth() ||
       (today.getMonth() == birth.getMonth() &&
        today.getDate() < birth.getDate())) {
        age--;
    }

    if (age < 16) {
        alert("Nhân viên phải từ 16 tuổi trở lên!");
        return false;
    }

    return true;
}
</script>

</head>
<body>

<div class="container">

<h2>Sửa nhân viên</h2>

<form action="editEmployee" method="post" onsubmit="return checkAge()">

<input type="hidden" name="maNV" value="${nv.maNV}">

<label>Họ tên</label>
<input type="text"
       name="hoTen"
       value="${nv.hoTen}"
       required>

<label>Ngày sinh</label>
<input type="date"
       id="ngaySinh"
       name="ngaySinh"
       value="${nv.ngaySinh}"
       required>

<label>Giới tính</label>
<select name="gioiTinh">
    <option value="Nam" ${nv.gioiTinh=='Nam'?'selected':''}>Nam</option>
    <option value="Nữ" ${nv.gioiTinh=='Nữ'?'selected':''}>Nữ</option>
</select>

<label>Quốc tịch</label>
<input type="text"
       name="quocTich"
       value="${nv.quocTich}"
       required>

<label>Quê quán</label>
<input type="text"
       name="queQuan"
       value="${nv.queQuan}"
       required>

<label>Nơi thường trú</label>
<input type="text"
       name="noiThuongTru"
       value="${nv.noiThuongTru}"
       required>

<label>SĐT</label>
<input type="text"
       name="sdt"
       value="${nv.sdt}"
       required
       pattern="^(0[0-9]{9}|\\+84[0-9]{9})$"
       title="SĐT phải bắt đầu bằng 0 hoặc +84">

<label>Email</label>
<input type="email"
       name="email"
       value="${nv.email}"
       required>

<label>CCCD</label>
<input type="text"
       name="cccd"
       value="${nv.cccd}"
       required
       pattern="[0-9]{12}"
       maxlength="12"
       title="CCCD gồm đúng 12 số">

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

<button type="submit" class="btn">
    Cập nhật
</button>

</form>

<a href="employee" class="back"> Quay lại</a>

</div>

</body>
</html>