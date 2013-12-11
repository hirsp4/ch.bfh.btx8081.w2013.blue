package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign;

import java.io.IOException;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.DatabaseHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.FileIsEmptyException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PersonNotFoundException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

/**
 * 
 * @author Adrian Wyss
 * 
 *         this class extends the basic abstract class states.
 * 
 */

public class Dangerous extends State {

	/**
	 * 
	 * Declare a valid Person in order to give the person a state
	 * 
	 * @param p
	 * 
	 */
	public Dangerous(Patient p) {
		super(p);
		this.stateDescription = "DANGEROUS";
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 * This method will change a persons state from dangerous to normal
	 * 
	 */
	public void setNormal() {
		DatabaseHandler dbh = new DatabaseHandler("Patient");
		try {
			dbh.removePatient(this.p);
			p.setState(new Normal(this.p));
			dbh.addPatient(this.p);

		} catch (FileIsEmptyException | IOException | PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
