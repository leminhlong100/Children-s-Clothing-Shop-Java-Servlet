package entity;

public class BillProduct {
	private int id;
	private double unitPrice;
	private int quantity;
	private Bill bill;
	private Product product;

	public BillProduct(int id, double unitPrice, int quantity, Bill bill, Product product) {
		super();
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.bill = bill;
		this.product = product;
	}

	@Override
	public String toString() {
		return "BillProduct [id=" + id + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", bill=" + bill
				+ ", product=" + product + "]";
	}

	public BillProduct() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAllProductInBill(int bid) {
		String result = "";
		if (bill.getId() == bid) {
			result += this.product.getName();
		}
		return result;
	}

}