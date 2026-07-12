package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addKhachHang")
public class AddKhachHangController extends HttpServlet {

    KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addKhachHang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hoTen = request.getParameter("hoTen");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");

        KhachHang kh = new KhachHang();

        kh.setHoTen(hoTen);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);

        dao.insert(kh);

        response.sendRedirect("khachhang");
    }
}