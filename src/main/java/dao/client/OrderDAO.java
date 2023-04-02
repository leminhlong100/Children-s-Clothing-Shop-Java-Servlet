package dao.client;//package dao.client;

import java.util.List;

import context.DBContext;
import entity.Account;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import org.jdbi.v3.core.Jdbi;

public class OrderDAO {
    public static List<Order> getListOrderByAcountId(String uid) {
        Jdbi me = DBContext.me();
        String query = "SELECT od.id,od.createAt,statusPay,od.status,od.idAccount,acc.accountName,od.totalPrice  FROM kidstore.orders od join kidstore.accounts acc on od.idAccount = acc.id where acc.id = ?;";
        return me.withHandle(handle -> handle.createQuery(query).bind(0, uid).map((rs, ctx) -> new Order(rs.getInt("id"), rs.getString("createAt"), rs.getString("statusPay"), rs.getString("status"), new Account(rs.getInt("idAccount"), rs.getString("accountName")),rs.getDouble("totalPrice"))).list());
    }

    public static List<OrderDetail> getOrderDetailByBid(String pId) {
        Jdbi me = DBContext.me();
        String query = "SELECT od.id,idOrder,idProduct,nameProduct,quantity,price,node FROM kidstore.order_details od join kidstore.orders o on  o.id = od.idOrder join kidstore.products p on od.idProduct = p.id where idOrder =?;";
        return me.withHandle(handle -> handle.createQuery(query).bind(0, pId).map((rs, ctx) -> new OrderDetail(rs.getInt("id"), rs.getInt("idOrder"), rs.getInt("idProduct"), new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))),rs.getInt("quantity"),rs.getDouble("price"),rs.getString("node"))).list());
    }

    public static int createOrder(int idAccount){
        Jdbi me = DBContext.me();
        String query = "insert into orders (idAccount) values(?);";
       return me.withHandle(handle -> handle.createUpdate(query).bind(0,idAccount).executeAndReturnGeneratedKeys("id").mapTo(int.class).one());
    }
	public static void createOrderDetail(OrderDetail orderDetail) {
        Jdbi me = DBContext.me();
		String query = "insert into order_details (idOrder,idProduct,quantity,price,productSize,productColor) values(?,?,?,?,?,?);";
	    me.withHandle(handle -> handle.createUpdate(query).bind(0,orderDetail.getIdOrder()).bind(1,orderDetail.getProduct().getId()).bind(2,orderDetail.getQuantity()).bind(3,orderDetail.getPrice()).bind(4,orderDetail.getProductSize()).bind(5,orderDetail.getProductColor()).execute());
	}

	public static void updateOrder(Order order) {
        Jdbi me = DBContext.me();
		String query = "update orders set totalPrice = ?,createAt =?,sale=?,status=?,statusPay=?,address=?,note=? where id = ?";
        me.withHandle(handle -> handle.createUpdate(query).bind(0,order.getTotalPrice()).bind(1,order.getCreateAt()).bind(2,order.getSale()).bind(3,order.getStatus()).bind(4,order.getStatusPay()).bind(5,order.getAddress()).bind(6,order.getNote()).bind(7,order.getId()).execute());
	}
public static Order getOrderByBid(String id) {
    Jdbi me = DBContext.me();
    String query = "select id,createAt,deliveryAt,statusPay,idAccount,sale,totalPrice,status,address,note,idEmployee,updateAt from orders where id = ?";
     Order  order = me.withHandle(handle -> handle.createQuery(query).bind(0,id).mapToBean(Order.class).one());
     order.setAccount(UtilDAO.findAccountById(order.getIdAccount()));
    return order;
}
public static int updateInventoryProduct(String idProduct,int new_quantity){
        Jdbi me = DBContext.me();
        String query = "update inventorys set quantity = ? where idProduct = ?";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,new_quantity).bind(1,idProduct).execute());
}

    public static void main(String[] args) {
    }

}
