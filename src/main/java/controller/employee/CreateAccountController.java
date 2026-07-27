package controller.employee;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import model.NhanVien;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/createAccount")
public class CreateAccountController extends HttpServlet {

    private NhanVienDao nhanVienDao = new NhanVienDao();
    private TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int maNV = Integer.parseInt(request.getParameter("maNV"));

        NhanVien nv = nhanVienDao.findById(maNV);

        request.setAttribute("nhanVien", nv);

        request.getRequestDispatcher("createAccount.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int maNV = Integer.parseInt(request.getParameter("maNV"));

        String username = request.getParameter("tenDangNhap");
        String password = request.getParameter("matKhau");
        String confirm = request.getParameter("xacNhan");

        NhanVien nv = nhanVienDao.findById(maNV);

        // Kiểm tra nhập lại mật khẩu
        if (!password.equals(confirm)) {

            request.setAttribute("error", "Mật khẩu xác nhận không đúng.");
            request.setAttribute("nhanVien", nv);

            request.getRequestDispatcher("createAccount.jsp")
                    .forward(request, response);
            return;
        }

        // Kiểm tra username
        if (taiKhoanDao.checkUsername(username)) {

            request.setAttribute("error", "Tên đăng nhập đã tồn tại.");
            request.setAttribute("nhanVien", nv);

            request.getRequestDispatcher("createAccount.jsp")
                    .forward(request, response);
            return;
        }

        // Kiểm tra nhân viên đã có tài khoản chưa
        if (taiKhoanDao.checkNhanVien(maNV)) {

            request.setAttribute("error", "Nhân viên đã có tài khoản.");
            request.setAttribute("nhanVien", nv);

            request.getRequestDispatcher("createAccount.jsp")
                    .forward(request, response);
            return;
        }

        TaiKhoan tk = new TaiKhoan();

        tk.setTenDangNhap(username);
        tk.setMatKhau(password);
        tk.setTrangThai(true);
        tk.setMaRole(nv.getMaRole());
        tk.setMaNV(maNV);

        if (taiKhoanDao.insert(tk)) {

            response.sendRedirect("listNhanVien");

        } else {

            request.setAttribute("error", "Tạo tài khoản thất bại.");
            request.setAttribute("nhanVien", nv);

            request.getRequestDispatcher("createAccount.jsp")
                    .forward(request, response);
        }
    }
}