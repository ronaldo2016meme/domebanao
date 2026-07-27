package controller.Banhang;

import dao.MauSacDao;
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

@WebServlet("/banhang")
public class BanHangController extends HttpServlet {

    private final SanPhamChiTietDao chiTietDao =
            new SanPhamChiTietDao();

    private final SanPhamDao sanPhamDao =
            new SanPhamDao();

    private final MauSacDao mauSacDao =
            new MauSacDao();

    private final SizeDao sizeDao =
            new SizeDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect("login");
            return;
        }

        /*
         * Lấy sản phẩm chính từ bảng SANPHAM.
         * Không lấy từ SANPHAMCHITIET nữa.
         */
        request.setAttribute(
                "listSP",
                sanPhamDao.getAll()
        );

        request.setAttribute(
                "listMau",
                mauSacDao.getAll()
        );

        request.setAttribute(
                "listSize",
                sizeDao.getAll()
        );

        /*
         * Chuyển lỗi từ session sang request
         * để JSP có thể hiển thị sau redirect.
         */
        if (session.getAttribute("error") != null) {

            request.setAttribute(
                    "error",
                    session.getAttribute("error")
            );

            session.removeAttribute("error");
        }

        if (session.getAttribute("message") != null) {

            request.setAttribute(
                    "message",
                    session.getAttribute("message")
            );

            session.removeAttribute("message");
        }

        request.getRequestDispatcher("/banhang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect("login");
            return;
        }

        try {

            int maSP = Integer.parseInt(
                    request.getParameter("maSP")
            );

            /*
             * Trong model SanPhamChiTiet,
             * maMau và maSize là String.
             */
            String maMau =
                    request.getParameter("maMau");

            String maSize =
                    request.getParameter("maSize");

            if (maMau == null
                    || maMau.trim().isEmpty()
                    || maSize == null
                    || maSize.trim().isEmpty()) {

                session.setAttribute(
                        "error",
                        "Vui long chon day du mau va size"
                );

                response.sendRedirect("banhang");
                return;
            }

            SanPhamChiTiet sp =
                    chiTietDao.getBySanPhamMauSize(
                            maSP,
                            maMau,
                            maSize
                    );

            if (sp == null) {

                session.setAttribute(
                        "error",
                        "San pham khong co mau va size da chon"
                );

                response.sendRedirect("banhang");
                return;
            }

            if (sp.getSoLuongTon() <= 0) {

                session.setAttribute(
                        "error",
                        "San pham da het hang"
                );

                response.sendRedirect("banhang");
                return;
            }

            int maSPCT = sp.getMaSPCT();

            List<GioHang> gioHang =
                    (List<GioHang>)
                            session.getAttribute("gioHang");

            if (gioHang == null) {
                gioHang = new ArrayList<>();
            }

            boolean tonTai = false;

            for (GioHang item : gioHang) {

                if (item.getMaSPCT() == maSPCT) {

                    if (item.getSoLuong()
                            < sp.getSoLuongTon()) {

                        item.setSoLuong(
                                item.getSoLuong() + 1
                        );

                        session.setAttribute(
                                "message",
                                "Da tang so luong san pham"
                        );

                    } else {

                        session.setAttribute(
                                "error",
                                "So luong trong gio da dat muc ton kho"
                        );
                    }

                    tonTai = true;
                    break;
                }
            }

            if (!tonTai) {

                GioHang gh = new GioHang();

                gh.setMaSPCT(sp.getMaSPCT());
                gh.setTenSP(sp.getTenSP());
                gh.setTenMau(sp.getTenMau());
                gh.setTenSize(sp.getTenSize());
                gh.setDonGia(
                        getGiaBan(sp.getMaSP())
                );
                gh.setSoLuong(1);

                gioHang.add(gh);

                session.setAttribute(
                        "message",
                        "Da them san pham vao gio hang"
                );
            }

            session.setAttribute(
                    "gioHang",
                    gioHang
            );

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "error",
                    "Du lieu san pham khong hop le"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "error",
                    "Co loi xay ra khi them san pham"
            );
        }

        response.sendRedirect("banhang");
    }

    private double getGiaBan(int maSP) {

        try {

            return sanPhamDao
                    .getById(maSP)
                    .getGiaBan();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}