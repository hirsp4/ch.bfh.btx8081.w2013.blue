package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCreator {

	public CSVCreator(String input, String path) {
		try {
			File file = new File(path); // create the database.txt file
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

