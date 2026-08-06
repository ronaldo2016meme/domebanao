package controller;

import dao.TaiKhoanDao;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class loginController extends HttpServlet {

    private final TaiKhoanDao dao =
            new TaiKhoanDao();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        if (username == null
                || username.trim().isEmpty()
                || password == null
                || password.trim().isEmpty()) {

            request.setAttribute(
                    "errorCode",
                    "THIEU_THONG_TIN"
            );

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        TaiKhoan tk =
                dao.login(
                        username.trim(),
                        password
                );

        if (tk == null) {

            request.setAttribute(
                    "errorCode",
                    "SAI_TAI_KHOAN_MAT_KHAU"
            );

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        /*
         * Chặn nhân viên đã nghỉ việc.
         * Cần model TaiKhoan có getMaTrangThaiNV().
         */
        if ("TTNV02".equals(
                tk.getMaTrangThaiNV()
        )) {

            request.setAttribute(
                    "errorCode",
                    "NHAN_VIEN_DA_NGHI_VIEC"
            );

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        /*
         * Chặn tài khoản đã khóa nếu bảng TaiKhoan có TrangThai.
         */
        if (!tk.isTrangThai()) {

            request.setAttribute(
                    "errorCode",
                    "TAI_KHOAN_BI_KHOA"
            );

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();

        session.setAttribute("user",tk);

        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }
}