package controller.admin.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
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
import dao.client.AuthDAO;
import dao.client.UtilDAO;
import entity.Category;
import entity.Product;
import entity.SizeColorProduct;

@MultipartConfig
@WebServlet("/admin-products/ProductEditController")
public class ProductEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String idp = request.getParameter("idproduct");
		Gson gson = new Gson();
		Product products = UtilDAO.findproductByID(Integer.parseInt(idp));

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
		try {

			String idproduct = request.getParameter("idproducts");

			String name = request.getParameter("nameproducts");
			String num = request.getParameter("number");
			String status = request.getParameter("status");
			String cost = request.getParameter("cost");
			String selected = request.getParameter("listselected");

			Product p = new Product(Integer.parseInt(idproduct),name,Integer.parseInt(cost));

			ProductAdminDAO.updatenameproduct(p);
			ProductAdminDAO.updatecost(p);
			List<Product> listproduct= ProductAdminDAO.showlistproduct();
			List<Category> listcate = ProductAdminDAO.listcate();
			Gson gson = new Gson();
			JsonObject obj = new JsonObject();
			obj.addProperty("load",gson.toJson(listproduct));
			obj.addProperty("cate",gson.toJson(listcate));
			response.getWriter().println(gson.toJson(obj));
		}catch (Exception e){
			e.printStackTrace();
		}


	}
}
