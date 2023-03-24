package controller.client.cart;

import dao.client.OrderDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
		Object obj = session.getAttribute("cart");// luu tam vao session
		if (account == null) {
			response.sendRedirect(request.getContextPath()+"/client/Login.jsp");
		} else {
			double total = 0;
			List<Order> listOrders = OrderDAO.getListOrderByAcountId(String.valueOf(account.getId()));
			Map<Integer, List<OrderDetail>> map = new HashMap<>();
			for (int i = 0; i < listOrders.size(); i++) {
				List<OrderDetail> listProducts = OrderDAO.getOrderDetailByBid(String.valueOf(listOrders.get(i).getId()));
				map.put(i, listProducts);

			}
			if (obj != null) {
				Map<String, OrderDetail> cartTemporary = (Map<String, OrderDetail>) obj;
				for (Map.Entry<String, OrderDetail> entry : cartTemporary.entrySet()) {
                    OrderDetail billProduct = entry.getValue();
					// tinh tong gia
					total += billProduct.getQuantity() * billProduct.getPrice();
				}
			}
			request.setAttribute("total", total);
			request.setAttribute("products", map);
			request.setAttribute("listOrders", listOrders);
			request.getRequestDispatcher("/client/Cart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
