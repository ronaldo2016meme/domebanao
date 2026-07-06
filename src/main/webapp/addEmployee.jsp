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

        <label>Số điện thoại</label>
        <input type="text" name="sdt" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>CCCD</label>
        <input type="text" name="cccd" required>

        <label>Trạng thái</label>
        <select name="maTrangThai">
            <option value="TTNV01">Đang làm</option>
            <option value="TTNV02">Nghỉ việc</option>
        </select>

        <button type="submit" class="btn">Thêm nhân viên</button>

    </form>

    <a href="employee" class="back">← Quay lại</a>

</div>

</body>
</html>