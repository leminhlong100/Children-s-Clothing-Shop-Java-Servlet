package entity;

public class SizeColorProduct {
    private int id;
    private int idProduct;
    private String size;
    private String color;
    private int  quantity;

    public SizeColorProduct() {
    }

    public SizeColorProduct(int id, int idProduct, String size, String color, int quantity) {
        this.id = id;
        this.idProduct = idProduct;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public SizeColorProduct(int id, String size) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SizeColorProduct{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
