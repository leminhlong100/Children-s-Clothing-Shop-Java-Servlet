package entity;

public class Category {
	private int idCategory;
	private String nameCategory;
	public Category(int id, String name) {
		super();
		this.idCategory = id;
		this.nameCategory = name;
	}
	@Override
	public String toString() {
		return "Category [id=" + idCategory + ", name=" + nameCategory + "]";
	}
	public int getId() {
		return idCategory;
	}
	public void setId(int id) {
		this.idCategory = id;
	}
	public String getName() {
		return nameCategory;
	}
	public void setName(String name) {
		this.nameCategory = name;
	}
	
}
