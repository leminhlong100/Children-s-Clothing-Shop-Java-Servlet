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
            String query="select distinct p.id,p.nameProduct ,pp.listPrice,pp.discountPrice,cate.nameCategorie , p.isActive,p.isDelete \n" +
                    "                                    from products p join  product_prices pp on p.id = pp.idProduct \n" +
                    "                                      join categories cate on cate.id = p.idCategorie \n" +
                    "                                    join size_color_products sizepro on sizepro.idProduct = p.id\n" +
                    "                                                   \n" +
                    "                    where p.isActive =1 ";
         return  list = me.withHandle(handle -> {
             return handle.createQuery(query).map((rs, ctx) ->new Product(rs.getInt("id"),rs.getString("nameProduct")
                     ,rs.getDouble("listPrice"),new Category(rs.getString("nameCategorie")),UtilDAO.findListImageByIdProduct(rs.getInt("id")),UtilDAO.findListSizeColorByIdProduct(rs.getInt("id"))
                     ,rs.getString("isActive"),rs.getString("isDelete"),rs.getDouble("discountPrice"))).list();
            });
        }
    public static boolean deleteproduct(String idpro){
        Jdbi me = DBContext.me();
        String query ="UPdate products p\n" +
                "set p.isActive = 0 , p.isDelete =1\n" +
                "where id = ?";

        return me.withHandle(handle ->  handle.createUpdate(query).bind(0,idpro).execute()==1) ;
    }
    public static int updatenameproduct(Product p){
        Jdbi me = DBContext.me();
        String query = "update products set nameProduct = ? where id = ? ";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,p.getNameProduct()).bind(1,p.getId()).execute());

    }
    public static int updatecost(Product p){
        Jdbi me = DBContext.me();
        String query = "update product_prices set listPrice = ? where idProduct = ? ";
        return me.withHandle(handle ->
                handle.createUpdate(query).bind(0,p.getListPrice()).bind(1,p.getId()).execute());

    }
    public static int updatequantity(Product p){
        Jdbi me = DBContext.me();
        String query = "update products set nameProduct = ? where id = ? ";
        return me.withHandle(handle ->
                handle.createUpdate(query).bind(0,p.getNameProduct()).bind(1,p.getId()).execute());

    }

    public static List<Category> listcate(){
        Jdbi me = DBContext.me();
        List<Category> list;
        String query ="select  id , nameCategorie from categories ";
        list = me.withHandle(handle -> {
            return handle.createQuery(query).map((rs, ctx) -> new Category(rs.getInt("id"),rs.getString("nameCategorie"))).list();

        });
        return list;
    }
        public static int idproduct (String name){
        Jdbi me = DBContext.me();
        String query="Select id from categories where nameCategorie =?";
        return me.withHandle(handle -> handle.createQuery(query).bind(0,name).mapTo(Integer.class).one());

        }
        public static List<SizeColorProduct> listsize(){
            List<SizeColorProduct> list;
            Jdbi me = DBContext.me();
            String query="Select id,size from size_color_products";

             list= me.withHandle(handle -> {
             return  handle.createQuery(query).map((rs, ctx) -> new SizeColorProduct(rs.getInt("id"),rs.getString("size"))).list();
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
        System.out.println(showlistproduct());
//        System.out.println(deleteproduct("17"));
//        System.out.println(listcate());
        }

}
