package controller;

import dao.NhanVienDao;
import model.NhanVien;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class profileController extends HttpServlet {

    NhanVienDao dao = new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        TaiKhoan tk = (TaiKhoan) session.getAttribute("user");

        NhanVien nv = dao.findByMaNV(tk.getMaNV());

        request.setAttribute("nv", nv);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
