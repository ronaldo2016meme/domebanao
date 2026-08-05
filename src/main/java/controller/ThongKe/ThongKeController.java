package controller.ThongKe;

import dao.ThongKeDao;
import model.DoanhThuNgay;
import model.TaiKhoan;
import model.TopSanPham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/thongke")
public class ThongKeController extends HttpServlet {

    ThongKeDao dao = new ThongKeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        HttpSession session = req.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

// Chỉ quản lý được xem doanh thu
        TaiKhoan user = (TaiKhoan) session.getAttribute("user");

        if (!"R01".equals(user.getMaRole())) {

            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }



        String kieu = req.getParameter("kieu");

        if (kieu == null || kieu.isEmpty()) {
            kieu = "thang";
        }


        LocalDate tu;
        LocalDate den;


        // =========================
        // THEO NGÀY
        // tuNgay -> denNgay
        // =========================

        if (kieu.equals("ngay")) {

            String tuNgayParam = req.getParameter("tuNgay");
            String denNgayParam = req.getParameter("denNgay");


            if (tuNgayParam == null || tuNgayParam.isEmpty()
                    || denNgayParam == null || denNgayParam.isEmpty()) {

                LocalDate now = LocalDate.now();

                tu = now.withDayOfMonth(1);
                den = now;

            } else {

                tu = LocalDate.parse(tuNgayParam);
                den = LocalDate.parse(denNgayParam);
            }


            // Giữ lại ngày đã chọn cho JSP
            req.setAttribute(
                    "tuNgay",
                    tu.toString()
            );

            req.setAttribute(
                    "denNgay",
                    den.toString()
            );
        }


        // =========================
        // THEO THÁNG
        // thang -> tự tính ngày đầu/cuối tháng
        // =========================
        else if (kieu.equals("thang")) {

            String thangParam = req.getParameter("thang");

            LocalDate now = LocalDate.now();

            if (thangParam == null || thangParam.isEmpty()) {

                // Mặc định tháng hiện tại

                tu = now.withDayOfMonth(1);

                den = now.withDayOfMonth(
                        now.lengthOfMonth()
                );

                thangParam = String.format(
                        "%04d-%02d",
                        now.getYear(),
                        now.getMonthValue()
                );

            } else {

                // thang có dạng yyyy-MM
                // Ví dụ: 2026-08

                LocalDate date =
                        LocalDate.parse(
                                thangParam + "-01"
                        );

                tu = date.withDayOfMonth(1);

                den = date.withDayOfMonth(
                        date.lengthOfMonth()
                );
            }


            // Giữ lại tháng đã chọn

            req.setAttribute(
                    "thang",
                    thangParam
            );
        }


        // =========================
        // THEO NĂM
        // nam -> 01/01 -> 31/12
        // =========================

        else {

            String namParam = req.getParameter("nam");

            int nam;

            if (namParam == null || namParam.isEmpty()) {

                nam = LocalDate.now().getYear();

            } else {

                nam = Integer.parseInt(namParam);
            }


            tu = LocalDate.of(
                    nam,
                    1,
                    1
            );

            den = LocalDate.of(
                    nam,
                    12,
                    31
            );


            // Giữ lại năm đã chọn

            req.setAttribute(
                    "nam",
                    nam
            );
        }


        // =========================
        // JAVA SQL DATE
        // =========================

        Date tuNgay = Date.valueOf(tu);
        Date denNgay = Date.valueOf(den);


        // =========================
        // TỔNG THỐNG KÊ
        // =========================

        double tongDoanhThu =
                dao.getTongDoanhThu(
                        tuNgay,
                        denNgay
                );

        int tongHoaDon =
                dao.getTongHoaDon(
                        tuNgay,
                        denNgay
                );

        int tongSanPham =
                dao.getTongSanPham(
                        tuNgay,
                        denNgay
                );


        // =========================
        // BIỂU ĐỒ
        // =========================

        List<DoanhThuNgay> doanhThuNgay;

        if (kieu.equals("nam")) {

            // Theo năm:
            // 01/2026
            // 02/2026
            // ...
            // 12/2026

            doanhThuNgay =
                    dao.getDoanhThuTheoThang(
                            tuNgay,
                            denNgay
                    );

        } else {

            // Theo ngày:
            // từng ngày

            // Theo tháng:
            // cũng từng ngày trong tháng

            doanhThuNgay =
                    dao.getDoanhThuTheoNgay(
                            tuNgay,
                            denNgay
                    );
        }


        // =========================
        // TOP 5 SẢN PHẨM
        // =========================

        List<TopSanPham> topSanPham =
                dao.getTop5SanPham(
                        tuNgay,
                        denNgay
                );


        // =========================
        // GỬI SANG JSP
        // =========================

        req.setAttribute(
                "kieu",
                kieu
        );

        req.setAttribute(
                "tongDoanhThu",
                tongDoanhThu
        );

        req.setAttribute(
                "tongHoaDon",
                tongHoaDon
        );

        req.setAttribute(
                "tongSanPham",
                tongSanPham
        );

        req.setAttribute(
                "doanhThuNgay",
                doanhThuNgay
        );

        req.setAttribute(
                "topSanPham",
                topSanPham
        );


        // =========================
        // FORWARD
        // =========================

        req.getRequestDispatcher(
                "/thongke.jsp"
        ).forward(req, resp);
    }


    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        doGet(req, resp);
    }
}