package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import context.DBContext;
import dao.client.UtilDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import org.jdbi.v3.core.Jdbi;

public class BillAdminDAO {
	public static List<Order> getListOrder() {
		Jdbi me = DBContext.me();
		String query = "SELECT od.id,od.createAt,statusPay,od.status,od.idAccount,acc.accountName,od.totalPrice  FROM orders od join accounts acc on od.idAccount = acc.id";
		return me.withHandle(handle -> handle.createQuery(query).map((rs, ctx) -> new Order(rs.getInt("id"), rs.getString("createAt"), rs.getString("statusPay"), rs.getString("status"), new Account(rs.getInt("idAccount"), rs.getString("accountName")),rs.getDouble("totalPrice"))).list());
	}
	public static Order getOrderById(int idOrder) {
		Jdbi me = DBContext.me();
		String query = "SELECT id,createAt,deliveryAt,statusPay,idAccount,feeship,totalPrice,status,address,wardId,districtId,note FROM kidstore.orders where id = ?";
		return me.withHandle(handle -> handle.createQuery(query).bind(0,idOrder).mapToBean(Order.class).findFirst().orElse(null));
	}

	public static List<OrderDetail> getOrderDetail() {
		Jdbi me = DBContext.me();
		String query = "SELECT od.id,idOrder,idProduct,nameProduct,quantity,price,node,productSize,productColor FROM order_details od join orders o on  o.id = od.idOrder join products p on od.idProduct = p.id";
		return me.withHandle(handle -> handle.createQuery(query).map((rs, ctx) -> new OrderDetail(rs.getInt("id"), rs.getInt("idOrder"), rs.getInt("idProduct"), new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))),rs.getInt("quantity"),rs.getDouble("price"),rs.getString("node"),rs.getString("productSize"),rs.getString("productColor"))).list());
	}
	public static boolean removeBill(String bid) {
		Jdbi me = DBContext.me();
		String query = "delete from orders where id = ?";
		return me.withHandle(handle -> handle.createUpdate(query).bind(0,bid).execute()==1);
	}

	public static boolean updateBill(String status, String bid) {
		String query = "update orders set status = ? where id =?;";
		Jdbi me = DBContext.me();
		return me.withHandle(handle -> handle.createUpdate(query).bind(0,status).bind(1,bid).execute())==1;
	}
	public static void main(String[] args) {
		System.out.println(getOrderById(39));
	}

}
