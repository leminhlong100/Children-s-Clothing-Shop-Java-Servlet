package controller.admin.user;

import bean.Log;
import com.google.gson.JsonObject;
import context.DB;
import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.admin.AccountDAO;
import entity.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-user/PermissionDeleteController")
public class PermissionDeleteController extends HttpServlet {
    String namelog="Delete-permission";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String idRole = request.getParameter("idRole");
        String ipAddress =request.getRemoteAddr();
        Log log = new Log(Log.WARNING,ipAddress,-1,this.namelog,"",0);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        for (String s : AccountDAO.getListIdByIdRole(idRole)) {
            UpdateAccountEndPoint.notifyUserUpdate(Integer.parseInt(s), "update");
        }
        boolean isSuc = false;
        isSuc =	AccountDAO.removeRoleRessource(idRole,id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isSuc", isSuc);
        log.setSrc(namelog +" DELETE");
        log.setContent("DELETE PERMISSION SUCCESS BY USER: "+account.getAccountName());
        log.setUserId(account.getId());
        DB.me().insert(log);
        response.getWriter().println(jsonObject);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
