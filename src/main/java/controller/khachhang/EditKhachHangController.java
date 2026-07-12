package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editKhachHang")
public class EditKhachHangController extends HttpServlet {

    KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        KhachHang kh = dao.getById(id);

        request.setAttribute("kh", kh);

        request.getRequestDispatcher("editKhachHang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        KhachHang kh = new KhachHang();

        kh.setMaKH(Integer.parseInt(request.getParameter("maKH")));
        kh.setHoTen(request.getParameter("hoTen"));
        kh.setSdt(request.getParameter("sdt"));
        kh.setDiaChi(request.getParameter("diaChi"));

        dao.update(kh);

        response.sendRedirect("khachhang");
    }
}