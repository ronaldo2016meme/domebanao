<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mã giảm giá</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>

<%@ include file="menu.jsp"%>

<div class="container">

<h2>THÊM MÃ GIẢM GIÁ</h2>

<form action="addMaGiamGia" method="post">

<table>

<tr>
    <td>Mã code</td>
    <td>
        <input type="text"
               name="maCode"
               required>
    </td>
</tr>

<tr>
    <td>Tên mã</td>
    <td>
        <input type="text"
               name="tenMa"
               required>
    </td>
</tr>

<tr>
    <td>Điểm cần đổi</td>
    <td>
        <input type="number"
               name="diemCan"
               required>
    </td>
</tr>

<tr>
    <td>Phần trăm giảm</td>
    <td>
        <input type="number"
               name="phanTramGiam"
               required>
    </td>
</tr>

<tr>
    <td>Giảm tối đa</td>
    <td>
        <input type="number"
               name="giamToiDa"
               required>
    </td>
</tr>

<tr>
    <td>Ngày bắt đầu</td>
    <td>
        <input type="date"
               name="ngayBatDau"
               required>
    </td>
</tr>

<tr>
    <td>Ngày kết thúc</td>
    <td>
        <input type="date"
               name="ngayKetThuc"
               required>
    </td>
</tr>

<tr>
    <td>Số lượng</td>
    <td>
        <input type="number"
               name="soLuong"
               required>
    </td>
</tr>

<tr>
    <td>Trạng thái</td>
    <td>
        <select name="trangThai">
            <option value="true">Hoạt động</option>
            <option value="false">Ngừng</option>
        </select>
    </td>
</tr>

<tr>
    <td colspan="2">

        <button class="btn">
            Thêm
        </button>

        <a class="btn"
           href="maGiamGia">
            Quay lại
        </a>

    </td>
</tr>

</table>

</form>

</div>

</body>
</html>