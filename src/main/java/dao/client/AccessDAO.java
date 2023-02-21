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
		String query = "select * from product where nameProduct like ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
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
		String query = "select * from product  where  nameProduct like ? order by " + sort + " " + type
				+ "  limit ?,12";

		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			ps.setInt(2, (index - 1) * 12);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {

		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(searchByName( "gi"));
	}
}
