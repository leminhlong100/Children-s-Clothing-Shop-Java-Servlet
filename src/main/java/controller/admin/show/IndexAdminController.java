package controller.admin.show;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.admin.AccountDAO;
import dao.admin.AdminDAO;
import dao.admin.ProductAdminDAO;

@WebServlet("/admin-index")
public class IndexAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int sumAccount = AccountDAO.getTotalAccount();
		request.setAttribute("sumAccount", sumAccount);

		int sumProduct = ProductAdminDAO.getTotalActiveProduct();
		request.setAttribute("sumProduct", sumProduct);

		request.getRequestDispatcher("/admin/admin-index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
