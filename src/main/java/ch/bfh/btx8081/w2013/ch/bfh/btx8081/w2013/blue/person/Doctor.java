package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

public class Doctor extends Person{
	private int did; //doctor ID
	

	public Doctor(int did, String name, String foreName, Address address, char gender,
			String birthdate) {
		super(name, foreName, address, gender, birthdate);
		this.did = did;
	}

}
