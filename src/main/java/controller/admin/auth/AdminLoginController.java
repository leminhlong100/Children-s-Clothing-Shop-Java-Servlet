package controller.admin.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO.SecurityDAO;
import dao.admin.LoginAdminDAO;
import dao.client.AuthDAO;
import entity.Account;
import entity.Role;

@WebServlet("/admin/AdminLoginController")
public class AdminLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/admin/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String username = request.getParameter("admin-username");
            String password = request.getParameter("admin-password");
            HttpSession session = request.getSession();
            Account a = LoginAdminDAO.loginAdmin(username, password);
            if (a != null) {
                if (SecurityDAO.hasPermission("/admin-index", a.getAccountName(), "read")) {
                    session.setAttribute("admin", a);
                    RequestDispatcher rd = request.getRequestDispatcher("/admin-index");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Tài khoản đăng nhập hoặc mật khẩu sai !!!");
                    RequestDispatcher rd = request.getRequestDispatcher("/admin/admin-login.jsp");
                    rd.forward(request, response);
                }
            }else {
                request.setAttribute("errorMessage", "Tài khoản đăng nhập hoặc mật khẩu sai !!!");
                RequestDispatcher rd = request.getRequestDispatcher("/admin/admin-login.jsp");
                rd.forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
