package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/khachhang")
public class KhachHangController extends HttpServlet {

    private final KhachHangDao dao =
            new KhachHangDao();

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

        try {

            String keyword =
                    request.getParameter("keyword");

            /*
             * Fix lỗi tiếng Việt của Tomcat 7 khi dùng GET.
             *
             * Ví dụ:
             * tiến long
             * bị thành
             * tiáº¿n long
             */
            if (keyword != null
                    && !keyword.trim().isEmpty()) {

                if (keyword.contains("Ã")
                        || keyword.contains("á")
                        || keyword.contains("Â")) {

                    keyword =
                            new String(
                                    keyword.getBytes(
                                            StandardCharsets.ISO_8859_1
                                    ),
                                    StandardCharsets.UTF_8
                            );
                }

                keyword = keyword.trim();
            }

            List<KhachHang> list;

            if (keyword == null
                    || keyword.isEmpty()) {

                list = dao.getAll();

            } else {

                list = dao.search(keyword);
            }

            request.setAttribute(
                    "list",
                    list
            );

            request.setAttribute(
                    "keyword",
                    keyword
            );

            request.getRequestDispatcher(
                    "/khachhang.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorCode",
                    "LOI_TAI_KHACH_HANG"
            );

            request.getRequestDispatcher(
                    "/khachhang.jsp"
            ).forward(request, response);
        }
    }
}