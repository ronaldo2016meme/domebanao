package controller.product;

import dao.MauSacDao;
import dao.SanPhamChiTietDao;
import dao.SizeDao;
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

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        String maSPParam =
                request.getParameter("maSP");

        if (maSPParam == null
                || maSPParam.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
            return;
        }

        try {
            int maSP =
                    Integer.parseInt(
                            maSPParam.trim()
                    );

            if (maSP <= 0) {

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

            request.setAttribute(
                    "listMau",
                    mauSacDao.getAll()
            );

            request.setAttribute(
                    "listSize",
                    sizeDao.getAll()
            );

            Object message =
                    session.getAttribute("message");

            if (message != null) {

                request.setAttribute(
                        "message",
                        message
                );

                session.removeAttribute(
                        "message"
                );
            }

            Object error =
                    session.getAttribute("error");

            if (error != null) {

                request.setAttribute(
                        "error",
                        error
                );

                session.removeAttribute(
                        "error"
                );
            }

            if ((list == null || list.isEmpty())
                    && message == null
                    && error == null) {

                request.setAttribute(
                        "message",
                        "Sản phẩm chưa có thông tin chi tiết"
                );
            }

            request.getRequestDispatcher(
                    "/sanphamchitiet.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi tải chi tiết sản phẩm"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );
        }
    }
}