<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Bán hàng</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css?v=30">

</head>

<body>
<%@ include file="menu.jsp" %>

<div class="sale-header">
    <h2>BÁN HÀNG</h2>

    <a href="${pageContext.request.contextPath}/index.jsp"
       class="sale-back">
        Quay lại
    </a>
</div>

<c:if test="${not empty messageCode}">
    <div class="alert-message alert-success">

        <span class="alert-icon">✓</span>

        <span>
            <c:choose>

                <c:when test="${messageCode == 'THEM_VAO_GIO_HANG'}">
                    Đã thêm sản phẩm vào giỏ hàng
                </c:when>

                <c:when test="${messageCode == 'TANG_SO_LUONG'}">
                    Đã tăng số lượng sản phẩm
                </c:when>

            </c:choose>
        </span>

    </div>
</c:if>

<c:if test="${not empty errorCode}">
    <div class="alert-message alert-error">

        <span class="alert-icon">!</span>

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

                <c:otherwise>
                    Có lỗi xảy ra
                </c:otherwise>

            </c:choose>
        </span>

    </div>
</c:if>

<div class="bh-container">

    <!-- ================= DANH SÁCH SẢN PHẨM ================= -->

    <div class="bh-left">

        <h3>Danh sách sản phẩm</h3>

        <table class="bh-table">

            <tr>
                <th>Mã</th>
                <th>Tên sản phẩm</th>
                <th>Màu</th>
                <th>Size</th>
                <th>Tồn</th>
                <th></th>
            </tr>

            <c:forEach items="${listSP}" var="sp">

                <tr>

                    <td>${sp.maSP}</td>

                    <td>${sp.tenSP}</td>

                    <td>
                        <select name="maMau"
                                 id="mau-${sp.maSP}"
                                 class="select-mau"
                                 data-masp="${sp.maSP}"
                                 form="formThem${sp.maSP}"
                                 required>

                             <option value="">-- Chọn màu --</option>

                         </select>
                    </td>

                    <td>
                        <select name="maSize"
                                id="size-${sp.maSP}"
                                class="select-size"
                                data-masp="${sp.maSP}"
                                form="formThem${sp.maSP}"
                                required>

                            <option value="">-- Chọn size --</option>

                        </select>
                    </td>

                    <td>
                        <span id="ton-${sp.maSP}" class="stock-value">
                            --
                        </span>
                    </td>

                    <td>
                        <form id="formThem${sp.maSP}"
                              action="banhang"
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
    <div id="stock-data" style="display:none;">

        <c:forEach items="${listChiTiet}" var="ct">

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

    <!-- ================= GIỎ HÀNG ================= -->

    <div class="bh-right">

        <h3>Giỏ hàng</h3>

        <table class="bh-table">

            <tr>

                <th>Sản phẩm</th>
                <th>SL</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>

            </tr>

            <c:set var="tongTien" value="0"/>

            <c:forEach items="${sessionScope.gioHang}" var="g">

                <tr>

                    <td>

                        ${g.tenSP}

                        <br>

                        ${g.tenMau} - ${g.tenSize}

                    </td>

                        <td>
                            <div class="qty-box">

                                <form action="capNhatGioHang" method="post">
                                    <input type="hidden" name="maSPCT" value="${g.maSPCT}">
                                    <input type="hidden" name="action" value="giam">
                                    <button type="submit" class="bh-btn">-</button>
                                </form>

                                <span>${g.soLuong}</span>

                                <form action="capNhatGioHang" method="post">
                                    <input type="hidden" name="maSPCT" value="${g.maSPCT}">
                                    <input type="hidden" name="action" value="tang">
                                    <button type="submit" class="bh-btn">+</button>
                                </form>

                            </div>
                        </td>

                    <td>

                        ${g.donGia}

                    </td>

                    <td>

                        ${g.thanhTien}

                    </td>

                </tr>

                <c:set
                        var="tongTien"
                        value="${tongTien + g.thanhTien}"/>

            </c:forEach>

        </table>

        <div class="bh-total">

            Tổng tiền:
            <span id="tongTien" data-amount="${tongTien}">
                ${tongTien}
            </span>
            VNĐ

        </div>

        <br>

        <form action="thanhToan" method="post">

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
            <div id="qrPaymentBox"
                 class="qr-payment-box"
                 style="display:none;">

                <h3>Quét mã để thanh toán</h3>

                <img id="qrPaymentImage"
                     src=""
                     alt="Mã QR thanh toán">

                <p id="qrAmountText"></p>

                <p>
                    Nội dung chuyển khoản:
                    <strong id="qrContentText"></strong>
                </p>

            </div>

            <label>Tiền khách đưa</label>

            <input
                    type="text"
                    name="tienKhachDua"
                    pattern="[0-9]+"
                    inputmode="numeric"
                    required>

            <input
                    type="hidden"
                    name="tongTien"
                    value="${tongTien}">

            <br>

            <button
                    class="bh-btn"
                    type="submit">

                Thanh toán

            </button>

        </form>

    </div>

</div>
<script>
document.addEventListener("DOMContentLoaded", function () {

    const stockData = Array.from(
        document.querySelectorAll(".stock-item")
    ).map(function (item) {

        return {
            maSP: String(item.dataset.masp),
            maMau: String(item.dataset.mamau),
            tenMau: item.dataset.tenmau || item.dataset.mamau,
            maSize: String(item.dataset.masize),
            tenSize: item.dataset.tensize || item.dataset.masize,
            ton: parseInt(item.dataset.ton, 10) || 0
        };
    });

    const productIds = Array.from(
        document.querySelectorAll(".select-mau")
    ).map(function (select) {
        return String(select.dataset.masp);
    });

    function layDanhSachKhongTrung(list, key) {

        const map = new Map();

        list.forEach(function (item) {

            if (!map.has(item[key])) {
                map.set(item[key], item);
            }
        });

        return Array.from(map.values());
    }

    function taoOption(
            select,
            placeholder,
            list,
            valueKey,
            textKey,
            selectedValue) {

        select.innerHTML = "";

        const placeholderOption =
            document.createElement("option");

        placeholderOption.value = "";
        placeholderOption.textContent = placeholder;

        select.appendChild(placeholderOption);

        list.forEach(function (item) {

            const option =
                document.createElement("option");

            option.value = item[valueKey];
            option.textContent = item[textKey];

            if (String(item[valueKey]) === String(selectedValue)) {
                option.selected = true;
            }

            select.appendChild(option);
        });
    }

    function layBienTheConHang(maSP) {

        return stockData.filter(function (item) {

            return item.maSP === String(maSP)
                    && item.ton > 0;
        });
    }

    function capNhatCotTon(maSP) {

        const mauSelect =
            document.getElementById("mau-" + maSP);

        const sizeSelect =
            document.getElementById("size-" + maSP);

        const tonElement =
            document.getElementById("ton-" + maSP);

        const maMau = mauSelect.value;
        const maSize = sizeSelect.value;

        tonElement.textContent = "";

        tonElement.classList.remove(
            "stock-available",
            "stock-empty",
            "stock-not-found",
            "stock-detail"
        );

        /*
         * Chỉ hiện tồn khi đã chọn đủ màu và size.
         */
        if (maMau === "" || maSize === "") {
            return;
        }

        const variant = stockData.find(function (item) {

            return item.maSP === String(maSP)
                    && item.maMau === maMau
                    && item.maSize === maSize
                    && item.ton > 0;
        });

        if (variant) {

            tonElement.textContent = variant.ton;
            tonElement.classList.add("stock-available");
        }
    }

    function khiChonMau(maSP) {

        const mauSelect =
            document.getElementById("mau-" + maSP);

        const sizeSelect =
            document.getElementById("size-" + maSP);

        const available =
            layBienTheConHang(maSP);

        const maMau = mauSelect.value;
        const sizeDangChon = sizeSelect.value;

        let sizeHopLe = available;

        if (maMau !== "") {

            sizeHopLe = available.filter(function (item) {
                return item.maMau === maMau;
            });
        }

        sizeHopLe =
            layDanhSachKhongTrung(sizeHopLe, "maSize");

        const sizeConTonTai =
            sizeHopLe.some(function (item) {
                return item.maSize === sizeDangChon;
            });

        taoOption(
            sizeSelect,
            "-- Chọn size --",
            sizeHopLe,
            "maSize",
            "tenSize",
            sizeConTonTai ? sizeDangChon : ""
        );

        capNhatCotTon(maSP);
    }

    function khiChonSize(maSP) {

        const mauSelect =
            document.getElementById("mau-" + maSP);

        const sizeSelect =
            document.getElementById("size-" + maSP);

        const available =
            layBienTheConHang(maSP);

        const maSize = sizeSelect.value;
        const mauDangChon = mauSelect.value;

        let mauHopLe = available;

        if (maSize !== "") {

            mauHopLe = available.filter(function (item) {
                return item.maSize === maSize;
            });
        }

        mauHopLe =
            layDanhSachKhongTrung(mauHopLe, "maMau");

        const mauConTonTai =
            mauHopLe.some(function (item) {
                return item.maMau === mauDangChon;
            });

        taoOption(
            mauSelect,
            "-- Chọn màu --",
            mauHopLe,
            "maMau",
            "tenMau",
            mauConTonTai ? mauDangChon : ""
        );

        capNhatCotTon(maSP);
    }

    function khoiTaoSanPham(maSP) {

        const mauSelect =
            document.getElementById("mau-" + maSP);

        const sizeSelect =
            document.getElementById("size-" + maSP);

        const tonElement =
            document.getElementById("ton-" + maSP);

        if (!mauSelect || !sizeSelect) {
            return;
        }

        const available =
            layBienTheConHang(maSP);

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

        if (tonElement) {
            tonElement.textContent = "";
        }

        mauSelect.addEventListener(
            "change",
            function () {
                khiChonMau(maSP);
            }
        );

        sizeSelect.addEventListener(
            "change",
            function () {
                khiChonSize(maSP);
            }
        );
    }

    productIds.forEach(function (maSP) {
        khoiTaoSanPham(maSP);
    });
        /* ================= TÌM KHÁCH HÀNG THEO SĐT ================= */

        const phoneInput =
            document.getElementById("soDienThoai");

        const customerInfo =
            document.getElementById("customerInfo");

        let customerSearchTimer = null;

        if (phoneInput && customerInfo) {

            phoneInput.addEventListener("input", function () {

                /*
                 * Chỉ giữ lại chữ số và giới hạn 10 số.
                 */
                this.value = this.value
                        .replace(/\D/g, "")
                        .slice(0, 10);

                clearTimeout(customerSearchTimer);

                const phone = this.value.trim();

                customerInfo.textContent = "";
                customerInfo.className = "customer-info";

                /*
                 * Để trống thì coi là khách lẻ.
                 */
                if (phone === "") {
                    return;
                }

                if (phone.length < 10) {

                    customerInfo.textContent =
                        "Vui lòng nhập đủ 10 số điện thoại";

                    customerInfo.classList.add(
                        "customer-warning"
                    );

                    return;
                }

                customerInfo.textContent =
                    "Đang tìm khách hàng...";

                customerInfo.classList.add(
                    "customer-warning"
                );

                customerSearchTimer = setTimeout(function () {

                    fetch(
                        "${pageContext.request.contextPath}"
                        + "/timKhachHang?soDienThoai="
                        + encodeURIComponent(phone)
                    )
                    .then(function (response) {

                        if (!response.ok) {
                            throw new Error(
                                "Không thể tìm khách hàng"
                            );
                        }

                        return response.json();
                    })
                    .then(function (data) {

                        /*
                         * Không hiện kết quả của số điện thoại cũ
                         * nếu người dùng đã nhập số khác.
                         */
                        if (phoneInput.value !== phone) {
                            return;
                        }

                        if (data.found) {

                            customerInfo.textContent =
                                "Khách hàng: " + data.hoTen;

                            customerInfo.className =
                                "customer-info customer-found";

                        } else {

                            customerInfo.textContent =
                                data.message
                                || "Không tìm thấy khách hàng";

                            customerInfo.className =
                                "customer-info customer-not-found";
                        }
                    })
                    .catch(function (error) {

                        console.error(error);

                        customerInfo.textContent =
                            "Có lỗi khi tìm khách hàng";

                        customerInfo.className =
                            "customer-info customer-not-found";
                    });

                }, 400);
            });
        }


    /* ================= THANH TOÁN QR ================= */

    const paymentSelect =
        document.getElementById("phuongThucThanhToan");

    const qrPaymentBox =
        document.getElementById("qrPaymentBox");

    const qrPaymentImage =
        document.getElementById("qrPaymentImage");

    const qrAmountText =
        document.getElementById("qrAmountText");

    const qrContentText =
        document.getElementById("qrContentText");

    const tongTienElement =
        document.getElementById("tongTien");

    const tienKhachDuaInput =
        document.querySelector('input[name="tienKhachDua"]');

    function taoMaQRThanhToan() {

        if (!paymentSelect
                || !qrPaymentBox
                || !qrPaymentImage
                || !tongTienElement) {
            return;
        }

        if (paymentSelect.value !== "CHUYEN_KHOAN") {
            qrPaymentBox.style.display = "none";
            qrPaymentImage.src = "";

            if (tienKhachDuaInput) {
                tienKhachDuaInput.required = true;
                tienKhachDuaInput.readOnly = false;
                tienKhachDuaInput.value = "";
            }

            return;
        }

        const amount = parseInt(
            tongTienElement.dataset.amount,
            10
        ) || 0;

        if (amount <= 0) {
            qrPaymentBox.style.display = "none";
            qrPaymentImage.src = "";
            return;
        }

        /*
         * THAY THÔNG TIN TÀI KHOẢN THẬT TẠI ĐÂY.
         * Ví dụ VPBank dùng bankId = "VPB".
         */
        const bankId = "VPB";
        const accountNumber = "352221589";
        const accountName = "NGUYEN KHANH AN";
        const paymentContent = "THANH TOAN DON HANG";

        const qrUrl =
            "https://img.vietqr.io/image/"
            + encodeURIComponent(bankId)
            + "-"
            + encodeURIComponent(accountNumber)
            + "-compact.png"
            + "?amount="
            + encodeURIComponent(amount)
            + "&addInfo="
            + encodeURIComponent(paymentContent)
            + "&accountName="
            + encodeURIComponent(accountName);

        qrPaymentImage.src = qrUrl;

        qrAmountText.textContent =
            "Số tiền: "
            + amount.toLocaleString("vi-VN")
            + " VNĐ";

        qrContentText.textContent = paymentContent;
        qrPaymentBox.style.display = "block";

        /*
         * Chuyển khoản thì tiền khách đưa bằng đúng tổng tiền.
         */
        if (tienKhachDuaInput) {
            tienKhachDuaInput.value = amount;
            tienKhachDuaInput.required = false;
            tienKhachDuaInput.readOnly = true;
        }
    }

    if (paymentSelect) {
        paymentSelect.addEventListener(
            "change",
            taoMaQRThanhToan
        );

        taoMaQRThanhToan();
    }

});
</script>
</body>
</html>