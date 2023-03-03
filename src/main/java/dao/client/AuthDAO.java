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

	public static boolean checkAccountExist(String userName, String email) { // ton tai la true
		Jdbi me = DBContext.me();
		try {
			return me.withHandle(handle -> handle
					.createQuery("SELECT EXISTS(SELECT idCustomer FROM customer WHERE userName = ? OR Email = ?)")
					.bind(0, userName).bind(1, email).mapTo(Boolean.class).one());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		} 
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

	public static void signinGoogle(String id, String name, String email, String picture) {
		String cusQuery = "insert into customer (userName, password, Name, Email, isDelete, isActive) values (?,?,?,?,0,1)";
		String googleQuery = "insert into google_login (idgoogle_login, name, email, idCustomer, image) values (?,?,?,?,?)";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement psCus = conn.prepareStatement(cusQuery, Statement.RETURN_GENERATED_KEYS);) {
			psCus.setString(1, EnCode.toSHA1(EnCode.toSHA1(name)));
			psCus.setString(2, EnCode.toSHA1(EnCode.toSHA1(email)));
			psCus.setString(3, name);
			psCus.setString(4, email);
			psCus.executeUpdate();
			ResultSet rs = psCus.getGeneratedKeys();
			int cusId = 0;
			if (rs.next()) {
				cusId = rs.getInt(1);
			}
			PreparedStatement psGG = conn.prepareStatement(googleQuery);
			psGG.setString(1, id);
			psGG.setString(2, name);
			psGG.setString(3, email);
			psGG.setInt(4, cusId);
			psGG.setString(5, picture);
			psGG.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public static void signinFacebook(String id, String name, String email) {
		String cusQuery = "insert into customer (userName, password, Name, Email, isDelete, isActive) values (?,?,?,?,0,1)";
		String fbQuery = "insert into login_facebook (idlogin_facebook, name, email, idCustomer) values (?,?,?,?)";
		try (Connection conn = DBContext.getConnection();
				PreparedStatement psCus = conn.prepareStatement(cusQuery, Statement.RETURN_GENERATED_KEYS);) {
			psCus.setString(1, EnCode.toSHA1(EnCode.toSHA1(name)));
			psCus.setString(2, EnCode.toSHA1(EnCode.toSHA1(email)));
			psCus.setString(3, name);
			psCus.setString(4, email);
			psCus.executeUpdate();
			ResultSet rs = psCus.getGeneratedKeys();
			int cusId = 0;
			if (rs.next()) {
				cusId = rs.getInt(1);
			}
			PreparedStatement psFB = conn.prepareStatement(fbQuery);
			psFB.setString(1, id);
			psFB.setString(2, name);
			psFB.setString(3, email);
			psFB.setInt(4, cusId);
			psFB.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	public static void main(String[] args){
		System.out.println(checkAccountExist("leminhl1ong@gmail.com", "leminhlongi1t@gmail.com"));
	}


}
