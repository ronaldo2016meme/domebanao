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

    private final SanPhamChiTietDao dao =
            new SanPhamChiTietDao();

    private final MauSacDao mauSacDao =
            new MauSacDao();

    private final SizeDao sizeDao =
            new SizeDao();

    private void loadComboboxData(
            HttpServletRequest request) {

        request.setAttribute(
                "listMau",
                mauSacDao.getAll()
        );

        request.setAttribute(
                "listSize",
                sizeDao.getAll()
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        try {
            String idParam =
                    request.getParameter("id");

            if (idParam == null
                    || idParam.trim().isEmpty()) {

                session.setAttribute(
                        "error",
                        "Không tìm thấy mã sản phẩm chi tiết"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanpham"
                );
                return;
            }

            int id =
                    Integer.parseInt(idParam.trim());

            SanPhamChiTiet spct =
                    dao.getById(id);

            if (spct == null) {

                session.setAttribute(
                        "error",
                        "Không tìm thấy sản phẩm chi tiết"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanpham"
                );
                return;
            }

            request.setAttribute(
                    "spct",
                    spct
            );

            loadComboboxData(request);

            request.getRequestDispatcher(
                    "/editSanPhamChiTiet.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "error",
                    "Mã sản phẩm chi tiết không hợp lệ"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi tải dữ liệu"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session =
                request.getSession();

        SanPhamChiTiet spct =
                new SanPhamChiTiet();

        try {
            String maSPCTParam =
                    request.getParameter("maSPCT");

            String maSPParam =
                    request.getParameter("maSP");

            String soLuongParam =
                    request.getParameter("soLuongTon");

            String giaNhapParam =
                    request.getParameter("giaNhap");

            if (maSPCTParam == null
                    || maSPParam == null
                    || soLuongParam == null
                    || giaNhapParam == null) {

                throw new IllegalArgumentException(
                        "Thiếu dữ liệu cập nhật"
                );
            }

            spct.setMaSPCT(
                    Integer.parseInt(
                            maSPCTParam.trim()
                    )
            );

            spct.setMaSP(
                    Integer.parseInt(
                            maSPParam.trim()
                    )
            );

            spct.setMaMau(
                    request.getParameter("maMau")
            );

            spct.setMaSize(
                    request.getParameter("maSize")
            );

            int soLuongTon =
                    Integer.parseInt(
                            soLuongParam.trim()
                    );

            BigDecimal giaNhap =
                    new BigDecimal(
                            giaNhapParam.trim()
                    );

            spct.setSoLuongTon(soLuongTon);
            spct.setGiaNhap(giaNhap);

            if (spct.getMaMau() == null
                    || spct.getMaMau()
                    .trim()
                    .isEmpty()) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "Vui lòng chọn màu sắc"
                );
                return;
            }

            if (spct.getMaSize() == null
                    || spct.getMaSize()
                    .trim()
                    .isEmpty()) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "Vui lòng chọn size"
                );
                return;
            }

            if (soLuongTon < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "Số lượng tồn phải lớn hơn hoặc bằng 0"
                );
                return;
            }

            if (giaNhap.compareTo(
                    BigDecimal.ZERO) < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "Giá nhập phải lớn hơn hoặc bằng 0"
                );
                return;
            }

            boolean updated =
                    dao.update(spct);

            if (updated) {
                session.setAttribute(
                        "message",
                        "Cập nhật sản phẩm chi tiết thành công"
                );
            } else {
                session.setAttribute(
                        "error",
                        "Cập nhật sản phẩm chi tiết thất bại"
                );
            }

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanphamchitiet?maSP="
                            + spct.getMaSP()
            );

        } catch (NumberFormatException e) {

            hienThiLaiForm(
                    request,
                    response,
                    spct,
                    "Số lượng tồn hoặc giá nhập không hợp lệ"
            );

        } catch (IllegalArgumentException e) {

            hienThiLaiForm(
                    request,
                    response,
                    spct,
                    e.getMessage()
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết"
            );

            if (spct.getMaSP() > 0) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/sanphamchitiet?maSP="
                                + spct.getMaSP()
                );
            } else {
                response.sendRedirect(
                        request.getContextPath()
                                + "/sanpham"
                );
            }
        }
    }

    private void hienThiLaiForm(
            HttpServletRequest request,
            HttpServletResponse response,
            SanPhamChiTiet spct,
            String error)
            throws ServletException, IOException {

        request.setAttribute(
                "error",
                error
        );

        request.setAttribute(
                "spct",
                spct
        );

        loadComboboxData(request);

        request.getRequestDispatcher(
                "/editSanPhamChiTiet.jsp"
        ).forward(request, response);
    }
}