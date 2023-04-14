package controller.admin.user;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.admin.AccountDAO;
import entity.Account;
import entity.Resource;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-user/UserAddPermission")
public class UserAddPermission extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<Role> roles = AccountDAO.getAllRoles();
            List<Resource> resources = AccountDAO.getResourceForRoles(1);
            request.setAttribute("roles", roles);
            request.setAttribute("resources", resources);
            request.getRequestDispatcher("/admin/addpemission.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idRole = request.getParameter("idRole");
        if (idRole != null) {
            List<Resource> resources = AccountDAO.getResourceForRoles(Integer.parseInt(idRole));
            Gson gson = new Gson();
            JsonArray jsonArray  = new JsonArray();
            jsonArray.add(gson.toJson(resources));
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("listResource",jsonArray);
            response.getWriter().println(gson.toJson(jsonObject));
        }
    }
}
