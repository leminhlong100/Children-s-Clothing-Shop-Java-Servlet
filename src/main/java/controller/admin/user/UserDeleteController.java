package controller.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.admin.AccountDAO;
import entity.Account;
import entity.Role;

@WebServlet("/admin-user/UserDelete")
public class UserDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uid = request.getParameter("uid");
        AccountDAO.removeAccount(uid);
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(gson.toJson(AccountDAO.getListAccount()));
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("listAccount", jsonArray);
        Account account = (Account) request.getSession().getAttribute("admin");
        boolean isAdmin = false;
        for (Role role : account.getRoles()
        ) {
            if (role.getName().equals("admin")) {
                isAdmin = true;
                break;
            }

        }
        jsonObject.addProperty("isAdmin",isAdmin);
        UpdateAccountEndPoint.notifyUserUpdate(Integer.parseInt(uid),"remove");
        response.getWriter().println(gson.toJson(jsonObject));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
