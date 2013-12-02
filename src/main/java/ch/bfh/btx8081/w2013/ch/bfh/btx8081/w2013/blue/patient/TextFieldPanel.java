package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author Patrick Hirschi
 * 
 * 
 *         TextFieldPanel v1.0 29.11.2013
 * 
 *         Creates the panel for our PatientView. The panel consists of several
 *         text fields to add specific patient data, a button for adding the
 *         patient in our "database" (.txt - file) and a text area with general
 *         informations for the user.
 * 
 */

public class TextFieldPanel extends Panel {

	private TextField pidField;
	private TextField nameField;
	private TextField forenameField;
	private TextArea infoArea;

	public TextFieldPanel() {

		this.addStyleName("labelPanel");
		this.setSizeUndefined();
		this.pidField = new TextField("PID:");
		this.nameField = new TextField("Name:");
		this.forenameField = new TextField("Forename:");
		this.infoArea = new TextArea();

		FormLayout content = new FormLayout();
		content.addStyleName("labelPanelContent");
		content.addComponent(this.pidField);
		content.addComponent(this.nameField);
		content.addComponent(this.forenameField);
		content.addComponent(createAddPatientButton());
		content.addComponent(this.infoArea);
		content.setSizeUndefined();
		content.setMargin(true);
		this.setContent(content);
	}

	/**
	 * Creates the "Add Patient" Button and adds a ClickListener to generate the
	 * output.
	 * 
	 * @return Button
	 */
	public Button createAddPatientButton() {
		Button addPatientButton = new Button("Add Patient");
		addPatientButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				//check whether the input data is valid or not. Update the csv file if
				//the input is correct, and show the error message in the infoArea field
				//if the input is incorrect.
				if (checkInputValues()) {
					String pid = getPID();
					String name = getName();
					String forename = getForename();
					new CSVGenerator(pid + ";" + name + ";" + forename + ";\n");
					infoArea.setValue("the database update was successful.");
				}else{
					infoArea.setValue("the database update failed.");
				}
			}
		});
		return addPatientButton;
	}

	/**
	 * Returns the value of the Name-TextField.
	 * 
	 * @return String
	 */
	private String getName() {
		return this.nameField.getValue();
	}

	/**
	 * Returns the value of the Forename-TextField.
	 * 
	 * @return String
	 */
	private String getForename() {
		return this.forenameField.getValue();
	}

	/**
	 * Returns the value of the PID-TextField.
	 * 
	 * @return String
	 */
	private String getPID() {
		return this.pidField.getValue();
	}

	/**
	 * This private method returns true if and only if all input is correct.
	 * It checks the PID Field, if the input is a valid integer and the String Fields
	 * if they are filled.
	 * @return boolean
	 */
	private boolean checkInputValues() {
		
		//set defaults for the booleans validPID, validStrings
		boolean validPID = false;
		boolean validStrings = false;
		
		//check whether the PID field is filled with numbers. if yes,
		//the boolean valid PID is set true. if not, a notification 
		//is shown and the field will be set empty.
		if (!getPID().matches("\\d*")) {
			Notification.show("Input failure",
					"Please enter a valid PID (integer)",
					Notification.Type.WARNING_MESSAGE);
			this.pidField.setValue("");
		} else
			validPID = true;
		
		//check whether the Name and Forename fields are filled. if yes,
		//the boolean valid PID is set true. if not, a notification 
		//is shown.
		if (getName().isEmpty() || getForename().isEmpty()) {
			Notification.show("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
		} else
			validStrings = true;
		
		//returns true, if and only if both booleans are true.
		return validPID && validStrings;
	}

}