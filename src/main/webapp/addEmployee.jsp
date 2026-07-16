<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm nhân viên</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="container">

    <h2>Thêm nhân viên</h2>

    <form action="addEmployee" method="post">
    <c:if test="${not empty error}">
        <div style="color:red; margin-bottom:10px;">
            ${error}
        </div>
    </c:if>

        <label>Họ tên</label>
        <input type="text"
               name="hoTen"
               required
               maxlength="100">

        <label>Ngày sinh</label>
        <input type="date"
               name="ngaySinh"
               required
               max="2009-12-31">

        <label>Giới tính</label>
        <select name="gioiTinh" required>
            <option value="Nam">Nam</option>
            <option value="Nữ">Nữ</option>
        </select>

        <label>Quốc tịch</label>
        <input type="text"
               name="quocTich"
               value="Việt Nam"
               required>

        <label>Quê quán</label>
        <input type="text"
               name="queQuan"
               list="dsTinh"
               placeholder="Nhập hoặc chọn tỉnh/thành"
               required>

        <datalist id="dsTinh">
            <option value="Hà Nội">
            <option value="Hồ Chí Minh">
            <option value="Đà Nẵng">
            <option value="Hải Phòng">
            <option value="Cần Thơ">
        </datalist>

        <label>Nơi thường trú</label>
        <input type="text"
               name="noiThuongTru"
               placeholder="Ví dụ: Cầu Giấy, Hà Nội"
               required>

        <label>Số điện thoại</label>
        <input type="text"
               name="sdt"
               required
               pattern="^(0[0-9]{9}|\\+84[0-9]{9})$"
               maxlength="13"
               title="Số điện thoại bắt đầu bằng 0 hoặc +84 và gồm 10 số">

        <label>Email</label>
        <input type="email"
               name="email"
               required
               maxlength="100"
               placeholder="example@gmail.com">

        <label>CCCD</label>
        <input type="text"
               name="cccd"
               required
               pattern="[0-9]{12}"
               maxlength="12"
               minlength="12"
               title="CCCD phải gồm đúng 12 số">

        <label>Trạng thái</label>
        <select name="maTrangThai" required>
            <option value="TTNV01">Đang làm</option>
            <option value="TTNV02">Nghỉ việc</option>
        </select>

        <label>Chức vụ</label>
        <select name="maRole" required>
            <option value="R01">Quản lý</option>
            <option value="R02">Nhân viên</option>
        </select>

        <button type="submit" class="btn">
            Thêm nhân viên
        </button>

    </form>

    <a href="employee" class="back"> Quay lại</a>

</div>

</body>
</html>