<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý sản phẩm</title>

<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="employee-container">

    <h2>Quản lý sản phẩm</h2>

    <div class="search-bar">

        <input type="text" placeholder="Tìm kiếm sản phẩm..." name="keyword">

        <select name="danhMuc">
            <option value="">Tất cả danh mục</option>
            <c:forEach items="${listDanhMuc}" var="dm">
                <option value="${dm.maDanhMuc}">
                    ${dm.tenDanhMuc}
                </option>
            </c:forEach>
        </select>

        <select name="nhaCungCap">
            <option value="">Tất cả nhà cung cấp</option>
            <c:forEach items="${listNCC}" var="ncc">
                <option value="${ncc.maNCC}">
                    ${ncc.tenNCC}
                </option>
            </c:forEach>
        </select>

        <button class="btn">Tìm kiếm</button>

        <a href="addProduct.jsp">
            <button class="btn-add">Thêm sản phẩm</button>
        </a>

    </div>

    <table class="employee-table">

        <tr>
            <th>Mã SP</th>
            <th>Ảnh</th>
            <th>Tên sản phẩm</th>
            <th>Danh mục</th>
            <th>Nhà cung cấp</th>
            <th>Giá bán</th>
            <th>Mô tả</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Chức năng</th>
        </tr>

        <c:forEach items="${list}" var="sp">

            <tr>

                <td>${sp.maSP}</td>

                <td>
                    <img src="images/${sp.hinhAnh}"
                         width="60"
                         height="60">
                </td>

                <td>${sp.tenSP}</td>

                <td>${sp.tenDanhMuc}</td>

                <td>${sp.tenNCC}</td>

                <td>${sp.giaBan}</td>

                <td>${sp.moTa}</td>

                <td>${sp.ngayTao}</td>

                <td>${sp.ngayCapNhat}</td>

                <td>

                    <a href="editProduct?id=${sp.maSP}">
                        <button class="btn-edit">Sửa</button>
                    </a>

                    <a href="deleteProduct?id=${sp.maSP}"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">
                        <button class="btn-delete">Xóa</button>
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

</div>

</body>
</html>