package entity;

public class ColorProduct {
	private int id;
	private int idProduct;
	private String color;

	public ColorProduct(int id, int idProduct, String color) {
		this.id = id;
		this.idProduct = idProduct;
		this.color = color;
	}

	public ColorProduct() {
		super();
	}

	@Override
	public String toString() {
		return "ColorProduct{" +
				"id=" + id +
				", idProduct=" + idProduct +
				", color='" + color + '\'' +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
}
