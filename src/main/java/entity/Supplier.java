package entity;

public class Supplier { // nha cung cap
	private int idSupplier;
	private String nameSupplier;
	private String emailSupplier;
	private String numberPhone;
	private boolean isAtice;
	public Supplier(int idSupplier, String nameSupplier, String emailSupplier, String numberPhone, boolean isAtice) {
		super();
		this.idSupplier = idSupplier;
		this.nameSupplier = nameSupplier;
		this.emailSupplier = emailSupplier;
		this.numberPhone = numberPhone;
		this.isAtice = isAtice;
	}
	@Override
	public String toString() {
		return "Supplier [idSupplier=" + idSupplier + ", nameSupplier=" + nameSupplier + ", emailSupplier="
				+ emailSupplier + ", numberPhone=" + numberPhone + ", isAtice=" + isAtice + "]";
	}
	public int getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}
	public String getNameSupplier() {
		return nameSupplier;
	}
	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}
	public String getEmailSupplier() {
		return emailSupplier;
	}
	public void setEmailSupplier(String emailSupplier) {
		this.emailSupplier = emailSupplier;
	}
	public String getNumberPhone() {
		return numberPhone;
	}
	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
	public boolean isAtice() {
		return isAtice;
	}
	public void setAtice(boolean isAtice) {
		this.isAtice = isAtice;
	}
	

}
