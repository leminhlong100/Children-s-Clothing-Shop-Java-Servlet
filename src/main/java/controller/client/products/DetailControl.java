package controller.client.products;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.client.ProductDAO;
import dao.client.UtilDAO;
import entity.Comment;
import entity.Product;

@WebServlet("/DetailControl")
public class DetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("pid");

			try {
				Product p = UtilDAO.findProductById(Integer.parseInt(id));
				String pid = request.getParameter("pid");
				List<Comment> listcmt = ProductDAO.displayfiveproduct(p.getId(),null,true);
				int num = ProductDAO.numberofcomment();

				request.setAttribute("list",listcmt);
				request.setAttribute("Checknumber",num);

				response.getWriter().println(listcmt);
				request.setAttribute("detail", p);
				request.getRequestDispatcher("/client/Detail.jsp").forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
				}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
