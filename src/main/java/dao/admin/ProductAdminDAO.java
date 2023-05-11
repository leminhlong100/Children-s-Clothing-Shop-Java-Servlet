package dao.admin;

import java.sql.*;
import java.util.*;

import context.*;
import dao.client.UtilDAO;
import entity.*;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class ProductAdminDAO {
    public static List<Product> getListProduct(int index) {
        Jdbi me = DBContext.me();
        String query = "SELECT p.id,p.nameProduct,pp.listPrice,pp.discount,pp.discountPrice,p.isActive FROM products p join suppliers s on p.idSupplier = s.id join product_prices pp on pp.idProduct = p.id where p.isActive = '1' and s.isActive ='1' order by p.id limit ?,10";
        return me.withHandle(handle -> handle.createQuery(query).bind(0, ((index - 1) * 10))
                .map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"),
                        rs.getDouble("listPrice"), UtilDAO.findListImageByIdProduct(rs.getInt("id")),
                        rs.getInt("discount"), rs.getDouble("discountPrice"), rs.getString("isActive")))
                .list());
    }

    public static int getTotalProduct() {
        String query = "select distinct count(nameProduct) from products where status = 'active'";
        try (Handle handle = DBContext.me().open()) {
            return handle.createQuery(query)
                    .mapTo(Integer.class)
                    .findOne()
                    .orElse(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void removeProduct(String pid) {
//        String query = "DELETE FROM products WHERE [pid] = ?";
//        try {
//            Connection conn = DBContext.getConnection();
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, pid);
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static List<Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String query = "select * from categories ";
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
////        String query = "select * from products where pid = ?;";
//        try {
//            Connection conn = DBContext.getConnection();
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, cid);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
////				return new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
////						rs.getDouble("discount"), UtilDAO.findListImageByIdProduct(rs.getInt("idProduct")));
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//
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

    public static int getTotalOutOfStock() {
        String query = "SELECT count(quantity) FROM inventorys where quantity = 0";
        try (Handle handle = DBContext.me().open()) {
            return handle.createQuery(query)
                    .mapTo(Integer.class)
                    .findOne()
                    .orElse(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Product> getSellingProducts() {
        String query = "SELECT p.id, p.nameProduct, pp.listPrice, pp.discount, pp.discountPrice, ct.nameCategorie FROM products p join product_prices pp on p.id = pp.idProduct join inventorys  invent on invent.idProduct = p.id join categories ct on p.idCategorie = ct.id where p.id IN (SELECT orr.idProduct FROM order_details orr JOIN orders od ON orr.idOrder = od.id WHERE od.status = 'Hoàn thành' AND YEAR(od.createAt) = YEAR(CURRENT_DATE())) GROUP BY p.id ORDER BY SUM(invent.quantity) LIMIT 10";
        try (Handle handle = DBContext.me().open()) {
            return handle.createQuery(query)
                    .map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct"), rs.getDouble("listPrice"), new Category(rs.getString("nameCategorie")))).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Product> getProductOOS() {
        String query = "SELECT p.id, p.nameProduct, pp.listPrice, pp.discount, ct.nameCategorie, sp.size, sp.color FROM products p join product_prices pp on p.id = pp.idProduct join inventorys invent on invent.idProduct = p.id join categories ct on p.idCategorie = ct.id join size_color_products sp on invent.id_size_color = sp.id where p.id IN (select p1.id  from products p1 join inventorys i on p1.id = i.idProduct WHERE i.quantity = 0) GROUP BY p.id, sp.size, sp.color";
        try (Handle handle = DBContext.me().open()) {
            return handle.createQuery(query)
                    .map((rs, ctx) -> new Product(rs.getInt("id"), rs.getString("nameProduct")+"_"+rs.getString("size")+"/"+rs.getString("color"), rs.getDouble("listPrice"), new Category(rs.getString("nameCategorie")))).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        System.out.println();
    }

}
