package controller.product;

import dao.MauSacDao;
import dao.SanPhamChiTietDao;
import dao.SizeDao;
import model.SanPhamChiTiet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addSanPhamChiTiet")
public class AddChiTietController extends HttpServlet {

    private final SanPhamChiTietDao dao = new SanPhamChiTietDao();
    private final MauSacDao mauSacDao = new MauSacDao();
    private final SizeDao sizeDao = new SizeDao();

    private void loadComboboxData(HttpServletRequest request) {
        request.setAttribute("listMau", mauSacDao.getAll());
        request.setAttribute("listSize", sizeDao.getAll());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        loadComboboxData(request);

        request.getRequestDispatcher("addSanPhamChiTiet.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String maSP = request.getParameter("maSP");
        String maMau = request.getParameter("maMau");
        String maSize = request.getParameter("maSize");
        String soLuongTon = request.getParameter("soLuongTon");
        String giaNhap = request.getParameter("giaNhap");

        if (maSP == null || maSP.trim().isEmpty()
                || maMau == null || maMau.trim().isEmpty()
                || maSize == null || maSize.trim().isEmpty()
                || soLuongTon == null || soLuongTon.trim().isEmpty()
                || giaNhap == null || giaNhap.trim().isEmpty()) {

            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            loadComboboxData(request);

            request.getRequestDispatcher("addSanPhamChiTiet.jsp")
                    .forward(request, response);
            return;
        }

        try {
            int maSPInt = Integer.parseInt(maSP);
            int soLuongTonInt = Integer.parseInt(soLuongTon);
            BigDecimal giaNhapBD = new BigDecimal(giaNhap);

            if (soLuongTonInt < 0) {
                request.setAttribute("error",
                        "Số lượng tồn phải lớn hơn hoặc bằng 0!");
                loadComboboxData(request);

                request.getRequestDispatcher("addSanPhamChiTiet.jsp")
                        .forward(request, response);
                return;
            }

            if (giaNhapBD.compareTo(BigDecimal.ZERO) < 0) {
                request.setAttribute("error",
                        "Giá nhập phải lớn hơn hoặc bằng 0!");
                loadComboboxData(request);

                request.getRequestDispatcher("addSanPhamChiTiet.jsp")
                        .forward(request, response);
                return;
            }

            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setMaSP(maSPInt);
            spct.setMaMau(maMau);
            spct.setMaSize(maSize);
            spct.setSoLuongTon(soLuongTonInt);
            spct.setGiaNhap(giaNhapBD);

            dao.insert(spct);

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanphamchitiet?maSP="
                            + spct.getMaSP()
            );

        } catch (NumberFormatException e) {
            request.setAttribute("error",
                    "Mã sản phẩm, số lượng tồn hoặc giá nhập không hợp lệ!");
            loadComboboxData(request);

            request.getRequestDispatcher("addSanPhamChiTiet.jsp")
                    .forward(request, response);
        }
    }
}