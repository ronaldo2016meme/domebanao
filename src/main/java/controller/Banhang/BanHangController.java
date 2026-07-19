package controller.Banhang;

import dao.SanPhamChiTietDao;
import model.GioHang;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/banhang")
public class BanHangController extends HttpServlet {
    SanPhamChiTietDao dao = new SanPhamChiTietDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<SanPhamChiTiet> list = dao.getAll();

        request.setAttribute("listSP", list);

        request.getRequestDispatcher("/banhang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int maSPCT = Integer.parseInt(request.getParameter("maSPCT"));

        SanPhamChiTiet sp = dao.getById(maSPCT);

        HttpSession session = request.getSession();

        List<GioHang> gioHang =
                (List<GioHang>) session.getAttribute("gioHang");

        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }

        boolean tonTai = false;

        for (GioHang item : gioHang) {

            if (item.getMaSPCT() == maSPCT) {

                // Không cho vượt tồn kho
                if (item.getSoLuong() < sp.getSoLuongTon()) {

                    item.setSoLuong(item.getSoLuong() + 1);

                }

                tonTai = true;
                break;
            }
        }

        if (!tonTai) {

            GioHang gh = new GioHang();

            gh.setMaSPCT(sp.getMaSPCT());
            gh.setTenSP(sp.getTenSP());
            gh.setTenMau(sp.getTenMau());
            gh.setTenSize(sp.getTenSize());

            // Giá bán lấy từ bảng SANPHAM
            gh.setDonGia(getGiaBan(sp.getMaSP()));

            gh.setSoLuong(1);

            gioHang.add(gh);
        }

        session.setAttribute("gioHang", gioHang);

        response.sendRedirect("banhang");
    }

    private double getGiaBan(int maSP) {

        try {

            dao.SanPhamDao spDao = new dao.SanPhamDao();

            return spDao.getById(maSP).getGiaBan();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }
}
