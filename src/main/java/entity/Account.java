package entity;

public class Account {
	private int id;
	private String user;
	private String pass;
	private int isAdmin;
	private String email;
	private String address;
	private String phoneNumber;

	public Account(int id, String user, String pass, int isAdmin, String email, String address, String phoneNumber) {
		super();
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.isAdmin = isAdmin;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", pass=" + pass + ", isAdmin=" + isAdmin + ", email=" + email
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}
