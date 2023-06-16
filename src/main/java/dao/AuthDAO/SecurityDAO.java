package dao.AuthDAO;

import context.DBContext;
import dao.client.UtilDAO;
import entity.*;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SecurityDAO {
    public static boolean hasPermission(String requestedResource, String accountName, String action) {
        String query = "SELECT DISTINCT r.name \n" +
                "                FROM permissions p\n" +
                "                JOIN resources rs  ON p.idResource = rs.id\n" +
                "                JOIN role_resource res ON p.idResource = res.idResource\n" +
                "                JOIN roles r ON r.id = res.idRole \n" +
                "                JOIN account_roles ar on r.id = ar.idRole\n" +
                "                JOIN accounts a on a.id = ar.idAccount\n" +
                "                WHERE a.accountName = ? AND rs.url = ? AND p.action = ?;";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, accountName);
            ps.setString(2, requestedResource);
            ps.setString(3, action);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                   return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasPermission("/admin-index", "leminhlong@gmail.com", "read"));
    }
}

