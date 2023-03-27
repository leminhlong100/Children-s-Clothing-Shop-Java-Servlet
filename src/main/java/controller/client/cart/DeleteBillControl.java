package controller.client.cart;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.OrderDetail;

@WebServlet("/DeleteBillControl")
public class DeleteBillControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String key = request.getParameter("key");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");
        int totalQuantity = 0;
        if (obj != null) {
            Map<String, OrderDetail> map = (Map<String, OrderDetail>) obj;
            map.remove(key);
            for (OrderDetail o : map.values()) {
                totalQuantity += o.getQuantity();
            }
            session.setAttribute("cartTotalQuantity", totalQuantity);
            session.setAttribute("cart", map);// update lai vao session
        }

        request.getRequestDispatcher("CartControl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
