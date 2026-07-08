package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editsanpham")
public class EditSanPhamController extends HttpServlet {
    SanPhamDao dao = new SanPhamDao();

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        sanpham sp = dao.getById(id);

        request.setAttribute("sp", sp);

        request.getRequestDispatcher("editsanpham.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        sanpham sp = new sanpham();

        sp.setMaSP(Integer.parseInt(request.getParameter("maSP")));
        sp.setTenSP(request.getParameter("tenSP"));
        sp.setDanhMuc(request.getParameter("danhMuc"));
        sp.setNhaCungCap(request.getParameter("nhaCungCap"));
        sp.setGiaBan(Double.parseDouble(request.getParameter("giaBan")));
        sp.setMoTa(request.getParameter("moTa"));
        sp.setNgayTao(request.getParameter("ngayTao"));
        sp.setNgayCapNhat(request.getParameter("ngayCapNhat"));
        sp.setAnh(request.getParameter("anh"));

        dao.update(sp);

        response.sendRedirect("sanpham");
    }
}
