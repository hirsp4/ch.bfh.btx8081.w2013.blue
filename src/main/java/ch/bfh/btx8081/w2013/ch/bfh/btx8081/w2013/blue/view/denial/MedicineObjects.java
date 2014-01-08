package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import java.util.ArrayList;

public class MedicineObjects {

	/**
	 * 
	 * Medicine Objects for the MedicineComboBox in the DenialPanel.
	 * 
	 * 
	 * @author basls1
	 * @version 08.01.2014
	 */

	public static ArrayList<String> Medicines = new ArrayList<String>();

	String medicine;

	static {
		Medicines.add("Dafalgan");
		Medicines.add("Algifor");
		Medicines.add("Ponstan");
		Medicines.add("Panadol");
	}

	public MedicineObjects() {
		this.medicine = null;

	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getInsuranceKVG() {
		return this.medicine;
	}

}