package controller.admin.user;

import com.google.gson.JsonObject;
import context.DB;
import dao.admin.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import bean.Log;
import entity.Account;

@WebServlet("/admin-user/role-delete")
public class RoleDeleteController extends HttpServlet {
    String namelog="Delete-role";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idRole = request.getParameter("idRole");
        boolean isSuc = false;
        String ipAddress = request.getRemoteAddr();
        Log log = new Log(Log.DANGER,ipAddress,-1,this.namelog,"",0);
        Account account = (Account) request.getSession().getAttribute("admin");
        isSuc =	AccountDAO.removeRole(idRole);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isSuc", isSuc);
        log.setSrc(namelog + " DELETE ");
        log.setContent("DELETE ROLE SUCCESS BY USER: "+ account.getAccountName());
        log.setUserId(account.getId());
        DB.me().insert(log);
        response.getWriter().println(jsonObject);
    }
}
