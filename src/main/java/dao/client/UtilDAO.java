package dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import entity.Category;
import entity.Comment;
import entity.Customer;
import entity.ImageProduct;
import entity.Producer;
import entity.Product;
import entity.Star;
import entity.Supplier;
import entity.View;

public class UtilDAO {
	public static List<View> findListViewByIdProduct(int idProduct) {
		ArrayList<View> result = new ArrayList<>();
		;
		String query = "SELECT idView,idProduct,Status,idCustomer FROM view where idProduct = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new View(rs.getInt("idView"), findProductById(rs.getInt("idProduct")),
						rs.getString("Status"), findPCustomerById(rs.getInt("idCustomer"))));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static Product findProductById(int idProduct) {
		Product result = null;
		String query = "SELECT idProduct,nameProduct,priceProduct,dateAdd,description,quantity_available,view,Star,Comment,products_sold,discount,idSupplier,p.idProducer,idCategorie  FROM product p join producers s on p.idProducer = s.idProducer where p.isActice ='1' and s.isActice and idProduct = ?  ;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Product(rs.getInt("idProduct"), rs.getString("nameProduct"), rs.getDouble("priceProduct"),
						rs.getString("dateAdd"), rs.getString("description"), rs.getInt("quantity_available"),
						UtilDAO.findListViewByIdProduct(rs.getInt("idProduct")),
						UtilDAO.findListStarByIdProduct(rs.getInt("idProduct")),
						UtilDAO.findListCommentByIdProduct(rs.getInt("idProduct")), rs.getString("products_sold"),
						rs.getDouble("discount"), UtilDAO.findSupplierById(rs.getInt("idSupplier")),
						UtilDAO.findProducerById(rs.getInt("idProducer")),
						UtilDAO.findCategoryById(rs.getInt("idCategorie")), UtilDAO.findListImageByIdProduct(idProduct));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static Customer findPCustomerById(int idCustomer) {
		Customer result = null;
		String query = "SELECT idCustomer,userName,password,Name,Address,Email,NumberPhone,id_role_member FROM customer where idCustomer = ? and isDelete = 0 and isActice = 1;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCustomer);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return new Customer(rs.getInt("idCustomer"), rs.getString("userName"), rs.getString("password"),
						rs.getString("Name"), rs.getString("Address"), rs.getString("Email"),
						rs.getString("NumberPhone"), rs.getInt("NumberPhone"));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static List<Star> findListStarByIdProduct(int idStar) {
		ArrayList<Star> result = new ArrayList<>();
		String query = "SELECT idassess,idCustomer,idProduct,NumberStar FROM star;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idStar);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new Star(rs.getInt("idassess"), findPCustomerById(rs.getInt("idCustomer")),
						findProductById(rs.getInt("idProduct")), rs.getString("NumberStar")));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static List<Comment> findListCommentByIdProduct(int idStar) {
		ArrayList<Comment> result = new ArrayList<>();
		String query = "SELECT idcomment,Content,idCustomer,idProduct FROM comment where idCustome= ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idStar);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new Comment(rs.getInt("idcomment"), rs.getString("Content"),
						findPCustomerById(rs.getInt("idCustomer")), findProductById(rs.getInt("idProduct"))));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static Producer findProducerById(int idProducer) {
		Producer result = null;
		String query = "SELECT idProducer,nameProducer,Information,Logo,isActice FROM producers where idProducer = ? and isActice = 1;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idProducer);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Producer(rs.getInt("idProducer"), rs.getString("nameProducer"),
						rs.getString("Information"), rs.getString("Logo"));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static Supplier findSupplierById(int idSupplier) {
		Supplier result = null;
		String query = "SELECT idSupplier,nameSupplier,emailSupplier,numberPhone FROM suppliers where isActive = 1 and idSupplier = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idSupplier);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Supplier(rs.getInt("idSupplier"), rs.getString("nameSupplier"),
						rs.getString("emailSupplier"), rs.getString("numberPhone"));
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static Category findCategoryById(int idCategory) {
		Category result = null;
		String query = "SELECT idCategorie,nameCategorie FROM kidstore.categories where idCategorie = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idCategory);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Category(rs.getInt("idCategorie"), rs.getString("nameCategorie"));
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	public static List<ImageProduct> findListImageByIdProduct(int idProduct) {
		ArrayList<ImageProduct> result = new ArrayList<>();
		String query = "SELECT idImageProduct,idProduct,Image FROM imageproduct where idProduct = ?;";
		try {
			Connection conn = DBContext.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idProduct);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new ImageProduct(rs.getInt("idImageProduct"), rs.getInt("idProduct"), rs.getString("Image")) );
			}
		} catch (Exception e) {
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(findProductById(1));
	}

}
