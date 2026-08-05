<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Quản lý sản phẩm</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=42">

</head>

<body>

<%@ include file="menu.jsp" %>

<div class="product-container">

    <h2>Quản lý sản phẩm</h2>

    <!-- THÔNG BÁO THÀNH CÔNG -->
    <c:if test="${not empty messageCode}">

        <div class="alert-success">

            <c:choose>

                <c:when test="${messageCode == 'THEM_SAN_PHAM_THANH_CONG'}">
                    Thêm sản phẩm thành công.
                </c:when>

                <c:when test="${messageCode == 'CAP_NHAT_SAN_PHAM_THANH_CONG'}">
                    Cập nhật sản phẩm thành công.
                </c:when>

                <c:otherwise>
                    Thao tác thành công.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <!-- THÔNG BÁO LỖI -->
    <c:if test="${not empty errorCode}">

        <div class="alert-error">

            <c:choose>

                <c:when test="${errorCode == 'THIEU_THONG_TIN'}">
                    Vui lòng nhập đầy đủ thông tin bắt buộc.
                </c:when>

                <c:when test="${errorCode == 'THIEU_MA_SAN_PHAM'}">
                    Không tìm thấy mã sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'MA_SAN_PHAM_KHONG_HOP_LE'}">
                    Mã sản phẩm không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'KHONG_TIM_THAY_SAN_PHAM'}">
                    Không tìm thấy sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'GIA_BAN_KHONG_HOP_LE'}">
                    Giá bán phải lớn hơn hoặc bằng 0.
                </c:when>

                <c:when test="${errorCode == 'THEM_SAN_PHAM_THAT_BAI'}">
                    Thêm sản phẩm thất bại.
                </c:when>

                <c:when test="${errorCode == 'CAP_NHAT_SAN_PHAM_THAT_BAI'}">
                    Cập nhật sản phẩm thất bại.
                </c:when>

                <c:when test="${errorCode == 'LOI_THEM_SAN_PHAM'}">
                    Có lỗi xảy ra khi thêm sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'LOI_CAP_NHAT_SAN_PHAM'}">
                    Có lỗi xảy ra khi cập nhật sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'LOI_TAI_SAN_PHAM'}">
                    Có lỗi xảy ra khi tải thông tin sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'LOI_TAI_DANH_SACH_SAN_PHAM'}">
                    Có lỗi xảy ra khi tải danh sách sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'LOI_LUU_SAN_PHAM'}">
                    Có lỗi xảy ra khi lưu sản phẩm.
                </c:when>

                <c:when test="${errorCode == 'THIEU_HANH_DONG'}">
                    Không xác định được thao tác cần thực hiện.
                </c:when>

                <c:when test="${errorCode == 'THAO_TAC_KHONG_HOP_LE'}">
                    Thao tác sản phẩm không hợp lệ.
                </c:when>

                <c:when test="${errorCode == 'DU_LIEU_KHONG_HOP_LE'}">
                    Dữ liệu nhập không hợp lệ.
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra. Vui lòng thử lại.
                </c:otherwise>

            </c:choose>

        </div>

    </c:if>

    <div class="top-action">

        <a href="${pageContext.request.contextPath}/addsanpham"
           class="btn">
            Thêm sản phẩm
        </a>

        <a href="${pageContext.request.contextPath}/home"
           class="back">
            Quay lại
        </a>

    </div>

    <div class="product-table-wrap">

        <table class="product-table">

            <thead>

            <tr>
                <th>Mã SP</th>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Danh mục</th>
                <th>Nhà cung cấp</th>
                <th>Trạng thái</th>
                <th>Giá bán</th>
                <th>Mô tả</th>
                <th>Ngày tạo</th>
                <th>Ngày cập nhật</th>
                <th>Chức năng</th>
            </tr>

            </thead>

            <tbody>

            <c:choose>

                <c:when test="${empty list}">

                    <tr>

                        <td colspan="11"
                            style="
                                text-align:center;
                                padding:30px;
                                font-weight:700;
                            ">
                            Chưa có sản phẩm nào.
                        </td>

                    </tr>

                </c:when>

                <c:otherwise>

                    <c:forEach var="sp"
                               items="${list}">

                        <tr>

                            <td>
                                <c:out value="${sp.maSP}"/>
                            </td>

                            <td>

                                <c:choose>

                                    <c:when test="${not empty sp.anh}">

                                        <img
                                            src="${pageContext.request.contextPath}/image/${sp.anh}"
                                            alt="${sp.tenSP}"
                                            class="product-image">

                                    </c:when>

                                    <c:otherwise>

                                        <span class="no-image">
                                            Chưa có ảnh
                                        </span>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                            <td class="product-name">
                                <c:out value="${sp.tenSP}"/>
                            </td>

                            <td>
                                <c:out value="${sp.tenDanhMuc}"/>
                            </td>

                            <td>
                                <c:out value="${sp.tenNCC}"/>
                            </td>

                            <td>

                                <span class="product-status">
                                    <c:out value="${sp.tenTrangThai}"/>
                                </span>

                            </td>

                            <td class="product-price">

                                <c:choose>

                                    <c:when test="${sp.giaBan > 0}">

                                        <fmt:formatNumber
                                                value="${sp.giaBan}"
                                                pattern="#,##0"/>

                                        VNĐ

                                    </c:when>

                                    <c:otherwise>

                                        <span class="no-price">
                                            Chưa có giá
                                        </span>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                            <td>
                                <c:out value="${sp.moTa}"/>
                            </td>

                            <td>
                                <c:out value="${sp.ngayTao}"/>
                            </td>

                            <td>
                                <c:out value="${sp.ngayCapNhat}"/>
                            </td>

                            <td class="product-actions">

                                <a href="${pageContext.request.contextPath}/sanphamchitiet?maSP=${sp.maSP}"
                                   class="btn-detail">
                                    Chi tiết
                                </a>

                                <a href="${pageContext.request.contextPath}/editsanpham?id=${sp.maSP}"
                                   class="btn-edit">
                                    Sửa
                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </c:otherwise>

            </c:choose>

            </tbody>

        </table>

    </div>

</div>

</body>

</html>