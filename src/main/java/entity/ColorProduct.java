package entity;

public class ColorProduct {
	private int idColorProduct;
	private String color;

	public ColorProduct(int idColorProduct, String color) {
		super();
		this.idColorProduct = idColorProduct;
		this.color = color;
	}

	public ColorProduct() {
		super();
	}

	@Override
	public String toString() {
		return "ColorProduct [idColorProduct=" + idColorProduct + ", color=" + color + "]";
	}

	public int getIdColorProduct() {
		return idColorProduct;
	}

	public void setIdColorProduct(int idColorProduct) {
		this.idColorProduct = idColorProduct;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
