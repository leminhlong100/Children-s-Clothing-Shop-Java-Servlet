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

import dao.admin.ProductAdminDAO;
import entity.Category;
import entity.Product;

@MultipartConfig
@WebServlet("/ProductAddController")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> cateList = ProductAdminDAO.listcate();
		request.setAttribute("catelist", cateList);
		request.getRequestDispatcher("/admin/addproduct.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String product_cate = request.getParameter("product-cate");
		String product_name = request.getParameter("product-name");
		String product_price = request.getParameter("product-price");
		String product_title = request.getParameter("product-title");
		String product_desc = request.getParameter("product-desc");
		String sum_Product = request.getParameter("sum-Product");
		String present_Product = request.getParameter("present-Product");
		String product_oldPrice = request.getParameter("product-oldPrice");
//		Product product = new Product(0, product_name, null, Double.parseDouble(product_price), product_title,
//				product_desc, Double.parseDouble(product_oldPrice), Integer.parseInt(product_cate),
//				Integer.parseInt(sum_Product), Integer.parseInt(present_Product));

		Part part = request.getPart("product-image");
		String realPath = request.getServletContext().getRealPath("/image");
		String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectory(Path.of(realPath));
		}
		part.write(realPath + "/" + filename);
//		product.setImage("./image/" + filename);
//		ProductAdminDAO.insertProduct(product);
		HttpSession session = request.getSession();
		String sessionID = ";jsessionid="+session.getId();
		response.sendRedirect(request.getContextPath() + "/ProductListController"+sessionID);
	}

}
