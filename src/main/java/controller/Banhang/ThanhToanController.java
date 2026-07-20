package controller.Banhang;

import dao.HoaDonDao;
import model.ChiTietHoaDon;
import model.GioHang;
import model.HoaDon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/thanhToan")
public class ThanhToanController extends HttpServlet {

    HoaDonDao dao = new HoaDonDao();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<GioHang> gioHang =
                (List<GioHang>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.isEmpty()) {

            response.sendRedirect("banhang");
            return;
        }

        double tongTien =
                Double.parseDouble(request.getParameter("tongTien"));

        double tienKhachDua =
                Double.parseDouble(request.getParameter("tienKhachDua"));

        if (tienKhachDua < tongTien) {

            request.setAttribute("error", "Tien khach đua khong đu");

            request.getRequestDispatcher("/banhang")
                    .forward(request, response);

            return;
        }

        HoaDon hd = new HoaDon();

        hd.setNgayLap(new Date(System.currentTimeMillis()));
        hd.setTongTien(tongTien);
        hd.setTienKhachDua(tienKhachDua);
        hd.setTienThua(tienKhachDua - tongTien);

        hd.setPhuongThucThanhToan("Tien mat");


        hd.setMaNV(1);

        hd.setMaKH(
                Integer.parseInt(request.getParameter("maKH"))
        );

        hd.setMaTrangThaiHD("TTHD01");

        List<ChiTietHoaDon> list = new ArrayList<>();

        for (GioHang g : gioHang) {

            ChiTietHoaDon ct = new ChiTietHoaDon();

            ct.setMaSPCT(g.getMaSPCT());
            ct.setSoLuong(g.getSoLuong());
            ct.setDonGia(g.getDonGia());
            ct.setThanhTien(g.getThanhTien());

            list.add(ct);
        }

        int maHD = dao.thanhToan(hd, list);

        if (maHD > 0) {

            session.removeAttribute("gioHang");

            response.sendRedirect("hoadon?maHD=" + maHD);
            return;

        } else {

            request.setAttribute("error", "Thanh toán thất bại");

            request.getRequestDispatcher("/banhang.jsp")
                    .forward(request, response);
            return;

        }
    }
}