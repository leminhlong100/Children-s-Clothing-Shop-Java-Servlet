package dao.client;

import context.DBContext;
import entity.Account;
import entity.Permission;
import entity.Resource;
import entity.Role;
import org.jdbi.v3.core.Jdbi;
import util.EnCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthDAO {
	public AuthDAO() {
		super();
	}
	public static Set<Role> getRoles(int idAccount) {
		Jdbi me = DBContext.me();
		// Lấy ra danh sách role của account
		Set<Role> roles;
		return roles = me.withHandle(handle -> {
			return handle.createQuery("select roles.* from roles join account_roles on roles.id = account_roles.idRole where account_roles.idAccount = ?")
				   .bind(0, idAccount)
				   .mapToBean(Role.class)
				   .collect(Collectors.toSet());
		});
	}
	public static Set<Resource> getResources(int idAccount) {
		Jdbi me = DBContext.me();
		// Lấy ra danh sách role của account
		Set<Resource> resources;
		return resources = me.withHandle(handle -> {
			return handle.createQuery("select resources.* from resources join permissions on resources.id = permissions.idResource where permissions.idRole in (select roles.id from roles join account_roles on roles.id = account_roles.idRole where account_roles.idAccount = ?)")
					.bind(0, idAccount)
					.mapToBean(Resource.class)
					.collect(Collectors.toSet());
	});
	}
	public static Set<Permission> getPermissions(int idAccount) {
		Jdbi me = DBContext.me();
		// Lấy ra danh sách role của account
		Set<Permission> permissions;
		return permissions = me.withHandle(handle -> {
			return handle.createQuery("select p.id,p.idRole,roles.name,p.idResource,r.name,r.url,p.action from permissions p join roles on p.idRole = roles.id join account_roles on roles.id = account_roles.idRole join resources r on r.id = p.idResource where account_roles.idAccount = ?")
					.bind(0, idAccount)
					.map((rs, ctx) -> new Permission(rs.getLong("id"),new Role(rs.getLong("idRole"),rs.getString(3)),new Resource(rs.getLong("idResource"),rs.getString(5),rs.getString("url")),rs.getString("action")))
					.collect(Collectors.toSet());
		});
	}
	public static Account login(String username, String pass) {
		Jdbi me = DBContext.me();
		String passEncode = EnCode.toSHA1(pass);
		String queryLogin = "select id,accountName,password,fullName,address,email,phone,idRoleMember from accounts where accountName = ? and password  = ?";
		Account account;
		account= me.withHandle(handle -> {
			return handle.createQuery(queryLogin)
					.bind(0, username).bind(1, passEncode).mapToBean(Account.class).findFirst().orElse(null);
		});
		if(account!=null){
			account.setRoles(getRoles(account.getId()));
			account.setResources(getResources(account.getId()));
			account.setPermissions(getPermissions(account.getId()));
		}
		return account;
	}

	public static boolean checkAccountExist(String userName) { // ton tai la true
		Jdbi me = DBContext.me();
		try {
			return me.withHandle(handle -> handle
					.createQuery("SELECT EXISTS(SELECT id FROM accounts WHERE accountName = ? )")
					.bind(0, userName).mapTo(Boolean.class).one());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	public static int loginFail(String username) {
		Jdbi me = DBContext.me();
		try {
			int num = me.withHandle(handle -> handle.createQuery(
							"SELECT numberloginfail FROM accounts WHERE accountName = ?")
					.bind(0, username).mapTo(int.class).one());
			if (num >= 5) {
				return 5;
			}
			me.withHandle(handle -> handle.createUpdate(
							"UPDATE accounts SET numberloginfail = ? WHERE accountName = ?")
					.bind(0, num + 1).bind(1, username).execute());
			return num + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void resetlogin(String username) {
		Jdbi me = DBContext.me();
		try {
			me.withHandle(handle -> handle.createQuery(
							"SELECT numberloginfail FROM accounts WHERE accountName = ?")
					.bind(0, username).mapTo(int.class).one());
			me.withHandle(handle -> handle.createUpdate(
							"UPDATE accounts SET numberloginfail = ? WHERE accountName = ?")
					.bind(0, 0 ).bind(1, username).execute());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean checkEmailExist( String email) { // ton tai la true
		Jdbi me = DBContext.me();
		try {
			return me.withHandle(handle -> handle
					.createQuery("SELECT EXISTS(SELECT id FROM accounts WHERE  Email = ?)")
					.bind(0, email).mapTo(Boolean.class).one());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	public static boolean signUp(String userName, String password, String name, String email, String address, String NumberPhone) {
		String signInQuery = "INSERT INTO accounts (accountName, password, fullName, email, address,phone) VALUES (?, ?, ?, ?, ?, ?);";
		String passEncode = EnCode.toSHA1(password);
		Jdbi me = DBContext.me();
		me.withHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(signInQuery).bind(0, userName).bind(1, passEncode).bind(2, name)
						.bind(3, email).bind(4, address).bind(5, NumberPhone).execute();
				handle.commit();
				return true;
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
			return false;
		});
		return false;
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

	public static Account checkAccountOldPassword(String uid, String pass) {
		pass = EnCode.toSHA1(pass);
		String query = "select idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member from customer from customer where idCustomer = ? and password = ?";
		try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, uid);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new Account(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
							rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
							rs.getString("NumberPhone"));
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

	public static void signUpGoogle(String id, String name, String email, String picture) {
		String ggCus = "insert into accounts (accountName, password, fullName, email, image, type, idOther,isDelete,isActive) values (:accountName, :password, :fullName, :email, :image, :type, :idOther,:isDelete,:isActive)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(ggCus).bind("accountName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("fullName", name).bind("email", email)
						.bind("image", picture).bind("type", 3).bind("idOther", id).bind("isDelete",0).bind("isActive",1)
						.execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
		});
	}

	private static Account getAccount(String id, String loginBy) {
		Jdbi me = DBContext.me();
		return (Account) me.withHandle(handle -> {
			return handle.createQuery(loginBy).bind(0, id).mapToBean(Account.class).findFirst().orElse(null);
		});
	}

	public static Account loginGG(String id) {
		String loginGGQuery = "select id, accountName, password, fullName, email, image, type, idOther from accounts where idOther= ? and type = 3";
		return getAccount(id, loginGGQuery);
	}

	public static void signUpFacebook(String id, String name, String email, String pic) {
		String fbCus = "insert into accounts (accountName, password, fullName, email, image, type, idOther) values (:accountName, :password, :fullName, :email, :image, :type, :idOther)";
		Jdbi me = DBContext.me();
		me.useHandle(handle -> {
			try {
				handle.begin();
				handle.createUpdate(fbCus).bind("accountName", EnCode.toSHA1(EnCode.toSHA1(name)))
						.bind("password", EnCode.toSHA1(EnCode.toSHA1(email))).bind("fullName", name).bind("email", email)
						.bind("image", pic).bind("type", 2).bind("idOther", id)
						.execute();
				handle.commit();
			} catch (Exception e) {
				handle.rollback();
				e.printStackTrace();
			}
		});
	}

	public static Account loginFacebook(String id) {
		String loginFBQuery = "select id, accountName, password, fullName, email, image, type, idOther from accounts where idOther= ? and type = 2";
		return getAccount(id, loginFBQuery);
	}

	public static void main(String[] args) {
		System.out.println();
	}

}