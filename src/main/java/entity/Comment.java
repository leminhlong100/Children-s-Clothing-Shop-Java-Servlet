package entity;

public class Comment {
	private int idComment;
	private String content;
	private Customer customer;
	private Product product;
	public Comment(int idComment, String content, Customer customer, Product product) {
		super();
		this.idComment = idComment;
		this.content = content;
		this.customer = customer;
		this.product = product;
	}
	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", content=" + content + ", customer=" + customer + ", product="
				+ product + "]";
	}
	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
}
