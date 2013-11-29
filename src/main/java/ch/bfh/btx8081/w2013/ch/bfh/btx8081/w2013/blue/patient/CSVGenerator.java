package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Patrick Hirschi
 *
 *
 * CSVGenerator v1.0 29.11.2013
 *
 * Appends or generates (if it doesn't exist yet) a CSV-file named "database.txt"
 * based on the user input about the patient to be added to the database.
 * This class is basically started when the user presses the button "Add Patient" from
 * our PatientView.
 * 
 */

public class CSVGenerator {

	public CSVGenerator(String input) {
		try {
			File file = new File("database.txt"); // create the database.txt file
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter out = new FileWriter(file.getAbsoluteFile(), true); // create a FileWriter object (append=true)
			out.write(input); // write the input string
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
