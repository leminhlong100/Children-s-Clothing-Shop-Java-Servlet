package controller.admin.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.admin.AccountDAO;
import dao.client.AuthDAO;
import dao.client.UtilDAO;
import entity.Account;
import entity.Resource;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/admin-user/PermissionAddController")
public class PermissionAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Resource> resources = AccountDAO.getAllResource();
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("resources", gson.toJsonTree(resources));
            response.getWriter().println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] accessAdd = request.getParameterValues("accessAdd[]");
            String resource = request.getParameter("resource");
            String idRole = request.getParameter("idRole");
            AccountDAO.insertRoleRessource(idRole, resource);
            for (String s : AccountDAO.getListIdByIdRole(idRole)) {
                UpdateAccountEndPoint.notifyUserUpdate(Integer.parseInt(s), "update");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
