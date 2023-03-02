package controller.client.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.client.AuthDAO;
import entity.Customer;

/**
 * Servlet implementation class LoginFacebook
 */
@WebServlet("/client/LoginFacebook")
public class LoginFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();

		Customer cus = AuthDAO.loginFacebook(id, email);
		if (action.equals("Face")) {
			AuthDAO.signinFacebook(id, name, email);
			cus = AuthDAO.loginFacebook(id, email);
			System.out.println(name);
			System.out.println(email);
			System.out.println(id);
			session.setAttribute("acc", cus);
			session.setMaxInactiveInterval(1800);
			if (pid == null) {
				response.sendRedirect(request.getContextPath() + "/IndexControl");
			} else {
				response.sendRedirect("DetailControl?pid=" + pid);
			}
		} else {
			session.setAttribute("acc", cus);
			session.setMaxInactiveInterval(1800);
			if (pid == null) {
				response.sendRedirect(request.getContextPath() + "/IndexControl");
			} else {
				response.sendRedirect("DetailControl?pid=" + pid);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
