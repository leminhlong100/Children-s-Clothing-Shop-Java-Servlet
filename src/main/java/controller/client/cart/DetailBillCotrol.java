package controller.client.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.client.BillDAO;
import entity.Bill;
import entity.BillDetail;

@WebServlet("/DetailBill")
public class DetailBillCotrol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Bill b = BillDAO.getBillByBid(id);
		List<BillDetail> billProduct = BillDAO.getBillProductByBid(id);
		request.setAttribute("bill", b);
		request.setAttribute("billProducts", billProduct);
		request.getRequestDispatcher("/client/BillDetail.jsp").forward(request, response);
	}

}
