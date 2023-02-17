package entity;

public class Customer {
	private int idCustomer;
	private String userName;
	private String passWord;
	private String address;
	private String email;
	private String phoneNumber;
	private int id_role_member;
	public Customer(int idCustomer, String userName, String passWord, String address, String email, String phoneNumber,
			int id_role_member) {
		super();
		this.idCustomer = idCustomer;
		this.userName = userName;
		this.passWord = passWord;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.id_role_member = id_role_member;
	}
	@Override
	public String toString() {
		return "Account [idCustomer=" + idCustomer + ", userName=" + userName + ", passWord=" + passWord + ", address="
				+ address + ", email=" + email + ", phoneNumber=" + phoneNumber + ", id_role_member=" + id_role_member
				+ "]";
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getId_role_member() {
		return id_role_member;
	}
	public void setId_role_member(int id_role_member) {
		this.id_role_member = id_role_member;
	}
	
}
	
