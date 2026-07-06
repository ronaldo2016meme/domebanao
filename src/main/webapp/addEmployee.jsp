<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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

        <label>Họ tên</label>
        <input type="text" name="hoTen" required>

        <label>Ngày sinh</label>
        <input type="date" name="ngaySinh" required>

        <label>Giới tính</label>
        <select name="gioiTinh">
            <option value="Nam">Nam</option>
            <option value="Nữ">Nữ</option>
        </select>

        <label>Quốc tịch</label>
        <input type="text" name="quocTich" value="Việt Nam" required>

        <label>Quê quán</label>
        <input type="text" name="queQuan" required>

        <label>Nơi thường trú</label>
        <input type="text" name="noiThuongTru" required>

        <label>Số điện thoại</label>
        <input type="text"
               name="sdt"
               required
               pattern="^(0[0-9]{9}|\\+84[0-9]{9})$"
               title="Bắt đầu bằng 0 hoặc +84">

        <label>Email</label>
        <input type="email" name="email" required>

        <label>CCCD</label>
        <input type="text"
               name="cccd"
               required
               pattern="[0-9]{12}"
               maxlength="12"
               title="CCCD gồm đúng 12 số">

        <label>Trạng thái</label>
        <select name="maTrangThai">
            <option value="TTNV01">Đang làm</option>
            <option value="TTNV02">Nghỉ việc</option>
        </select>

        <label>Chức vụ</label>
        <select name="maChucVu">
            <option value="CV01">Quản lý</option>
            <option value="CV02">Nhân viên</option>
        </select>

        <button type="submit" class="btn">
            Thêm nhân viên
        </button>

    </form>

    <a href="employee" class="back">← Quay lại</a>

</div>

</body>
</html>