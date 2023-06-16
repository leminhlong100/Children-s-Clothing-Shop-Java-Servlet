


package controller.admin.product;

        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.util.HashMap;
        import java.util.List;

        import javax.json.Json;
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
        import entity.*;
        import jnr.ffi.annotations.In;

@MultipartConfig
@WebServlet("/admin-products/Editcolor")
public class EditColorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idp = request.getParameter("idproduct");
        String idSize = request.getParameter("idSize");
        Gson gson = new Gson();
        Product products = UtilDAO.findProductById(Integer.parseInt(idp));
        request.setAttribute("product", products);
        JsonObject obj = new JsonObject();
        obj.addProperty("products", gson.toJson(products));
        response.getWriter().println(gson.toJson(obj));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String idproduct = request.getParameter("idProduct");
            String idsize = request.getParameter("idSize");
            String name = request.getParameter("nameProduct");

            String color = request.getParameter("color");
            String quantity = request.getParameter("number");
            HttpSession session = request.getSession();
            SizeColorProduct s = new SizeColorProduct(Integer.parseInt(idsize),Integer.parseInt(idproduct),color);
            Inventory i = new Inventory(Integer.parseInt(idproduct),Integer.parseInt(idsize),Integer.parseInt(quantity));

            Product p = new Product(Integer.parseInt(idproduct),name);
            ProductAdminDAO.updatecolor(s);
            ProductAdminDAO.updatenumber(i);
            Product product = UtilDAO.findProductById(p.getId());
            Gson gson = new Gson();
            JsonObject obj = new JsonObject();
            obj.addProperty("update",gson.toJson(product));
            obj.addProperty("idSizeColor",idsize);
            obj.addProperty("newcolor",color);
            obj.addProperty("newquantity",quantity);

            response.getWriter().println(gson.toJson(obj));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}