package controller.admin.show;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.admin.AdminDAO;

@WebServlet("/admin-index")
public class IndexAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> map = new HashMap<>();
//		map.put("sumbill", AdminDAO.getSumBill());
//		map.put("sumprice", AdminDAO.getSumPrice());
		map.put("sumbill", "200");
		map.put("sumprice", "100");
		request.setAttribute("map", map);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
