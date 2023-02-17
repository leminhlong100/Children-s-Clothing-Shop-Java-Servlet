package entity;

public class Star {
	private int idStar;
	private Customer customer;
	private Product product;
	private String NumberStar;
	public Star(int idStar, Customer customer, Product product, String numberStar) {
		super();
		this.idStar = idStar;
		this.customer = customer;
		this.product = product;
		NumberStar = numberStar;
	}
	@Override
	public String toString() {
		return "Star [idStar=" + idStar + ", customer=" + customer + ", product=" + product + ", NumberStar="
				+ NumberStar + "]";
	}
	public int getIdStar() {
		return idStar;
	}
	public void setIdStar(int idStar) {
		this.idStar = idStar;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getNumberStar() {
		return NumberStar;
	}
	public void setNumberStar(String numberStar) {
		NumberStar = numberStar;
	}
	
}
