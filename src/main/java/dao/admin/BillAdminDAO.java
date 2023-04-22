package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import context.DBContext;
import dao.client.UtilDAO;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import org.jdbi.v3.core.Jdbi;

public class BillAdminDAO {
	
//	public static List<Bill> getListBill() {
//		ArrayList<Bill> list = new ArrayList<>();
//		String query = "select * from Bill ";
//		try {
//			Connection conn =  DBContext.getConnection();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				list.add(new Bill(rs.getInt(1), getAccountByBid(rs.getString(1)), rs.getString(3), rs.getLong(4),
//						rs.getString(5),rs.getString(6)));
//			}
//		} catch (Exception e) {
//		}
//		return list;
//	}

	public static List<Order> getListOrder() {
		Jdbi me = DBContext.me();
		String query = "SELECT od.id,od.createAt,statusPay,od.status,od.idAccount,acc.accountName,od.totalPrice  FROM orders od join accounts acc on od.idAccount = acc.id";
		return me.withHandle(handle -> handle.createQuery(query).map((rs, ctx) -> new Order(rs.getInt("id"), rs.getString("createAt"), rs.getString("statusPay"), rs.getString("status"), new Account(rs.getInt("idAccount"), rs.getString("accountName")),rs.getDouble("totalPrice"))).list());
	}

	public static List<OrderDetail> getOrderDetail() {
		Jdbi me = DBContext.me();
		String query = "SELECT od.id,idOrder,idProduct,nameProduct,quantity,price,node,productSize,productColor FROM order_details od join orders o on  o.id = od.idOrder join products p on od.idProduct = p.id";
		return me.withHandle(handle -> handle.createQuery(query).map((rs, ctx) -> new OrderDetail(rs.getInt("id"), rs.getInt("idOrder"), rs.getInt("idProduct"), new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))),rs.getInt("quantity"),rs.getDouble("price"),rs.getString("node"),rs.getString("productSize"),rs.getString("productColor"))).list());
	}
//	public static Customer getAccountByBid(String bid) {
//		String query = "select a.* from Account a join Bill b on a.uid=b.uid where bid = ? ";
//		try {
//			Connection conn =  DBContext.getConnection();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, bid);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//						rs.getString(6), rs.getString(7));
//			}
//		} catch (Exception e) {
//		}
//		return null;
//	}

	public static void removeBill(String bid) {
		String query1 = "DELETE FROM BillProduct WHERE [bid] =?";
		String query2 = "DELETE FROM Bill WHERE [bid] = ?";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query1);
			ps.setString(1, bid);
			ps.executeUpdate();			
			ps = conn.prepareStatement(query2);
			ps.setString(1, bid);
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

	public static void updateBill(String status, String bid) {
		String query = "update Bill set [status] = ? where bid =?;";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, status);
			ps.setString(2, bid);
			ps.executeUpdate();
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
	}

}
