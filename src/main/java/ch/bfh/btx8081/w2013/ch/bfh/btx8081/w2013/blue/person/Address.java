package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

public class Address {
	private String street;
	private int streetNr;
	private int zip;
	private String city;
	
	public Address(String street, int streetNr,	int zip, String city){
		this.street = street;
		this.streetNr = streetNr;
		this.zip = zip;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getStreetNr() {
		return streetNr;
	}

	public void setStreetNr(int streetNr) {
		this.streetNr = streetNr;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
