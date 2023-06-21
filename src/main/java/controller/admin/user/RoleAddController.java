package controller.admin.user;

import dao.admin.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-user/role-add")
public class RoleAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nameRole = request.getParameter("nameRole");
            AccountDAO.insertRole(nameRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
