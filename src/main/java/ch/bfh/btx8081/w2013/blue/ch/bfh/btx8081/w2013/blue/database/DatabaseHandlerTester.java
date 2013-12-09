package ch.bfh.btx8081.w2013.blue.ch.bfh.btx8081.w2013.blue.database;

import java.io.IOException;
import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Address;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;

public class DatabaseHandlerTester {
	public static void main(String[] args) throws PersonNotFoundException {
		DatabaseHandler dbh = new DatabaseHandler("Patient");
		
		Patient patient = new Patient(1333, "Wyss", "Adrian", new Address("Schlumpfweg", 3, 9000, "Entenhausen"), 'm', "13.03.1991", false);
		
		try {
			dbh.addPatient(patient);
		} catch (IOException e) {}
		
//		patient.setPid(1111);
		
		
		ArrayList<Patient> list = dbh.searchPatients(DatabaseHandler.PID, "1234");
		System.out.println(list.isEmpty());
//		try {
//			dbh.removePatient(patient);
//		} catch (FileIsEmptyException | IOException | PersonNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}
