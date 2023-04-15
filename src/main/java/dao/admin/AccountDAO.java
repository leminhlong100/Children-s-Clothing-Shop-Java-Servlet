package dao.admin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import context.DBContext;
import dao.client.AuthDAO;
import entity.Account;
import entity.Permission;
import entity.Resource;
import entity.Role;
import org.jdbi.v3.core.Jdbi;
import util.EnCode;

public class AccountDAO {
    public static List<Account> getListAccount() {
        Jdbi me = DBContext.me();
        String query = "select distinct  a.id,accountName,password,fullName,address,email,phone,idRoleMember from accounts a join account_roles ar on a.id=ar.idAccount join roles r on ar.idRole = r.id where isDelete = 0 and r.name!='admin'";
        return me.withHandle(handle -> {
            return handle.createQuery(query).mapToBean(Account.class).list();
        });
    }

    public static Account findAccountById(int idAccount) {
        Jdbi me = DBContext.me();
        String queryLogin = "select id,accountName,password,fullName,address,email,phone,idRoleMember from accounts where id=?";
        Account account;
        account = me.withHandle(handle -> {
            return handle.createQuery(queryLogin)
                    .bind(0, idAccount).mapToBean(Account.class).findFirst().orElse(null);
        });
        account.setRoles(AuthDAO.getRoles(account.getId()));
        account.setResources(AuthDAO.getResources(account.getId()));
        account.setPermissions(AuthDAO.getPermissions(account.getId()));
        return account;
    }


    public static int removeAccount(String uid) {
        String query = "update accounts set isDelete = 1 where id = ?";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createUpdate(query).bind(0, uid).execute());
    }

    public static int removeAccountPermission(String pid) {
        String query = "delete from permissions where id = ?";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createUpdate(query).bind(0, pid).execute());
    }

    public static List<Role> getAllRoles() {
        String query = "select id,name from roles where id not in(4)";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createQuery(query).mapToBean(Role.class).list());
    }
    public static List<Resource> getResourceForRoles(int idRole) {
        String query = "select id,name,url from resources r join role_resource rr on r.id = rr.idResource where idRole =?";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createQuery(query).bind(0,idRole).mapToBean(Resource.class).list());
    }
    public static Set<Permission> getPermissions(int idRole) {
        Jdbi me = DBContext.me();
        // Lấy ra danh sách role của account
        Set<Permission> permissions;
        return permissions = me.withHandle(handle -> {
            return handle.createQuery("select distinct p.id,p.idRole,roles.name,p.idResource,r.name,r.url,p.action from permissions p join roles on p.idRole = roles.id join account_roles on roles.id = account_roles.idRole join resources r on r.id = p.idResource where p.idRole = ?")
                    .bind(0,idRole)
                    .map((rs, ctx) -> new Permission(rs.getLong("id"),new Role(rs.getLong("idRole"),rs.getString(3)),new Resource(rs.getLong("idResource"),rs.getString(5),rs.getString("url")),rs.getString("action")))
                    .collect(Collectors.toSet());
        });
    }

    public static int updateAccount(Account a) {
        String newpass = EnCode.toSHA1(a.getPassword());
        String query = "update accounts set accountName = ?,fullName=?,email=?,phone=?,password=?,address=? where id = ?";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createUpdate(query).bind(0, a.getAccountName()).bind(1, a.getFullName()).bind(2, a.getEmail()).bind(3, a.getPhone()).bind(4,newpass).bind(5, a.getAddress()).bind(6,a.getId()).execute());
    }
    public static int updateAccountNoPass(Account a) {
        String query = "update accounts set accountName = ?,fullName=?,email=?,phone=?,address=? where id = ?";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createUpdate(query).bind(0, a.getAccountName()).bind(1, a.getFullName()).bind(2, a.getEmail()).bind(3, a.getPhone()).bind(4, a.getAddress()).bind(5,a.getId()).execute());
    }
    public static int insertPermission(String idRole,String idResource,String action) {
        String query = "insert into permissions (idRole,idResource,action) values(?,?,?)";
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,idRole).bind(1,idResource).bind(2,action).execute());
    }
    public static void main(String[] args) {

        System.out.println(insertPermission("1","1","read"));
    }



}
