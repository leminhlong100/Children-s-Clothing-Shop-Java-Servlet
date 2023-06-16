package controller.admin.product;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import dao.admin.*;
import entity.*;

import static jdk.jpackage.internal.IOUtils.getFileName;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 *2, //2MB
				maxFileSize = 1024 * 1024 * 10, //10MB
				maxRequestSize = 1024 * 1024 * 50 ) // 50MB
@WebServlet("/admin-products/ProductAddController")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "upload"; // file save image

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/admin/admin-add-product.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	try{
		//Get imformation from form

		//Save informmation in CSDL

		//Save images
//		String uploadPath = getServletContext().getRealPath("/images");
		String uploadPath = "D:\\TTLTW\\src\\main\\webapp\\images";
		System.out.println(uploadPath);
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}

		//Save main image
		Part mainImagePart = request.getPart(("mainImage"));
		String mainImageFileName = "main_" + System.currentTimeMillis() + "_" + getFileName(mainImagePart);
		mainImagePart.write(uploadPath + File.separator + mainImageFileName);

		//Save description of product
		Collection<Part> descriptionImageParts = request.getParts();
		for(Part part : descriptionImageParts){
			if(part.getName().equals("descriptionImages")){
				String descriptionImageFileName = System.currentTimeMillis() + "_" + getFileName(part);
				part.write(uploadPath + File.separator + descriptionImageFileName);
			}
		}

		//Send redirect when save success
		response.sendRedirect(request.getContextPath() + "/admin-products/ProductListController");
	}catch (Exception e){
		e.printStackTrace();
	}
	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}

}
