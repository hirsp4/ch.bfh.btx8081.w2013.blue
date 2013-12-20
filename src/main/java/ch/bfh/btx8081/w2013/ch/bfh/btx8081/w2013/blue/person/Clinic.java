package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

public class Clinic {
	private Address address;
	private String name;
	private int id;
	
	public Clinic(int id, String name, Address address){
		this.setAddress(address);
		this.setName(name);
		this.setId(id);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
