package controller.ThongKe;

import dao.ThongKeDao;
import model.DoanhThuNgay;
import model.TopSanPham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/thongke")
public class ThongKeController extends HttpServlet {

    ThongKeDao dao = new ThongKeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tu = req.getParameter("tuNgay");
        String den = req.getParameter("denNgay");

        Date tuNgay;
        Date denNgay;


        if (tu == null || tu.isEmpty() || den == null || den.isEmpty()) {

            LocalDate now = LocalDate.now();

            tuNgay = Date.valueOf(now.withDayOfMonth(1));
            denNgay = Date.valueOf(now);

        } else {

            tuNgay = Date.valueOf(tu);
            denNgay = Date.valueOf(den);

        }

        double tongDoanhThu = dao.getTongDoanhThu(tuNgay, denNgay);

        int tongHoaDon = dao.getTongHoaDon(tuNgay, denNgay);

        int tongSanPham = dao.getTongSanPham(tuNgay, denNgay);

        List<DoanhThuNgay> doanhThuNgay =
                dao.getDoanhThuTheoNgay(tuNgay, denNgay);

        List<TopSanPham> topSanPham =
                dao.getTop5SanPham(tuNgay, denNgay);

        req.setAttribute("tongDoanhThu", tongDoanhThu);

        req.setAttribute("tongHoaDon", tongHoaDon);

        req.setAttribute("tongSanPham", tongSanPham);

        req.setAttribute("doanhThuNgay", doanhThuNgay);

        req.setAttribute("topSanPham", topSanPham);

        req.getRequestDispatcher("/thongke.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
