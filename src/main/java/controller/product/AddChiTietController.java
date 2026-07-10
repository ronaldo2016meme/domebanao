package controller.product;

import dao.SanPhamChiTietDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addSanPhamChiTiet")
public class AddChiTietController extends HttpServlet {

    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addsanphamchitiet.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String maSP = request.getParameter("maSP");
        String maMau = request.getParameter("maMau");
        String maSize = request.getParameter("maSize");
        String soLuongTon = request.getParameter("soLuongTon");
        String giaNhap = request.getParameter("giaNhap");

        // Kiểm tra dữ liệu rỗng
        if (maSP.isEmpty() || maMau.isEmpty() || maSize.isEmpty()
                || soLuongTon.isEmpty() || giaNhap.isEmpty()) {

            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("addsanphamchitiet.jsp")
                    .forward(request, response);
            return;
        }

        SanPhamChiTiet spct = new SanPhamChiTiet();

        spct.setMaSP(Integer.parseInt(maSP));
        spct.setMaMau(maMau);
        spct.setMaSize(maSize);
        spct.setSoLuongTon(Integer.parseInt(soLuongTon));
        spct.setGiaNhap(new BigDecimal(giaNhap));

        dao.insert(spct);

        response.sendRedirect("sanphamchitiet");
    }
}