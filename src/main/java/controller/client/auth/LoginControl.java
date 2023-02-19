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


@WebServlet("/Login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userName = request.getParameter("email");
		String passWord = request.getParameter("password");
		String pid = request.getParameter("pid");
	
		Customer a = AuthDAO.login(userName, passWord);
		if(a==null) {
			request.setAttribute("error", "Tài khoản hoặc mật khẩu sai");
			request.getRequestDispatcher("/client/Login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("acc", a);
			session.setMaxInactiveInterval(1800);
			if(pid==null) {
//				request.getRequestDispatcher("IndexControl").forward(request, response);
				response.sendRedirect("IndexControl");
			}else {
				response.sendRedirect("DetailControl?pid="+pid);
			}
		
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
