package controller.client.cart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.client.BillDAO;
import entity.Account;
import entity.Bill;
import entity.BillProduct;

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
			List<Bill> listBills = BillDAO.getListBillByUid(String.valueOf(account.getId()));

			Map<Integer, List<BillProduct>> map = new HashMap<>();
			double total = 0;
			for (int i = 0; i < listBills.size(); i++) {
				List<BillProduct> listProducts = BillDAO.getBillProductByBid(String.valueOf(listBills.get(i).getId()));
				map.put(i, listProducts);

			}
			if (obj != null) {
				Map<String, BillProduct> cartTemporary = (Map<String, BillProduct>) obj;
				for (Entry<String, BillProduct> entry : cartTemporary.entrySet()) {
					BillProduct billProduct = entry.getValue();
					// tinh tong gia
					total += billProduct.getQuantity() * billProduct.getUnitPrice();
				}
			}
			request.setAttribute("total", total);
			request.setAttribute("products", map);
			request.setAttribute("listBills", listBills);
			request.getRequestDispatcher("/client/Cart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
