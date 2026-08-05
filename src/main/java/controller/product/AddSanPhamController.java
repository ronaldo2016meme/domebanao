package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/addsanpham")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class AddSanPhamController extends HttpServlet {

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

        request.getRequestDispatcher(
                "/addsanpham.jsp"
        ).forward(request, response);
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

        try {

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

            /*
             * Kiểm tra dữ liệu bắt buộc.
             */
            if (isBlank(tenSP)
                    || isBlank(maDanhMuc)
                    || isBlank(maNCC)
                    || isBlank(maTrangThaiSP)
                    || isBlank(giaBanParam)) {

                hienThiLoi(
                        request,
                        response,
                        "THIEU_THONG_TIN"
                );
                return;
            }

            double giaBan;

            try {

                giaBan =
                        Double.parseDouble(
                                giaBanParam.trim()
                        );

            } catch (NumberFormatException e) {

                hienThiLoi(
                        request,
                        response,
                        "GIA_BAN_KHONG_HOP_LE"
                );
                return;
            }

            if (giaBan < 0) {

                hienThiLoi(
                        request,
                        response,
                        "GIA_BAN_KHONG_HOP_LE"
                );
                return;
            }

            /*
             * Lấy ảnh từ form.
             */
            Part filePart =
                    request.getPart("anh");

            if (filePart == null
                    || filePart.getSize() <= 0
                    || isBlank(filePart.getSubmittedFileName())) {

                hienThiLoi(
                        request,
                        response,
                        "CHUA_CHON_ANH"
                );
                return;
            }

            if (filePart.getSize()
                    > 5L * 1024 * 1024) {

                hienThiLoi(
                        request,
                        response,
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
                    getExtension(originalFileName);

            if (!isValidImage(
                    contentType,
                    extension
            )) {

                hienThiLoi(
                        request,
                        response,
                        "SAI_DINH_DANG_ANH"
                );
                return;
            }

            /*
             * Tạo tên ảnh mới để tránh trùng.
             */
            String savedFileName =
                    System.currentTimeMillis()
                            + extension.toLowerCase();

            /*
             * Thư mục lưu ảnh:
             * src/main/webapp/images
             */
            String uploadPath =
                    getServletContext()
                            .getRealPath("/images");

            if (uploadPath == null) {

                hienThiLoi(
                        request,
                        response,
                        "KHONG_TAO_DUOC_THU_MUC_ANH"
                );
                return;
            }

            File uploadDirectory =
                    new File(uploadPath);

            if (!uploadDirectory.exists()
                    && !uploadDirectory.mkdirs()) {

                hienThiLoi(
                        request,
                        response,
                        "KHONG_TAO_DUOC_THU_MUC_ANH"
                );
                return;
            }

            File savedFile =
                    new File(
                            uploadDirectory,
                            savedFileName
                    );

            filePart.write(
                    savedFile.getAbsolutePath()
            );

            /*
             * Tạo model sản phẩm.
             */
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

            sp.setGiaBan(
                    giaBan
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
                    savedFileName
            );

            /*
             * Thêm sản phẩm.
             */
            boolean inserted =
                    dao.insert(sp);

            if (!inserted) {

                /*
                 * Nếu insert thất bại thì xóa ảnh vừa lưu.
                 */
                if (savedFile.exists()) {
                    savedFile.delete();
                }

                hienThiLoi(
                        request,
                        response,
                        "THEM_SAN_PHAM_THAT_BAI"
                );
                return;
            }

            session.setAttribute(
                    "messageCode",
                    "THEM_SAN_PHAM_THANH_CONG"
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/sanpham"
            );

        } catch (IllegalStateException e) {

            hienThiLoi(
                    request,
                    response,
                    "ANH_QUA_LON"
            );

        } catch (Exception e) {

            e.printStackTrace();

            hienThiLoi(
                    request,
                    response,
                    "LOI_THEM_SAN_PHAM"
            );
        }
    }

    private boolean isBlank(
            String value) {

        return value == null
                || value.trim().isEmpty();
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

        return fileName.substring(dotIndex);
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

    private void hienThiLoi(
            HttpServletRequest request,
            HttpServletResponse response,
            String errorCode)
            throws ServletException, IOException {

        request.setAttribute(
                "errorCode",
                errorCode
        );

        request.getRequestDispatcher(
                "/addsanpham.jsp"
        ).forward(request, response);
    }
}