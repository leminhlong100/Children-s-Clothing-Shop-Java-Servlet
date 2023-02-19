package dao.client;

import java.sql.*;

import context.DBContext;
import entity.Customer;
import util.EnCode;

public class AuthDAO {
	public AuthDAO() {
		super();
	}

	public static Customer login(String email, String pass) {
		pass = EnCode.toSHA1(pass);
		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from customer where userName = ? and password  = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, email);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					return new Customer(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
							rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
							rs.getString("NumberPhone"), rs.getInt("id_role_member"));
				}
			}
		} catch (Exception e) {
		}
		return null;

	}

	public static Customer checkAccountExist(String user, String email) {

		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from Account where userName = ? and Email = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, user);
			ps.setString(2, email);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					return new Customer(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
							rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
							rs.getString("NumberPhone"), rs.getInt("id_role_member"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void signup(String userName, String password, String name, String email, String address,
			String NumberPhone) {
		String query = "INSERT INTO customer (userName, password, Name, email, address,NumberPhone) VALUES (?, ?, ?, ?, ?, ?);";
		password = EnCode.toSHA1(password);
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, name);
			ps.setString(4, email);
			ps.setString(5, address);
			ps.setString(6, NumberPhone);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Customer checkAccountExistByid(String uid) {

		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from customer where idCustomer = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, uid);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new Customer(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
							rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
							rs.getString("NumberPhone"), rs.getInt("id_role_member"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void editAccountInfo(String user, String address, String phone, String uid) {
		String query = "update customer set userName = ? ,Address = ?,NumberPhone = ?  where idCustomer = ?;";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, user);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setString(4, uid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Customer checkAccountOldPassword(String uid, String pass) {
		pass = EnCode.toSHA1(pass);
		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from customer from customer where idCustomer = ? and password = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, uid);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new Customer(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
							rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
							rs.getString("NumberPhone"), rs.getInt("id_role_member"));
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static void editAccountPassword(String uid, String newpass) {
		String query = "update idCustomer set password = ? where idCustomer = ?;";
		newpass = EnCode.toSHA1(newpass);
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, newpass);
			ps.setString(2, uid);
			ps.executeUpdate();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
//		editAccountInfo("Lê Minh Long", "Ấp Phú Thuận,xã Châu Hòa, huyện Giồng Trôm Tỉnh Bến Tre", "0374781483", "1");
		System.out.println(login("leminhlongit", "L0123456Lll!@"));
	}

}
