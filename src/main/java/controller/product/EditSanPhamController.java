package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/editsanpham")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class EditSanPhamController extends HttpServlet {

    private final SanPhamDao dao =
            new SanPhamDao();

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
                    "KHONG_CO_MA_SAN_PHAM"
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
                        "MA_SAN_PHAM_KHONG_HOP_LE"
                );

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
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

                response.sendRedirect(
                        request.getContextPath() + "/sanpham"
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

        } catch (NumberFormatException e) {

            session.setAttribute(
                    "errorCode",
                    "MA_SAN_PHAM_KHONG_HOP_LE"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute(
                    "errorCode",
                    "LOI_TAI_SAN_PHAM"
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

        String maSPParam =
                request.getParameter("maSP");

        String tenSP =
                request.getParameter("tenSP");

        String maDanhMuc =
                request.getParameter("maDanhMuc");

        String maNCC =
                request.getParameter("maNCC");

        String maTrangThaiSP =
                request.getParameter("maTrangThaiSP");

        String giaBanParam =
                request.getParameter("giaBan");

        String moTa =
                request.getParameter("moTa");

        String ngayTao =
                request.getParameter("ngayTao");

        String ngayCapNhat =
                request.getParameter("ngayCapNhat");

        String anhCu =
                request.getParameter("anhCu");

        if (isBlank(maSPParam)
                || isBlank(tenSP)
                || isBlank(maDanhMuc)
                || isBlank(maNCC)
                || isBlank(maTrangThaiSP)
                || isBlank(giaBanParam)) {

            int maSP =
                    parseIntSafe(maSPParam);

            hienThiLaiForm(
                    request,
                    response,
                    maSP,
                    "THIEU_THONG_TIN"
            );
            return;
        }

        int maSP;

        double giaBan;

        try {

            maSP =
                    Integer.parseInt(
                            maSPParam.trim()
                    );

            giaBan =
                    Double.parseDouble(
                            giaBanParam.trim()
                    );

        } catch (NumberFormatException e) {

            int maSPTam =
                    parseIntSafe(maSPParam);

            hienThiLaiForm(
                    request,
                    response,
                    maSPTam,
                    "DU_LIEU_KHONG_HOP_LE"
            );
            return;
        }

        if (maSP <= 0) {

            hienThiLaiForm(
                    request,
                    response,
                    maSP,
                    "MA_SAN_PHAM_KHONG_HOP_LE"
            );
            return;
        }

        if (giaBan < 0) {

            hienThiLaiForm(
                    request,
                    response,
                    maSP,
                    "GIA_BAN_KHONG_HOP_LE"
            );
            return;
        }

        String fileName =
                anhCu == null
                        ? ""
                        : anhCu.trim();

        File savedFile = null;

        try {

            Part filePart =
                    request.getPart("anh");

            if (filePart != null
                    && filePart.getSize() > 0
                    && !isBlank(
                    filePart.getSubmittedFileName()
            )) {

                if (filePart.getSize()
                        > 5L * 1024 * 1024) {

                    hienThiLaiForm(
                            request,
                            response,
                            maSP,
                            "ANH_QUA_LON"
                    );
                    return;
                }

                String originalFileName =
                        Paths.get(
                                filePart.getSubmittedFileName()
                        ).getFileName().toString();

                String contentType =
                        filePart.getContentType();

                String extension =
                        getExtension(
                                originalFileName
                        );

                if (!isValidImage(
                        contentType,
                        extension
                )) {

                    hienThiLaiForm(
                            request,
                            response,
                            maSP,
                            "SAI_DINH_DANG_ANH"
                    );
                    return;
                }

                fileName =
                        System.currentTimeMillis()
                                + extension.toLowerCase();

                String uploadPath =
                        getServletContext()
                                .getRealPath("/images");

                if (uploadPath == null) {

                    hienThiLaiForm(
                            request,
                            response,
                            maSP,
                            "KHONG_TAO_DUOC_THU_MUC_ANH"
                    );
                    return;
                }

                File uploadDirectory =
                        new File(uploadPath);

                if (!uploadDirectory.exists()
                        && !uploadDirectory.mkdirs()) {

                    hienThiLaiForm(
                            request,
                            response,
                            maSP,
                            "KHONG_TAO_DUOC_THU_MUC_ANH"
                    );
                    return;
                }

                savedFile =
                        new File(
                                uploadDirectory,
                                fileName
                        );

                filePart.write(
                        savedFile.getAbsolutePath()
                );
            }

            sanpham sp =
                    new sanpham();

            sp.setMaSP(maSP);
            sp.setTenSP(tenSP.trim());
            sp.setMaDanhMuc(maDanhMuc.trim());
            sp.setMaNCC(maNCC.trim());
            sp.setMaTrangThaiSP(
                    maTrangThaiSP.trim()
            );

            sp.setGiaBan(giaBan);

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
                    fileName
            );

            boolean updated =
                    dao.update(sp);

            if (!updated) {

                if (savedFile != null
                        && savedFile.exists()) {

                    savedFile.delete();
                }

                hienThiLaiForm(
                        request,
                        response,
                        maSP,
                        "CAP_NHAT_SAN_PHAM_THAT_BAI"
                );
                return;
            }


            if (savedFile != null
                    && !isBlank(anhCu)
                    && !anhCu.equals(fileName)) {

                String uploadPath =
                        getServletContext()
                                .getRealPath("/images");

                if (uploadPath != null) {

                    File oldFile =
                            new File(
                                    uploadPath,
                                    anhCu
                            );

                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }
            }

            session.setAttribute(
                    "messageCode",
                    "CAP_NHAT_SAN_PHAM_THANH_CONG"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (IllegalStateException e) {

            hienThiLaiForm(
                    request,
                    response,
                    maSP,
                    "ANH_QUA_LON"
            );

        } catch (Exception e) {

            e.printStackTrace();

            if (savedFile != null
                    && savedFile.exists()) {

                savedFile.delete();
            }

            hienThiLaiForm(
                    request,
                    response,
                    maSP,
                    "LOI_CAP_NHAT_SAN_PHAM"
            );
        }
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
    }

    private int parseIntSafe(
            String value) {

        try {

            return Integer.parseInt(
                    value.trim()
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private String getExtension(
            String fileName) {

        if (fileName == null) {
            return "";
        }

        int dotIndex =
                fileName.lastIndexOf(".");

        if (dotIndex < 0) {
            return "";
        }

        return fileName.substring(
                dotIndex
        );
    }

    private boolean isValidImage(
            String contentType,
            String extension) {

        if (contentType == null
                || extension == null) {

            return false;
        }

        String type =
                contentType.toLowerCase();

        String ext =
                extension.toLowerCase();

        boolean validContentType =
                type.equals("image/png")
                        || type.equals("image/jpeg")
                        || type.equals("image/webp");

        boolean validExtension =
                ext.equals(".png")
                        || ext.equals(".jpg")
                        || ext.equals(".jpeg")
                        || ext.equals(".webp");

        return validContentType
                && validExtension;
    }

    private void hienThiLaiForm(
            HttpServletRequest request,
            HttpServletResponse response,
            int maSP,
            String errorCode)
            throws ServletException, IOException {

        sanpham sp =
                dao.getById(maSP);

        if (sp == null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "errorCode",
                    "KHONG_TIM_THAY_SAN_PHAM"
            );

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );
            return;
        }

        /*
         * Giữ lại dữ liệu vừa nhập khi có lỗi.
         */
        String tenSP =
                request.getParameter("tenSP");

        String maDanhMuc =
                request.getParameter("maDanhMuc");

        String maNCC =
                request.getParameter("maNCC");

        String maTrangThaiSP =
                request.getParameter("maTrangThaiSP");

        String giaBan =
                request.getParameter("giaBan");

        String moTa =
                request.getParameter("moTa");

        String ngayTao =
                request.getParameter("ngayTao");

        String ngayCapNhat =
                request.getParameter("ngayCapNhat");

        if (!isBlank(tenSP)) {
            sp.setTenSP(tenSP.trim());
        }

        if (!isBlank(maDanhMuc)) {
            sp.setMaDanhMuc(
                    maDanhMuc.trim()
            );
        }

        if (!isBlank(maNCC)) {
            sp.setMaNCC(
                    maNCC.trim()
            );
        }

        if (!isBlank(maTrangThaiSP)) {
            sp.setMaTrangThaiSP(
                    maTrangThaiSP.trim()
            );
        }

        if (!isBlank(giaBan)) {

            try {

                sp.setGiaBan(
                        Double.parseDouble(
                                giaBan.trim()
                        )
                );

            } catch (NumberFormatException ignored) {
            }
        }

        if (moTa != null) {
            sp.setMoTa(moTa);
        }

        if (ngayTao != null) {
            sp.setNgayTao(ngayTao);
        }

        if (ngayCapNhat != null) {
            sp.setNgayCapNhat(
                    ngayCapNhat
            );
        }

        request.setAttribute(
                "sp",
                sp
        );

        request.setAttribute(
                "errorCode",
                errorCode
        );

        request.getRequestDispatcher(
                "/editsanpham.jsp"
        ).forward(request, response);
    }
}