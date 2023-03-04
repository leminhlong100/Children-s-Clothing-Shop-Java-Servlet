package entity;

public class OrderDetail {
	private int idBill_detail;
	private int idOrdetail;
	private Product product;
	private String nameProduct;
	private int quantity; // tong so luong
	private double price;
	private String node; // ghi chu

	public OrderDetail(int idBill_detail, int idOrdetail, Product product, String nameProduct, int quantity,
			double price, String node) {
		super();
		this.idBill_detail = idBill_detail;
		this.idOrdetail = idOrdetail;
		this.product = product;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.price = price;
		this.node = node;
	}

	public OrderDetail() {
		super();
	}

	@Override
	public String toString() {
		return "OrderDetail [idBill_detail=" + idBill_detail + ", idOrdetail=" + idOrdetail + ", product=" + product
				+ ", nameProduct=" + nameProduct + ", quantity=" + quantity + ", price=" + price + ", node=" + node
				+ "]";
	}

	public int getIdBill_detail() {
		return idBill_detail;
	}

	public void setIdBill_detail(int idBill_detail) {
		this.idBill_detail = idBill_detail;
	}

	public int getIdOrdetail() {
		return idOrdetail;
	}

	public void setIdOrdetail(int idOrdetail) {
		this.idOrdetail = idOrdetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}