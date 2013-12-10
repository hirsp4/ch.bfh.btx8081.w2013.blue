package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person;

import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.event.IEvent;

public class Patient extends Person {

	private int pid;
	private ArrayList<String> medication;
	
	private String state;

	public Patient(int pid, String name, String foreName, Address address,
			char gender, String birthdate, String state) {
		super(name, foreName, address, gender, birthdate);
		this.setPid(pid);
		this.setState(state);
	}
	
	public int getPid(){
		return this.pid;
	}
	
	public void setPid(int pid){
		this.pid = pid;
	}

	public ArrayList<String> addDrug(String drug) {
		this.medication.add(drug);
		return this.medication;
	}

	@Override
	public void addEvent(IEvent event) {

	}

	public String toString() {
		return super.toString() + " " + this.getPid();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
