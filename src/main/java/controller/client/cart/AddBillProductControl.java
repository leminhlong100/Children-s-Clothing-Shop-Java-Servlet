package controller.client.cart;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.OrderDetail;

@WebServlet("/AddBillProductControl")
public class AddBillProductControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("cart");
		if(obj!=null) {
			Map<String, OrderDetail> map = (Map<String, OrderDetail>) obj;
			map.get(key).setQuantity(map.get(key).getQuantity()+1);
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
