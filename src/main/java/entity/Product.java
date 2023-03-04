package entity;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
	private int idProduct;
	private String nameProduct;
	private double priceProduct;
	private String description; // mo ta
	private List<View> views;
	private List<Star> stars;
	private List<Comment> comments;
	private String productsale; // so luong sp da ban
	private int idSupplier;
	private int idProducer;
	private int idCategory;
	private Supplier supplier; // nha cung cap
	private Producer producer; // nha san suat
	private Category category; // phan loai
	private List<ImageProduct> imageProducts;
	private String isActive;
	private List<SizeProduct> productSizes;
	private List<ColorProduct> productColors;

	public Product() {
	}

	public Product(int idProduct, String nameProduct, double priceProduct, String description, List<View> views,
			List<Star> stars, List<Comment> comments, String productsale, int idSupplier, int idProducer,
			int idCategory, Supplier supplier, Producer producer, Category category, List<ImageProduct> imageProducts,
			String isActive, List<SizeProduct> productSizes, List<ColorProduct> productColors) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.description = description;
		this.views = views;
		this.stars = stars;
		this.comments = comments;
		this.productsale = productsale;
		this.idSupplier = idSupplier;
		this.idProducer = idProducer;
		this.idCategory = idCategory;
		this.supplier = supplier;
		this.producer = producer;
		this.category = category;
		this.imageProducts = imageProducts;
		this.isActive = isActive;
		this.productSizes = productSizes;
		this.productColors = productColors;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct
				+ ", description=" + description + ", views=" + views + ", stars=" + stars + ", comments=" + comments
				+ ", productsale=" + productsale + ", idSupplier=" + idSupplier + ", idProducer=" + idProducer
				+ ", idCategory=" + idCategory + ", supplier=" + supplier + ", producer=" + producer + ", category="
				+ category + ", imageProducts=" + imageProducts + ", isActive=" + isActive + ", productSizes="
				+ productSizes + ", productColors=" + productColors + "]";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}

	public int getIdProducer() {
		return idProducer;
	}

	public void setIdProducer(int idProducer) {
		this.idProducer = idProducer;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<SizeProduct> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(List<SizeProduct> productSizes) {
		this.productSizes = productSizes;
	}

	public List<ColorProduct> getProductColors() {
		return productColors;
	}

	public void setProductColors(List<ColorProduct> productColors) {
		this.productColors = productColors;
	}

}