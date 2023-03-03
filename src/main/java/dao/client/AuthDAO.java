package dao.client;

import java.sql.*;

import org.jdbi.v3.core.Jdbi;

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

		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from customer  where userName = ? and Email = ?";
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

	public static void signinGoogle(String id, String name, String email, String picture){
		String cusQuery = "insert into customer (userName, password, Name, Email, isDelete, isActive) values (:userName, :password, :Name, :Email, :isDelete, :isActive)";
		String googleQuery = "insert into google_login (idgoogle_login, name, email, idCustomer, image) values (:idgoogle_login, :name, :email, :idCustomer, :image)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				int generatedId = handle.createUpdate(cusQuery).bind("userName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("Name", name).bind("Email", email)
						.bind("isDelete", 0).bind("isActive", 1).executeAndReturnGeneratedKeys("idCustomer")
						.mapTo(int.class).one();

				handle.createUpdate(googleQuery).bind("idgoogle_login", id).bind("name", name).bind("email", email)
						.bind("idCustomer", generatedId).bind("image", picture).execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
		});

	}

	public static Customer LoginGG(String id, String email) {
		String query = "select c.idCustomer, c.userName, c.password, c.Name, c.Address, c.Email, c.NumberPhone, c.id_role_member, c.isDelete, c.isActive from customer c join google_login g on c.email = g.email where g.idgoogle_login = ? and g.email = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, id);
			ps.setString(2, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void signinFacebook(String id, String name, String email, String pic) {
		String cusQuery = "insert into customer (userName, password, Name, Email, isDelete, isActive) values (:userName, :password, :Name, :Email, :isDelete, :isActive)";
		String fbQuery = "insert into login_facebook (idlogin_facebook, name, email, idCustomer, image) values (:idlogin_facebook, :name, :email, :idCustomer, :image)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				int generatedId = handle.createUpdate(cusQuery).bind("userName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("Name", name).bind("Email", email)
						.bind("isDelete", 0).bind("isActive", 1).executeAndReturnGeneratedKeys("idCustomer")
						.mapTo(int.class).one();

				handle.createUpdate(fbQuery).bind("idlogin_facebook", id).bind("name", name).bind("email", email)
						.bind("idCustomer", generatedId).bind("image", pic).execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.getStackTrace();
			}
		});

	}

	public static Customer loginFacebook(String id, String email) {
		String query = "select c.idCustomer, c.userName, c.password, c.Name, c.Address, c.Email, c.NumberPhone, c.id_role_member, c.isDelete, c.isActive from customer c join login_facebook l on c.email = l.email where l.idlogin_facebook = ? and l.email = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, id);
			ps.setString(2, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
//		signinGoogle("1k134152", "hao1", "adjksauiodh", "123cdsjf");
		System.out.println(LoginGG("1k134152", "adjksauiodh"));
	}

}
