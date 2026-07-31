package controller.hoadon;

import dao.HoaDonDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/danhSachHoaDon")
public class DanhSachHoaDonController extends HttpServlet {

    private final HoaDonDao hoaDonDao = new HoaDonDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute(
                "listHoaDon",
                hoaDonDao.getAllHoaDon()
        );

        request.getRequestDispatcher(
                "/danhSachHoaDon.jsp"
        ).forward(request, response);
    }
}