package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addKhachHang")
public class AddKhachHangController extends HttpServlet {

    KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addKhachHang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hoTen = request.getParameter("hoTen");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");

        // Không được để trống
        if (hoTen.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("addKhachHang.jsp").forward(request, response);
            return;
        }


        if (!sdt.matches("^(0\\d{9}|\\+84\\d{9})$")) {
            request.setAttribute("error", "Số điện thoại không hợp lệ.");
            request.getRequestDispatcher("addKhachHang.jsp").forward(request, response);
            return;
        }


        if (dao.isPhoneExists(sdt)) {
            request.setAttribute("error", "Số điện thoại đã tồn tại.");
            request.getRequestDispatcher("addKhachHang.jsp").forward(request, response);
            return;
        }

        KhachHang kh = new KhachHang();

        kh.setHoTen(hoTen);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);

        dao.insert(kh);

        response.sendRedirect("khachhang");
    }
}