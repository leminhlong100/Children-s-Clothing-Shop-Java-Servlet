package dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import context.DBContext;
import entity.Customer;
import util.EnCode;

public class LoginAdminDAO {
	public static Customer loginAdmin(String email, String pass) {
		pass = EnCode.toSHA1(pass);

		String query = "select * from Account where email = ? and password  = ? and  isAdmin = 1 ";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;
	}
}
