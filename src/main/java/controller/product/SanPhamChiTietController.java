package controller.product;

import dao.SanPhamChiTietDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/sanphamchitiet")
public class SanPhamChiTietController extends HttpServlet {

    private final SanPhamChiTietDao dao =
            new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        /*
         * Bắt buộc đăng nhập mới được truy cập.
         * Nếu dự án của bạn chưa dùng đăng nhập,
         * có thể bỏ đoạn kiểm tra session này.
         */
        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        String maSPParam =
                request.getParameter("maSP");

        /*
         * Kiểm tra trường hợp không truyền maSP:
         * /sanphamchitiet
         */
        if (maSPParam == null
                || maSPParam.trim().isEmpty()) {

            session.setAttribute(
                    "error",
                    "Không tìm thấy mã sản phẩm"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
            return;
        }

        try {
            int maSP =
                    Integer.parseInt(maSPParam.trim());

            /*
             * Không cho phép mã sản phẩm bằng 0
             * hoặc là số âm.
             */
            if (maSP <= 0) {

                session.setAttribute(
                        "error",
                        "Mã sản phẩm không hợp lệ"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanpham"
                );
                return;
            }

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

            /*
             * Hiển thị thông báo nếu sản phẩm
             * chưa có màu và size.
             */
            if (list == null || list.isEmpty()) {
                request.setAttribute(
                        "message",
                        "Sản phẩm chưa có thông tin chi tiết"
                );
            }

            request.getRequestDispatcher(
                    "/sanphamchitiet.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "error",
                    "Mã sản phẩm không đúng định dạng"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi tải chi tiết sản phẩm"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
        }
    }
}