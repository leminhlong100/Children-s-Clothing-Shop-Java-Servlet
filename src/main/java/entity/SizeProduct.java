package entity;

public class SizeProduct {
    private int id;
    private int idProduct;
    private String size;

    public SizeProduct(int id, int idProduct, String size) {
        this.id = id;
        this.idProduct = idProduct;
        this.size = size;
    }

    public SizeProduct() {
        super();
    }

    @Override
    public String toString() {
        return "SizeProduct{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", size='" + size + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

}
