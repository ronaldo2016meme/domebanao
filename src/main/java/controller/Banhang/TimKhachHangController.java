package controller.Banhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/timKhachHang")
public class TimKhachHangController extends HttpServlet {

    private final KhachHangDao khachHangDao =
            new KhachHangDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(
                "application/json;charset=UTF-8"
        );

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.getWriter().write(
                    "{\"found\":false,\"message\":\"Chưa đăng nhập\"}"
            );

            return;
        }

        String sdt =
                request.getParameter("soDienThoai");

        if (sdt == null
                || sdt.trim().isEmpty()) {

            response.getWriter().write(
                    "{\"found\":false,\"message\":\"\"}"
            );

            return;
        }

        sdt = sdt.trim();

        if (!sdt.matches("\\d{10}")) {

            response.getWriter().write(
                    "{\"found\":false,"
                            + "\"message\":\"Số điện thoại phải gồm 10 chữ số\"}"
            );

            return;
        }

        KhachHang khachHang =
                khachHangDao.getBySoDienThoai(sdt);

        if (khachHang == null) {

            response.getWriter().write(
                    "{\"found\":false,"
                            + "\"message\":\"Không tìm thấy khách hàng\"}"
            );

            return;
        }

        response.getWriter().write(
                "{"
                        + "\"found\":true,"
                        + "\"maKH\":"
                        + khachHang.getMaKH()
                        + ","
                        + "\"hoTen\":\""
                        + escapeJson(
                        khachHang.getHoTen()
                )
                        + "\","
                        + "\"sdt\":\""
                        + escapeJson(
                        khachHang.getSdt()
                )
                        + "\""
                        + "}"
        );
    }

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}