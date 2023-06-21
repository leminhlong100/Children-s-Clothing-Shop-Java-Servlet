package controller.admin.discount;

import com.google.gson.JsonObject;
import dao.admin.DiscountDAO;
import entity.Discount;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin-discount/add")
public class DiscountAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuc = false;
        String nameDiscount = request.getParameter("nameDiscount");
        String description = request.getParameter("description");
        String codeDiscount = request.getParameter("codeDiscount");
        String percentage = request.getParameter("percentage");
        String quantity = request.getParameter("quantity");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String status = request.getParameter("status");
        Discount discount = new Discount(0,nameDiscount,description,codeDiscount,Double.parseDouble(percentage),Integer.parseInt(quantity),startTime,endTime,status);
        isSuc = DiscountDAO.insertDiscount(discount);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isSuc", isSuc);
        response.getWriter().println(jsonObject);
    }
}
