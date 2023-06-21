package controller.admin.discount;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.admin.webSocket.UpdateAccountEndPoint;
import dao.admin.AccountDAO;
import dao.admin.DiscountDAO;
import dao.client.AuthDAO;
import dao.client.UtilDAO;
import entity.Account;
import entity.Discount;
import entity.Role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@WebServlet("/admin-discount/update")
public class UpdateDiscountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            Discount discount = DiscountDAO.getDiscountById(id);
            LocalDateTime startTime = LocalDateTime.parse(discount.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String startTimeString = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            discount.setStartTime(startTimeString);
            LocalDateTime endTime = LocalDateTime.parse(discount.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String endTimeString = endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            discount.setEndTime(endTimeString);
            Gson gson = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("discount", gson.toJsonTree(discount));
            response.getWriter().println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String nameDiscount = request.getParameter("nameDiscount");
        String description = request.getParameter("description");
        String codeDiscount = request.getParameter("codeDiscount");
        String percentage = request.getParameter("percentage");
        String quantity = request.getParameter("quantity");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String status = request.getParameter("status");
        Discount discount = new Discount(Integer.parseInt(id),nameDiscount,description,codeDiscount,Double.parseDouble(percentage),Integer.parseInt(quantity),startTime,endTime,status);
        DiscountDAO.updateDiscount(discount);
        Discount discountNew = DiscountDAO.getDiscountById(id);
        System.out.println(discountNew);
        LocalDateTime startTime1 = LocalDateTime.parse(discountNew.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String startTimeString = startTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        discountNew.setStartTime(startTimeString);
        LocalDateTime endTime1 = LocalDateTime.parse(discountNew.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTimeString = endTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        discountNew.setEndTime(endTimeString);
        System.out.println(discountNew);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("discount", gson.toJsonTree(discountNew));
        response.getWriter().println(gson.toJson(jsonObject));
    }
}
