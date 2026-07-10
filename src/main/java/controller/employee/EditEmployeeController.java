package controller.employee;

import dao.NhanVienDao;
import model.NhanVien;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/editEmployee")
public class EditEmployeeController extends HttpServlet {

    NhanVienDao dao = new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        NhanVien nv = dao.getById(id);

        request.setAttribute("nv", nv);

        request.getRequestDispatcher("editEmployee.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
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

        // Không được để trống
        if (hoTen.isEmpty() || ngaySinh.isEmpty() || quocTich.isEmpty()
                || queQuan.isEmpty() || noiThuongTru.isEmpty()
                || sdt.isEmpty() || email.isEmpty() || cccd.isEmpty()) {

            response.getWriter().println("Không được để trống thông tin.");
            return;
        }

        // Kiểm tra tuổi >=16
        LocalDate birth = LocalDate.parse(ngaySinh);
        int age = Period.between(birth, LocalDate.now()).getYears();

        if (age < 16) {
            response.getWriter().println("Nhân viên phải từ 16 tuổi trở lên.");
            return;
        }


        if (!sdt.matches("^(0\\d{9}|\\+84\\d{9})$")) {
            response.getWriter().println("Số điện thoại không hợp lệ.");
            return;
        }

        // Kiểm tra Email
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            response.getWriter().println("Email không hợp lệ.");
            return;
        }

        // Kiểm tra CCCD
        if (!cccd.matches("\\d{12}")) {
            response.getWriter().println("CCCD phải gồm đúng 12 số.");
            return;
        }

        NhanVien nv = new NhanVien();

        nv.setMaNV(Integer.parseInt(request.getParameter("maNV")));
        nv.setHoTen(hoTen);
        nv.setNgaySinh(Date.valueOf(ngaySinh));
        nv.setGioiTinh(gioiTinh);
        nv.setQuocTich(quocTich);
        nv.setQueQuan(queQuan);
        nv.setNoiThuongTru(noiThuongTru);
        nv.setSdt(sdt);
        nv.setEmail(email);
        nv.setCccd(cccd);
        nv.setMaTrangThai(maTrangThai);
        nv.setMaRole(maRole);

        dao.update(nv);

        response.sendRedirect("employee");
    }
}