package dao.client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Product;

public class IndexDAO {
	public static List<Product> getSellProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product order by price ";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		
		}

		return list;
	}

	public static List<Product> getSellProductTwo() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product order by price  desc";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
	
		}

		return list;
	}

	public static List<Product> getOutstandingProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from product where pid in (44,45,46)";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		
		}

		return list;
	}

	public static List<Product> getOutstandingProductShoes() {
		List<Product> list = new ArrayList<>();
		String query = "select * from product where pid in (10,11)";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		
		}

		return list;
	}

	public static List<Product> getNewProductBoy() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product where cateID in (2,7)  order by pid desc";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		
		}

		return list;
	}

	public static List<Product> getNewProductGirl() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product where cateID in (4,8)  order by pid desc";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		
		}

		return list;
	}

	public static List<Product> getNewProductAccessory() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product where cateID in (1,3,5,6)  order by pid desc";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
			
		}

		return list;
	}

	public static List<Product> getSuperSellProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product ";
		try {
			Connection conn =  DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getString(6), rs.getDouble(8));
				if (100 / p.getOlePrice() * (p.getOlePrice() - p.getPrice()) > 15) {
					list.add(p);
				}
			}
		} catch (Exception e) {
		
		}

		return list;
	}
	
}
