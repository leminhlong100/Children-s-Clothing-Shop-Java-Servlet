package controller.admin.user;

import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.AuthDAO.SecurityDAO;
import dao.admin.AccountDAO;
import entity.Account;
import entity.Permission;
import entity.Resource;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-user/permission")
public class PermissionListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("admin");
        if (SecurityDAO.hasPermission("/admin-user", account.getAccountName(), "read")) {
            List<Role> roles = AccountDAO.getAllRoles();
            request.setAttribute("roles", roles);
            request.getRequestDispatcher("/admin/admin-roles-manager.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/client/403.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String isChecked = request.getParameter("isChecked");
        String resourceId = request.getParameter("resourceId");
        String idRole = request.getParameter("idRole");

        boolean isCheck = Boolean.parseBoolean(isChecked);
        if (!isCheck) {
            AccountDAO.removePermission(resourceId,idRole, action);
        } else {
            AccountDAO.insertPermission(resourceId,idRole, action);
        }
        for (String s : AccountDAO.getListIdByIdRole(idRole)) {
            UpdateAccountEndPoint.notifyUserUpdate(Integer.parseInt(s), "update");
        }
    }
}
