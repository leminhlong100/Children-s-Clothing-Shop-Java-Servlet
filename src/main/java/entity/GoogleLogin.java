package entity;

public class GoogleLogin {
	private String idGoogleLogin;
	private String name;
	private String email;
	private Customer customer;
	private String image;

	public GoogleLogin(String idGoogleLogin, String name, String email, Customer customer, String image) {
		super();
		this.idGoogleLogin = idGoogleLogin;
		this.name = name;
		this.email = email;
		this.customer = customer;
		this.image = image;
	}

	public GoogleLogin(String idGoogleLogin, String name, String email, Customer customer) {
		super();
		this.idGoogleLogin = idGoogleLogin;
		this.name = name;
		this.email = email;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "GoogleLogin [idGoogleLogin=" + idGoogleLogin + ", name=" + name + ", email=" + email + ", customer="
				+ customer + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIdGoogleLogin() {
		return idGoogleLogin;
	}

	public void setIdGoogleLogin(String idGoogleLogin) {
		this.idGoogleLogin = idGoogleLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
