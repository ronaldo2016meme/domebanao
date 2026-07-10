package controller.product;

import dao.SanPhamChiTietDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteSanPhamChiTiet")
public class DeleteChiTietController extends HttpServlet {

    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        dao.delete(id);

        response.sendRedirect("sanphamchitiet");
    }
}