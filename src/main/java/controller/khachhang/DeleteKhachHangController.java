package controller.khachhang;

import dao.KhachHangDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletekhachhang")
public class DeleteKhachHangController extends HttpServlet {

    KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        dao.delete(id);

        response.sendRedirect("khachhang");
    }
}