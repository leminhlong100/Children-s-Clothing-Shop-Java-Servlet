package dao.admin;

import java.sql.*;
import java.util.*;

import context.*;
import dao.client.UtilDAO;
import entity.*;
import org.jdbi.v3.core.Jdbi;

public class ProductAdminDAO {
    public static List<Product> getListProduct(int index) {
        Jdbi me = DBContext.me();
        String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice,p.isActive FROM products p join suppliers s on p.idSupplier = s.id join product_prices pp on pp.idProduct = p.id where p.isActive = '1' and s.isActive ='1' order by p.id limit ?,10";
        return me.withHandle(handle -> handle.createQuery(query).bind(0,((index - 1) * 10))
                .map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
                        rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
                        rs.getInt("discount"), rs.getDouble("discountPrice"), rs.getString("isActive")))
                .list());
    }

    public static int getTotalProduct() {
        String query = "select count(p.nameProduct) from products p join product_prices pp on p.id = pp.idProduct";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }

    public static void removeProduct(String pid) {
        String query = "DELETE FROM Product WHERE [pid] = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {

        }
    }

    public static List<Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String query = "select * from Category ";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;

    }

    public static Product getProductByID(String cid) {

        String query = "select * from product\r\n" + "where pid = ?;";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//				return new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
//						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct")));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;

    }

//	public static void updateProduct(Product product) {
//		String query = "update Product set [name] = ?,image=?,price=?,title=?,[description]=?,cateID=?,oldPrice=?,sumProduct=?,presentProduct=? where pid = ?";
//		try {
//			Connection conn = DBContext.getConnection();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, product.getName());
//			ps.setString(2, product.getImage());
//			ps.setDouble(3, product.getPrice());
//			ps.setString(4, product.getTitle());
//			ps.setString(5, product.getDescription());
//			ps.setInt(6, product.getCateId());
//			ps.setDouble(7, product.getOlePrice());
//			ps.setInt(8, product.getSumProduct());
//			ps.setInt(9, product.getPresentProduct());
//			ps.setInt(10, product.getId());
//			ps.executeUpdate();
//		} catch (Exception e) {
//		}
//	}

//	public static void insertProduct(Product product) {
//		String query = "insert Product values( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		try {
//			Connection conn = DBContext.getConnection();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, product.getName());
//			ps.setString(2, product.getImage());
//			ps.setDouble(3, product.getPrice());
//			ps.setString(4, product.getTitle());
//			ps.setString(5, product.getDescription());
//			ps.setInt(6, product.getCateId());
//			ps.setDouble(7, product.getOlePrice());
//			ps.setInt(8, product.getSumProduct());
//			ps.setInt(9, product.getPresentProduct());
//			ps.executeUpdate();
//		} catch (Exception e) {
//		}
//	}

    public static void main(String[] args) {
        System.out.println(getListProduct(1));
        ;
    }
}
