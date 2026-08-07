package controller.Banhang;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.MaGiamGiaDao;

import model.ChiTietHoaDon;
import model.GioHang;
import model.HoaDon;
import model.KhachHang;
import model.MaGiamGia;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/thanhToan")
public class ThanhToanController
        extends HttpServlet {

    private final HoaDonDao dao =
            new HoaDonDao();

    private final KhachHangDao khDao =
            new KhachHangDao();

    private final MaGiamGiaDao mggDao =
            new MaGiamGiaDao();


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");

        response.setContentType(
                "text/html;charset=UTF-8"
        );


        HttpSession session =
                request.getSession(false);


        // =====================================================
        // KIỂM TRA ĐĂNG NHẬP
        // =====================================================

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login"
            );

            return;
        }


        TaiKhoan taiKhoan =
                (TaiKhoan)
                        session.getAttribute(
                                "user"
                        );


        // =====================================================
        // GIỎ HÀNG
        // =====================================================

        List<GioHang> gioHang =
                (List<GioHang>)
                        session.getAttribute(
                                "gioHang"
                        );


        if (gioHang == null
                || gioHang.isEmpty()) {

            session.setAttribute(
                    "errorCode",
                    "GIO_HANG_TRONG"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/banhang"
            );

            return;
        }


        try {

            // =================================================
            // TÍNH TỔNG TIỀN GỐC TỪ GIỎ HÀNG
            // KHÔNG LẤY TỔNG TIỀN CLIENT LÀM GIÁ TRỊ CHÍNH
            // =================================================

            double tongTienGoc = 0;

            for (GioHang g : gioHang) {

                tongTienGoc +=
                        g.getThanhTien();
            }


            if (tongTienGoc <= 0) {

                session.setAttribute(
                        "errorCode",
                        "TONG_TIEN_KHONG_HOP_LE"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/banhang"
                );

                return;
            }


            // =================================================
            // KHÁCH HÀNG
            // =================================================

            String sdt =
                    request.getParameter(
                            "soDienThoai"
                    );


            KhachHang kh = null;


            if (sdt != null
                    && !sdt.trim().isEmpty()) {

                kh =
                        khDao.getBySoDienThoai(
                                sdt.trim()
                        );
            }


            // =================================================
            // MÃ GIẢM GIÁ
            // =================================================

            String maCode =
                    request.getParameter(
                            "maCode"
                    );


            MaGiamGia voucher = null;

            double tienGiam = 0;

            double tongTien =
                    tongTienGoc;


            if (maCode != null
                    && !maCode.trim().isEmpty()) {


                // Voucher bắt buộc phải có khách hàng
                if (kh == null) {

                    session.setAttribute(
                            "errorCode",
                            "VOUCHER_CAN_KHACH_HANG"
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/banhang"
                    );

                    return;
                }


                voucher =
                        mggDao.getByCodeDangHoatDong(
                                maCode.trim()
                        );


                // Voucher không tồn tại / hết hạn / hết số lượng
                if (voucher == null) {

                    session.setAttribute(
                            "errorCode",
                            "MA_GIAM_GIA_HET_HIEU_LUC"
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/banhang"
                    );

                    return;
                }


                // =================================================
                // KIỂM TRA ĐIỂM
                // =================================================

                if (kh.getDiemTichLuy()
                        < voucher.getDiemCan()) {

                    session.setAttribute(
                            "errorCode",
                            "KHONG_DU_DIEM"
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/banhang"
                    );

                    return;
                }


                // =================================================
                // SERVER TỰ TÍNH TIỀN GIẢM
                // =================================================

                tienGiam =
                        tongTienGoc
                                * voucher.getPhanTramGiam()
                                / 100.0;


                tongTien =
                        tongTienGoc
                                - tienGiam;


                if (tongTien < 0) {
                    tongTien = 0;
                }
            }


            // Làm tròn tiền
            tienGiam =
                    Math.round(tienGiam);

            tongTien =
                    Math.round(tongTien);


            // =================================================
            // PHƯƠNG THỨC THANH TOÁN
            // =================================================

            String phuongThucThanhToan =
                    request.getParameter(
                            "phuongThucThanhToan"
                    );


            if (phuongThucThanhToan == null
                    || phuongThucThanhToan.trim().isEmpty()) {

                session.setAttribute(
                        "errorCode",
                        "CHUA_CHON_PHUONG_THUC"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/banhang"
                );

                return;
            }


            double tienKhachDua;


            if ("CHUYEN_KHOAN".equals(
                    phuongThucThanhToan
            )) {

                tienKhachDua =
                        tongTien;

            } else {

                String tienKhachDuaStr =
                        request.getParameter(
                                "tienKhachDua"
                        );


                if (tienKhachDuaStr == null
                        || tienKhachDuaStr.trim().isEmpty()) {

                    session.setAttribute(
                            "errorCode",
                            "DU_LIEU_THANH_TOAN_KHONG_HOP_LE"
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/banhang"
                    );

                    return;
                }


                tienKhachDua =
                        Double.parseDouble(
                                tienKhachDuaStr
                        );


                if (tienKhachDua
                        < tongTien) {

                    session.setAttribute(
                            "errorCode",
                            "TIEN_KHACH_DUA_KHONG_DU"
                    );

                    response.sendRedirect(
                            request.getContextPath()
                                    + "/banhang"
                    );

                    return;
                }
            }


            // =================================================
            // TẠO HÓA ĐƠN
            // =================================================

            HoaDon hd =
                    new HoaDon();


            hd.setNgayLap(
                    new Date(
                            System.currentTimeMillis()
                    )
            );


            // Tổng tiền sau giảm
            hd.setTongTien(
                    tongTien
            );


            // Tiền giảm
            hd.setTienGiam(
                    tienGiam
            );


            // =================================================
            // THANH TOÁN
            // =================================================

            if ("CHUYEN_KHOAN".equals(
                    phuongThucThanhToan
            )) {

                hd.setPhuongThucThanhToan(
                        "Chuyển khoản"
                );

                hd.setTienKhachDua(
                        tongTien
                );

                hd.setTienThua(
                        0
                );

            } else {

                hd.setPhuongThucThanhToan(
                        "Tiền mặt"
                );

                hd.setTienKhachDua(
                        tienKhachDua
                );

                hd.setTienThua(
                        tienKhachDua
                                - tongTien
                );
            }


            // =================================================
            // NHÂN VIÊN
            // =================================================

            hd.setMaNV(
                    taiKhoan.getMaNV()
            );


            // =================================================
            // KHÁCH HÀNG
            // =================================================

            if (kh != null) {

                hd.setMaKH(
                        kh.getMaKH()
                );

            } else {

                hd.setMaKH(
                        null
                );
            }


            // =================================================
            // TRẠNG THÁI HÓA ĐƠN
            // =================================================

            hd.setMaTrangThaiHD(
                    "TTHD01"
            );


            // =================================================
            // CHI TIẾT HÓA ĐƠN
            // =================================================

            List<ChiTietHoaDon> list =
                    new ArrayList<>();


            for (GioHang g : gioHang) {

                ChiTietHoaDon ct =
                        new ChiTietHoaDon();


                ct.setMaSPCT(
                        g.getMaSPCT()
                );


                ct.setSoLuong(
                        g.getSoLuong()
                );


                // Giá gốc
                ct.setDonGia(
                        g.getDonGia()
                );


                // Thành tiền gốc
                ct.setThanhTien(
                        g.getThanhTien()
                );


                list.add(ct);
            }


            // =================================================
            // LƯU HÓA ĐƠN
            // =================================================

            int maHD =
                    dao.thanhToan(
                            hd,
                            list
                    );


            if (maHD > 0) {


                // =================================================
                // VOUCHER:
                // TRỪ ĐIỂM + GIẢM SỐ LƯỢNG
                // =================================================

                if (kh != null
                        && voucher != null) {


                    boolean truDiemThanhCong =
                            khDao.truDiem(
                                    kh.getMaKH(),
                                    voucher.getDiemCan()
                            );


                    if (truDiemThanhCong) {

                        mggDao.giamSoLuong(
                                voucher.getMaMGG()
                        );


                        mggDao.capNhatTrangThai(
                                voucher.getMaMGG()
                        );
                    }
                }


                // =================================================
                // CỘNG ĐIỂM SAU KHI MUA
                //
                // QUY ƯỚC HIỆN TẠI:
                // 10.000 VNĐ = 1 điểm
                // =================================================

                if (kh != null) {

                    int diemCong =
                            (int)
                                    (
                                            tongTien
                                                    / 10000
                                    );


                    if (diemCong > 0) {

                        khDao.congDiem(
                                kh.getMaKH(),
                                diemCong
                        );
                    }
                }


                // =================================================
                // XÓA GIỎ HÀNG
                // =================================================

                session.removeAttribute(
                        "gioHang"
                );


                // =================================================
                // CHUYỂN SANG HÓA ĐƠN
                // =================================================

                response.sendRedirect(
                        request.getContextPath()
                                + "/hoadon?maHD="
                                + maHD
                );


                return;
            }


            // =================================================
            // THANH TOÁN THẤT BẠI
            // =================================================

            session.setAttribute(
                    "errorCode",
                    "THANH_TOAN_THAT_BAI"
            );


            response.sendRedirect(
                    request.getContextPath()
                            + "/banhang"
            );


        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "DU_LIEU_THANH_TOAN_KHONG_HOP_LE"
            );


            response.sendRedirect(
                    request.getContextPath()
                            + "/banhang"
            );


        } catch (Exception e) {

            e.printStackTrace();


            session.setAttribute(
                    "errorCode",
                    "LOI_THANH_TOAN"
            );


            response.sendRedirect(
                    request.getContextPath()
                            + "/banhang"
            );
        }
    }
}