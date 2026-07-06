
package controller;

import dao.TaiKhoanDao;
import model.TaiKhoan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class loginController extends HttpServlet {

    TaiKhoanDao dao = new TaiKhoanDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        TaiKhoan tk = dao.login(username, password);

        if (tk != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", tk);
            response.sendRedirect("home");
        } else {
            request.setAttribute("error",
                    "Sai m\u1EADt kh\u1EA9u ho\u1EB7c t\u00E0i kho\u1EA3n");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
