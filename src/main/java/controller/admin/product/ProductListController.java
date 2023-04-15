package controller.admin.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.admin.ProductAdminDAO;
import dao.client.ProductDAO;
import entity.Product;

@WebServlet("/admin-products/ProductListController")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String indexPage = request.getParameter("index");
			if (indexPage == null) {
				indexPage = "1";
			}
			int index = Integer.parseInt(indexPage);
			int count = ProductAdminDAO.getTotalProduct();
			int endPage = count / 10;
			if (count % 10 != 0) {
				endPage++;
			}
			List<Product> productList = ProductAdminDAO.getListProduct(index);
			request.setAttribute("tag", index);
			request.setAttribute("endPage", endPage);
			request.setAttribute("productlist", productList);
			request.getRequestDispatcher("/admin/show-product.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
