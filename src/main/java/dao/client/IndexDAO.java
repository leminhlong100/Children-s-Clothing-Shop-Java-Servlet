package dao.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Product;
import entity.View;

public class IndexDAO {
	public static List<Product> getSellProduct() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount,idCategorie FROM product p join producers s on p.idProducer = s.idProducer where p.isActice ='1' and s.isActice and (p.products_sold/p.quantity_available)>0.5 order by priceProduct asc limit 4 ;";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Product> getSellProductTwo() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount,idCategorie FROM product p join producers s on p.idProducer = s.idProducer where p.isActice ='1' and s.isActice and (p.products_sold/p.quantity_available)>0.5 order by priceProduct asc limit 4,4 ;";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static List<Product> getOutstandingProduct() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice order by idProduct desc limit 3 ;";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static List<Product> getOutstandingProductShoes() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice and p.idCategorie in(1,3,5,6) limit 2 ;";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static List<Product> getNewProductBoy() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice and p.idCategorie in(2,7,6,9);";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static List<Product> getNewProductGirl() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice and p.idCategorie in(4,8,10,11);";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static List<Product> getNewProductAccessory() {
		List<Product> list = new ArrayList<>();
		String query = "SELECT idProduct,nameProduct,priceProduct,discount\r\n"
				+ "FROM product p join producers s on p.idProducer = s.idProducer \r\n"
				+ "where p.isActice ='1' and s.isActice and p.idCategorie in(1,3,5,6,12,13);";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
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
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}

		return list;
	}

	public static void main(String[] args) {
		
	}
}
