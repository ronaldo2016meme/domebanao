package controller.khachhang;

import dao.KhachHangDao;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addKhachHang")
public class AddKhachHangController extends HttpServlet {

    private final KhachHangDao dao = new KhachHangDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String returnUrl = request.getParameter("returnUrl");

        if (!"banhang".equals(returnUrl)
                && !"khachhang".equals(returnUrl)) {

            returnUrl = "khachhang";
        }

        request.setAttribute("returnUrl", returnUrl);

        request.getRequestDispatcher("/addKhachHang.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String returnUrl = request.getParameter("returnUrl");

        if (!"banhang".equals(returnUrl)
                && !"khachhang".equals(returnUrl)) {

            returnUrl = "khachhang";
        }

        String hoTen = request.getParameter("hoTen");
        String sdt = request.getParameter("sdt");
        String diaChi = request.getParameter("diaChi");

        hoTen = hoTen == null ? "" : hoTen.trim();
        sdt = sdt == null ? "" : sdt.trim();
        diaChi = diaChi == null ? "" : diaChi.trim();

        KhachHang kh = new KhachHang();

        kh.setHoTen(hoTen);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);

        if (hoTen.isEmpty()
                || sdt.isEmpty()
                || diaChi.isEmpty()) {

            forwardError(
                    request,
                    response,
                    kh,
                    returnUrl,
                    "Vui lòng nhập đầy đủ thông tin."
            );

            return;
        }

        if (!sdt.matches("^(0\\d{9}|\\+84\\d{9})$")) {

            forwardError(
                    request,
                    response,
                    kh,
                    returnUrl,
                    "Số điện thoại không hợp lệ."
            );

            return;
        }

        if (dao.isPhoneExists(sdt)) {

            forwardError(
                    request,
                    response,
                    kh,
                    returnUrl,
                    "Số điện thoại đã tồn tại."
            );

            return;
        }

        dao.insert(kh);

        response.sendRedirect(
                request.getContextPath()
                        + "/"
                        + returnUrl
        );
    }

    private void forwardError(HttpServletRequest request,
                              HttpServletResponse response,
                              KhachHang kh,
                              String returnUrl,
                              String error)
            throws ServletException, IOException {

        request.setAttribute("kh", kh);
        request.setAttribute("returnUrl", returnUrl);
        request.setAttribute("error", error);

        request.getRequestDispatcher("/addKhachHang.jsp")
                .forward(request, response);
    }
}