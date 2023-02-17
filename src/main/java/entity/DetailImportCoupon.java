package entity;

public class DetailImportCoupon { //  chi tiet phieu nhap
	private int idDetailImportCoupon;
	private ImportCoupon importCoupon;
	private Product product;
	private double price;
	private int quantity;
	public DetailImportCoupon(int idDetailImportCoupon, ImportCoupon importCoupon, Product product, double price,
			int quantity) {
		super();
		this.idDetailImportCoupon = idDetailImportCoupon;
		this.importCoupon = importCoupon;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "DetailImportCoupon [idDetailImportCoupon=" + idDetailImportCoupon + ", importCoupon=" + importCoupon
				+ ", product=" + product + ", price=" + price + ", quantity=" + quantity + "]";
	}
	public int getIdDetailImportCoupon() {
		return idDetailImportCoupon;
	}
	public void setIdDetailImportCoupon(int idDetailImportCoupon) {
		this.idDetailImportCoupon = idDetailImportCoupon;
	}
	public ImportCoupon getImportCoupon() {
		return importCoupon;
	}
	public void setImportCoupon(ImportCoupon importCoupon) {
		this.importCoupon = importCoupon;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
