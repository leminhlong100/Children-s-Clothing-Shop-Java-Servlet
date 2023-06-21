package controller.admin.bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Log;
import com.google.gson.JsonObject;
import context.DB;
import dao.admin.BillAdminDAO;
import entity.Account;

@WebServlet("/admin-bill/BillDeleteController")
public class BillDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String namelog ="Remove";
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean isSuc = false;
		String ipAddress =request.getRemoteAddr();
		Log log = new Log(Log.WARNING,ipAddress,-1,this.namelog,"",0);
		Account account = (Account) request.getSession().getAttribute("admin");

		isSuc =	BillAdminDAO.removeBill(id);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isSuc", isSuc);
		log.setSrc(namelog + " REMOVE ");
		log.setContent("DELETE BILL SUCCESS BY USER: "+ account.getAccountName());
		log.setUserId(account.getId());
		DB.me().insert(log);
		response.getWriter().println(jsonObject);
	}

}
