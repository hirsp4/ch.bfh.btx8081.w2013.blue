package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

public abstract class State {
	
	protected Patient p;
	protected String stateDescription;
	
	public State(Patient p){
		this.p = p;
	}
	
	
	public String getStateDescription(){
		return this.stateDescription;
	}

}
