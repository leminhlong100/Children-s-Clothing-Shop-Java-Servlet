package controller.admin.user;

import dao.AuthDAO.SecurityDAO;
import dao.admin.AccountDAO;
import entity.Account;
import entity.Permission;
import entity.Resource;

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
            List<Permission> permissionCustomer = new ArrayList<>();
            List<Permission> permissionEmployee = new ArrayList<>();
            List<Permission> permissionMod = new ArrayList<>();
            List<Resource> resourcesCustomer = AccountDAO.getResourceForRoles(1);
            List<Resource> resourcesEmployee = AccountDAO.getResourceForRoles(2);
            List<Resource> resourcesMod = AccountDAO.getResourceForRoles(3);
            for (Resource resource:resourcesCustomer ) {
                permissionCustomer.addAll(AccountDAO.getPermissionForRole(String.valueOf(resource.getId())));
            }
            for (Resource resource:resourcesEmployee ) {
                permissionEmployee.addAll(AccountDAO.getPermissionForRole(String.valueOf(resource.getId())));
            }
            for (Resource resource:resourcesMod ) {
                permissionMod.addAll(AccountDAO.getPermissionForRole(String.valueOf(resource.getId())));
            }
            request.setAttribute("resourcesCustomer", resourcesCustomer);
            request.setAttribute("resourcesEmployee", resourcesEmployee);
            request.setAttribute("resourcesMod", resourcesMod);
            request.setAttribute("permissionCustomer", permissionCustomer);
            request.setAttribute("permissionEmployee", permissionEmployee);
            request.setAttribute("permissionMod", permissionMod);
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
        boolean isCheck = Boolean.parseBoolean(isChecked);
            if(!isCheck){
                AccountDAO.removePermission(resourceId,action);
            }else{
                AccountDAO.insertPermission(resourceId,action);
            }
    }
}
