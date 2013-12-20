package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

public class Doctor extends Person{
	private int did; //doctor ID
	private String officeName;

	public Doctor(int did, String name, String foreName, Address address, char gender,
			String birthdate, String officeName) {
		super(name, foreName, address, gender, birthdate);
		this.setDid(did);
		this.setOfficeName(officeName);
	}


	public int getDid() {
		return did;
	}


	public void setDid(int did) {
		this.did = did;
	}


	public String getOfficeName() {
		return officeName;
	}


	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
}
