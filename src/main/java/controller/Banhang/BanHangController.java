package controller.Banhang;

import dao.MauSacDao;
import dao.SanPhamChiTietDao;
import dao.SanPhamDao;
import dao.SizeDao;
import model.GioHang;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        request.setAttribute(
                "listSP",
                sanPhamDao.getAll()
        );

        request.setAttribute(
                "listChiTiet",
                chiTietDao.getAll()
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
         * Chuyen ma loi tu session sang request.
         */
        if (session.getAttribute("errorCode") != null) {

            request.setAttribute(
                    "errorCode",
                    session.getAttribute("errorCode")
            );

            session.removeAttribute("errorCode");
        }

        /*
         * Chuyen ma thong bao tu session sang request.
         */
        if (session.getAttribute("messageCode") != null) {

            request.setAttribute(
                    "messageCode",
                    session.getAttribute("messageCode")
            );

            session.removeAttribute("messageCode");
        }

        request.getRequestDispatcher("/banhang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        try {

            int maSP = Integer.parseInt(
                    request.getParameter("maSP")
            );

            String maMau =
                    request.getParameter("maMau");

            String maSize =
                    request.getParameter("maSize");

            if (maMau == null
                    || maMau.trim().isEmpty()
                    || maSize == null
                    || maSize.trim().isEmpty()) {

                session.setAttribute(
                        "errorCode",
                        "CHUA_CHON_MAU_SIZE"
                );

                redirectBanHang(request, response);
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
                        "errorCode",
                        "KHONG_CO_BIEN_THE"
                );

                redirectBanHang(request, response);
                return;
            }

            if (sp.getSoLuongTon() <= 0) {

                session.setAttribute(
                        "errorCode",
                        "SAN_PHAM_HET_HANG"
                );

                redirectBanHang(request, response);
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
                                "messageCode",
                                "TANG_SO_LUONG"
                        );

                    } else {

                        session.setAttribute(
                                "errorCode",
                                "VUOT_QUA_TON_KHO"
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

        redirectBanHang(request, response);
    }

    private void redirectBanHang(HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + "/banhang"
        );
    }

    private double getGiaBan(int maSP) {

        try {

            if (sanPhamDao.getById(maSP) != null) {

                return sanPhamDao
                        .getById(maSP)
                        .getGiaBan();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}