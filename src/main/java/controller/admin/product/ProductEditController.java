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
import entity.ImageProduct;
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
        Product products = UtilDAO.findProductById(Integer.parseInt(idp));

        List<Category> list = ProductAdminDAO.listcate();
        request.setAttribute("product", products);
        JsonObject obj = new JsonObject();
        obj.addProperty("listcate", gson.toJson(list));
        obj.addProperty("products", gson.toJson(products));
        response.getWriter().println(gson.toJson(obj));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String idproduct = request.getParameter("idproducts");

            String name = request.getParameter("nameproducts");
            String namecategory = request.getParameter("namecate");

            String discount = request.getParameter("discount");
            String status = request.getParameter("status");
            String cost = request.getParameter("cost");
            String idcate = request.getParameter("idcate");
            System.out.println(idcate);
            int intValue = Integer.parseInt(cost); // Chuyển thành số nguyên
            double doubleValue = (double) intValue; // Chuyển thành số thực\
            int gg = Integer.parseInt(discount);
            HttpSession session = request.getSession();
            //            if(gg<=100 && gg>=0){
                Product p = new Product(Integer.parseInt(idproduct), name, doubleValue, status, Integer.parseInt(discount),Integer.parseInt(idcate));
                SizeColorProduct s = new SizeColorProduct();
                ProductAdminDAO.updatenameproduct(p);
                ProductAdminDAO.updatecost(p);
                ProductAdminDAO.updatediscount(p);
                ProductAdminDAO.updatestatus(p);
                ProductAdminDAO.updateidcate(p);

                Product product = UtilDAO.findProduct(p.getId());
            System.out.println(product);
                Gson gson = new Gson();
                JsonObject obj = new JsonObject();
                obj.addProperty("product", gson.toJson(product));
//                obj.addProperty("size", gson.toJson(size));

                response.getWriter().println(obj);
//            }
//                session.setAttribute("errordis","So luong khong hop le");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
