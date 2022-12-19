package entity;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {
	private int id;
	private Account buyer;// nguoi mua
	private String buyDate;// ngay mua
	private long priceTotal;// tong tien
	private String status;
	private String node;

	

	public Bill(int id, Account buyer, String buyDate, long priceTotal, String status, String node) {
		super();
		this.id = id;
		this.buyer = buyer;
		this.buyDate = buyDate;
		this.priceTotal = priceTotal;
		this.status = status;
		this.node = node;
	}

	public String getStatus() {
		return status;
	}
	

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bill() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getBuyer() {
		return buyer;
	}

	public void setBuyer(Account buyer) {
		this.buyer = buyer;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public long getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(long priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	@Override
	public String toString() {
		return "Bill [id=" + id + ", buyer=" + buyer + ", buyDate=" + buyDate + ", priceTotal=" + priceTotal
				+ ", status=" + status + "]";
	}

}
