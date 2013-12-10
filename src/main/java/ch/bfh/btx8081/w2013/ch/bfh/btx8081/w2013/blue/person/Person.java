package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.event.IEvent;

public abstract class Person {

	private Address address;
	private String name, foreName;
	private char gender;
	private String birthdate;

	public Person(String name, String foreName, Address address, char gender,
			String birthdate) {
		this.setName(name);
		this.setForeName(foreName);
		this.setAddress(address);
		this.setGender(gender);
		this.setBirthdate(birthdate);
	}

	public void addEvent(IEvent event) {

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

	public String getForeName() {
		return foreName;
	}

	public void setForeName(String foreName) {
		this.foreName = foreName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String toString() {
		return getName() + " "+ getForeName();
	}
}
