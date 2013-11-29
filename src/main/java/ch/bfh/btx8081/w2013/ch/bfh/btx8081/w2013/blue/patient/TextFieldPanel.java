package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author Patrick Hirschi
 *
 *
 * TextFieldPanel v1.0 29.11.2013
 *
 * Creates the panel for our PatientView. The panel consists of 
 * several text fields to add specific patient data, a button for
 * adding the patient in our "database" (.txt - file) and a text area
 * with general informations for the user.
 * 
 */


public class TextFieldPanel extends Panel{
	
	private TextField pidField;
	private TextField nameField;
	private TextField forenameField;
	private TextArea infoArea;
	
	public TextFieldPanel(){
		
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
	 * Creates the "Add Patient" Button and adds a ClickListener to generate
	 * the output.
	 * @return Button
	 */
	public Button createAddPatientButton(){
		Button addPatientButton = new Button("Add Patient");
		addPatientButton.addClickListener(new Button.ClickListener() {
		    	public void buttonClick(ClickEvent event) {
		    		String pid = getPID();
		    		String name = getName();
		    		String forename = getForename();
		    		new CSVGenerator(pid +";"+ name +";"+ forename + ";\n");
		    		infoArea.setValue("the database update was successful.");
		    	}
		    });
		return addPatientButton;
	}
	
	/**
	 * Returns the value of the Name-TextField.
	 * @return String
	 */
	public String getName(){
		return this.nameField.getValue();
	}
	
	/**
	 * Returns the value of the Forename-TextField.
	 * @return String
	 */
	public String getForename(){
		return this.forenameField.getValue();
	}
	
	/**
	 * Returns the value of the PID-TextField.
	 * @return String
	 */
	public String getPID(){
		return this.pidField.getValue();
	}

}
