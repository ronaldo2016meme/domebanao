package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addsanpham")
public class AddSanPhamController extends HttpServlet {
    SanPhamDao dao = new SanPhamDao();

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("addsanpham.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        sanpham sp = new sanpham();

        sp.setTenSP(request.getParameter("tenSP"));
        sp.setMaDanhMuc(request.getParameter("maDanhMuc"));
        sp.setMaNCC(request.getParameter("maNCC"));
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        if (giaBan < 0) {
            request.setAttribute("error", "Giá bán phải lớn hơn hoặc bằng 0");
            request.getRequestDispatcher("addsanpham.jsp")
                    .forward(request, response);
            return;
        }
        sp.setGiaBan(giaBan);
        sp.setMoTa(request.getParameter("moTa"));
        sp.setNgayTao(request.getParameter("ngayTao"));
        sp.setNgayCapNhat(request.getParameter("ngayCapNhat"));
        sp.setAnh(request.getParameter("anh"));
        sp.setMaTrangThaiSP(request.getParameter("maTrangThaiSP"));


        dao.insert(sp);

        response.sendRedirect("sanpham");
    }

}
