package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.patient;

import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PatientHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PersonNotFoundException;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Normal;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

/**
 * 
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

public class PatientPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;
	private ComboBox combobox;
	private TextArea infoArea;
	private Button addPatientButton;
	private Panel panel;
	private Button btn;

	public PatientPanel() {
		this.panel = new Panel(" Alert");
		this.panel.setIcon(new ThemeResource("alarmKlein.jpg"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();
		this.btn = createIndexButton();
		
		Label label = new Label("Select a patient:");
		label.addStyleName("h3");
		
		this.infoArea = new TextArea();

		FormLayout content = new FormLayout();
		content.addComponent(label);
		content.addComponent(this.createComboBox());
		content.addComponent(this.infoArea);
		
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setWidth("340px");
		hlayout.addComponent(createAddPatientButton());
		hlayout.addComponent(createIndexButton());
		content.addComponent(hlayout);
		
		content.setSizeUndefined();
		content.setMargin(true);
		this.panel.setContent(content);
		setContent(panel);
	}

	/**
	 * Creates the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
	public Button createIndexButton() {
		btn = new Button("Index");
		btn.setWidth("100px");
		btn.setHeight("75px");
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("index.png"));
		btn.setEnabled(false);
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn;
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
		addPatientButton.setWidth("100px");
		addPatientButton.setHeight("75px");
		addPatientButton.addStyleName("borderless icon-on-top");
		addPatientButton.addStyleName(ChameleonTheme.BUTTON_SMALL);
		addPatientButton.setIcon(new ThemeResource("add_to_folder.png"));
		addPatientButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				infoArea.setValue((String) combobox.getValue());
				
				PatientHandler dbh = new PatientHandler("Patient");
				Patient selectedPatient;
				try {
					selectedPatient = dbh.getPatient(PatientHandler.PID, ((String) combobox.getValue()).split(" ")[2]);
					if(selectedPatient.getState().getStateDescription().equals("NORMAL")){
						
						Normal normal = new Normal(selectedPatient);
						selectedPatient.setState(normal);
						normal.setDangerous();
						
					}
					
					infoArea.setValue(selectedPatient.getState().getStateDescription());
					btn.setEnabled(true);
				} catch (PersonNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return addPatientButton;
	}
	
	private ComboBox createComboBox(){
		combobox = new ComboBox();
		combobox.setInvalidAllowed(false);
        combobox.setNullSelectionAllowed(false);
        
        PatientHandler dbh = new PatientHandler("Patient");
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
