package dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import context.DBContext;
import entity.Category;
import entity.Comment;
import entity.Account;
import entity.ImageProduct;
import entity.Producer;
import entity.Product;
import entity.Star;
import entity.Supplier;
import entity.View;

public class UtilDAO {
    public static List<ImageProduct> findListImageByIdProduct(int idProduct) {
        Jdbi me = DBContext.me();
        return me.withHandle(handle -> handle.createQuery("SELECT id,idProduct,image FROM image_products where idProduct = ?").bind(0,idProduct).mapToBean(ImageProduct.class).list());
    }
    public static Product findProductById(int idProduct) {
        Jdbi me = DBContext.me();
        String query = "SELECT p.id,p.nameProduct,listPrice,description,idView,idStar,idComment,idSupplier,idProducer,idProducer,pp.discount,pp.discountPrice FROM products p join product_prices pp on p.id = pp.idProduct join suppliers s on p.idSupplier = s.id where p.isActive ='1' and s.isActive= '1' and p.id = ?";
        return null;
    }


    public static void main(String[] args) {
        System.out.println(findListImageByIdProduct(2));
    }

}
