package controller.product;

import dao.SanPhamChiTietDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/editSanPhamChiTiet")
public class EditChiTietController extends HttpServlet {

    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        SanPhamChiTiet spct = dao.getById(id);

        request.setAttribute("spct", spct);

        request.getRequestDispatcher("editsanphamchitiet.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SanPhamChiTiet spct = new SanPhamChiTiet();

        spct.setMaSPCT(Integer.parseInt(request.getParameter("maSPCT")));
        spct.setMaSP(Integer.parseInt(request.getParameter("maSP")));
        spct.setMaMau(request.getParameter("maMau"));
        spct.setMaSize(request.getParameter("maSize"));
        spct.setSoLuongTon(Integer.parseInt(request.getParameter("soLuongTon")));
        spct.setGiaNhap(new BigDecimal(request.getParameter("giaNhap")));

        dao.update(spct);

        response.sendRedirect("sanphamchitiet");
    }
}