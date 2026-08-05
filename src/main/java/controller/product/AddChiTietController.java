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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        String maSP =
                request.getParameter("maSP");

        if (isBlank(maSP)) {

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
            return;
        }

        try {

            int maSPInt =
                    Integer.parseInt(maSP.trim());

            if (maSPInt <= 0) {

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
                );
                return;
            }

            request.setAttribute(
                    "maSP",
                    maSPInt
            );

            loadComboboxData(request);

            request.getRequestDispatcher(
                    "/addSanPhamChiTiet.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
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
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        String maSP =
                request.getParameter("maSP");

        String maMau =
                request.getParameter("maMau");

        String maSize =
                request.getParameter("maSize");

        String soLuongTon =
                request.getParameter("soLuongTon");

        String giaNhap =
                request.getParameter("giaNhap");

        String giaBan =
                request.getParameter("giaBan");

        if (isBlank(maSP)
                || isBlank(maMau)
                || isBlank(maSize)
                || isBlank(soLuongTon)
                || isBlank(giaNhap)
                || isBlank(giaBan)) {

            hienThiLoi(
                    request,
                    response,
                    maSP,
                    "THIEU_THONG_TIN"
            );
            return;
        }

        try {

            int maSPInt =
                    Integer.parseInt(maSP.trim());

            int soLuongTonInt =
                    Integer.parseInt(soLuongTon.trim());

            BigDecimal giaNhapBD =
                    new BigDecimal(giaNhap.trim());

            BigDecimal giaBanBD =
                    new BigDecimal(giaBan.trim());

            if (maSPInt <= 0) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "MA_SAN_PHAM_KHONG_HOP_LE"
                );
                return;
            }

            if (soLuongTonInt < 0) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "SO_LUONG_KHONG_HOP_LE"
                );
                return;
            }

            if (giaNhapBD.compareTo(
                    BigDecimal.ZERO) < 0) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "GIA_NHAP_KHONG_HOP_LE"
                );
                return;
            }

            if (giaBanBD.compareTo(
                    BigDecimal.ZERO) < 0) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "GIA_BAN_KHONG_HOP_LE"
                );
                return;
            }

            if (giaBanBD.compareTo(
                    giaNhapBD) < 0) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "GIA_BAN_NHO_HON_GIA_NHAP"
                );
                return;
            }

            SanPhamChiTiet tonTai =
                    dao.getBySanPhamMauSize(
                            maSPInt,
                            maMau,
                            maSize
                    );

            if (tonTai != null) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "BIEN_THE_DA_TON_TAI"
                );
                return;
            }

            SanPhamChiTiet spct =
                    new SanPhamChiTiet();

            spct.setMaSP(maSPInt);
            spct.setMaMau(maMau);
            spct.setMaSize(maSize);
            spct.setSoLuongTon(soLuongTonInt);
            spct.setGiaNhap(giaNhapBD);
            spct.setGiaBan(giaBanBD);

            boolean inserted =
                    dao.insert(spct);

            if (!inserted) {

                hienThiLoi(
                        request,
                        response,
                        maSP,
                        "THEM_SPCT_THAT_BAI"
                );
                return;
            }

            session.setAttribute(
                    "messageCode",
                    "THEM_SPCT_THANH_CONG"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanphamchitiet?maSP="
                            + maSPInt
            );

        } catch (NumberFormatException e) {

            hienThiLoi(
                    request,
                    response,
                    maSP,
                    "DU_LIEU_KHONG_HOP_LE"
            );

        } catch (Exception e) {

            e.printStackTrace();

            hienThiLoi(
                    request,
                    response,
                    maSP,
                    "LOI_THEM_SPCT"
            );
        }
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }

    private void hienThiLoi(
            HttpServletRequest request,
            HttpServletResponse response,
            String maSP,
            String errorCode)
            throws ServletException, IOException {

        request.setAttribute(
                "errorCode",
                errorCode
        );

        request.setAttribute(
                "maSP",
                maSP
        );

        loadComboboxData(request);

        request.getRequestDispatcher(
                "/addSanPhamChiTiet.jsp"
        ).forward(request, response);
    }
}