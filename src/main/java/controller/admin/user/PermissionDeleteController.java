package controller.admin.user;

import com.google.gson.JsonObject;
import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.admin.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-user/PermissionDeleteController")
public class PermissionDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String idRole = request.getParameter("idRole");
        for (String s : AccountDAO.getListIdByIdRole(idRole)) {
            UpdateAccountEndPoint.notifyUserUpdate(Integer.parseInt(s), "update");
        }
        boolean isSuc = false;
        isSuc =	AccountDAO.removeRoleRessource(idRole,id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isSuc", isSuc);
        response.getWriter().println(jsonObject);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
