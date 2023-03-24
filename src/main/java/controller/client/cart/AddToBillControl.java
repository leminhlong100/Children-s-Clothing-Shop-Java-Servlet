package controller.client.cart;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.client.OrderDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;

@WebServlet("/AddBillControl")
public class AddToBillControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("cart");// luu tam vao session
		if (obj != null) {// KIEM TRA XEM CO SP TRONG GIO HANG KO?
			Map<String, OrderDetail> map = (Map<String, OrderDetail>) obj;
//			 TAO HOA DON TRUOC, DE LAY DUOC ID BILL
			Order order = new Order();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			order.setCreateAt(sdf.format(new Date()));
			Account account = (Account) session.getAttribute("acc");
			order.setAccount(account);
			order.setStatus("Chưa duyệt");
			int idOrder = OrderDAO.createOrder(order.getAccount().getId());
			order.setId(idOrder);
			order.setStatusPay("unpaid");
			order.setAddress(request.getParameter("transaction_address"));
			long total = 0;// tinh tong gia
			for (Map.Entry<String, OrderDetail> entry : map.entrySet()) {
				OrderDetail orderDetail = entry.getValue();

				orderDetail.setIdOrder(order.getId());// set bill id vao day
				// luu lai cac mat hang
				OrderDAO.createOrderDetail(orderDetail);
				// tinh tong gia
				total += orderDetail.getQuantity() * orderDetail.getPrice();
			}
			/// cap nhat lai bill de co tong gia tien
			order.setTotalPrice(total);
			order.setNote(request.getParameter("transaction_mess"));
			OrderDAO.updateOrder(order);
			session.removeAttribute("cart");
			request.getRequestDispatcher("CartControl").forward(request, response);
		} else {
			request.getRequestDispatcher("CartControl").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
