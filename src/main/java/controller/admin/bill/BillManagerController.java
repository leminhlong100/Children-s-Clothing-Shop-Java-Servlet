package controller.admin.bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.admin.BillAdminDAO;

@WebServlet("/BillManagerController")
public class BillManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String manipulation = request.getParameter("manipulation");
		String id = request.getParameter("id");
		if (manipulation.equals("deny")) {
			BillAdminDAO.updateBill("Từ chối", id);
		}
		if (manipulation.equals("subject")) {
			BillAdminDAO.updateBill("Đã duyệt", id);
		}
		HttpSession session = request.getSession();
		String sessionID = ";jsessionid="+session.getId();
		response.sendRedirect(request.getContextPath() + "/BillListController"+sessionID);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
