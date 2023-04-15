package controller.admin.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.admin.AccountDAO;
import dao.client.UtilDAO;
import entity.Account;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/admin-user/UserUpdate")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("uid");
		Account account = UtilDAO.findAccountById(Integer.parseInt(user_id));
		request.setAttribute("account", account);
		request.getRequestDispatcher("/admin/edituser.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			String user_id = request.getParameter("uid");
			String user_name = request.getParameter("user-name");
			String full_name = request.getParameter("full-name");
			String user_email = request.getParameter("user-email");
			String user_phone = request.getParameter("user-phone");
			String user_password = request.getParameter("user-password");
			String user_address = request.getParameter("user-address");
			Account a = new Account(Integer.parseInt(user_id), user_name, user_password, full_name,user_address, user_email,
					user_phone);
			Account account = AccountDAO.findAccountById(Integer.parseInt(user_id));
			if(a.getPassword().equals(account.getPassword())||a.getPassword()==null){
				AccountDAO.updateAccountNoPass(a);
				System.out.println("cc");
			}else{
				AccountDAO.updateAccount(a);
			}
			response.sendRedirect(request.getContextPath() + "/admin-user");
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
