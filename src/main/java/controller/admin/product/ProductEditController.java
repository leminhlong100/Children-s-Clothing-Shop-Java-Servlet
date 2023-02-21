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
@WebServlet("/ProductEditController")
public class ProductEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> cateList = ProductAdminDAO.getListCategory();
		request.setAttribute("catelist", cateList);
		String id = request.getParameter("id");
		Product product = ProductAdminDAO.getProductByID(id);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/admin/editproduct.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		Product product = ProductAdminDAO.getProductByID(request.getParameter("product-id"));
//		product.setName(request.getParameter("product-name"));
//		product.setOlePrice(Double.parseDouble(request.getParameter("product-oldPrice")));
//		product.setCateId(Integer.parseInt(request.getParameter("product-cate")));
//		product.setPrice(Double.parseDouble(request.getParameter("product-price")));
//		product.setPresentProduct(Integer.parseInt(request.getParameter("present-Product")));
//		product.setDescription(request.getParameter("product-desc"));
//		product.setTitle(request.getParameter("product-title"));
//		product.setSumProduct(Integer.parseInt(request.getParameter("sum-Product")));
		try {
			Part part = request.getPart("product-image");

			String realPath = request.getServletContext().getRealPath("/image");
			String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectory(Path.of(realPath));
			}
			part.write(realPath + "/" + filename);
//			product.setImage("./image/" + filename);
		} catch (Exception e) {
			
		}
//		ProductAdminDAO.updateProduct(product);
		HttpSession session = request.getSession();
		String sessionID = ";jsessionid="+session.getId();
		response.sendRedirect(request.getContextPath() + "/ProductListController"+sessionID);
	}

}
