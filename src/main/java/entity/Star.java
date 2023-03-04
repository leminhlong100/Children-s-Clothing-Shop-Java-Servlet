package entity;

public class Star {
	private int idStar;
	private Customer customer;
	private int idproduct;
	private String NumberStar;

	public Star(int idStar, Customer customer, int idproduct, String numberStar) {
		super();
		this.idStar = idStar;
		this.customer = customer;
		this.idproduct = idproduct;
		NumberStar = numberStar;
	}

	public Star() {
		super();
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

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getNumberStar() {
		return NumberStar;
	}

	public void setNumberStar(String numberStar) {
		NumberStar = numberStar;
	}

	@Override
	public String toString() {
		return "Star [idStar=" + idStar + ", customer=" + customer + ", idproduct=" + idproduct + ", NumberStar="
				+ NumberStar + "]";
	}

}