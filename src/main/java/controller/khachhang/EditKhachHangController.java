package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editKhachHang")
public class EditKhachHangController extends HttpServlet {

    private KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Kiểm tra đăng nhập
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        try {

            // Lấy mã khách hàng
            int maKH = Integer.parseInt(
                    request.getParameter("maKH")
            );

            KhachHang kh = dao.getById(maKH);

            if (kh == null) {
                response.sendRedirect("khachhang");
                return;
            }

            request.setAttribute("kh", kh);

            request.getRequestDispatcher("/editKhachHang.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("khachhang");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int maKH = Integer.parseInt(
                    request.getParameter("maKH")
            );

            String hoTen = request.getParameter("hoTen");
            String sdt = request.getParameter("sdt");
            String diaChi = request.getParameter("diaChi");

            // ==============================
            // KIỂM TRA DỮ LIỆU
            // ==============================

            if (hoTen == null || hoTen.trim().isEmpty()
                    || sdt == null || sdt.trim().isEmpty()
                    || diaChi == null || diaChi.trim().isEmpty()) {

                request.setAttribute(
                        "error",
                        "Vui lòng nhập đầy đủ thông tin."
                );

                request.setAttribute(
                        "kh",
                        dao.getById(maKH)
                );

                request.getRequestDispatcher(
                        "/editKhachHang.jsp"
                ).forward(request, response);

                return;
            }

            hoTen = hoTen.trim();
            sdt = sdt.trim();
            diaChi = diaChi.trim();

            // ==============================
            // KIỂM TRA SỐ ĐIỆN THOẠI
            // ==============================

            if (!sdt.matches("^(0\\d{9}|\\+84\\d{9})$")) {

                request.setAttribute(
                        "error",
                        "Số điện thoại không hợp lệ."
                );

                request.setAttribute(
                        "kh",
                        dao.getById(maKH)
                );

                request.getRequestDispatcher(
                        "/editKhachHang.jsp"
                ).forward(request, response);

                return;
            }

            // ==============================
            // KIỂM TRA SĐT TRÙNG
            // ==============================

            if (dao.isPhoneExistsForUpdate(sdt, maKH)) {

                request.setAttribute(
                        "error",
                        "Số điện thoại đã tồn tại."
                );

                request.setAttribute(
                        "kh",
                        dao.getById(maKH)
                );

                request.getRequestDispatcher(
                        "/editKhachHang.jsp"
                ).forward(request, response);

                return;
            }

            // ==============================
            // CẬP NHẬT KHÁCH HÀNG
            // ==============================

            KhachHang kh = new KhachHang();

            kh.setMaKH(maKH);
            kh.setHoTen(hoTen);
            kh.setSdt(sdt);
            kh.setDiaChi(diaChi);

            // Không set DiemTichLuy ở đây
            // Vì điểm phải được giữ nguyên

            dao.update(kh);

            response.sendRedirect("khachhang");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect("khachhang");
        }
    }
}