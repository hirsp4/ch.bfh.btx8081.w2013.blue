package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database;

import java.io.IOException;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Address;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Clinic;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Doctor;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Normal;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.State;

public class DatabaseHandlerTester {
	public static void main(String[] args){
		PatientHandler ph = new PatientHandler("Patient");
		ClinicHandler ch = new ClinicHandler("Clinic");
		DoctorHandler dh = new DoctorHandler("Doctor");
		
		System.out.println("All Handler initalized!");
		
		Clinic c1 = new Clinic(1234, "Insel Bern", new Address("Spitalgasse", 11, 3000, "Bern"));
		
		Doctor d1 = new Doctor(2201, "Fritzel", "Horst", new Address("Strasse", 3, 4302, "Ort"), 'm', "09.12.1950", "Medizentrum Ins");
		
		Patient p1 = null;
		State s1 = new Normal(p1);
		p1 = new Patient(9171, "Unser", "Patient", new Address("Wohnt", 4, 8191, "IN"), 'w', "11.08.1999", s1);
		
		System.out.println("made patient, clinic, doctor");
		
		try {
			ph.addPatient(p1);
		} catch (IOException e) {
			System.out.println("Patient could not be added!");
		}
		
		try {
			ch.addClinic(c1);
		} catch (IOException e) {
			System.out.println("Clinic could not be added!");
		}
		
		try {
			dh.addDoctor(d1);
		} catch (IOException e) {
			System.out.println("Doctor could not be added!");
		}
		
		System.out.println("Test is over!");
	}
}
