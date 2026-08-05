<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">

    <a href="${pageContext.request.contextPath}/profile">
        Thông tin cá nhân
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        Quản lý sản phẩm
    </a>

    <%-- Chỉ quản lý mới thấy quản lý nhân viên --%>
    <c:if test="${sessionScope.user.maRole == 'R01'}">
        <a href="${pageContext.request.contextPath}/employee">
            Quản lý nhân viên
        </a>
    </c:if>

    <a href="${pageContext.request.contextPath}/khachhang">
        Quản lý khách hàng
    </a>

    <a href="${pageContext.request.contextPath}/banhang">
        Bán hàng
    </a>

    <a href="${pageContext.request.contextPath}/danhSachHoaDon">
        Hóa đơn
    </a>

    <%-- Chỉ quản lý mới thấy doanh thu --%>
    <c:if test="${sessionScope.user.maRole == 'R01'}">
        <a href="${pageContext.request.contextPath}/thongke">
            Doanh thu
        </a>
    </c:if>

</div>