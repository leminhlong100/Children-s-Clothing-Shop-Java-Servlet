package entity;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
	private int idProduct;
	private String nameProduct;
	private double priceProduct;
	private String dateAdd; // ngay nhap
	private String description; // mo ta
	private int quantity;
	private List<View> views;
	private List<Star> stars;
	private List<Comment> comments;
	private String productsale; // so luong sp da ban
	private double discount; // giam gia;
	private Supplier supplier; // nha cung cap
	private Producer producer; // nha san suat
	private Category category; // phan loai
	private List<ImageProduct> imageProducts;

	public Product(int idProduct, String nameProduct, double priceProduct, String dateAdd, String description,
			int quantity, List<View> views, List<Star> stars, List<Comment> comments, String productsale,
			double discount, Supplier supplier, Producer producer, Category category,
			List<ImageProduct> imageProducts) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.dateAdd = dateAdd;
		this.description = description;
		this.quantity = quantity;
		this.views = views;
		this.stars = stars;
		this.comments = comments;
		this.productsale = productsale;
		this.discount = discount;
		this.supplier = supplier;
		this.producer = producer;
		this.category = category;
		this.imageProducts = imageProducts;
	}

	public Product(int idProduct, String nameProduct, double priceProduct, double discount,
			List<ImageProduct> imageProducts) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.discount = discount;
		this.imageProducts = imageProducts;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct
				+ ", dateAdd=" + dateAdd + ", description=" + description + ", quantity=" + quantity + ", views="
				+ views + ", stars=" + stars + ", comments=" + comments + ", productsale=" + productsale + ", discount="
				+ discount + ", supplier=" + supplier + ", producer=" + producer + ", category=" + category
				+ ", imageProducts=" + imageProducts + "]" + "\n";
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public double getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(double priceProduct) {
		this.priceProduct = priceProduct;
	}

	public String getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public List<Star> getStars() {
		return stars;
	}

	public void setStars(List<Star> stars) {
		this.stars = stars;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getProductsale() {
		return productsale;
	}

	public void setProductsale(String productsale) {
		this.productsale = productsale;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ImageProduct> getImageProducts() {
		return imageProducts;
	}

	public void setImageProducts(List<ImageProduct> imageProducts) {
		this.imageProducts = imageProducts;
	}

}