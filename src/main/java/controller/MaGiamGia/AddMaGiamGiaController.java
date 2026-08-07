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

@WebServlet("/addMaGiamGia")
public class AddMaGiamGiaController extends HttpServlet {

    private MaGiamGiaDao dao = new MaGiamGiaDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        req.getRequestDispatcher("/addMaGiamGia.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String maCode = req.getParameter("maCode");
        String tenMGG = req.getParameter("tenMGG");
        String phanTram = req.getParameter("phanTramGiam");
        String diemCan = req.getParameter("diemCan");
        String ngayBatDau = req.getParameter("ngayBatDau");
        String ngayKetThuc = req.getParameter("ngayKetThuc");
        String soLuong = req.getParameter("soLuong");
        String trangThai = req.getParameter("trangThai");

        try {

            MaGiamGia m = new MaGiamGia();

            m.setMaCode(maCode);
            m.setTenMGG(tenMGG);

            m.setPhanTramGiam(
                    Integer.parseInt(phanTram)
            );

            m.setDiemCan(
                    Integer.parseInt(diemCan)
            );

            m.setNgayBatDau(
                    Date.valueOf(ngayBatDau)
            );

            m.setNgayKetThuc(
                    Date.valueOf(ngayKetThuc)
            );

            m.setSoLuong(
                    Integer.parseInt(soLuong)
            );

            // checkbox/select gửi "1" hoặc "0"
            boolean trangThaiValue =
                    "1".equals(trangThai);

            m.setTrangThai(trangThaiValue);

            dao.insert(m);

            resp.sendRedirect(
                    req.getContextPath() + "/maGiamGia"
            );

        } catch (Exception e) {

            e.printStackTrace();

            req.setAttribute(
                    "error",
                    "Dữ liệu nhập không hợp lệ!"
            );

            req.getRequestDispatcher(
                    "/addMaGiamGia.jsp"
            ).forward(req, resp);
        }
    }
}