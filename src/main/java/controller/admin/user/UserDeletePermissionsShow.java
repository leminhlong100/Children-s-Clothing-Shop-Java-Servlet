package controller.admin.user;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.admin.AccountDAO;
import entity.Account;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-user/UserDeletePermissionsShow")
public class UserDeletePermissionsShow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String pid = request.getParameter("pid");
        AccountDAO.removeAccountPermission(pid);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        if  (pid != null) {
            Account account = (Account) request.getSession().getAttribute("admin");
            jsonObject.add("permissions_customer",gson.toJsonTree(AccountDAO.getPermissions(1)));
            jsonObject.add("permissions_employee",gson.toJsonTree(AccountDAO.getPermissions(2)));
            jsonObject.add("permissions_mod",gson.toJsonTree(AccountDAO.getPermissions(3)));
            boolean isAdmin = false;
            for (Role role : account.getRoles()
            ) {
                if (role.getName().equals("admin")) {
                    isAdmin = true;
                    break;
                }

            }
            jsonObject.addProperty("isAdmin",isAdmin);
            response.getWriter().println(gson.toJson(jsonObject));
        } else {
            request.getRequestDispatcher("/client/403.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
