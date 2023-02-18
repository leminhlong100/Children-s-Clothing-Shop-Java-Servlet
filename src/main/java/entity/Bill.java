package entity;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {
	private int idBill;
	private String dateAdd; // ngay dat
	private String deliveryDate; // ngay mua
	private String StatusPay; // Trinh trang thanh toan
	private Customer customer;
	private double endow; // giam gia
	private String status;
	private String address;

	public Bill(int idBill, String dateAdd, String deliveryDate, String statusPay, Customer customer, double endow,
			String status, String address) {
		super();
		this.idBill = idBill;
		this.dateAdd = dateAdd;
		this.deliveryDate = deliveryDate;
		StatusPay = statusPay;
		this.customer = customer;
		this.endow = endow;
		this.status = status;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Bill [idBill=" + idBill + ", dateAdd=" + dateAdd + ", deliveryDate=" + deliveryDate + ", StatusPay="
				+ StatusPay + ", customer=" + customer + ", endow=" + endow + ", status=" + status + ", address="
				+ address + "]";
	}

	public int getIdBill() {
		return idBill;
	}

	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}

	public String getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatusPay() {
		return StatusPay;
	}

	public void setStatusPay(String statusPay) {
		StatusPay = statusPay;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getEndow() {
		return endow;
	}

	public void setEndow(double endow) {
		this.endow = endow;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
