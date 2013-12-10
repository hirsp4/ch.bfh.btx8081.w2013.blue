package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign;

import java.io.IOException;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.DatabaseHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.FileIsEmptyException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PersonNotFoundException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

public class Normal extends State {

	public Normal(Patient p) {
		super(p);
		this.stateDescription = "NORMAL";
		// TODO Auto-generated constructor stub
	}
	
	public void setDangerous(){
		DatabaseHandler dbh = new DatabaseHandler("Patient");
		try {
			dbh.removePatient(this.p);
			p.setState(new Dangerous(this.p));
			dbh.addPatient(this.p);
			
		} catch (FileIsEmptyException | IOException | PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
