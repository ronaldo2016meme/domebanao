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

        String idParam =
                request.getParameter("id");

        if (isBlank(idParam)) {

            session.setAttribute(
                    "errorCode",
                    "KHONG_CO_MA_SPCT"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            idParam.trim()
                    );

            if (id <= 0) {

                session.setAttribute(
                        "errorCode",
                        "MA_SPCT_KHONG_HOP_LE"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
                );
                return;
            }

            SanPhamChiTiet spct =
                    dao.getById(id);

            if (spct == null) {

                session.setAttribute(
                        "errorCode",
                        "KHONG_TIM_THAY_SPCT"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
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
                    "errorCode",
                    "MA_SPCT_KHONG_HOP_LE"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_TAI_DU_LIEU_SPCT"
            );

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

        SanPhamChiTiet spct =
                new SanPhamChiTiet();

        String maSPCTParam =
                request.getParameter("maSPCT");

        String maSPParam =
                request.getParameter("maSP");

        String maMau =
                request.getParameter("maMau");

        String maSize =
                request.getParameter("maSize");

        String soLuongParam =
                request.getParameter("soLuongTon");

        String giaNhapParam =
                request.getParameter("giaNhap");

        String giaBanParam =
                request.getParameter("giaBan");

        if (isBlank(maSPCTParam)
                || isBlank(maSPParam)
                || isBlank(maMau)
                || isBlank(maSize)
                || isBlank(soLuongParam)
                || isBlank(giaNhapParam)
                || isBlank(giaBanParam)) {

            hienThiLaiForm(
                    request,
                    response,
                    spct,
                    "THIEU_THONG_TIN"
            );
            return;
        }

        try {

            int maSPCT =
                    Integer.parseInt(
                            maSPCTParam.trim()
                    );

            int maSP =
                    Integer.parseInt(
                            maSPParam.trim()
                    );

            int soLuongTon =
                    Integer.parseInt(
                            soLuongParam.trim()
                    );

            BigDecimal giaNhap =
                    new BigDecimal(
                            giaNhapParam.trim()
                    );

            BigDecimal giaBan =
                    new BigDecimal(
                            giaBanParam.trim()
                    );

            spct.setMaSPCT(maSPCT);
            spct.setMaSP(maSP);
            spct.setMaMau(maMau);
            spct.setMaSize(maSize);
            spct.setSoLuongTon(soLuongTon);
            spct.setGiaNhap(giaNhap);
            spct.setGiaBan(giaBan);

            if (maSPCT <= 0 || maSP <= 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "MA_SAN_PHAM_KHONG_HOP_LE"
                );
                return;
            }

            if (soLuongTon < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "SO_LUONG_KHONG_HOP_LE"
                );
                return;
            }

            if (giaNhap.compareTo(
                    BigDecimal.ZERO) < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "GIA_NHAP_KHONG_HOP_LE"
                );
                return;
            }

            if (giaBan.compareTo(
                    BigDecimal.ZERO) < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "GIA_BAN_KHONG_HOP_LE"
                );
                return;
            }

            if (giaBan.compareTo(
                    giaNhap) < 0) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "GIA_BAN_NHO_HON_GIA_NHAP"
                );
                return;
            }

            SanPhamChiTiet tonTai =
                    dao.getBySanPhamMauSize(
                            maSP,
                            maMau,
                            maSize
                    );

            if (tonTai != null
                    && tonTai.getMaSPCT()
                    != maSPCT) {

                hienThiLaiForm(
                        request,
                        response,
                        spct,
                        "BIEN_THE_DA_TON_TAI"
                );
                return;
            }

            boolean updated =
                    dao.update(spct);

            if (updated) {

                session.setAttribute(
                        "messageCode",
                        "CAP_NHAT_SPCT_THANH_CONG"
                );

            } else {

                session.setAttribute(
                        "errorCode",
                        "CAP_NHAT_SPCT_THAT_BAI"
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
                    "DU_LIEU_KHONG_HOP_LE"
            );

        } catch (Exception e) {

            e.printStackTrace();

            if (spct.getMaSP() > 0) {

                session.setAttribute(
                        "errorCode",
                        "LOI_CAP_NHAT_SPCT"
                );

                response.sendRedirect(
                        request.getContextPath()
                                + "/sanphamchitiet?maSP="
                                + spct.getMaSP()
                );

            } else {

                session.setAttribute(
                        "errorCode",
                        "LOI_CAP_NHAT_SPCT"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
                );
            }
        }
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }

    private void hienThiLaiForm(
            HttpServletRequest request,
            HttpServletResponse response,
            SanPhamChiTiet spct,
            String errorCode)
            throws ServletException, IOException {

        request.setAttribute(
                "errorCode",
                errorCode
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