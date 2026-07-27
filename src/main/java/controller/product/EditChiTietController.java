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

@WebServlet("/editSanPhamChiTiet")
public class EditChiTietController extends HttpServlet {

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

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            SanPhamChiTiet spct = dao.getById(id);

            if (spct == null) {
                response.sendRedirect(
                        request.getContextPath() + "/sanphamchitiet"
                );
                return;
            }

            request.setAttribute("spct", spct);
            loadComboboxData(request);

            request.getRequestDispatcher("editSanPhamChiTiet.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(
                    request.getContextPath() + "/sanphamchitiet"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        SanPhamChiTiet spct = new SanPhamChiTiet();

        try {
            spct.setMaSPCT(
                    Integer.parseInt(request.getParameter("maSPCT"))
            );

            spct.setMaSP(
                    Integer.parseInt(request.getParameter("maSP"))
            );

            spct.setMaMau(request.getParameter("maMau"));
            spct.setMaSize(request.getParameter("maSize"));

            int soLuongTon = Integer.parseInt(
                    request.getParameter("soLuongTon")
            );

            BigDecimal giaNhap = new BigDecimal(
                    request.getParameter("giaNhap")
            );

            spct.setSoLuongTon(soLuongTon);
            spct.setGiaNhap(giaNhap);

            if (spct.getMaMau() == null
                    || spct.getMaMau().trim().isEmpty()
                    || spct.getMaSize() == null
                    || spct.getMaSize().trim().isEmpty()) {

                request.setAttribute("error", "Vui lòng chọn màu sắc và size!");
                request.setAttribute("spct", spct);
                loadComboboxData(request);

                request.getRequestDispatcher("editSanPhamChiTiet.jsp")
                        .forward(request, response);
                return;
            }

            if (soLuongTon < 0) {
                request.setAttribute("error",
                        "Số lượng tồn phải lớn hơn hoặc bằng 0!");
                request.setAttribute("spct", spct);
                loadComboboxData(request);

                request.getRequestDispatcher("editSanPhamChiTiet.jsp")
                        .forward(request, response);
                return;
            }

            if (giaNhap.compareTo(BigDecimal.ZERO) < 0) {
                request.setAttribute("error",
                        "Giá nhập phải lớn hơn hoặc bằng 0!");
                request.setAttribute("spct", spct);
                loadComboboxData(request);

                request.getRequestDispatcher("editSanPhamChiTiet.jsp")
                        .forward(request, response);
                return;
            }

            dao.update(spct);

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanphamchitiet?maSP="
                            + spct.getMaSP()
            );

        } catch (NumberFormatException e) {
            request.setAttribute("error",
                    "Số lượng tồn hoặc giá nhập không hợp lệ!");
            request.setAttribute("spct", spct);
            loadComboboxData(request);

            request.getRequestDispatcher("editSanPhamChiTiet.jsp")
                    .forward(request, response);
        }
    }
}