package controller.Banhang;

import dao.SanPhamChiTietDao;
import model.GioHang;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/capNhatGioHang")
public class CapNhatGioHangController extends HttpServlet {

    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<GioHang> gioHang =
                (List<GioHang>) session.getAttribute("gioHang");

        if (gioHang == null) {
            response.sendRedirect("banhang");
            return;
        }

        int maSPCT = Integer.parseInt(request.getParameter("maSPCT"));
        String action = request.getParameter("action");

        SanPhamChiTiet sp = dao.getById(maSPCT);

        Iterator<GioHang> iterator = gioHang.iterator();

        while (iterator.hasNext()) {

            GioHang item = iterator.next();

            if (item.getMaSPCT() == maSPCT) {

                if ("tang".equals(action)) {

                    // Không vượt quá tồn kho
                    if (item.getSoLuong() < sp.getSoLuongTon()) {

                        item.setSoLuong(item.getSoLuong() + 1);

                    }

                } else if ("giam".equals(action)) {

                    if (item.getSoLuong() > 1) {

                        item.setSoLuong(item.getSoLuong() - 1);

                    }

                }

                break;
            }
        }

        session.setAttribute("gioHang", gioHang);

        response.sendRedirect("banhang");

    }
}