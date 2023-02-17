package entity;

public class Producer { // nha san suat
	private int idProducer;
	private int nameProducer;
	private String information;
	private String logo;
	public Producer(int idProducer, int nameProducer, String information, String logo) {
		super();
		this.idProducer = idProducer;
		this.nameProducer = nameProducer;
		this.information = information;
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "Producer [idProducer=" + idProducer + ", nameProducer=" + nameProducer + ", information=" + information
				+ ", logo=" + logo + "]";
	}
	public int getIdProducer() {
		return idProducer;
	}
	public void setIdProducer(int idProducer) {
		this.idProducer = idProducer;
	}
	public int getNameProducer() {
		return nameProducer;
	}
	public void setNameProducer(int nameProducer) {
		this.nameProducer = nameProducer;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
}
