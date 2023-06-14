package controller.admin.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.admin.ProductAdminDAO;
import dao.client.ProductDAO;
import dao.client.UtilDAO;
import entity.Product;

@WebServlet("/admin-products/ProductListController")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
		List<Product> render = ProductAdminDAO.showlistproduct();
			request.setAttribute("renderproduct", render);
			request.getRequestDispatcher("/admin/admin-product-manager.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("idproduct");
		boolean isdelete =false;
		isdelete = ProductAdminDAO.deleteproduct(id);
		JsonObject obj = new JsonObject();
		obj.addProperty("isDel",isdelete);
		response.getWriter().println(obj);
	}

}
