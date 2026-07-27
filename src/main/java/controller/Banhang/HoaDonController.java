package controller.Banhang;

import dao.HoaDonInDao;
import model.HoaDonIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/hoadon")
public class HoaDonController extends HttpServlet {

    HoaDonInDao dao = new HoaDonInDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        int maHD = Integer.parseInt(request.getParameter("maHD"));

        List<HoaDonIn> list = dao.getHoaDon(maHD);

        request.setAttribute("list", list);

        request.getRequestDispatcher("/hoadon.jsp")
                .forward(request, response);

    }
}