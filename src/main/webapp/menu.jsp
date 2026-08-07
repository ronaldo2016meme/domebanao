<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">

    <a href="${pageContext.request.contextPath}/profile">
        Thông tin cá nhân
    </a>

    <a href="${pageContext.request.contextPath}/sanpham">
        Sản phẩm
    </a>

    <a href="${pageContext.request.contextPath}/khachhang">
        Khách hàng
    </a>

    <a href="${pageContext.request.contextPath}/banhang">
        Bán hàng
    </a>

    <a href="${pageContext.request.contextPath}/danhSachHoaDon">
        Hóa đơn
    </a>

    <a href="${pageContext.request.contextPath}/maGiamGia">
        Giảm giá
    </a>

    <c:if test="${sessionScope.user != null && sessionScope.user.maRole == 'R01'}">

        <div class="menu-dropdown">

            <button type="button" class="menu-dropdown-btn">
                Quản trị
                <span>▼</span>
            </button>

            <div class="menu-dropdown-content">

                <a href="${pageContext.request.contextPath}/employee">
                    Nhân viên
                </a>

                <a href="${pageContext.request.contextPath}/thongke">
                    Doanh thu
                </a>

            </div>

        </div>

    </c:if>

</div>