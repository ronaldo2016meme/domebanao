package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/sanpham")
public class SanPhamController extends HttpServlet {

    private final SanPhamDao dao =
            new SanPhamDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(
                "text/html;charset=UTF-8"
        );

        HttpSession session =
                request.getSession(false);

        /*
         * Kiểm tra đăng nhập.
         */
        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        try {

            String action =
                    request.getParameter("action");

            /*
             * Mặc định hiển thị danh sách sản phẩm.
             */
            if (isBlank(action)) {

                List<sanpham> list =
                        dao.getAll();

                request.setAttribute(
                        "list",
                        list
                );

                /*
                 * Chuyển thông báo từ session sang request.
                 */
                chuyenThongBao(
                        session,
                        request,
                        "messageCode"
                );

                chuyenThongBao(
                        session,
                        request,
                        "errorCode"
                );

                request.getRequestDispatcher(
                        "/sanpham.jsp"
                ).forward(request, response);

                return;
            }

            if ("edit".equals(action)) {

                String idParam =
                        request.getParameter("id");

                if (isBlank(idParam)) {

                    session.setAttribute(
                            "errorCode",
                            "THIEU_MA_SAN_PHAM"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                int id =
                        Integer.parseInt(
                                idParam.trim()
                        );

                if (id <= 0) {

                    session.setAttribute(
                            "errorCode",
                            "MA_SAN_PHAM_KHONG_HOP_LE"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                sanpham sp =
                        dao.getById(id);

                if (sp == null) {

                    session.setAttribute(
                            "errorCode",
                            "KHONG_TIM_THAY_SAN_PHAM"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                request.setAttribute(
                        "sp",
                        sp
                );

                request.getRequestDispatcher(
                        "/editsanpham.jsp"
                ).forward(request, response);

                return;
            }

            /*
             * Chuyển tới trang thêm sản phẩm.
             *
             * Project hiện có servlet riêng:
             * /addsanpham
             */
            if ("add".equals(action)) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/addsanpham"
                );
                return;
            }

            /*
             * Action không hợp lệ.
             */
            session.setAttribute(
                    "errorCode",
                    "THAO_TAC_KHONG_HOP_LE"
            );

            redirectSanPham(
                    request,
                    response
            );

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "MA_SAN_PHAM_KHONG_HOP_LE"
            );

            redirectSanPham(
                    request,
                    response
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_TAI_DANH_SACH_SAN_PHAM"
            );

            redirectSanPham(
                    request,
                    response
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
        response.setContentType(
                "text/html;charset=UTF-8"
        );

        HttpSession session =
                request.getSession(false);

        /*
         * Kiểm tra đăng nhập.
         */
        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        try {

            String action =
                    request.getParameter("action");

            if (isBlank(action)) {

                session.setAttribute(
                        "errorCode",
                        "THIEU_HANH_DONG"
                );

                redirectSanPham(
                        request,
                        response
                );
                return;
            }

            String tenSP =
                    request.getParameter("tenSP");

            String maDanhMuc =
                    request.getParameter("maDanhMuc");

            String maNCC =
                    request.getParameter("maNCC");

            String maTrangThaiSP =
                    request.getParameter(
                            "maTrangThaiSP"
                    );

            String giaBanParam =
                    request.getParameter("giaBan");

            String moTa =
                    request.getParameter("moTa");

            String ngayTao =
                    request.getParameter("ngayTao");

            String ngayCapNhat =
                    request.getParameter(
                            "ngayCapNhat"
                    );

            String anh =
                    request.getParameter("anh");

            /*
             * Kiểm tra thông tin bắt buộc.
             */
            if (isBlank(tenSP)
                    || isBlank(maDanhMuc)
                    || isBlank(maNCC)
                    || isBlank(maTrangThaiSP)) {

                session.setAttribute(
                        "errorCode",
                        "THIEU_THONG_TIN"
                );

                redirectSanPham(
                        request,
                        response
                );
                return;
            }

            sanpham sp =
                    new sanpham();

            sp.setTenSP(
                    tenSP.trim()
            );

            sp.setMaDanhMuc(
                    maDanhMuc.trim()
            );

            sp.setMaNCC(
                    maNCC.trim()
            );

            sp.setMaTrangThaiSP(
                    maTrangThaiSP.trim()
            );

            sp.setMoTa(
                    moTa == null
                            ? ""
                            : moTa.trim()
            );

            sp.setNgayTao(
                    ngayTao
            );

            sp.setNgayCapNhat(
                    ngayCapNhat
            );

            sp.setAnh(
                    anh == null
                            ? ""
                            : anh.trim()
            );

            /*
             * Nếu model SANPHAM có giá bán thì đọc giá bán.
             *
             * Nếu giá bán chỉ nằm trong SANPHAMCHITIET,
             * hãy xóa toàn bộ khối này.
             */
            if (!isBlank(giaBanParam)) {

                double giaBan =
                        Double.parseDouble(
                                giaBanParam.trim()
                        );

                if (giaBan < 0) {

                    session.setAttribute(
                            "errorCode",
                            "GIA_BAN_KHONG_HOP_LE"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                sp.setGiaBan(giaBan);
            }

            /*
             * Thêm sản phẩm.
             */
            if ("insert".equals(action)) {

                boolean inserted =
                        dao.insert(sp);

                if (inserted) {

                    session.setAttribute(
                            "messageCode",
                            "THEM_SAN_PHAM_THANH_CONG"
                    );

                } else {

                    session.setAttribute(
                            "errorCode",
                            "THEM_SAN_PHAM_THAT_BAI"
                    );
                }

                redirectSanPham(
                        request,
                        response
                );
                return;
            }

            /*
             * Cập nhật sản phẩm.
             */
            if ("update".equals(action)) {

                String maSPParam =
                        request.getParameter("maSP");

                if (isBlank(maSPParam)) {

                    session.setAttribute(
                            "errorCode",
                            "THIEU_MA_SAN_PHAM"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                int maSP =
                        Integer.parseInt(
                                maSPParam.trim()
                        );

                if (maSP <= 0) {

                    session.setAttribute(
                            "errorCode",
                            "MA_SAN_PHAM_KHONG_HOP_LE"
                    );

                    redirectSanPham(
                            request,
                            response
                    );
                    return;
                }

                sp.setMaSP(maSP);

                boolean updated =
                        dao.update(sp);

                if (updated) {

                    session.setAttribute(
                            "messageCode",
                            "CAP_NHAT_SAN_PHAM_THANH_CONG"
                    );

                } else {

                    session.setAttribute(
                            "errorCode",
                            "CAP_NHAT_SAN_PHAM_THAT_BAI"
                    );
                }

                redirectSanPham(
                        request,
                        response
                );
                return;
            }

            /*
             * Action không hỗ trợ.
             */
            session.setAttribute(
                    "errorCode",
                    "THAO_TAC_KHONG_HOP_LE"
            );

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "DU_LIEU_KHONG_HOP_LE"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_LUU_SAN_PHAM"
            );
        }

        redirectSanPham(
                request,
                response
        );
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }

    private void chuyenThongBao(
            HttpSession session,
            HttpServletRequest request,
            String tenThuocTinh) {

        Object value =
                session.getAttribute(
                        tenThuocTinh
                );

        if (value != null) {

            request.setAttribute(
                    tenThuocTinh,
                    value
            );

            session.removeAttribute(
                    tenThuocTinh
            );
        }
    }

    private void redirectSanPham(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.sendRedirect(
                request.getContextPath()
                        + "/sanpham"
        );
    }
}