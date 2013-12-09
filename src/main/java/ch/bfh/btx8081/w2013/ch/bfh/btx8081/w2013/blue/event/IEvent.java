package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.event;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

public interface IEvent {
	
	
	public Patient getPatient();
	public void setPatient(Patient patient);
	
	public String toString();
	
}
