package controller;

import dao.NhanVienDao;
import model.TaiKhoan;
import model.NhanVien;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {

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

        if (!"R01".equals(tk.getMaRole())) {
            response.sendRedirect("home");
            return;
        }

        List<NhanVien> list = dao.getAll();

        request.setAttribute("list", list);

        request.getRequestDispatcher("employee.jsp").forward(request, response);
    }
}