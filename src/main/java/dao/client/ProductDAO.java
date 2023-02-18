package dao.client;

import java.sql.*;
import java.util.*;

import context.DBContext;
import entity.Product;

public class ProductDAO {
	public static int getTotalProduct(int cid) {
		String query = "";
		switch (cid) {
		case 0:
			query = "select count(*) from product";
			break;
		case 1:
			query = "select count(*) from product where cateID in(7,2,5,9,13)";
			break;
		case 2:
			query = "select count(*) from product where cateID in(4,11,8,3,13)";
			break;
		case 3:
			query = "select count(*) from product where cateID in(1,3,5,6,12)";
			break;
		case 4:
			query = "select count(*) from product  where oldPrice>price";
			break;
		default:
			break;
		}
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;

	}

	public static List<Product> pagingProduct(int index, int cid, String sort, String type) {
		List<Product> list = new ArrayList<>();
		String query = "";
		switch (cid) {
		case 0:
			query = "select * from product order by " + sort + " " + type + "  limit ?,12";
			break;
		case 1:
			query = "select * from product where cateID in(7,2,5,9,13) order by " + sort + " " + type
					+ "  limit ?,12";
			break;
		case 2:
			query = "select * from product where cateID in(4,11,8,3,13) order by " + sort + " " + type
					+ "  limit ?,12";
			break;
		case 3:
			query = "select * from product where cateID in(1,3,5,6,12) order by " + sort + " " + type
					+ "  limit ?,12";
			break;
		case 4:
			query = "select * from product where oldPrice>price order by " + sort + " " + type
					+ "  limit ?,12";
			break;
		default:
			break;
		}

		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, (index - 1) * 12);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public static Product getProductByID(String cid) {

		String query = "select * from product\r\n" + "where pid = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8));
			}
		} catch (Exception e) {
		}

		return null;

	}

	public static void main(String[] args) {

		System.out.println(pagingProduct(1, 1, "price", "asc"));
	}
}
