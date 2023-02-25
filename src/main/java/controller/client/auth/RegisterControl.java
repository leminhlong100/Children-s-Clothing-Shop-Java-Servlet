package controller.client.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.client.AuthDAO;
import entity.Customer;
import util.VerifyRecaptchas;

@WebServlet("/Register")
public class RegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");
		String name = request.getParameter("name");
		String passWord = request.getParameter("password");
		String repassWord = request.getParameter("repassword");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phoneNumber");
		String gRecap = request.getParameter("g-recaptcha-response");
		boolean verify = VerifyRecaptchas.verify(gRecap);
		if (verify) {
			if (!passWord.equals(repassWord)) {
				response.sendRedirect("Register.jsp");
			} else {
				Customer a = AuthDAO.checkAccountExist(user,email);
				if (a == null) {
					AuthDAO.signup(user, repassWord,name, email, address, phone);
					request.getRequestDispatcher("/client/Login.jsp").forward(request, response);
				} else {
					request.setAttribute("error", "Người dùng đã tồn tại");
					request.getRequestDispatcher("/client/Register.jsp").forward(request, response);
				}}

		}else {
			request.setAttribute("error", "Chưa nhập Capcha ");
			request.getRequestDispatcher("/client/Register.jsp").forward(request, response);
	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
