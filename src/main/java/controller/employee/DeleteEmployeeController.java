package controller.employee;

import dao.NhanVienDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeController extends HttpServlet {

    NhanVienDao dao=new NhanVienDao();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        int id=Integer.parseInt(request.getParameter("id"));

        dao.delete(id);

        response.sendRedirect("employee");
    }
}