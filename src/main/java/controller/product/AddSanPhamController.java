package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private final SanPhamDao dao = new SanPhamDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/addsanpham.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {

            String tenSP = request.getParameter("tenSP");
            String maDanhMuc = request.getParameter("maDanhMuc");
            String maNCC = request.getParameter("maNCC");
            String maTrangThaiSP =
                    request.getParameter("maTrangThaiSP");

            String giaBanParam =
                    request.getParameter("giaBan");

            String moTa = request.getParameter("moTa");
            String ngayTao = request.getParameter("ngayTao");
            String ngayCapNhat =
                    request.getParameter("ngayCapNhat");

            if (tenSP == null
                    || tenSP.trim().isEmpty()
                    || giaBanParam == null
                    || giaBanParam.trim().isEmpty()) {

                request.setAttribute(
                        "error",
                        "Vui lòng nhập đầy đủ thông tin bắt buộc"
                );

                request.getRequestDispatcher("/addsanpham.jsp")
                        .forward(request, response);
                return;
            }

            double giaBan =
                    Double.parseDouble(giaBanParam);

            if (giaBan < 0) {

                request.setAttribute(
                        "error",
                        "Giá bán phải lớn hơn hoặc bằng 0"
                );

                request.getRequestDispatcher("/addsanpham.jsp")
                        .forward(request, response);
                return;
            }

            /*
             * Lấy file ảnh từ form
             */
            Part filePart =
                    request.getPart("anh");

            String fileName = "";

            if (filePart != null
                    && filePart.getSize() > 0) {

                fileName = Paths.get(
                        filePart.getSubmittedFileName()
                ).getFileName().toString();

                /*
                 * Kiểm tra đúng loại ảnh
                 */
                String contentType =
                        filePart.getContentType();

                if (contentType == null
                        || !contentType.startsWith("image/")) {

                    request.setAttribute(
                            "error",
                            "Vui lòng chỉ chọn tệp ảnh"
                    );

                    request.getRequestDispatcher("/addsanpham.jsp")
                            .forward(request, response);
                    return;
                }

                /*
                 * Thư mục lưu ảnh:
                 * src/main/webapp/images
                 */
                String uploadPath =
                        getServletContext()
                                .getRealPath("/images");

                File uploadDirectory =
                        new File(uploadPath);

                if (!uploadDirectory.exists()) {
                    uploadDirectory.mkdirs();
                }

                /*
                 * Tránh trùng tên ảnh
                 */
                String extension = "";

                int dotIndex =
                        fileName.lastIndexOf(".");

                if (dotIndex >= 0) {
                    extension =
                            fileName.substring(dotIndex);
                }

                String savedFileName =
                        System.currentTimeMillis()
                                + extension;

                filePart.write(
                        uploadPath
                                + File.separator
                                + savedFileName
                );

                fileName = savedFileName;
            }

            sanpham sp = new sanpham();

            sp.setTenSP(tenSP.trim());
            sp.setMaDanhMuc(maDanhMuc);
            sp.setMaNCC(maNCC);
            sp.setGiaBan(giaBan);
            sp.setMoTa(moTa);
            sp.setNgayTao(ngayTao);
            sp.setNgayCapNhat(ngayCapNhat);
            sp.setAnh(fileName);
            sp.setMaTrangThaiSP(maTrangThaiSP);

            dao.insert(sp);

            response.sendRedirect(
                    request.getContextPath() + "/sanpham"
            );

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Giá bán không hợp lệ"
            );

            request.getRequestDispatcher("/addsanpham.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi thêm sản phẩm"
            );

            request.getRequestDispatcher("/addsanpham.jsp")
                    .forward(request, response);
        }
    }
}