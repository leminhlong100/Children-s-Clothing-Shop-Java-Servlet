package entity;

import java.io.Serializable;

public class Product implements Serializable {
	private int idProduct;
	private String nameProduct;
	private double priceProduct;
	private String date;
	private String dateAdd; // ngay dat
	private String description; // mo ta
	private int quantity;
	private View view;
	private Star star;
	private String comment;
	private String productsale; // so luong sp da ban
	private double discount; // giam gia;
	private Supplier supplier; // nha cung cap
	private Producer producer; // nha san suat
	private Category category; // phan loai
	private boolean isAtice;

	public Product(int idProduct, String nameProduct, double priceProduct, String date, String dateAdd,
			String description, int quantity, View view, Star star, String comment, String productsale, double discount,
			Supplier supplier, Producer producer, Category category, boolean isAtice) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.date = date;
		this.dateAdd = dateAdd;
		this.description = description;
		this.quantity = quantity;
		this.view = view;
		this.star = star;
		this.comment = comment;
		this.productsale = productsale;
		this.discount = discount;
		this.supplier = supplier;
		this.producer = producer;
		this.category = category;
		this.isAtice = isAtice;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", nameProduct=" + nameProduct + ", priceProduct=" + priceProduct
				+ ", date=" + date + ", dateAdd=" + dateAdd + ", description=" + description + ", quantity=" + quantity
				+ ", view=" + view + ", star=" + star + ", comment=" + comment + ", productsale=" + productsale
				+ ", discount=" + discount + ", supplier=" + supplier + ", producer=" + producer + ", category="
				+ category + ", isAtice=" + isAtice + "]";
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public boolean isAtice() {
		return isAtice;
	}

	public void setAtice(boolean isAtice) {
		this.isAtice = isAtice;
	}
}