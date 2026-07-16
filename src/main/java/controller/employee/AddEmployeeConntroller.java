package controller.employee;

import dao.NhanVienDao;
import model.NhanVien;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addEmployee")
public class AddEmployeeConntroller extends HttpServlet {

    NhanVienDao dao = new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hoTen = request.getParameter("hoTen");
        String ngaySinh = request.getParameter("ngaySinh");
        String gioiTinh = request.getParameter("gioiTinh");
        String quocTich = request.getParameter("quocTich");
        String queQuan = request.getParameter("queQuan");
        String noiThuongTru = request.getParameter("noiThuongTru");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String cccd = request.getParameter("cccd");
        String maTrangThai = request.getParameter("maTrangThai");
        String maRole = request.getParameter("maRole");

        // Không để trống
        if (hoTen.isEmpty() || ngaySinh.isEmpty() || gioiTinh.isEmpty()
                || quocTich.isEmpty() || queQuan.isEmpty()
                || noiThuongTru.isEmpty() || sdt.isEmpty()
                || email.isEmpty() || cccd.isEmpty()
                || maTrangThai.isEmpty() || maRole.isEmpty()) {

            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

        // Kiểm tra Email
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            request.setAttribute("error", "Email không đúng định dạng.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

        if (!sdt.matches("^(0\\d{9}|\\+84\\d{9})$")) {
            request.setAttribute("error", "Số điện thoại phải bắt đầu bằng 0 hoặc +84.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }


        if (dao.isPhoneExists(sdt)) {
            request.setAttribute("error", "Số điện thoại đã tồn tại.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

// Kiểm tra trùng CCCD
        if (dao.isCccdExists(cccd)) {
            request.setAttribute("error", "CCCD đã tồn tại.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

        // Kiểm tra CCCD
        if (!cccd.matches("^\\d{12}$")) {
            request.setAttribute("error", "CCCD phải gồm đúng 12 chữ số.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

        // Kiểm tra tuổi >=16
        java.time.LocalDate birth = java.time.LocalDate.parse(ngaySinh);
        int age = java.time.Period.between(birth, java.time.LocalDate.now()).getYears();

        if (age < 16) {
            request.setAttribute("error", "Nhân viên phải từ 16 tuổi trở lên.");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            return;
        }

        // Lưu dữ liệu
        NhanVien nv = new NhanVien();

        nv.setHoTen(hoTen);
        nv.setNgaySinh(java.sql.Date.valueOf(ngaySinh));
        nv.setGioiTinh(gioiTinh);
        nv.setQuocTich(quocTich);
        nv.setQueQuan(queQuan);
        nv.setNoiThuongTru(noiThuongTru);
        nv.setSdt(sdt);
        nv.setEmail(email);
        nv.setCccd(cccd);
        nv.setMaTrangThai(maTrangThai);
        nv.setMaRole(maRole);

        dao.insert(nv);

        response.sendRedirect("employee");
    }
}