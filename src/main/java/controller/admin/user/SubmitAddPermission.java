package controller.admin.user;

import dao.admin.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SubmitAddPermission", value = "/admin-user/SubmitAddPermission")
public class SubmitAddPermission extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String role = request.getParameter("role");
            String resource = request.getParameter("resource");
            String action = request.getParameter("permission");
            AccountDAO.insertPermission(role, resource, action);
            request.getRequestDispatcher("/admin-user/UserPermissionsShow").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
