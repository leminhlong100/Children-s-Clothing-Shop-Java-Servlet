package controller.client.cart;

import dao.client.OrderDAO;
import entity.OrderDetail;
import util.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart/OrderBillControl")
public class OrderBillControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            String total = request.getParameter("total");
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("cart");// luu tam vao session
            int idProductSizeColor;
            int quantitySizeColor;
            int newQuantitySizeColor;
            boolean isSuc = false;
            int totalQuantity = 0;
            if (obj != null) {
                Map<String, List<OrderDetail>> cartTemp = (Map<String, List<OrderDetail>>) obj;
                for (Map.Entry<String, List<OrderDetail>> entry : cartTemp.entrySet()) {
                    List<OrderDetail> orderDetails = entry.getValue();
                    for (OrderDetail orderDetail : orderDetails) {
                        idProductSizeColor = OrderDAO.getIdSizeColor(orderDetail.getProduct().getId(), orderDetail.getProductSize(), orderDetail.getProductColor());
                        quantitySizeColor = OrderDAO.getQuantitySizeColor(orderDetail.getProduct().getId(), idProductSizeColor);
                        newQuantitySizeColor = quantitySizeColor - orderDetail.getQuantity();
                        if (newQuantitySizeColor >= 0) {
                            totalQuantity += orderDetail.getQuantity();
                            isSuc = true;
                        }else{
                            request.setAttribute("sorry", "Xin lỗi chúng tôi không đủ số lượng sản phẩm này vui lòng cập nhật lại giỏ hàng");
                            isSuc = false;
                        }

                    }
                }
                if(!isSuc){
                    request.getRequestDispatcher("CartControl").forward(request, response);
                }else {
                    request.setAttribute("total", total);
                    request.setAttribute("totalQuantity", totalQuantity);
                    request.getRequestDispatcher("/client/Order.jsp").forward(request, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

}
