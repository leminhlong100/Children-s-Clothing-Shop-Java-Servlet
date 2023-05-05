package dao.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import context.DB;
import org.jdbi.v3.core.Jdbi;

import context.DBContext;
import entity.Product;
import entity.View;

public class IndexDAO {
	public static List<Product> getSellProduct() {
		Jdbi me = DBContext.me();
		String query ="SELECT p.id, p.nameProduct, pp.listPrice, pp.discount, pp.discountPrice \n" +
				"FROM kidstore.products p \n" +
				"join kidstore.product_prices pp on p.id = pp.idProduct\n" +
				"join kidstore.inventorys  invent on invent.idProduct = p.id\n" +
				"WHERE p.id IN (\n" +
				"SELECT orr.idProduct\n" +
				"FROM kidstore.order_details orr \n" +
				"JOIN kidstore.orders od ON orr.idOrder = od.id\n" +
				"WHERE od.statusPay = 'paid' AND YEAR(od.createAt) = YEAR(CURRENT_DATE()) AND MONTH(od.createAt) = MONTH(CURRENT_DATE()))\n" +
				"\tGROUP BY p.id\n" +
				"\tORDER BY SUM(invent.quantity) DESC LIMIT 4";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}

	public static List<Product> getSellProductTwo() {
		Jdbi me = DBContext.me();
		String query ="SELECT p.id, p.nameProduct, pp.listPrice, pp.discount, pp.discountPrice \n" +
				"FROM kidstore.products p \n" +
				"join kidstore.product_prices pp on p.id = pp.idProduct\n" +
				"join kidstore.inventorys  invent on invent.idProduct = p.id\n" +
				"WHERE p.id IN (\n" +
				"SELECT orr.idProduct\n" +
				"FROM kidstore.order_details orr \n" +
				"JOIN kidstore.orders od ON orr.idOrder = od.id\n" +
				"WHERE od.statusPay = 'paid' AND YEAR(od.createAt) = YEAR(CURRENT_DATE()) AND MONTH(od.createAt) = MONTH(CURRENT_DATE()))\n" +
				"\tGROUP BY p.id\n" +
				"\tORDER BY SUM(invent.quantity) DESC LIMIT 4,4";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}


	public static List<Product> getOutstandingProduct() {
		Jdbi me = DBContext.me();
		String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice FROM products p join suppliers s on p.idSupplier = s.id join product_prices pp on pp.idProduct = p.id where p.isActive = '1' and s.isActive ='1' limit 3";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());

	}

	public static List<Product> getOutstandingProductShoes() {
		Jdbi me = DBContext.me();
		List<Product> list = new ArrayList<>();
		String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice from products p join product_prices pp \n" +
				"on p.id = pp.idProduct join producers pd \n" +
				"on p.idProducer = pd.id  join categories ct\n" +
				"on p.idCategorie = ct.id \n" +
				"where p.isActive =1 and ct.id in (1,3,5,6)\n" +
				"limit 2";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}

	public static List<Product> getNewProductBoy() {
		Jdbi me = DBContext.me();
		List<Product> list = new ArrayList<>();
		String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice from products p join product_prices pp \n" +
				"on p.id = pp.idProduct join producers pd \n" +
				"on p.idProducer = pd.id  join categories ct\n" +
				"on p.idCategorie = ct.id \n" +
				"where p.isActive =1 and ct.id in (1,2,5,7,9,13)\n" +
				"limit 4";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}


	public static List<Product> getNewProductGirl() {
		Jdbi me = DBContext.me();
		List<Product> list = new ArrayList<>();
		String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice from products p join product_prices pp \n" +
				"on p.id = pp.idProduct join producers pd \n" +
				"on p.idProducer = pd.id  join categories ct\n" +
				"on p.idCategorie = ct.id \n" +
				"where p.isActive =1 and ct.id in (3,4,6,8,11,12,13)\n" +
				"limit 4";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}
	public static List<Product> getNewProductAccessory() {
		Jdbi me = DBContext.me();
		List<Product> list = new ArrayList<>();

		String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice from products p join product_prices pp \n" +
				"on p.id = pp.idProduct join producers pd \n" +
				"on p.idProducer = pd.id  join categories ct\n" +
				"on p.idCategorie = ct.id \n" +
				"where p.isActive =1 and ct.id in (14,15)\n" +
				"limit 4";
		return me.withHandle(handle -> handle.createQuery(query)
				.map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
						rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
						rs.getInt("discount"),rs.getDouble("discountPrice")))
				.list());
	}
	public static List<Product> getSuperSellProduct() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice and (100/p.discount*(p.discount-p.priceProduct)>10);";
		try (Connection conn = DBContext.getConnection();
			 PreparedStatement ps = conn.prepareStatement(query);
			 ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"),
						rs.getDouble("priceProduct"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct")),
						rs.getInt("discount"),rs.getDouble("discountPrice")));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println(getOutstandingProduct());
//		System.out.println(getSellProductTwo());

	}
}