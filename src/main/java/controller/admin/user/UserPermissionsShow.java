package controller.admin.user;

import dao.AuthDAO.SecurityDAO;
import dao.admin.AccountDAO;
import dao.client.AuthDAO;
import entity.Account;
import entity.Permission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin-user/UserPermissionsShow")
public class UserPermissionsShow extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("admin");
        try {
            if (SecurityDAO.hasPermission("/admin-user", account.getAccountName(), "read")) {
                request.setAttribute("permissions_customer", AccountDAO.getPermissions(1));
                request.setAttribute("permissions_employee", AccountDAO.getPermissions(2));
                request.setAttribute("permissions_mod", AccountDAO.getPermissions(3));
                request.getRequestDispatcher("/admin/permissions.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/client/403.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
