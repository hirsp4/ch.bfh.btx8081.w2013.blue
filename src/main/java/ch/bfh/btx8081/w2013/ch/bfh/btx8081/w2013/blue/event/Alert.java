//package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.event;
//
//import java.io.IOException;
//
//import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.DatabaseHandler;
//import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.FileIsEmptyException;
//import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PersonNotFoundException;
//import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
//
///**
// * The Alert class which include the Patient information.
// * 
// * @author Rafael Kapp
// * 
// *         V1.0 09.2013
// * 
// */
//public class Alert implements IEvent {
//
//	private Patient patient;
//
//	/**
//	 * The Construncter of this class, will already set the Patient, which he
//	 * search from the delevered PID, to dangerous.
//	 * 
//	 * @param pid
//	 * @throws PersonNotFoundException
//	 */
//	public Alert(String pid) throws PersonNotFoundException {
//		DatabaseHandler dbh = new DatabaseHandler("Patient");
//		patient = dbh.getPatient(DatabaseHandler.PID, pid);
//		setPatient(patient);
//		setState("dangerous");
//		try {
//			dbh.removePatient(patient); // To change an attribute of an existing
//										// Patient, we have to remove the old
//										// and add the changed.
//			dbh.addPatient(patient);
//		} catch (FileIsEmptyException | IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void setState(String state) {
//		this.patient.setStateDescription(state);
//	}
//
//	@Override
//	public Patient getPatient() {
//		return this.patient;
//	}
//
//	@Override
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
//
//	@Override
//	public String toString() {
//		return this.patient.toString();
//	}
//}
