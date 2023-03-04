package entity;

public class View {
	private int idView;
	private int idProduct;
	private String status;
	private Customer customer;
	public View(int idView, int idProduct, String status, Customer customer) {
		super();
		this.idView = idView;
		this.idProduct = idProduct;
		this.status = status;
		this.customer = customer;
	}
	public View() {
		super();
	}
	@Override
	public String toString() {
		return "View [idView=" + idView + ", idProduct=" + idProduct + ", status=" + status + ", customer=" + customer
				+ "]";
	}
	public int getIdView() {
		return idView;
	}
	public void setIdView(int idView) {
		this.idView = idView;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
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
