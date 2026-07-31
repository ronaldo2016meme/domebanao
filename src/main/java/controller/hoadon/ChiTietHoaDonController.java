package controller.hoadon;

import dao.HoaDonDao;
import model.HoaDon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chitiethoadon")
public class ChiTietHoaDonController extends HttpServlet {

    private final HoaDonDao hoaDonDao = new HoaDonDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            int maHD = Integer.parseInt(
                    request.getParameter("maHD")
            );

            HoaDon hoaDon =
                    hoaDonDao.getHoaDonById(maHD);

            if (hoaDon == null) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/danhSachHoaDon"
                );
                return;
            }

            request.setAttribute(
                    "hoaDon",
                    hoaDon
            );

            request.setAttribute(
                    "listChiTiet",
                    hoaDonDao.getChiTietHoaDon(maHD)
            );

            request.getRequestDispatcher(
                    "/chitiethoadon.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/danhSachHoaDon"
            );
        }
    }
}