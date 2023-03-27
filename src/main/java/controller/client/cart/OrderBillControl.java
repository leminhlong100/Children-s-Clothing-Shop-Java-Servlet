package controller.client.cart;

import entity.OrderDetail;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderBillControl")
public class OrderBillControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String total = request.getParameter("total");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("cart");// luu tam vao session
		int totalQuantity = 0;
		if (obj != null) {
			Map<String, OrderDetail> cartTemporary = (Map<String, OrderDetail>) obj;
			for (OrderDetail orderDetail : cartTemporary.values()) {
				totalQuantity += orderDetail.getQuantity();
			}
		}
		System.out.println(totalQuantity);

		request.setAttribute("total", total);
		request.setAttribute("totalQuantity", totalQuantity);
		request.getRequestDispatcher("/client/Order.jsp").forward(request, response);

	}

}
