package entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Comment {
	private int id;
	private String content;
	private int idCustomer;
	private int idProduct;

	private int idParent;
	private String status;
	private int level;
	private static String  createAt;
	private String nameAccount;

	private List<Comment> listreply;

	public Comment() {
	}

	public List<Comment> getListreply() {
		return listreply;
	}

	public void setListreply(List<Comment> listreply) {
		this.listreply = listreply;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdParent() {
		return idParent;
	}

	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", content='" + content + '\'' +
				", idCustomer=" + idCustomer +
				", idProduct=" + idProduct +
				", idParent=" + idParent +
				", status='" + status + '\'' +
				", level=" + level +
				", createAt='" + createAt + '\'' +
				", nameAccount='" + nameAccount + '\'' +
				", listreply=" + listreply +
				'}'+'\n';
	}
	public static long getTimeSinceCreatedInSeconds() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime createTime = LocalDateTime.parse(createAt, formatter);
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(createTime, now);
		return duration.toSeconds();
	}




		}
