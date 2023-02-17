package entity;

public class View {
	private int idView;
	private Product product;
	private String status;
	private Customer customer;
	public View(int idView, Product product, String status, Customer customer) {
		super();
		this.idView = idView;
		this.product = product;
		this.status = status;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "View [idView=" + idView + ", product=" + product + ", status=" + status + ", customer=" + customer
				+ "]";
	}
	public int getIdView() {
		return idView;
	}
	public void setIdView(int idView) {
		this.idView = idView;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
