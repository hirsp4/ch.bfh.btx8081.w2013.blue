package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

import java.util.Date;

import ch.bfh.btx8081.w2013.blue.ch.bfh.btx8081.w2013.blue.event.IEvent;

public abstract class Person {

	private Address address;
	private String name, foreName;
	private char gender;
	private Date birthdate;
	
	public Person(String name, String foreName, Address address, char gender, Date birthdate){
		this.name = name;
		this.foreName = foreName;
		this.address = address;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	

	public void addEvent(IEvent event){
		
	}
}
