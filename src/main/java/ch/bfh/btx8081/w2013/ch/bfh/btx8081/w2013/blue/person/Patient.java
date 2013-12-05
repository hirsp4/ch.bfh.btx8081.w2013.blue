package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

import java.util.ArrayList;
import java.util.Date;

import ch.bfh.btx8081.w2013.blue.ch.bfh.btx8081.w2013.blue.event.IEvent;

public class Patient extends Person{

	private int pid;
	private ArrayList<String> medication;
	
	public Patient(int pid, String name, String foreName, Address address, char gender, Date birthdate){
		super(name, foreName, address, gender, birthdate);
		this.pid = pid;
	}
	
	public ArrayList<String> addDrug(String drug){
		this.medication.add(drug);
		return this.medication;
	}
	
	@Override
	public void addEvent(IEvent event){
		
	}
	
	public String toString(){
		return Integer.toString(pid);
	}
}
