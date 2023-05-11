package controller.client.cart;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.client.OrderDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import util.API;

@WebServlet("/cart/AddBillControl")
public class AddToBillControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String provinceId = request.getParameter("provinceId");
        String districtId = request.getParameter("districtId");
        String wardId = request.getParameter("wardId");
        String feeShip = API.feeShip("1540", "440505", districtId, wardId, "20", "20", "20", "100");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ship", feeShip);
        response.getWriter().println(jsonObject);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("cart");// luu tam vao session
            String ship = request.getParameter("shipFee");
            String districtId = request.getParameter("calc_shipping_district");
            String wardId = request.getParameter("calc_shipping_ward");
            boolean isSuc = false;
            int idProductSizeColor;
            int quantitySizeColor;
            int newQuantitySizeColor = 0;
            int totalQuantity = 0;
            if (obj != null) {// KIEM TRA XEM CO SP TRONG GIO HANG KO?
                Map<String, List<OrderDetail>> map = (Map<String, List<OrderDetail>>) obj;
//			 TAO HOA DON TRUOC, DE LAY DUOC ID BILL
                Order order = new Order();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Account account = (Account) session.getAttribute("acc");
                order.setAccount(account);
                order.setStatus("Đang xử lý");
                int idOrder = OrderDAO.createOrder(order.getAccount().getId());
                order.setId(idOrder);
                order.setStatusPay("Chưa thanh toán");
                String address = request.getParameter("billingAddress");
                if (address != null || !address.isEmpty()) {
                    order.setAddress(address);
                } else {
                    order.setAddress(account.getAddress());
                }

                long total = 0;// tinh tong gia
                for (Map.Entry<String, List<OrderDetail>> entry : map.entrySet()) {
                    List<OrderDetail> orderDetails = entry.getValue();
                    for (OrderDetail orderDetail : orderDetails) {
                        orderDetail.setIdOrder(order.getId());// set bill id vao day
                        // cap nhat so luong cua tung san pham
                         idProductSizeColor = OrderDAO.getIdSizeColor(orderDetail.getProduct().getId(), orderDetail.getProductSize(), orderDetail.getProductColor());
                         quantitySizeColor = OrderDAO.getQuantitySizeColor(orderDetail.getProduct().getId(), idProductSizeColor);
                         newQuantitySizeColor = quantitySizeColor - orderDetail.getQuantity();
                        if(newQuantitySizeColor>=0){
                            // luu lai cac mat hang
                            OrderDAO.createOrderDetail(orderDetail);
                            OrderDAO.updateInventoryProduct(String.valueOf(orderDetail.getProduct().getId()), newQuantitySizeColor, idProductSizeColor);
                        }else{
                            OrderDAO.deleteOrder(idOrder);
                            request.setAttribute("sorry","Xin lỗi chúng tôi không đủ số lượng sản phẩm này");
                            request.getRequestDispatcher("CartControl").forward(request, response);
                            return;
                        }
                        // tinh tong gia
                        total += orderDetail.getQuantity() * orderDetail.getPrice();
                        totalQuantity += orderDetail.getQuantity();
                    }
                }
                /// cap nhat lai bill de co tong gia tien
                if(ship!=null&&!ship.isEmpty()) {
                    order.setTotalPrice(total + Integer.parseInt(ship)); // vi du cua phi van chyen
                }
                order.setNote(request.getParameter("note"));
                order.setWardId(wardId);
                order.setDistrictId(districtId);
                OrderDAO.updateOrder(order);
                request.setAttribute("order", order);
                request.setAttribute("ship", ship);
                request.setAttribute("cart", obj);
                session.removeAttribute("cart");
                request.getRequestDispatcher("/client/CheckOut.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("CartControl").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
