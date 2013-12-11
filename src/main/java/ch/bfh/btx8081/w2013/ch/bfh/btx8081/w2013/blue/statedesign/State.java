package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

/**
 * 
 * @author Adrian Wyss
 * 
 *         this abstract class builds the base for all the necessary states a
 *         person could possibly have.
 * 
 */

public abstract class State {

	protected Patient p;

	// each patient object contains an object referencing to the state he or she
	// actually possesses.
	// since this information also has to be processed by the DatabaseHandler
	// Class, we've
	// decided to give each state object a seperate stateDescription variable,
	// which then can be
	// easily handled via the DatabaseHandler.
	protected String stateDescription;

	/**
	 * 
	 * Declare a valid Person in order to give the person a state
	 * 
	 * @param p
	 * 
	 */

	public State(Patient p) {
		this.p = p;
	}

	/**
	 * 
	 * this method returns the description of the state a person possesses
	 * 
	 * @return stateDescription
	 */
	public String getStateDescription() {
		return this.stateDescription;
	}

}
