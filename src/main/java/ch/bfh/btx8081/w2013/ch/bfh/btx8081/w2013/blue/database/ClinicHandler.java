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
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Clinic;

/**
 * 
 * @author Rafael Kapp
 * 
 *         Databasehandler V1.00 07.12.2013 Klasse als ersatz zur Datenbank,
 *         verwaltet txt CSV files. V1.0 nur für Patients, offen für
 *         erweiterungen!
 */
public class ClinicHandler {

	public final static String NAME = "n:";
	public final static String STREET = "str:";
	public final static String STREETNB = "strnb:";
	public final static String ZIP = "zip:";
	public final static String CITY = "city:";
	public final static String CID = "cid:";	//Clinic-Id

	private final File DATABASE;

	/**
	 * Use the constructor to deside which Database you will use, deliver the
	 * String : Person to choose the Person Database
	 * 
	 * @param selectDatabase
	 */
	public ClinicHandler(String selectDatabase) {
		if (selectDatabase.equals("Clinic")) {
			this.DATABASE = new File("Clinic.txt");
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
			s.reset();
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
			} catch (InvalideLineException e) {
				e.printStackTrace();
			} catch (OutOfBoundsException e) {
				e.printStackTrace();
			}

			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put(NAME, splitted[1]);
			hashMap.put(STREET, splitted[2]);
			hashMap.put(STREETNB, splitted[3]);
			hashMap.put(ZIP, splitted[4]);
			hashMap.put(CITY, splitted[5]);
			hashMap.put(CID, splitted[6]);

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
			printer.print(treeMap.get(i).get(STREET));
			printer.print(";");
			printer.print(treeMap.get(i).get(STREETNB));
			printer.print(";");
			printer.print(treeMap.get(i).get(ZIP));
			printer.print(";");
			printer.print(treeMap.get(i).get(CITY));
			printer.print(";");
			printer.print(treeMap.get(i).get(CID));
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
	private TreeMap<Integer, HashMap<String, String>> searchClinics(
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
			} else if (delemitter.equals(CID)) {
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
	public ArrayList<Clinic> searchClinics(String delemitter, String data)
			throws IllegalArgumentException, PersonNotFoundException {
		ArrayList<Clinic> foundClinic;
		TreeMap<Integer, HashMap<String, String>> treeFoundPerson = null;
		try {
			treeFoundPerson = this.searchClinics(
					this.buildMapFromFile(this.DATABASE), delemitter, data);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FileIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		foundClinic = writeMapToList(treeFoundPerson);
		if (foundClinic.isEmpty()) {
			throw new PersonNotFoundException();
		}
		return foundClinic;
	}

	/**
	 * In dieser Metode hier, werden Patienten die noch ein einer Map sind, also
	 * als Strings und Integer, umgewandelt in Patienten und in einer Liste
	 * zurückgebracht
	 * 
	 * @param tm
	 * @return ArrayList
	 */
	private ArrayList<Clinic> writeMapToList(
			TreeMap<Integer, HashMap<String, String>> tm) {
		ArrayList<Clinic> list = new ArrayList<Clinic>();

		Set<Integer> set = tm.keySet();
		Iterator<Integer> itr = set.iterator();

		while (itr.hasNext()) {
			int i = itr.next();

			String street = tm.get(i).get(STREET);
			int streetNr = Integer.parseInt(tm.get(i).get(STREETNB));
			int zip = Integer.parseInt(tm.get(i).get(ZIP));
			String cityName = tm.get(i).get(CITY);

			Address address = new Address(street, streetNr, zip, cityName);

			int cid = Integer.parseInt(tm.get(i).get(CID));
			String name = tm.get(i).get(NAME);
			
			Clinic clinic = null;
			clinic = new Clinic(cid, name, address);

			list.add(clinic);
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
	public void addClinic(Clinic clinic) throws IOException {
		TreeMap<Integer, HashMap<String, String>> tm;
		try {
			tm = this.buildMapFromFile(DATABASE);
		} catch (FileIsEmptyException e) {
			tm = new TreeMap<Integer, HashMap<String, String>>();
		}

		Address address = clinic.getAddress();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put(NAME, clinic.getName());
		hashMap.put(STREET, address.getStreet());
		hashMap.put(STREETNB, Integer.toString(address.getStreetNr()));
		hashMap.put(ZIP, Integer.toString(address.getZip()));
		hashMap.put(CITY, address.getCity());
		hashMap.put(CID, Integer.toString(clinic.getId()));

		tm.put(clinic.getId(), hashMap);

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
	public Clinic getClinic(String delemitter, String data)
			throws PersonNotFoundException {
		ArrayList<Clinic> tm = searchClinics(delemitter, data);
		return tm.get(0);
	}

	/**
	 * Liefer den Kommpleten datensatz an Patienten.
	 * @return ArrayList
	 */
	public ArrayList<Clinic> getAll(){
		TreeMap<Integer, HashMap<String, String>> tm = null; 
		try {
			tm = buildMapFromFile(DATABASE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FileIsEmptyException e) {
			// TODO Auto-generated catch block
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
	public void removeClinic(Clinic clinic) throws FileIsEmptyException,
			IOException, PersonNotFoundException {
		TreeMap<Integer, HashMap<String, String>> tm;
		tm = buildMapFromFile(DATABASE);
		ArrayList<Clinic> list = searchClinics(CID,
				Integer.toString(clinic.getId()));
		if (list.isEmpty()) {
			throw new PersonNotFoundException();
		}
		tm.remove(list.get(0).getId()); // In der Liste ist sicher nur ein
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
