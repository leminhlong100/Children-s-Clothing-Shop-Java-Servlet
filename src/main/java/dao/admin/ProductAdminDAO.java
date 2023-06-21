package dao.admin;

import java.sql.*;
import java.util.*;

import context.*;
import dao.client.UtilDAO;
import entity.*;
import jnr.a64asm.SIZE;
import org.jdbi.v3.core.Handle;
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
    public static int getTotalProductOuOtOfStock() {
        String query = "select count(p.nameProduct) from products p join product_prices pp on p.id = pp.idProduct where p.id = (SELECT idProduct FROM inventorys where idProduct = p.id having  SUM(quantity) < 20)";
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
    public static int getTotalActiveProduct() {
        String query = "select count(id) from products where isActive = '1'";
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
    public static List<Product> showlistproduct(){
        List<Product> list;
        Jdbi me = DBContext.me();
        String query="select distinct p.id,p.nameProduct,pd.nameProducer,p.status ,pp.listPrice,pp.discount,pp.discountPrice,cate.nameCategorie , p.isActive,p.isDelete  \n" +
                "                                    from products p join  product_prices pp on p.id = pp.idProduct \n" +
                "                                      join categories cate on cate.id = p.idCategorie \n" +
                "                                    join size_color_products sizepro on sizepro.idProduct = p.id\n" +
                "                                      join producers pd on pd.id = p.idProducer           \n" +
                "                    where p.isActive =1 ";
        return  list = me.withHandle(handle -> {
            return handle.createQuery(query).map((rs, ctx) ->new Product(rs.getInt("id"),rs.getString("nameProduct")
                    ,rs.getDouble("listPrice"),new Category(rs.getString("nameCategorie")),new Producer(rs.getString("nameProducer")),UtilDAO.findListImageByIdProduct(rs.getInt("id")),UtilDAO.findListSizeColorByIdProduct(rs.getInt("id"))
                    ,rs.getString("isActive"),rs.getString("isDelete"),rs.getInt("discount"),rs.getDouble("discountPrice"),rs.getString("status"))).list();
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
    public static int updatediscount(Product p){
        Jdbi me = DBContext.me();
        String query = "update product_prices set discount = ? where idProduct = ? ";
        return me.withHandle(handle ->
                handle.createUpdate(query).bind(0,p.getDiscount()).bind(1,p.getId()).execute());

    }
    public static int updatequantity(Product p){
        Jdbi me = DBContext.me();
        String query = "update products set nameProduct = ? where id = ? ";
        return me.withHandle(handle ->
                handle.createUpdate(query).bind(0,p.getNameProduct()).bind(1,p.getId()).execute());

    }
    public static int updatestatus(Product p){
        Jdbi me = DBContext.me();
        String query = "update products set status = ?  where id = ? ";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,p.getStatus()).bind(1,p.getId()).execute());

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

    public static int updateidcate(Product p){
        Jdbi me = DBContext.me();
        String query = "update products set idCategorie = ?  where id = ? ";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,p.getIdCategory()).bind(1,p.getId()).execute());

    }
    public static int quantityofsize(Product p){
        Jdbi me = DBContext.me();
        String query="select i.quantity from size_color_products s join inventorys i \n" +
                "on i.id_size_color = s.id where i.idProduct = ? and i.id_size_color =?";
        return me.withHandle(handle -> handle.createQuery(query).bind(0,p.getId()).bind(1,p.getInventory())).mapTo(Integer.class).one();
    }

    public static int updatecolor (SizeColorProduct s){
        Jdbi me = DBContext.me();
        String query = "update size_color_products set color = ? where idProduct = ? and id = ?";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,s.getColor()).bind(1,s.getIdProduct()).bind(2,s.getId()).execute());
    }
    public static int updatenumber(Inventory i){
        Jdbi me = DBContext.me();
        String query = "update inventorys set quantity = ? where id_size_color = ? and idProduct=?";
        return me.withHandle(handle -> handle.createUpdate(query).bind(0,i.getQuantity()).bind(1,i.getId_size_color()).bind(2,i.getIdProduct()).execute());

    }

    public static void main(String[] args) {
        System.out.println(getTotalActiveProduct());


    }
}
