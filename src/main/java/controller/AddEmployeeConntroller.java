package controller;

import dao.NhanVienDao;
import model.NhanVien;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addEmployee")
public class AddEmployeeConntroller extends HttpServlet {

    NhanVienDao dao = new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        NhanVien nv = new NhanVien();

        nv.setHoTen(request.getParameter("hoTen"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setEmail(request.getParameter("email"));
        nv.setCccd(request.getParameter("cccd"));
        nv.setMaTrangThai(request.getParameter("maTrangThai"));

        dao.insert(nv);

        response.sendRedirect("employee");
    }
}