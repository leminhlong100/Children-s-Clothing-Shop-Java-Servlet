package controller.admin.discount;

import com.google.gson.JsonObject;
import dao.admin.BillAdminDAO;
import dao.admin.DiscountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-discount/delete")
public class DiscountDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        boolean isSuc = false;
        isSuc =	DiscountDAO.deleteDiscount(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isSuc", isSuc);
        response.getWriter().println(jsonObject);
    }
}
