package controller.client.cart;

import dao.client.UtilDAO;
import entity.Account;
import entity.OrderDetail;
import entity.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCartControl")
public class AddToCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String pId = request.getParameter("pid");
		int totalQuantity = 0;
		Account account = (Account) session.getAttribute("acc");
		if (account == null) {
			response.sendRedirect(request.getContextPath() + "/client/Login.jsp?pid=" + pId);
		} else {
			int quantity = 1;
			Product product = UtilDAO.findProductById(Integer.parseInt(pId));
			Object obj = session.getAttribute("cart");
			String getQuantity = request.getParameter("quantity");
			if (getQuantity != null) {
				quantity = Integer.parseInt(getQuantity);
			}
			if (obj == null) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(product);
				orderDetail.setQuantity(quantity);
				orderDetail.setPrice(product.getDiscountPrice());
				Map<String, OrderDetail> map = new HashMap<>();
				map.put(pId, orderDetail);
				session.setAttribute("cart", map);
				for (OrderDetail o : map.values()) {
					totalQuantity += o.getQuantity();
				}
			}
			else {
				Map<String, OrderDetail> map = (Map<String, OrderDetail>) obj;
				OrderDetail orderDetail = map.get(pId);
				if (orderDetail == null) {
					orderDetail = new OrderDetail();
					orderDetail.setProduct(product);
					orderDetail.setQuantity(quantity);
					orderDetail.setPrice(product.getDiscountPrice());
					map.put(pId, orderDetail);
				} else {

					orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
				}
				for (OrderDetail o : map.values()) {
					totalQuantity += o.getQuantity();
				}
				session.setAttribute("cart", map);// luu tam vao session

			}
			session.setAttribute("cartTotalQuantity", totalQuantity);
			request.getRequestDispatcher("CartControl").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
