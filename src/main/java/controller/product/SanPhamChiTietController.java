package controller.product;

import dao.MauSacDao;
import dao.SanPhamChiTietDao;
import dao.SizeDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/sanphamchitiet")
public class SanPhamChiTietController extends HttpServlet {

    private final SanPhamChiTietDao dao =
            new SanPhamChiTietDao();

    private final MauSacDao mauSacDao =
            new MauSacDao();

    private final SizeDao sizeDao =
            new SizeDao();

    @Override
    protected void doGet(
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

        try {

            String maSPParam =
                    request.getParameter("maSP");

            /*
             * Không có mã sản phẩm.
             */
            if (isBlank(maSPParam)) {

                session.setAttribute(
                        "errorCode",
                        "THIEU_MA_SAN_PHAM"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
                );
                return;
            }

            int maSP =
                    Integer.parseInt(
                            maSPParam.trim()
                    );

            /*
             * Mã sản phẩm không hợp lệ.
             */
            if (maSP <= 0) {

                session.setAttribute(
                        "errorCode",
                        "MA_SAN_PHAM_KHONG_HOP_LE"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
                );
                return;
            }

            /*
             * Lấy danh sách sản phẩm chi tiết.
             */
            List<SanPhamChiTiet> list =
                    dao.getByMaSP(maSP);

            request.setAttribute(
                    "list",
                    list
            );

            request.setAttribute(
                    "maSP",
                    maSP
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
             * Chuyển thông báo từ session sang request.
             */
            chuyenThongBao(
                    session,
                    request,
                    "messageCode"
            );

            chuyenThongBao(
                    session,
                    request,
                    "errorCode"
            );

            /*
             * Sản phẩm chưa có biến thể.
             */
            if (list == null || list.isEmpty()) {

                if (request.getAttribute("messageCode") == null
                        && request.getAttribute("errorCode") == null) {

                    request.setAttribute(
                            "messageCode",
                            "CHUA_CO_SPCT"
                    );
                }
            }

            request.getRequestDispatcher(
                    "/sanphamchitiet.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "MA_SAN_PHAM_KHONG_HOP_LE"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_TAI_DANH_SACH_SPCT"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
        }
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }

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
}