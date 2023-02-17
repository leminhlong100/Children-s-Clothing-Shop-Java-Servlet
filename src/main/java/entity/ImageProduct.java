package entity;

public class ImageProduct {
	private int idImageProduct;
	private Product product;
	private String image;
	public ImageProduct(int idImageProduct, Product product, String image) {
		super();
		this.idImageProduct = idImageProduct;
		this.product = product;
		this.image = image;
	}
	@Override
	public String toString() {
		return "ImageProduct [idImageProduct=" + idImageProduct + ", product=" + product + ", image=" + image + "]";
	}
	public int getIdImageProduct() {
		return idImageProduct;
	}
	public void setIdImageProduct(int idImageProduct) {
		this.idImageProduct = idImageProduct;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
