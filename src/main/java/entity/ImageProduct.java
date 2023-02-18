package entity;

public class ImageProduct {
	private int idImageProduct;
	private int idproduct;
	private String image;
	public ImageProduct(int idImageProduct, int idproduct, String image) {
		super();
		this.idImageProduct = idImageProduct;
		this.idproduct = idproduct;
		this.image = image;
	}
	@Override
	public String toString() {
		return "ImageProduct [idImageProduct=" + idImageProduct + ", idproduct=" + idproduct + ", image=" + image + "]";
	}
	public int getIdImageProduct() {
		return idImageProduct;
	}
	public void setIdImageProduct(int idImageProduct) {
		this.idImageProduct = idImageProduct;
	}
	public int getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
