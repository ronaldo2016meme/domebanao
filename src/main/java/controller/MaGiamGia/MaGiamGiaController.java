package controller.MaGiamGia;

import dao.MaGiamGiaDao;
import model.MaGiamGia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/maGiamGia")
public class MaGiamGiaController extends HttpServlet {

    private MaGiamGiaDao dao = new MaGiamGiaDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        List<MaGiamGia> list = dao.getAll();

        req.setAttribute("listMaGiamGia", list);

        req.getRequestDispatcher("/maGiamGia.jsp")
                .forward(req, resp);
    }
}