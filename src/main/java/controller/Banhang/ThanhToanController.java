package controller.Banhang;

import dao.HoaDonDao;
import dao.KhachHangDao;
import model.ChiTietHoaDon;
import model.GioHang;
import model.HoaDon;
import model.KhachHang;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/thanhToan")
public class ThanhToanController extends HttpServlet {

    private final HoaDonDao dao =
            new HoaDonDao();

    private final KhachHangDao khDao =
            new KhachHangDao();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session =
                request.getSession(false);

        /*
         * Kiểm tra đăng nhập.
         */
        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        /*
         * Lấy đúng nhân viên đang đăng nhập.
         */
        TaiKhoan taiKhoan =
                (TaiKhoan) session.getAttribute("user");

        List<GioHang> gioHang =
                (List<GioHang>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.isEmpty()) {

            session.setAttribute(
                    "errorCode",
                    "GIO_HANG_TRONG"
            );

            response.sendRedirect(
                    request.getContextPath() + "/banhang"
            );
            return;
        }

        try {

            double tongTien =
                    Double.parseDouble(
                            request.getParameter("tongTien")
                    );

            double tienKhachDua =
                    Double.parseDouble(
                            request.getParameter("tienKhachDua")
                    );

            if (tongTien <= 0) {

                session.setAttribute(
                        "errorCode",
                        "TONG_TIEN_KHONG_HOP_LE"
                );

                response.sendRedirect(
                        request.getContextPath() + "/banhang"
                );
                return;
            }

            if (tienKhachDua < tongTien) {

                session.setAttribute(
                        "errorCode",
                        "TIEN_KHACH_DUA_KHONG_DU"
                );

                response.sendRedirect(
                        request.getContextPath() + "/banhang"
                );
                return;
            }

            HoaDon hd =
                    new HoaDon();

            hd.setNgayLap(
                    new Date(System.currentTimeMillis())
            );

            hd.setTongTien(tongTien);
            hd.setTienKhachDua(tienKhachDua);
            hd.setTienThua(tienKhachDua - tongTien);

            hd.setPhuongThucThanhToan(
                    "Tiền mặt"
            );

            /*
             * DÒNG QUAN TRỌNG:
             * Không dùng hd.setMaNV(1).
             */
            hd.setMaNV(
                    taiKhoan.getMaNV()
            );

            String sdt =
                    request.getParameter("soDienThoai");

            if (sdt == null
                    || sdt.trim().isEmpty()) {

                hd.setMaKH(null);

            } else {

                KhachHang kh =
                        khDao.getBySoDienThoai(
                                sdt.trim()
                        );

                if (kh != null) {
                    hd.setMaKH(kh.getMaKH());
                } else {
                    hd.setMaKH(null);
                }
            }

            hd.setMaTrangThaiHD(
                    "TTHD01"
            );

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

                ct.setDonGia(
                        g.getDonGia()
                );

                ct.setThanhTien(
                        g.getThanhTien()
                );

                list.add(ct);
            }

            int maHD =
                    dao.thanhToan(
                            hd,
                            list
                    );

            if (maHD > 0) {

                session.removeAttribute(
                        "gioHang"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/hoadon?maHD="
                                + maHD
                );
                return;
            }

            session.setAttribute(
                    "errorCode",
                    "THANH_TOAN_THAT_BAI"
            );

            response.sendRedirect(
                    request.getContextPath() + "/banhang"
            );

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "DU_LIEU_THANH_TOAN_KHONG_HOP_LE"
            );

            response.sendRedirect(
                    request.getContextPath() + "/banhang"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_THANH_TOAN"
            );

            response.sendRedirect(
                    request.getContextPath() + "/banhang"
            );
        }
    }
}