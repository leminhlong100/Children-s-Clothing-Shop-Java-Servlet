package dao.client;

import java.sql.*;

import context.DBContext;
import entity.Account;
import util.EnCode;

public class AuthDAO {
	public AuthDAO() {
		super();
	}

	public static Account login(String email, String pass) {
		pass = EnCode.toSHA1(pass);
		String query = "select * from Account where email = ? and password  = ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;

	}
	public static Account checkAccountExist( String email) {

		String query = "select * from Account where email = ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;
	}
	public static Account checkAccountExist(String user, String email) {

		String query = "select * from Account where [user] = ? and email = ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static void signup(String user, String pass, String email, String address, String phone) {
		String query = "insert into Account values(?, ?, '0', ?, ?, ?)";
		pass = EnCode.toSHA1(pass);
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, phone);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public static Account checkAccountExistByid(String uid) {

		String query = "select * from Account where UID = ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;

	}

	public static void editAccountInfo(String user, String address, String phone, String uid) {
		String query = "update Account set [user] = ? ,[address] = ?,phoneNumber = ?  where [uid] = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setString(4, uid);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public static Account checkAccountOldPassword(String uid, String pass) {
		pass = EnCode.toSHA1(pass);
		String query = "select * from Account where [uid] = ? and password = ?";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, uid);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static void editAccountPassword(String uid, String newpass) {
		String query = "update Account set [password] = ? where [uid] = ?;";
		newpass = EnCode.toSHA1(newpass);
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, newpass);
			ps.setString(2, uid);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}
	public static void editAccountPasswordByEmail(String email, String newpass) {
		String query = "update Account set [password] = ? where [email] = ?;";
		newpass = EnCode.toSHA1(newpass);
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, newpass);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		signup("La Thi Thuy Hao", "123", "rynvia1522@gmail.com", "Đà Lạt", "0374781483");
	}

}
