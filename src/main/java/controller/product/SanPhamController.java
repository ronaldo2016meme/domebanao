package controller.product;

import dao.SanPhamDao;
import model.sanpham;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sanpham")
public class SanPhamController extends HttpServlet {
    SanPhamDao dao = new SanPhamDao();

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            List<sanpham> list = dao.getAll();
            request.setAttribute("list", list);
            request.getRequestDispatcher("sanpham.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")) {

            int id = Integer.parseInt(request.getParameter("id"));
            sanpham sp = dao.getById(id);

            request.setAttribute("sp", sp);
            request.getRequestDispatcher("editSanPham.jsp")
                    .forward(request, response);

        } else if (action.equals("add")) {

            request.getRequestDispatcher("addSanPham.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        sanpham sp = new sanpham();

        sp.setTenSP(request.getParameter("tenSP"));
        sp.setMaDanhMuc(request.getParameter("danhMuc"));
        sp.setMaNCC(request.getParameter("nhaCungCap"));
        sp.setGiaBan(Double.parseDouble(request.getParameter("giaBan")));
        sp.setMoTa(request.getParameter("moTa"));
        sp.setNgayTao(request.getParameter("ngayTao"));
        sp.setNgayCapNhat(request.getParameter("ngayCapNhat"));
        sp.setAnh(request.getParameter("anh"));
        sp.setMaTrangThaiSP(request.getParameter("maTrangThaiSP"));

        if ("insert".equals(action)) {

            dao.insert(sp);

        } else if ("update".equals(action)) {

            sp.setMaSP(Integer.parseInt(request.getParameter("maSP")));
            dao.update(sp);
        }

        response.sendRedirect("sanpham");
    }
}
