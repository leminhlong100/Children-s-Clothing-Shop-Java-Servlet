package entity;

public class SizeProduct {
	private int idSizeProduct;
	private String size;
	public SizeProduct(int idSizeProduct, String size) {
		super();
		this.idSizeProduct = idSizeProduct;
		this.size = size;
	}
	
	public SizeProduct() {
		super();
	}

	@Override
	public String toString() {
		return "SizeProduct [idSizeProduct=" + idSizeProduct + ", size=" + size + "]";
	}
	public int getIdSizeProduct() {
		return idSizeProduct;
	}
	public void setIdSizeProduct(int idSizeProduct) {
		this.idSizeProduct = idSizeProduct;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
}
