package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Address;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Dangerous;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Normal;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.State;

/**
 * 
 * @author Rafael Kapp
 * 
 *         Databasehandler V1.00 07.12.2013 Klasse als ersatz zur Datenbank,
 *         verwaltet txt CSV files. V1.0 nur für Patients, offen für
 *         erweiterungen!
 */
public class DatabaseHandler {

	public final static String NAME = "n:";
	public final static String FORENAME = "f:";
	public final static String STREET = "str:";
	public final static String STREETNB = "strnb:";
	public final static String ZIP = "zip:";
	public final static String CITY = "city:";
	public final static String GENDER = "g:";
	public final static String BIRTHDATE = "b:";
	public final static String PID = "pid:";
	public final static String STATE = "state:";

	private final File DATABASE;

	/**
	 * Use the constructor to deside which Database you will use, deliver the
	 * String : Person to choose the Person Database
	 * 
	 * @param selectDatabase
	 */
	public DatabaseHandler(String selectDatabase) {
		if (selectDatabase.equals("Patient")) {
			this.DATABASE = new File("Patient.txt");
		} else {
			this.DATABASE = null;
		}

	}

	/**
	 * Macht eine TreeMap aus dem File, das in der Methode übergeben wird. Die
	 * TreeMap hat als Key einen Integer und als Value eine HashMap(HashMap ist
	 * soweit ich weiss effizienter als TreeMap), Die HashMap, beinhaltet 10
	 * sachen, und zwar Vorname, Nachname, Strasse, StrassenNummer, ZIP, Stadt,
	 * Geschlecht, geburtsdatum, PID(Fals vorhanden), Dangerous. Die Keys der
	 * HashMap, sind auch Strings. Ich Splitte den String und gebe dann die
	 * einzelnen Werte in die Hashmap. Der erste Wert ist allerdings einfach die
	 * Referenz(key) der Treemap.
	 * 
	 * @return TreeMap
	 * @throws FileNotFoundException
	 * @throws FileIsEmptyException
	 */

	@SuppressWarnings("resource")
	private TreeMap<Integer, HashMap<String, String>> buildMapFromFile(File file)
			throws FileNotFoundException, FileIsEmptyException {
		Scanner s = null;
		TreeMap<Integer, HashMap<String, String>> treeMap = new TreeMap<Integer, HashMap<String, String>>();
		if (file.exists()) {
			s = new Scanner(file);
			if (!s.hasNext())
				throw new FileIsEmptyException();
		} else {
			throw new FileNotFoundException();
		}

		String line;
		String[] splitted;
		while (s.hasNextLine()) {
			line = s.nextLine();

			splitted = line.split(";");

			try {
				lineChecker(splitted[0]);
			} catch (InvalideLineException | OutOfBoundsException e) {
				e.printStackTrace();
			}

			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put(NAME, splitted[1]);
			hashMap.put(FORENAME, splitted[2]);
			hashMap.put(STREET, splitted[3]);
			hashMap.put(STREETNB, splitted[4]);
			hashMap.put(ZIP, splitted[5]);
			hashMap.put(CITY, splitted[6]);
			hashMap.put(GENDER, splitted[7]);
			hashMap.put(BIRTHDATE, splitted[8]);
			hashMap.put(PID, splitted[9]);
			hashMap.put(STATE, splitted[10]);

			treeMap.put(Integer.parseInt(splitted[0]), hashMap);
		}
		return treeMap;
	}

	/**
	 * Macht aus der mitgelieferten Map ein .txt File in dem CSV Format. Die
	 * Methode erwartet zudem noch einen String, der den Pfad enthält in dem das
	 * .txt File abgespeichert wird.
	 * 
	 * @param path
	 * @param treeMap
	 * @throws IOException
	 */
	private void writeMapToFile(String path,
			TreeMap<Integer, HashMap<String, String>> treeMap)
			throws IOException {
		PrintWriter printer = new PrintWriter(path);

		Set<Integer> set = treeMap.keySet();
		Iterator<Integer> itr = set.iterator();
		while (itr.hasNext()) {
			int i = itr.next();
			printer.print(i);
			printer.print(";");
			printer.print(treeMap.get(i).get(NAME));
			printer.print(";");
			printer.print(treeMap.get(i).get(FORENAME));
			printer.print(";");
			printer.print(treeMap.get(i).get(STREET));
			printer.print(";");
			printer.print(treeMap.get(i).get(STREETNB));
			printer.print(";");
			printer.print(treeMap.get(i).get(ZIP));
			printer.print(";");
			printer.print(treeMap.get(i).get(CITY));
			printer.print(";");
			printer.print(treeMap.get(i).get(GENDER));
			printer.print(";");
			printer.print(treeMap.get(i).get(BIRTHDATE));
			printer.print(";");
			printer.print(treeMap.get(i).get(PID));
			printer.print(";");
			printer.print(treeMap.get(i).get(STATE));
			printer.println();
		}
		printer.close();
	}

	/**
	 * Diese Methode liefer eine TreeMap zurück mit den Values drin und den
	 * dazugehörigen Keys, nachdenen gesucht wurde. Es erwartet einen Delemitter
	 * und einen String(Nach dem ich suchen will, also zB den Namen), auch das
	 * Geburtsjahr muss als String geliefert werden!
	 * 
	 * @param TreeMap
	 * @param delemitter
	 *            -> String
	 * @param data
	 *            -> String
	 * @return TreeMap filled with the Values it searched.
	 * @throws IllegalArgumentException
	 */
	private TreeMap<Integer, HashMap<String, String>> searchPatients(
			TreeMap<Integer, HashMap<String, String>> tm, String delemitter,
			String data) throws IllegalArgumentException {
		TreeMap<Integer, HashMap<String, String>> resultMap = new TreeMap<Integer, HashMap<String, String>>();

		Set<Integer> set = tm.keySet();
		Iterator<Integer> itr = set.iterator();

		while (itr.hasNext()) {
			int i = itr.next();
			if (delemitter.equals(NAME)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(FORENAME)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(STREET)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(STREETNB)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(ZIP)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(CITY)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(GENDER)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(BIRTHDATE)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(PID)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else if (delemitter.equals(STATE)) {
				if (data.equals(tm.get(i).get(delemitter)))
					resultMap.put(i, tm.get(i));
			} else {
				throw new IllegalArgumentException();
			}
		}
		return resultMap;
	}

	/**
	 * Gibt alle Patient die durch den entsprechenden Delemitter gefunden wurden
	 * zurück, also alle Müller oder alle die am 1.1.1950 geboren sind usw..
	 * Wenn er nichts findet dann gibt er die ArrayListe leer zurück. Wenn der
	 * Falsche Dellemitter gegeben wird, wobei man hier Static Final Strings
	 * brauchen kann, dann gibts ne Exeption Wenn die Datenbank nicht gefunden
	 * wird auch, wenn das File leer ist auch.
	 * 
	 * @param delemitter
	 * @param data
	 * @return ArrayList<Patient>
	 * @throws IllegalArgumentException
	 * @throws PersonNotFoundException
	 */
	public ArrayList<Patient> searchPatients(String delemitter, String data)
			throws IllegalArgumentException, PersonNotFoundException {
		ArrayList<Patient> foundPerson;
		TreeMap<Integer, HashMap<String, String>> treeFoundPerson = null;
		try {
			treeFoundPerson = this.searchPatients(
					this.buildMapFromFile(this.DATABASE), delemitter, data);

		} catch (FileNotFoundException | FileIsEmptyException e) {
			e.printStackTrace();
		}
		foundPerson = writeMapToList(treeFoundPerson);
		if (foundPerson.isEmpty()) {
			throw new PersonNotFoundException();
		}
		return foundPerson;
	}

	/**
	 * In dieser Metode hier, werden Patienten die noch ein einer Map sind, also
	 * als Strings und Integer, umgewandelt in Patienten und in einer Liste
	 * zurückgebracht
	 * 
	 * @param tm
	 * @return ArrayList
	 */
	private ArrayList<Patient> writeMapToList(
			TreeMap<Integer, HashMap<String, String>> tm) {
		ArrayList<Patient> list = new ArrayList<Patient>();

		Set<Integer> set = tm.keySet();
		Iterator<Integer> itr = set.iterator();

		while (itr.hasNext()) {
			int i = itr.next();

			String street = tm.get(i).get(STREET);
			int streetNr = Integer.parseInt(tm.get(i).get(STREETNB));
			int zip = Integer.parseInt(tm.get(i).get(ZIP));
			String cityName = tm.get(i).get(CITY);

			Address address = new Address(street, streetNr, zip, cityName);

			int pid = Integer.parseInt(tm.get(i).get(PID));
			String name = tm.get(i).get(NAME);
			String foreName = tm.get(i).get(FORENAME);
			char gender = tm.get(i).get(GENDER).charAt(0);
			String birthdate = new String(tm.get(i).get(BIRTHDATE));
			
			String stateName = tm.get(i).get(STATE);
			State state = null;
			Patient patient = null;
			
			if(stateName.equals("DANGEROUS")){
				state = new Dangerous(patient);
			} else if (stateName.equals("NORMAL")){
				state = new Normal(patient);
			}
			

			patient = new Patient(pid, name, foreName, address, gender,
					birthdate, state);

			list.add(patient);
		}
		return list;
	}

	/**
	 * Es kann ein neuer Patient hinzugefügt werden, wobei der in der TreeKey =
	 * der PID ist. Speichert den neuen Patienten gleich in der entsprechenden
	 * Datenbank.
	 * 
	 * @param patient
	 * @throws IOException
	 */
	public void addPatient(Patient patient) throws IOException {
		TreeMap<Integer, HashMap<String, String>> tm;
		try {
			tm = this.buildMapFromFile(DATABASE);
		} catch (FileIsEmptyException e) {
			tm = new TreeMap<Integer, HashMap<String, String>>();
		}

		Address address = patient.getAddress();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put(NAME, patient.getName());
		hashMap.put(FORENAME, patient.getForeName());
		hashMap.put(STREET, address.getStreet());
		hashMap.put(STREETNB, Integer.toString(address.getStreetNr()));
		hashMap.put(ZIP, Integer.toString(address.getZip()));
		hashMap.put(CITY, address.getCity());
		hashMap.put(GENDER, String.valueOf(patient.getGender()));
		hashMap.put(BIRTHDATE, patient.getBirthdate());
		hashMap.put(PID, Integer.toString(patient.getPid()));
		hashMap.put(STATE, patient.getState().getStateDescription());

		tm.put(patient.getPid(), hashMap);

		this.writeMapToFile(DATABASE.getPath(), tm);
	}

	/**
	 * Hier noch die Methode zum erhalten eines einzelnen Patienten, also
	 * ähnlich wie die searchPersons Methode, nur liefert sie hier nur einen
	 * Patient Wir brauchen ja auch die search Person Methode.
	 * 
	 * @param delemitter
	 * @param data
	 * @return
	 * @throws PersonNotFoundException
	 */
	public Patient getPatient(String delemitter, String data)
			throws PersonNotFoundException {
		ArrayList<Patient> tm = searchPatients(delemitter, data);
		return tm.get(0);
	}

	/**
	 * Liefer den Kommpleten datensatz an Patienten.
	 * @return ArrayList
	 */
	public ArrayList<Patient> getAll(){
		TreeMap<Integer, HashMap<String, String>> tm = null; 
		try {
			tm = buildMapFromFile(DATABASE);
		} catch (FileNotFoundException | FileIsEmptyException e) {
			e.printStackTrace();
		}
		return writeMapToList(tm);
	}

	/**
	 * Löscht einen eintrag in der Datenbank, es wird wieder alles eingelesen in
	 * einer Map abgebildet, dann wird der Patient gesucht(Hier bekommen wir
	 * eine ArrayList, aber wir suchen mit dem eindeutigen PID, also ist in der
	 * Liste nur ein eintrag!) der gelöscht werden soll, dann wird er durch den
	 * Key(PID) gelöscht. Dann schreiben wir alles wieder in die DB. Wenn es
	 * niemanden gibt der entfernt werden kann, weil er nicht existier, wirft er
	 * eine Exception.
	 * 
	 * @param patient
	 * @throws FileIsEmptyException
	 * @throws IOException
	 * @throws PersonNotFoundException
	 */
	public void removePatient(Patient patient) throws FileIsEmptyException,
			IOException, PersonNotFoundException {
		TreeMap<Integer, HashMap<String, String>> tm;
		tm = buildMapFromFile(DATABASE);
		ArrayList<Patient> list = searchPatients(PID,
				Integer.toString(patient.getPid()));
		if (list.isEmpty()) {
			throw new PersonNotFoundException();
		}
		tm.remove(list.get(0).getPid()); // In der Liste ist sicher nur ein
											// element, da die PID eindeutig
											// ist. Desshalb bei .get() die
											// MagicNuber: 0..
		this.writeMapToFile(DATABASE.getPath(), tm);
	}

	/**
	 * Überprüft einen String, der einen eigentlich einen Integer enthalten
	 * sollte, wenn das nicht so ist wirft er InvalideLineException oder
	 * OutOfBoundsException.
	 * 
	 * @param checkString
	 * @throws InvalideLineException
	 * @throws OutOfBoundsException
	 */
	private void lineChecker(String checkString) throws InvalideLineException,
			OutOfBoundsException {
		if (!Character.isDigit(checkString.charAt(0)))
			throw new InvalideLineException();
		if (Integer.parseInt(checkString) > Integer.MAX_VALUE
				|| Integer.parseInt(checkString) < Integer.MIN_VALUE)
			throw new OutOfBoundsException();
	}
}
