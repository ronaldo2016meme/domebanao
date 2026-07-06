package controller;

import dao.NhanVienDao;
import model.NhanVien;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {

    NhanVienDao dao=new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));

        NhanVien nv=dao.getById(id);

        request.setAttribute("nv",nv);

        request.getRequestDispatcher("editEmployee.jsp")
                .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        NhanVien nv=new NhanVien();

        nv.setMaNV(Integer.parseInt(request.getParameter("maNV")));
        nv.setHoTen(request.getParameter("hoTen"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setEmail(request.getParameter("email"));
        nv.setCccd(request.getParameter("cccd"));
        nv.setMaTrangThai(request.getParameter("maTrangThai"));

        dao.update(nv);

        response.sendRedirect("employee");
    }
}