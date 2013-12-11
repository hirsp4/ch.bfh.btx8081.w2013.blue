package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;

import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.DatabaseHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PersonNotFoundException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Normal;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

/**
 * 
 * @author Patrick Hirschi / Rafael Kapp
 * 
 * 
 *         TextFieldPanel v1.0 29.11.2013
 *         
 *         v1.1 09.12.2013
 * 
 *         Creates the panel for our PatientView. The panel consists of several
 *         text fields to add specific patient data, a button for adding the
 *         patient in our "database" (.txt - file) and a text area with general
 *         informations for the user.
 * 
 */

public class PatientPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private ComboBox combobox;
	private TextArea infoArea;
	private Button addPatientButton;

	public PatientPanel() {
		this.infoArea = new TextArea();
		this.addStyleName(ChameleonTheme.PANEL_BORDERLESS);
		this.setWidth("290px");
		this.setHeight("450px");
		

		FormLayout content = new FormLayout();
		content.addComponent(this.createComboBox());
		content.addComponent(this.createAddPatientButton());
		content.addComponent(this.infoArea);
		
		content.setSizeUndefined();
		content.setMargin(true);
		this.setContent(content);
	}

	/**
	 * Creates the "Add Patient" Button and adds a ClickListener to generate the
	 * output.
	 * If the button was clicked it makes a new Alert
	 * 
	 * @return Button
	 */
	public Button createAddPatientButton() {
		addPatientButton = new Button("Add Patient");
		addPatientButton.setEnabled(false);
		addPatientButton.setStyleName(ChameleonTheme.BUTTON_BIG);
		addPatientButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				infoArea.setValue((String) combobox.getValue());
				
				DatabaseHandler dbh = new DatabaseHandler("Patient");
				Patient selectedPatient;
				try {
					selectedPatient = dbh.getPatient(DatabaseHandler.PID, ((String) combobox.getValue()).split(" ")[2]);
					if(selectedPatient.getState().getStateDescription().equals("NORMAL")){
						
						Normal normal = new Normal(selectedPatient);
						selectedPatient.setState(normal);
						normal.setDangerous();
						
					}
					
					infoArea.setValue(selectedPatient.getState().getStateDescription());
				} catch (PersonNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return addPatientButton;
	}
	
	private ComboBox createComboBox(){
		combobox = new ComboBox("Select a Patient");
		combobox.setInvalidAllowed(false);
        combobox.setNullSelectionAllowed(false);
        
        DatabaseHandler dbh = new DatabaseHandler("Patient");
        ArrayList<Patient> list = dbh.getAll();
        
        for(int i = 0; i<list.size(); i++){
        	combobox.addItem(list.get(i).toString());
        }
        
        combobox.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -5188369735622627751L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (combobox.getValue() != null) {
					addPatientButton.setEnabled(true);
				}				
			}
        });
        return combobox;
	}

}
