package controller.product;

import dao.SanPhamChiTietDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/sanphamchitiet")
public class SanPhamChiTietController extends HttpServlet {

    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int maSP = Integer.parseInt(request.getParameter("maSP"));

        List<SanPhamChiTiet> list = dao.getByMaSP(maSP);

        request.setAttribute("list", list);
        request.setAttribute("maSP", maSP);

        request.getRequestDispatcher("sanphamchitiet.jsp")
                .forward(request, response);
    }
}