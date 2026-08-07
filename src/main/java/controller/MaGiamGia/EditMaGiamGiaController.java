package controller.MaGiamGia;

import dao.MaGiamGiaDao;
import model.MaGiamGia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/editMaGiamGia")
public class EditMaGiamGiaController extends HttpServlet {

    private MaGiamGiaDao dao = new MaGiamGiaDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        try {

            int maMGG = Integer.parseInt(
                    req.getParameter("maMGG")
            );

            MaGiamGia m = dao.getById(maMGG);

            if (m == null) {
                resp.sendRedirect("maGiamGia");
                return;
            }

            req.setAttribute("mgg", m);

            req.getRequestDispatcher("/editMaGiamGia.jsp")
                    .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect("maGiamGia");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            MaGiamGia m = new MaGiamGia();

            m.setMaMGG(
                    Integer.parseInt(
                            req.getParameter("maMGG")
                    )
            );

            m.setMaCode(
                    req.getParameter("maCode")
            );

            m.setTenMGG(
                    req.getParameter("tenMGG")
            );

            m.setPhanTramGiam(
                    Integer.parseInt(
                            req.getParameter("phanTramGiam")
                    )
            );

            m.setDiemCan(
                    Integer.parseInt(
                            req.getParameter("diemCan")
                    )
            );

            m.setNgayBatDau(
                    Date.valueOf(
                            req.getParameter("ngayBatDau")
                    )
            );

            m.setNgayKetThuc(
                    Date.valueOf(
                            req.getParameter("ngayKetThuc")
                    )
            );

            m.setSoLuong(
                    Integer.parseInt(
                            req.getParameter("soLuong")
                    )
            );

            // 1 = đang hoạt động
            // 0 = ngừng hoạt động
            boolean trangThai =
                    "1".equals(req.getParameter("trangThai"));

            m.setTrangThai(trangThai);

            dao.update(m);

            resp.sendRedirect(
                    req.getContextPath() + "/maGiamGia"
            );

        } catch (Exception e) {

            e.printStackTrace();

            req.setAttribute(
                    "error",
                    "Dữ liệu sửa không hợp lệ!"
            );

            req.getRequestDispatcher(
                    "/editMaGiamGia.jsp"
            ).forward(req, resp);
        }
    }
}