package controller.client.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.client.AuthDAO;
import entity.Account;

@WebServlet("/Register")
public class RegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");
		String passWord = request.getParameter("password");
		String repassWord = request.getParameter("repassword");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phoneNumber");
		if (!passWord.equals(repassWord)) {
			response.sendRedirect("Register.jsp");
		} else {
			Account a = AuthDAO.checkAccountExist(user, email);
			if (a == null) {
				AuthDAO.signup(user, repassWord, email, address, phone);
				request.getRequestDispatcher("/client/Login.jsp").forward(request, response);
			} else {
				request.setAttribute("errorRe", "Tài khoản đã tồn tại");
				request.getRequestDispatcher("/client/Register.jsp").forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
