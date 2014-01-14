package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import java.util.ArrayList;

public class MedicineObjects {

	/**
	 * 
	 * @author Saskia Basler
	 * @version 08.01.2014
	 *
	 * Medicine Objects
	 * 
	 * Medicine Objects for the MedicineComboBox in the DenialPanel.
	 * 
	 *
	 */

	public static ArrayList<String> Medicines = new ArrayList<String>();

	String medicine;

	static {
		Medicines.add("Citaloprom");
		Medicines.add("Risperidon");
		Medicines.add("Holdol");
		Medicines.add("Distraneurin");
		Medicines.add("Temesta");
		Medicines.add("Cipralex");
		Medicines.add("Seraquel");
	}

	public MedicineObjects() {
		this.medicine = null;

	}

	/**
	 * Set the medicine.
	 * 
	 */
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	/**
	 * Gets the medicine back.
	 * 
	 */
	public String getMedicine() {
		return this.medicine;
	}

}