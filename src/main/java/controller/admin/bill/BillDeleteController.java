package controller.admin.bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controller.client.auth.UpdateAccountEndPoint;
import dao.admin.AccountDAO;
import dao.admin.BillAdminDAO;
import entity.Account;
import entity.Role;

@WebServlet("/admin-bill/BillDeleteController")
public class BillDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean isSuc = false;
		isSuc =	BillAdminDAO.removeBill(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isSuc", isSuc);
		response.getWriter().println(jsonObject);
	}

}
