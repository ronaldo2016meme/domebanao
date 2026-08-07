<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Bán hàng</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css?v=101">

</head>

<body>

<%@ include file="menu.jsp" %>


<!-- ================= HEADER ================= -->

<div class="sale-header">

    <h2>BÁN HÀNG</h2>

    <a href="${pageContext.request.contextPath}/home"
       class="sale-back">
        Quay lại
    </a>

</div>


<!-- ================= THÔNG BÁO THÀNH CÔNG ================= -->

<c:if test="${not empty messageCode}">

    <div class="alert-message alert-success">

        <span class="alert-icon">
            ✓
        </span>

        <span>

            <c:choose>

                <c:when test="${messageCode == 'THEM_VAO_GIO_HANG'}">
                    Đã thêm sản phẩm vào giỏ hàng
                </c:when>

                <c:when test="${messageCode == 'TANG_SO_LUONG'}">
                    Đã tăng số lượng sản phẩm
                </c:when>

                <c:otherwise>
                    Thao tác thành công
                </c:otherwise>

            </c:choose>

        </span>

    </div>

</c:if>


<!-- ================= THÔNG BÁO LỖI ================= -->

<c:if test="${not empty errorCode}">

    <div class="alert-message alert-error">

        <span class="alert-icon">
            !
        </span>

        <span>

            <c:choose>

                <c:when test="${errorCode == 'CHUA_CHON_MAU_SIZE'}">
                    Vui lòng chọn đầy đủ màu và size
                </c:when>

                <c:when test="${errorCode == 'KHONG_CO_BIEN_THE'}">
                    Sản phẩm không có màu và size đã chọn
                </c:when>

                <c:when test="${errorCode == 'SAN_PHAM_HET_HANG'}">
                    Sản phẩm đã hết hàng
                </c:when>

                <c:when test="${errorCode == 'VUOT_QUA_TON_KHO'}">
                    Số lượng trong giỏ đã đạt mức tồn kho
                </c:when>

                <c:when test="${errorCode == 'DU_LIEU_KHONG_HOP_LE'}">
                    Dữ liệu sản phẩm không hợp lệ
                </c:when>

                <c:when test="${errorCode == 'LOI_THEM_SAN_PHAM'}">
                    Có lỗi xảy ra khi thêm sản phẩm
                </c:when>

                <c:when test="${errorCode == 'KHONG_DU_DIEM'}">
                    Khách hàng không đủ điểm để sử dụng mã giảm giá
                </c:when>

                <c:when test="${errorCode == 'VOUCHER_CAN_KHACH_HANG'}">
                    Phải chọn khách hàng trước khi sử dụng mã giảm giá
                </c:when>

                <c:when test="${errorCode == 'MA_GIAM_GIA_KHONG_TON_TAI'}">
                    Mã giảm giá không tồn tại
                </c:when>

                <c:when test="${errorCode == 'MA_GIAM_GIA_HET_HIEU_LUC'}">
                    Mã giảm giá đã hết hiệu lực
                </c:when>

                <c:when test="${errorCode == 'TIEN_KHACH_DUA_KHONG_DU'}">
                    Tiền khách đưa không đủ
                </c:when>

                <c:otherwise>
                    Có lỗi xảy ra
                </c:otherwise>

            </c:choose>

        </span>

    </div>

</c:if>


<!-- =========================================================
     BÁN HÀNG
========================================================= -->

<div class="bh-container">


    <!-- =====================================================
         DANH SÁCH SẢN PHẨM
    ====================================================== -->

    <div class="bh-left">

        <h3>
            Danh sách sản phẩm
        </h3>


        <table class="bh-table">

            <tr>

                <th>Mã</th>

                <th>Tên sản phẩm</th>

                <th>Màu</th>

                <th>Size</th>

                <th>Tồn</th>

                <th></th>

            </tr>


            <c:forEach items="${listSP}"
                       var="sp">

                <tr>

                    <td>
                        ${sp.maSP}
                    </td>


                    <td>
                        ${sp.tenSP}
                    </td>


                    <!-- ================= MÀU ================= -->

                    <td>

                        <select name="maMau"
                                id="mau-${sp.maSP}"
                                class="select-mau"
                                data-masp="${sp.maSP}"
                                form="formThem${sp.maSP}"
                                required>

                            <option value="">
                                -- Chọn màu --
                            </option>

                        </select>

                    </td>


                    <!-- ================= SIZE ================= -->

                    <td>

                        <select name="maSize"
                                id="size-${sp.maSP}"
                                class="select-size"
                                data-masp="${sp.maSP}"
                                form="formThem${sp.maSP}"
                                required>

                            <option value="">
                                -- Chọn size --
                            </option>

                        </select>

                    </td>


                    <!-- ================= TỒN ================= -->

                    <td>

                        <span id="ton-${sp.maSP}"
                              class="stock-value">
                            --
                        </span>

                    </td>


                    <!-- ================= THÊM ================= -->

                    <td>

                        <form id="formThem${sp.maSP}"
                              action="${pageContext.request.contextPath}/banhang"
                              method="post">

                            <input type="hidden"
                                   name="maSP"
                                   value="${sp.maSP}">

                            <button class="bh-btn"
                                    type="submit">

                                Thêm

                            </button>

                        </form>

                    </td>

                </tr>

            </c:forEach>

        </table>

    </div>


    <!-- =====================================================
         DATA BIẾN THỂ ẨN
    ====================================================== -->

    <div id="stock-data"
         style="display:none;">

        <c:forEach items="${listChiTiet}"
                   var="ct">

            <span class="stock-item"

                  data-masp="${ct.maSP}"

                  data-mamau="${ct.maMau}"

                  data-tenmau="${ct.tenMau}"

                  data-masize="${ct.maSize}"

                  data-tensize="${ct.tenSize}"

                  data-ton="${ct.soLuongTon}">
            </span>

        </c:forEach>

    </div>


    <!-- =====================================================
         GIỎ HÀNG
    ====================================================== -->

    <div class="bh-right">

        <h3>
            Giỏ hàng
        </h3>


        <table class="bh-table">

            <tr>

                <th>Sản phẩm</th>

                <th>SL</th>

                <th>Đơn giá</th>

                <th>Thành tiền</th>

            </tr>


            <c:set var="tongTien"
                   value="0"/>


            <c:forEach items="${sessionScope.gioHang}"
                       var="g">

                <tr>

                    <td>

                        ${g.tenSP}

                        <br>

                        ${g.tenMau}
                        -
                        ${g.tenSize}

                    </td>


                    <!-- ================= SỐ LƯỢNG ================= -->

                    <td>

                        <div class="qty-box">

                            <!-- GIẢM -->

                            <form action="${pageContext.request.contextPath}/capNhatGioHang"
                                  method="post">

                                <input type="hidden"
                                       name="maSPCT"
                                       value="${g.maSPCT}">

                                <input type="hidden"
                                       name="action"
                                       value="giam">

                                <button type="submit"
                                        class="bh-btn">
                                    -
                                </button>

                            </form>


                            <span>
                                ${g.soLuong}
                            </span>


                            <!-- TĂNG -->

                            <form action="${pageContext.request.contextPath}/capNhatGioHang"
                                  method="post">

                                <input type="hidden"
                                       name="maSPCT"
                                       value="${g.maSPCT}">

                                <input type="hidden"
                                       name="action"
                                       value="tang">

                                <button type="submit"
                                        class="bh-btn">
                                    +
                                </button>

                            </form>

                        </div>

                    </td>


                    <!-- ================= ĐƠN GIÁ ================= -->

                    <td>

                        <span class="don-gia-sp"
                              data-gia="${g.donGia}">

                            <fmt:formatNumber
                                    value="${g.donGia}"
                                    pattern="#,##0"/>

                        </span>

                        VNĐ

                    </td>


                    <!-- ================= THÀNH TIỀN ================= -->

                    <td>

                        <span class="thanh-tien-sp"
                              data-thanh-tien="${g.thanhTien}">

                            <fmt:formatNumber
                                    value="${g.thanhTien}"
                                    pattern="#,##0"/>

                        </span>

                        VNĐ

                    </td>

                </tr>


                <c:set var="tongTien"
                       value="${tongTien + g.thanhTien}"/>

            </c:forEach>

        </table>


        <!-- =================================================
             TỔNG TIỀN
        ================================================== -->

        <div class="bh-total">

            Tổng tiền:

            <span id="tongTien"
                  data-amount="${tongTien}">

                <fmt:formatNumber
                        value="${tongTien}"
                        pattern="#,##0"/>

            </span>

            VNĐ

        </div>


        <br>


        <!-- =================================================
             FORM THANH TOÁN
        ================================================== -->

        <form action="${pageContext.request.contextPath}/thanhToan"
              method="post">


            <!-- =================================================
                 KHÁCH HÀNG
            ================================================== -->

            <label for="soDienThoai">

                Số điện thoại khách hàng

            </label>


            <div class="customer-search-box">


                <input type="text"
                       id="soDienThoai"
                       name="soDienThoai"
                       maxlength="10"
                       inputmode="numeric"
                       autocomplete="off"
                       placeholder="Nhập số điện thoại">


                <a href="${pageContext.request.contextPath}/addKhachHang?returnUrl=banhang"
                   class="btn-add-customer">

                    Thêm khách hàng

                </a>


            </div>


            <div id="customerInfo"
                 class="customer-info">
            </div>


            <br>


            <!-- =================================================
                 MÃ GIẢM GIÁ
            ================================================== -->

            <label for="maCode">

                Mã giảm giá

            </label>


            <div class="voucher-box">


                <select id="maCode"
                        name="maCode"
                        disabled>


                    <option value=""
                            data-percent="0"
                            data-diem="0">

                        -- Không sử dụng mã giảm giá --

                    </option>


                    <c:forEach items="${listMaGiamGia}"
                               var="m">


                        <c:if test="${m.trangThai && m.soLuong > 0}">


                            <option value="${m.maCode}"

                                    data-percent="${m.phanTramGiam}"

                                    data-diem="${m.diemCan}">

                                ${m.maCode}
                                - ${m.tenMGG}
                                - Giảm ${m.phanTramGiam}%
                                - Cần ${m.diemCan} điểm

                            </option>


                        </c:if>


                    </c:forEach>


                </select>


                <button type="button"
                        class="bh-btn"
                        id="btnApDungVoucher"
                        disabled>

                    Áp dụng

                </button>


            </div>


            <div id="voucherInfo"
                 class="customer-info">

                Nhập số điện thoại khách hàng để sử dụng mã giảm giá.

            </div>


            <br>


            <!-- =================================================
                 PHƯƠNG THỨC THANH TOÁN
            ================================================== -->

            <label for="phuongThucThanhToan">

                Phương thức thanh toán

            </label>


            <select id="phuongThucThanhToan"
                    name="phuongThucThanhToan"
                    required>


                <option value="">

                    -- Chọn phương thức thanh toán --

                </option>


                <option value="TIEN_MAT">

                    Tiền mặt

                </option>


                <option value="CHUYEN_KHOAN">

                    Chuyển khoản

                </option>


            </select>


            <!-- =================================================
                 QR
            ================================================== -->

            <div id="qrPaymentBox"
                 class="qr-payment-box"
                 style="display:none;">


                <h3>
                    Quét mã để thanh toán
                </h3>


                <img id="qrPaymentImage"
                     src=""
                     alt="Mã QR thanh toán">


                <p id="qrAmountText">
                </p>


                <p>

                    Nội dung chuyển khoản:

                    <strong id="qrContentText">
                    </strong>

                </p>


            </div>


            <!-- =================================================
                 TIỀN KHÁCH ĐƯA
            ================================================== -->

            <label>

                Tiền khách đưa

            </label>


            <input type="text"
                   name="tienKhachDua"
                   pattern="[0-9]+"
                   inputmode="numeric"
                   required>


            <!-- =================================================
                 HIDDEN
            ================================================== -->

            <input type="hidden"
                   id="tongTienHidden"
                   name="tongTien"
                   value="${tongTien}">


            <input type="hidden"
                   id="tienGiamHidden"
                   name="tienGiam"
                   value="0">


            <br>


            <!-- =================================================
                 THANH TOÁN
            ================================================== -->

            <button class="bh-btn"
                    type="submit">

                Thanh toán

            </button>


        </form>


    </div>


</div>


<!-- =========================================================
     JAVASCRIPT
========================================================= -->

<script>

document.addEventListener(
    "DOMContentLoaded",
    function () {


        /* =====================================================
           FORMAT TIỀN VNĐ
        ===================================================== */

        document.querySelectorAll(
            ".don-gia-sp"
        ).forEach(
            function (element) {

                const gia =
                    parseFloat(
                        element.dataset.gia
                    ) || 0;

                element.textContent =
                    Math.round(
                        gia
                    ).toLocaleString(
                        "vi-VN"
                    );
            }
        );


        document.querySelectorAll(
            ".thanh-tien-sp"
        ).forEach(
            function (element) {

                const thanhTien =
                    parseFloat(
                        element.dataset.thanhTien
                    ) || 0;

                element.textContent =
                    Math.round(
                        thanhTien
                    ).toLocaleString(
                        "vi-VN"
                    );
            }
        );


        const tongTienFormat =
            document.getElementById(
                "tongTien"
            );


        if (tongTienFormat) {

            const tong =
                parseFloat(
                    tongTienFormat.dataset.amount
                ) || 0;

            tongTienFormat.textContent =
                Math.round(
                    tong
                ).toLocaleString(
                    "vi-VN"
                );
        }


        /* =====================================================
           MÀU - SIZE
        ===================================================== */

        const stockData =
            Array.from(
                document.querySelectorAll(
                    ".stock-item"
                )
            ).map(
                function (item) {

                    return {

                        maSP:
                            String(
                                item.dataset.masp
                            ),

                        maMau:
                            String(
                                item.dataset.mamau
                            ),

                        tenMau:
                            item.dataset.tenmau
                            || item.dataset.mamau,

                        maSize:
                            String(
                                item.dataset.masize
                            ),

                        tenSize:
                            item.dataset.tensize
                            || item.dataset.masize,

                        ton:
                            parseInt(
                                item.dataset.ton,
                                10
                            ) || 0
                    };
                }
            );


        const productIds =
            Array.from(
                document.querySelectorAll(
                    ".select-mau"
                )
            ).map(
                function (select) {

                    return String(
                        select.dataset.masp
                    );
                }
            );


        function layDanhSachKhongTrung(
                list,
                key) {

            const map =
                new Map();


            list.forEach(
                function (item) {

                    if (!map.has(
                            item[key])) {

                        map.set(
                            item[key],
                            item
                        );
                    }
                }
            );


            return Array.from(
                map.values()
            );
        }


        function taoOption(
                select,
                placeholder,
                list,
                valueKey,
                textKey,
                selectedValue) {

            select.innerHTML =
                "";


            const placeholderOption =
                document.createElement(
                    "option"
                );


            placeholderOption.value =
                "";


            placeholderOption.textContent =
                placeholder;


            select.appendChild(
                placeholderOption
            );


            list.forEach(
                function (item) {

                    const option =
                        document.createElement(
                            "option"
                        );


                    option.value =
                        item[valueKey];


                    option.textContent =
                        item[textKey];


                    if (
                        String(
                            item[valueKey]
                        )
                        ===
                        String(
                            selectedValue
                        )
                    ) {

                        option.selected =
                            true;
                    }


                    select.appendChild(
                        option
                    );
                }
            );
        }


        function layBienTheConHang(
                maSP) {

            return stockData.filter(
                function (item) {

                    return item.maSP
                            ===
                            String(maSP)

                        &&
                        item.ton > 0;
                }
            );
        }


        function capNhatCotTon(
                maSP) {

            const mauSelect =
                document.getElementById(
                    "mau-" + maSP
                );


            const sizeSelect =
                document.getElementById(
                    "size-" + maSP
                );


            const tonElement =
                document.getElementById(
                    "ton-" + maSP
                );


            if (!mauSelect
                    || !sizeSelect
                    || !tonElement) {

                return;
            }


            const maMau =
                mauSelect.value;


            const maSize =
                sizeSelect.value;


            tonElement.textContent =
                "";


            if (maMau === ""
                    || maSize === "") {

                return;
            }


            const variant =
                stockData.find(
                    function (item) {

                        return item.maSP
                                ===
                                String(maSP)

                            &&
                            item.maMau
                                ===
                                maMau

                            &&
                            item.maSize
                                ===
                                maSize

                            &&
                            item.ton > 0;
                    }
                );


            if (variant) {

                tonElement.textContent =
                    variant.ton;
            }
        }


        function khiChonMau(
                maSP) {

            const mauSelect =
                document.getElementById(
                    "mau-" + maSP
                );


            const sizeSelect =
                document.getElementById(
                    "size-" + maSP
                );


            const available =
                layBienTheConHang(
                    maSP
                );


            const maMau =
                mauSelect.value;


            const sizeDangChon =
                sizeSelect.value;


            let sizeHopLe =
                available;


            if (maMau !== "") {

                sizeHopLe =
                    available.filter(
                        function (item) {

                            return item.maMau
                                    ===
                                    maMau;
                        }
                    );
            }


            sizeHopLe =
                layDanhSachKhongTrung(
                    sizeHopLe,
                    "maSize"
                );


            const sizeConTonTai =
                sizeHopLe.some(
                    function (item) {

                        return item.maSize
                                ===
                                sizeDangChon;
                    }
                );


            taoOption(
                sizeSelect,
                "-- Chọn size --",
                sizeHopLe,
                "maSize",
                "tenSize",
                sizeConTonTai
                    ? sizeDangChon
                    : ""
            );


            capNhatCotTon(
                maSP
            );
        }


        function khiChonSize(
                maSP) {

            const mauSelect =
                document.getElementById(
                    "mau-" + maSP
                );


            const sizeSelect =
                document.getElementById(
                    "size-" + maSP
                );


            const available =
                layBienTheConHang(
                    maSP
                );


            const maSize =
                sizeSelect.value;


            const mauDangChon =
                mauSelect.value;


            let mauHopLe =
                available;


            if (maSize !== "") {

                mauHopLe =
                    available.filter(
                        function (item) {

                            return item.maSize
                                    ===
                                    maSize;
                        }
                    );
            }


            mauHopLe =
                layDanhSachKhongTrung(
                    mauHopLe,
                    "maMau"
                );


            const mauConTonTai =
                mauHopLe.some(
                    function (item) {

                        return item.maMau
                                ===
                                mauDangChon;
                    }
                );


            taoOption(
                mauSelect,
                "-- Chọn màu --",
                mauHopLe,
                "maMau",
                "tenMau",
                mauConTonTai
                    ? mauDangChon
                    : ""
            );


            capNhatCotTon(
                maSP
            );
        }


        function khoiTaoSanPham(
                maSP) {

            const mauSelect =
                document.getElementById(
                    "mau-" + maSP
                );


            const sizeSelect =
                document.getElementById(
                    "size-" + maSP
                );


            if (!mauSelect
                    || !sizeSelect) {

                return;
            }


            const available =
                layBienTheConHang(
                    maSP
                );


            const danhSachMau =
                layDanhSachKhongTrung(
                    available,
                    "maMau"
                );


            const danhSachSize =
                layDanhSachKhongTrung(
                    available,
                    "maSize"
                );


            taoOption(
                mauSelect,
                "-- Chọn màu --",
                danhSachMau,
                "maMau",
                "tenMau",
                ""
            );


            taoOption(
                sizeSelect,
                "-- Chọn size --",
                danhSachSize,
                "maSize",
                "tenSize",
                ""
            );


            mauSelect.addEventListener(
                "change",
                function () {

                    khiChonMau(
                        maSP
                    );
                }
            );


            sizeSelect.addEventListener(
                "change",
                function () {

                    khiChonSize(
                        maSP
                    );
                }
            );
        }


        productIds.forEach(
            function (maSP) {

                khoiTaoSanPham(
                    maSP
                );
            }
        );


        /* =====================================================
           KHÁCH HÀNG + ĐIỂM
        ===================================================== */

        const phoneInput =
            document.getElementById(
                "soDienThoai"
            );


        const customerInfo =
            document.getElementById(
                "customerInfo"
            );


        const voucherSelect =
            document.getElementById(
                "maCode"
            );


        const voucherButton =
            document.getElementById(
                "btnApDungVoucher"
            );


        const voucherInfo =
            document.getElementById(
                "voucherInfo"
            );


        let customerSearchTimer =
            null;


        let diemKhachHang =
            0;


        let daTimThayKhachHang =
            false;


        /* =====================================================
           KHÓA / MỞ VOUCHER THEO ĐIỂM
        ===================================================== */

        function capNhatVoucherTheoDiem() {

            if (!voucherSelect) {
                return;
            }


            /*
             * CHƯA TÌM THẤY KHÁCH
             */
            if (!daTimThayKhachHang) {

                voucherSelect.disabled =
                    true;


                if (voucherButton) {

                    voucherButton.disabled =
                        true;
                }


                Array.from(
                    voucherSelect.options
                ).forEach(
                    function (option) {

                        if (
                            option.value
                            ===
                            ""
                        ) {

                            option.disabled =
                                false;

                        } else {

                            option.disabled =
                                true;
                        }
                    }
                );


                voucherSelect.value =
                    "";


                voucherInfo.textContent =
                    "Nhập số điện thoại khách hàng để sử dụng mã giảm giá.";


                return;
            }


            /*
             * ĐÃ TÌM THẤY KHÁCH
             */

            voucherSelect.disabled =
                false;


            if (voucherButton) {

                voucherButton.disabled =
                    false;
            }


            Array.from(
                voucherSelect.options
            ).forEach(
                function (option) {


                    /*
                     * Không sử dụng mã
                     */
                    if (
                        option.value
                        ===
                        ""
                    ) {

                        option.disabled =
                            false;

                        return;
                    }


                    const diemCan =
                        parseInt(
                            option.dataset.diem,
                            10
                        ) || 0;


                    /*
                     * QUAN TRỌNG
                     */
                    option.disabled =
                        diemKhachHang
                        <
                        diemCan;

                }
            );


            voucherSelect.value =
                "";


            voucherInfo.textContent =
                "Khách hàng hiện có "
                + diemKhachHang
                + " điểm.";

        }


        /*
         * Khởi tạo
         */
        capNhatVoucherTheoDiem();


        /* =====================================================
           NHẬP SỐ ĐIỆN THOẠI
        ===================================================== */

        if (phoneInput
                && customerInfo) {

            phoneInput.addEventListener(
                "input",
                function () {


                    this.value =
                        this.value
                            .replace(
                                /\D/g,
                                ""
                            )
                            .slice(
                                0,
                                10
                            );


                    clearTimeout(
                        customerSearchTimer
                    );


                    const phone =
                        this.value.trim();


                    /*
                     * Reset khách cũ
                     */
                    diemKhachHang =
                        0;


                    daTimThayKhachHang =
                        false;


                    customerInfo.textContent =
                        "";


                    customerInfo.className =
                        "customer-info";


                    capNhatVoucherTheoDiem();


                    /*
                     * KHÁCH LẺ
                     */
                    if (phone === "") {

                        customerInfo.textContent =
                            "Khách lẻ";

                        return;
                    }


                    /*
                     * CHƯA ĐỦ 10 SỐ
                     */
                    if (phone.length < 10) {

                        customerInfo.textContent =
                            "Vui lòng nhập đủ 10 số điện thoại";


                        customerInfo.className =
                            "customer-info customer-warning";


                        return;
                    }


                    customerInfo.textContent =
                        "Đang tìm khách hàng...";


                    customerInfo.className =
                        "customer-info customer-warning";


                    customerSearchTimer =
                        setTimeout(
                            function () {


                                fetch(
                                    "${pageContext.request.contextPath}"
                                    + "/timKhachHang?soDienThoai="
                                    + encodeURIComponent(
                                        phone
                                    )
                                )


                                .then(
                                    function (response) {


                                        if (!response.ok) {

                                            throw new Error(
                                                "Không thể tìm khách hàng"
                                            );
                                        }


                                        return response.json();
                                    }
                                )


                                .then(
                                    function (data) {


                                        /*
                                         * Nếu SĐT đã đổi
                                         */
                                        if (
                                            phoneInput.value
                                            !==
                                            phone
                                        ) {

                                            return;
                                        }


                                        /*
                                         * TÌM THẤY KHÁCH
                                         */
                                        if (data.found) {


                                            diemKhachHang =
                                                parseInt(
                                                    data.diemTichLuy,
                                                    10
                                                ) || 0;


                                            daTimThayKhachHang =
                                                true;


                                            customerInfo.textContent =
                                                "Khách hàng: "
                                                + data.hoTen
                                                + " - "
                                                + diemKhachHang
                                                + " điểm";


                                            customerInfo.className =
                                                "customer-info customer-found";


                                            capNhatVoucherTheoDiem();


                                        } else {


                                            diemKhachHang =
                                                0;


                                            daTimThayKhachHang =
                                                false;


                                            customerInfo.textContent =
                                                data.message
                                                ||
                                                "Không tìm thấy khách hàng";


                                            customerInfo.className =
                                                "customer-info customer-not-found";


                                            capNhatVoucherTheoDiem();
                                        }

                                    }
                                )


                                .catch(
                                    function (error) {


                                        console.error(
                                            error
                                        );


                                        diemKhachHang =
                                            0;


                                        daTimThayKhachHang =
                                            false;


                                        customerInfo.textContent =
                                            "Có lỗi khi tìm khách hàng";


                                        customerInfo.className =
                                            "customer-info customer-not-found";


                                        capNhatVoucherTheoDiem();
                                    }
                                );


                            },
                            400
                        );

                }
            );
        }


        /* =====================================================
           THANH TOÁN QR
        ===================================================== */

        const paymentSelect =
            document.getElementById(
                "phuongThucThanhToan"
            );


        const qrPaymentBox =
            document.getElementById(
                "qrPaymentBox"
            );


        const qrPaymentImage =
            document.getElementById(
                "qrPaymentImage"
            );


        const qrAmountText =
            document.getElementById(
                "qrAmountText"
            );


        const qrContentText =
            document.getElementById(
                "qrContentText"
            );


        const tongTienElement =
            document.getElementById(
                "tongTien"
            );


        const tienKhachDuaInput =
            document.querySelector(
                'input[name="tienKhachDua"]'
            );


        function taoMaQRThanhToan() {


            if (!paymentSelect
                    || !qrPaymentBox
                    || !qrPaymentImage
                    || !tongTienElement) {

                return;
            }


            if (
                paymentSelect.value
                !==
                "CHUYEN_KHOAN"
            ) {


                qrPaymentBox.style.display =
                    "none";


                qrPaymentImage.src =
                    "";


                if (tienKhachDuaInput) {


                    tienKhachDuaInput.required =
                        true;


                    tienKhachDuaInput.readOnly =
                        false;


                    tienKhachDuaInput.value =
                        "";
                }


                return;
            }


            const amount =
                parseInt(
                    tongTienElement.dataset.amount,
                    10
                ) || 0;


            if (amount <= 0) {


                qrPaymentBox.style.display =
                    "none";


                qrPaymentImage.src =
                    "";


                return;
            }


            const bankId =
                "VPB";


            const accountNumber =
                "352221589";


            const accountName =
                "NGUYEN KHANH AN";


            const paymentContent =
                "THANH TOAN DON HANG";


            const qrUrl =
                "https://img.vietqr.io/image/"
                + encodeURIComponent(
                    bankId
                )
                + "-"
                + encodeURIComponent(
                    accountNumber
                )
                + "-compact.png"
                + "?amount="
                + encodeURIComponent(
                    amount
                )
                + "&addInfo="
                + encodeURIComponent(
                    paymentContent
                )
                + "&accountName="
                + encodeURIComponent(
                    accountName
                );


            qrPaymentImage.src =
                qrUrl;


            qrAmountText.textContent =
                "Số tiền: "
                + amount.toLocaleString(
                    "vi-VN"
                )
                + " VNĐ";


            qrContentText.textContent =
                paymentContent;


            qrPaymentBox.style.display =
                "block";


            if (tienKhachDuaInput) {


                tienKhachDuaInput.value =
                    amount;


                tienKhachDuaInput.required =
                    false;


                tienKhachDuaInput.readOnly =
                    true;

            }

        }


        if (paymentSelect) {


            paymentSelect.addEventListener(
                "change",
                taoMaQRThanhToan
            );


            taoMaQRThanhToan();

        }


        /* =====================================================
           MÃ GIẢM GIÁ
        ===================================================== */

        let tongTienGoc =
            parseFloat(
                tongTienElement.dataset.amount
            ) || 0;


        let tongTienSauGiam =
            tongTienGoc;


        /* =====================================================
           KHÔI PHỤC GIÁ GỐC
        ===================================================== */

        function khoiPhucGiaGoc() {


            document.querySelectorAll(
                ".don-gia-sp"
            ).forEach(
                function (element) {


                    const giaGoc =
                        parseFloat(
                            element.dataset.gia
                        ) || 0;


                    element.textContent =
                        Math.round(
                            giaGoc
                        ).toLocaleString(
                            "vi-VN"
                        );

                }
            );


            document.querySelectorAll(
                ".thanh-tien-sp"
            ).forEach(
                function (element) {


                    const thanhTienGoc =
                        parseFloat(
                            element.dataset.thanhTien
                        ) || 0;


                    element.textContent =
                        Math.round(
                            thanhTienGoc
                        ).toLocaleString(
                            "vi-VN"
                        );

                }
            );

        }


        /* =====================================================
           ÁP DỤNG VOUCHER
        ===================================================== */

        function apDungVoucher() {


            if (!voucherSelect
                    || !tongTienElement
                    || !voucherInfo) {

                return;
            }


            const option =
                voucherSelect.options[
                    voucherSelect.selectedIndex
                ];


            const maCode =
                voucherSelect.value;


            /* =================================================
               KHÔNG DÙNG MÃ
            ================================================= */

            if (maCode === "") {


                tongTienSauGiam =
                    tongTienGoc;


                khoiPhucGiaGoc();


                tongTienElement.textContent =
                    Math.round(
                        tongTienGoc
                    ).toLocaleString(
                        "vi-VN"
                    );


                tongTienElement.dataset.amount =
                    tongTienGoc;


                document.getElementById(
                    "tongTienHidden"
                ).value =
                    tongTienGoc;


                document.getElementById(
                    "tienGiamHidden"
                ).value =
                    0;


                voucherInfo.textContent =
                    "Không sử dụng mã giảm giá.";


                voucherInfo.className =
                    "customer-info";


                taoMaQRThanhToan();


                return;
            }


            /* =================================================
               CHƯA CHỌN KHÁCH
            ================================================= */

            if (!daTimThayKhachHang) {


                voucherInfo.textContent =
                    "Phải nhập khách hàng trước khi sử dụng mã giảm giá.";


                voucherInfo.className =
                    "customer-info customer-not-found";


                voucherSelect.value =
                    "";


                return;
            }


            /* =================================================
               KIỂM TRA ĐIỂM
            ================================================= */

            const diemCan =
                parseInt(
                    option.dataset.diem,
                    10
                ) || 0;


            if (
                diemKhachHang
                <
                diemCan
            ) {


                voucherInfo.textContent =
                    "Không đủ điểm. Khách hàng có "
                    + diemKhachHang
                    + " điểm, mã "
                    + maCode
                    + " cần "
                    + diemCan
                    + " điểm.";


                voucherInfo.className =
                    "customer-info customer-not-found";


                voucherSelect.value =
                    "";


                return;
            }


            /* =================================================
               PHẦN TRĂM
            ================================================= */

            const phanTram =
                parseFloat(
                    option.dataset.percent
                ) || 0;


            /* =================================================
               TIỀN GIẢM
            ================================================= */

            const tienGiam =
                tongTienGoc
                *
                phanTram
                /
                100;


            tongTienSauGiam =
                tongTienGoc
                -
                tienGiam;


            if (
                tongTienSauGiam
                <
                0
            ) {

                tongTienSauGiam =
                    0;
            }


            /* =================================================
               HIDDEN TIỀN GIẢM
            ================================================= */

            document.getElementById(
                "tienGiamHidden"
            ).value =
                Math.round(
                    tienGiam
                );


            /* =================================================
               GIẢM GIÁ TRONG BẢNG
            ================================================= */

            document.querySelectorAll(
                ".don-gia-sp"
            ).forEach(
                function (element) {


                    const giaGoc =
                        parseFloat(
                            element.dataset.gia
                        ) || 0;


                    const giaSauGiam =
                        giaGoc
                        -
                        (
                            giaGoc
                            *
                            phanTram
                            /
                            100
                        );


                    element.textContent =
                        Math.round(
                            giaSauGiam
                        ).toLocaleString(
                            "vi-VN"
                        );

                }
            );


            document.querySelectorAll(
                ".thanh-tien-sp"
            ).forEach(
                function (element) {


                    const thanhTienGoc =
                        parseFloat(
                            element.dataset.thanhTien
                        ) || 0;


                    const thanhTienSauGiam =
                        thanhTienGoc
                        -
                        (
                            thanhTienGoc
                            *
                            phanTram
                            /
                            100
                        );


                    element.textContent =
                        Math.round(
                            thanhTienSauGiam
                        ).toLocaleString(
                            "vi-VN"
                        );

                }
            );


            /* =================================================
               TỔNG TIỀN
            ================================================= */

            tongTienElement.textContent =
                Math.round(
                    tongTienSauGiam
                ).toLocaleString(
                    "vi-VN"
                );


            tongTienElement.dataset.amount =
                tongTienSauGiam;


            /* =================================================
               HIDDEN TỔNG
            ================================================= */

            document.getElementById(
                "tongTienHidden"
            ).value =
                Math.round(
                    tongTienSauGiam
                );


            /* =================================================
               THÔNG BÁO
            ================================================= */

            voucherInfo.textContent =
                "Đã áp dụng "
                + maCode
                + " - Giảm "
                + phanTram
                + "% - Cần "
                + diemCan
                + " điểm.";


            voucherInfo.className =
                "customer-info customer-found";


            /* =================================================
               CẬP NHẬT QR
            ================================================= */

            taoMaQRThanhToan();

        }


        /* =====================================================
           NÚT ÁP DỤNG
        ===================================================== */

        if (voucherButton) {


            voucherButton.addEventListener(
                "click",
                apDungVoucher
            );

        }


    }
);

</script>


</body>

</html>