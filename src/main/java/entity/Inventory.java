package entity;

import java.sql.Date;

public class Inventory {
	private int idProduct;
	private int quantity;
	private Date createdDate;
	private Date lastUpdatedDate;

	public Inventory() {
		super();
	}

	public Inventory(int idProduct, int quantities, Date createdDate, Date lastUpdatedDate) {
		this.idProduct = idProduct;
		this.quantity = quantities;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Inventory(int idProduct, int quantities) {
		this.idProduct = idProduct;
		this.quantity = quantities;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString() {
		return "Inventory{" +
				"idProduct=" + idProduct +
				", quantities=" + quantity +
				", createdDate=" + createdDate +
				", lastUpdatedDate=" + lastUpdatedDate +
				'}';
	}
}
