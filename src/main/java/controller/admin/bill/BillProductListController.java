package controller.admin.bill;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Order;
import entity.OrderDetail;

@WebServlet("/BillProductListController")
public class BillProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		List<Bill> listBills = BillDAO.getListBill();
//		Map<Integer, List<BillDetail>> map = new HashMap<>();
//		for (int i = 0; i < listBills.size(); i++) {
//			List<BillDetail> listProducts = BillDAO.getBillProductByBid(String.valueOf(listBills.get(i).getId()));
//			map.put(i, listProducts);
//		}
//		request.setAttribute("products", map);
//		request.setAttribute("listBills", listBills);
//		request.getRequestDispatcher("/admin/show-bill-product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
