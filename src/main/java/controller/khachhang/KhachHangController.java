package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/khachhang")
public class KhachHangController extends HttpServlet {

    KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<KhachHang> list = dao.getAll();

        request.setAttribute("list", list);

        request.getRequestDispatcher("khachhang.jsp")
                .forward(request, response);
    }
}