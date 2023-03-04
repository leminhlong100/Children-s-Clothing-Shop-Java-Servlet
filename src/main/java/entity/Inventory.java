package entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class Inventory {
	private List<Product> products;
	private Map<Product, Integer> quantities;
	private Date createdDate;
	private Date lastUpdatedDate;

	public Inventory() {
		super();
	}

	public Inventory(List<Product> products, Map<Product, Integer> quantities, Date createdDate, Date lastUpdatedDate) {
		super();
		this.products = products;
		this.quantities = quantities;
		this.createdDate = createdDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	@Override
	public String toString() {
		return "Inventory [products=" + products + ", quantities=" + quantities + ", createdDate=" + createdDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Map<Product, Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(Map<Product, Integer> quantities) {
		this.quantities = quantities;
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

}
