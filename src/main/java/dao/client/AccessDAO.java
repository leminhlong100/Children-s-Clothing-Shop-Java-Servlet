package dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Product;

public class AccessDAO {
	public static List<Product> searchByName(String txtSearch) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product where [name] like ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {
		}

		return list;
	}

	public static int getTotalProductSearch(String txtSearch) {
		String query = " select count(*) from product where  name like ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {

		}

		return 0;
	}

	public static List<Product> pagingProductSearch(int index, String txtSearch, String sort, String type) {
		List<Product> list = new ArrayList<>();
		String query = "select * from product  where  name like ? order by " + sort + " " + type
				+ "  limit ?,12";

		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ps.setInt(2, (index - 1) * 12);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getDouble(8)));
			}
		} catch (Exception e) {

		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(pagingProductSearch(1, "gi", "name", "asc"));
	}
}
