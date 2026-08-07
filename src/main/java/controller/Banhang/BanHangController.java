package controller.Banhang;

import dao.MauSacDao;
import dao.MaGiamGiaDao;
import dao.SanPhamChiTietDao;
import dao.SanPhamDao;
import dao.SizeDao;

import model.GioHang;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        value = "/banhang",
        loadOnStartup = 1
)
public class BanHangController extends HttpServlet {

    private final SanPhamChiTietDao chiTietDao =
            new SanPhamChiTietDao();

    private final SanPhamDao sanPhamDao =
            new SanPhamDao();

    private final MauSacDao mauSacDao =
            new MauSacDao();

    private final SizeDao sizeDao =
            new SizeDao();

    // ================= MÃ GIẢM GIÁ =================

    private final MaGiamGiaDao maGiamGiaDao =
            new MaGiamGiaDao();


    @Override
    public void init() throws ServletException {

        /*
         * Màu và size được tải một lần khi ứng dụng khởi động.
         */
        getServletContext().setAttribute(
                "danhSachMauBanHang",
                mauSacDao.getAll()
        );

        getServletContext().setAttribute(
                "danhSachSizeBanHang",
                sizeDao.getAll()
        );
    }


    @Override
    protected void doGet(
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

        // ================= KIỂM TRA ĐĂNG NHẬP =================

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }


        try {

            // ================= SẢN PHẨM =================

            request.setAttribute(
                    "listSP",
                    sanPhamDao.getAll()
            );


            // ================= CHI TIẾT SẢN PHẨM =================

            request.setAttribute(
                    "listChiTiet",
                    chiTietDao.getAll()
            );


            // ================= MÀU =================

            request.setAttribute(
                    "listMau",
                    getServletContext().getAttribute(
                            "danhSachMauBanHang"
                    )
            );


            // ================= SIZE =================

            request.setAttribute(
                    "listSize",
                    getServletContext().getAttribute(
                            "danhSachSizeBanHang"
                    )
            );


            // ================= MÃ GIẢM GIÁ =================

            request.setAttribute(
                    "listMaGiamGia",
                    maGiamGiaDao.getAllDangHoatDong()
            );


            // ================= THÔNG BÁO =================

            chuyenThongBao(
                    session,
                    request,
                    "errorCode"
            );

            chuyenThongBao(
                    session,
                    request,
                    "messageCode"
            );


            // ================= HIỂN THỊ TRANG =================

            request.getRequestDispatcher(
                    "/banhang.jsp"
            ).forward(
                    request,
                    response
            );


        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_TAI_TRANG_BAN_HANG"
            );

            response.sendRedirect(
                    request.getContextPath() + "/home"
            );
        }
    }


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);


        // ================= KIỂM TRA ĐĂNG NHẬP =================

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }


        try {

            int maSP =
                    Integer.parseInt(
                            request.getParameter("maSP")
                    );


            String maMau =
                    request.getParameter("maMau");


            String maSize =
                    request.getParameter("maSize");


            // ================= KIỂM TRA MÀU SIZE =================

            if (isBlank(maMau)
                    || isBlank(maSize)) {

                session.setAttribute(
                        "errorCode",
                        "CHUA_CHON_MAU_SIZE"
                );

                redirectBanHang(
                        request,
                        response
                );

                return;
            }


            // ================= LẤY BIẾN THỂ =================

            SanPhamChiTiet sp =
                    chiTietDao.getBySanPhamMauSize(
                            maSP,
                            maMau,
                            maSize
                    );


            if (sp == null) {

                session.setAttribute(
                        "errorCode",
                        "KHONG_CO_BIEN_THE"
                );

                redirectBanHang(
                        request,
                        response
                );

                return;
            }


            // ================= KIỂM TRA TỒN KHO =================

            if (sp.getSoLuongTon() <= 0) {

                session.setAttribute(
                        "errorCode",
                        "SAN_PHAM_HET_HANG"
                );

                redirectBanHang(
                        request,
                        response
                );

                return;
            }


            // ================= LẤY GIỎ HÀNG =================

            List<GioHang> gioHang =
                    (List<GioHang>)
                            session.getAttribute(
                                    "gioHang"
                            );


            if (gioHang == null) {

                gioHang =
                        new ArrayList<>();
            }


            boolean daTonTai = false;


            // ================= KIỂM TRA SẢN PHẨM ĐÃ CÓ =================

            for (GioHang item : gioHang) {

                if (item.getMaSPCT()
                        == sp.getMaSPCT()) {


                    if (item.getSoLuong()
                            < sp.getSoLuongTon()) {

                        item.setSoLuong(
                                item.getSoLuong() + 1
                        );


                        session.setAttribute(
                                "messageCode",
                                "TANG_SO_LUONG"
                        );

                    } else {

                        session.setAttribute(
                                "errorCode",
                                "VUOT_QUA_TON_KHO"
                        );
                    }


                    daTonTai = true;

                    break;
                }
            }


            // ================= THÊM SẢN PHẨM MỚI =================

            if (!daTonTai) {

                GioHang item =
                        new GioHang();


                item.setMaSPCT(
                        sp.getMaSPCT()
                );


                item.setTenSP(
                        sp.getTenSP()
                );


                item.setTenMau(
                        sp.getTenMau()
                );


                item.setTenSize(
                        sp.getTenSize()
                );


                item.setDonGia(
                        sp.getGiaBan().doubleValue()
                );


                item.setSoLuong(1);


                gioHang.add(item);


                session.setAttribute(
                        "messageCode",
                        "THEM_VAO_GIO_HANG"
                );
            }


            session.setAttribute(
                    "gioHang",
                    gioHang
            );


        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "DU_LIEU_KHONG_HOP_LE"
            );


        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_THEM_SAN_PHAM"
            );
        }


        redirectBanHang(
                request,
                response
        );
    }


    // =====================================================
    // CHUYỂN THÔNG BÁO SESSION -> REQUEST
    // =====================================================

    private void chuyenThongBao(
            HttpSession session,
            HttpServletRequest request,
            String tenThuocTinh) {

        Object value =
                session.getAttribute(
                        tenThuocTinh
                );


        if (value != null) {

            request.setAttribute(
                    tenThuocTinh,
                    value
            );

            session.removeAttribute(
                    tenThuocTinh
            );
        }
    }


    // =====================================================
    // KIỂM TRA CHUỖI RỖNG
    // =====================================================

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }


    // =====================================================
    // QUAY LẠI TRANG BÁN HÀNG
    // =====================================================

    private void redirectBanHang(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath()
                        + "/banhang"
        );
    }
}