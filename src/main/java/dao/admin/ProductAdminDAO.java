package dao.admin;

import java.sql.*;
import java.util.*;

import context.*;
import dao.client.UtilDAO;
import entity.*;
import org.jdbi.v3.core.Jdbi;

import javax.swing.*;

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

    public static List<Product> showlistproduct(){
            List<Product> list;
            Jdbi me = DBContext.me();
            String query="select p.id,p.nameProduct,pp.listPrice,pp.discountPrice,cate.nameCategorie,inven.quantity , p.isActive,p.isDelete \n" +
                    "                     from kidstore.products p join kidstore.product_prices pp on p.id = pp.idProduct \n" +
                    "                    join kidstore.categories cate on cate.id = p.idCategorie \n" +
                    "                    join kidstore.size_color_products sizepro on sizepro.idProduct = p.id\n" +
                    "                    join kidstore.inventorys inven on inven.id_size_color =sizepro.id where sizepro.size in ('Nhỏ','8.5','S') and p.isActive =1 \n";
         return  list = me.withHandle(handle -> {
             return handle.createQuery(query).map((rs, ctx) ->new Product(rs.getInt("id"),rs.getString("nameProduct")
                     ,rs.getDouble("listPrice"),new Category(rs.getString("nameCategorie")),UtilDAO.findListImageByIdProduct(rs.getInt("id")),UtilDAO.findListSizeColorByIdProduct(rs.getInt("id"))
                     ,rs.getString("isActive"),rs.getString("isDelete"),rs.getDouble("discountPrice"),new Inventory(rs.getInt("quantity")))).list();
            });
        }
    public static boolean deleteproduct(String idpro){
        Jdbi me = DBContext.me();
        String query ="UPdate products p\n" +
                "set p.isActive = 0 , p.isDelete =1\n" +
                "where id = ?";

        return me.withHandle(handle -> handle.createUpdate(query).bind(0,idpro).execute()==1) ;
    }
//    public static void updateProduct(){
//        Jdbi me = DBContext.me();
//        String query = "update kidstore.products p join kidstore.product_prices pp \n" +
//                "on p.id = pp.idProduct\n" +
//                "join kidstore.categories cate on cate.id = p.idCategorie\n" +
//                "join kidstore.inventorys invent on invent.idProduct = p.id\n" +
//                "set  pp.listPrice =? , p.idCategorie =?, invent.quantity = ?\n" +
//                "where p.id = ? and cate.nameCategorie =?;";
//        return me.withHandle(handle -> handle.createUpdate(query).)
//
//    }
    public static List<Category> listcate(){
        Jdbi me = DBContext.me();
        List<Category> list;
        String query ="select  id , nameCategorie from categories ";
        list = me.withHandle(handle -> {
            return handle.createQuery(query).map((rs, ctx) -> new Category(rs.getInt("id"),rs.getString("nameCategorie"))).list();

        });
        return list;
    }
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
//        System.out.println(getListProduct(1));
//        System.out.println(showlistproduct());
//        System.out.println(deleteproduct("17"));
        System.out.println(listcate());
        }

}
