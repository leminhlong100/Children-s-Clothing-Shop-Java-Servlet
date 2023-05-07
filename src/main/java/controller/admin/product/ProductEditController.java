package controller.admin.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.admin.ProductAdminDAO;
import dao.client.UtilDAO;
import entity.Category;
import entity.Product;

@MultipartConfig
@WebServlet("/ProductEditController")
public class ProductEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String idp = request.getParameter("idproduct");
		Gson gson = new Gson();
		Product products = UtilDAO.findProductById(Integer.parseInt(idp));
		List<Category> list = ProductAdminDAO.listcate();
		request.setAttribute("product", products);
		JsonObject obj = new JsonObject();
		obj.addProperty("listcate",gson.toJson(list));
		obj.addProperty("products",gson.toJson(products));
		response.getWriter().println(gson.toJson(obj));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("idproduct");
		String name = request.getParameter("nameproduct");
		String num = request.getParameter("number");
		String status = request.getParameter("status");
		String cost = request.getParameter("cost");
		String list = request.getParameter("list");

		doGet(request,response);

	}
}
