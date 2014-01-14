package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.ClinicHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PatientHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.DoctorHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Clinic;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Doctor;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;

/**
 * 
 * @author Maja Kelterborn
 * 
 * 
 *         ReferralPanel v3.0  13.01.2014
 * 
 *         Creates the panel for our ReferralView. The panel consists of several
 *         text fields and dropdown to add referral informations, a button to send the referral
 *         to the doctor, which is selected in the dropdown menu, 
 *         which creates a "referral"(.txt - file).
 * 
 */

public class ReferralPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;
	private Button SendReferralButton;
	private Button IndexButton;
	private Button AlarmButton;
	private ComboBox patientComboBox;
	private ComboBox referralComboBox;
	private OptionGroup referralTypeGroup;
	private PopupDateField dateField;
	private PopupDateField dateField2;
	private TextArea textReferralMessage;
	private ComboBox clinicComboBox;
	private ComboBox doctorofficeComboBox;
	private ComboBox doctorComboBox;
	private Label lab;
	private Label lab1;
	private Label lab2;
	private Label lab3;
	private Label lab4;
	private Label lab5;
	private Panel panel;
	
	public ReferralPanel() {
		this.panel = new Panel(" Referral");
		this.panel.setIcon(new ThemeResource("Text-Edit-icon.png"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();
		this.lab = new Label("Select a patient:");
		this.lab.addStyleName("h3");
		this.lab1 = new Label("Reason for referral:");
		this.lab1.addStyleName("h3");
		this.lab2 = new Label("Possible timeframe:");
		this.lab2.addStyleName("h3");
		this.lab3 = new Label("Referral type:");
		this.lab3.addStyleName("h3");
		this.lab4 = new Label("Choose referral clinic or doctor office:");
		this.lab4.addStyleName("h3");
		this.lab5 = new Label("Choose responsible doctor:");
		this.lab5.addStyleName("h3");

		
		FormLayout content = new FormLayout();
		HorizontalLayout hlayout = new HorizontalLayout();
		HorizontalLayout hlayout2 = new HorizontalLayout();
		HorizontalLayout hlayout3 = new HorizontalLayout();
		content.addComponent(lab);
		content.addComponent(createPatientComboBox());
		content.addComponent(lab1);
		content.addComponent(this.createReferralComboBox());
		content.addComponent(this.lab2);
		hlayout.setWidth("340px");
		hlayout.addComponent(this.createDateField1());
		hlayout.addComponent(this.createDateField2());
		content.addComponent(hlayout);
		content.addComponent(this.lab3);
		content.addComponent(this.createOptionGroup());
		content.addComponent(this.createMessageTextArea());
		content.addComponent(lab4);
		hlayout2.setWidth("340px");
		hlayout2.addComponent(this.createClinicComboBox());
		hlayout2.addComponent(this.createDoctorOfficeComboBox());
		content.addComponent(hlayout2);
		content.addComponent(this.lab5);
		content.addComponent(this.createDoctorComboBox());
		hlayout3.setWidth("340px");
		hlayout3.setHeight("35px");
		hlayout3.addComponent(this.createLinkIndexButton());
		hlayout3.addComponent(this.createSendReferralButton());
		hlayout3.addComponent(this.createLinkAlarmButton());
		content.addComponent(hlayout3);
		
		content.setSizeUndefined();
		
		this.panel.setContent(content);
		setContent(panel);	
	}
	
	
	/**
	 * Creates the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
	public Button createLinkIndexButton() {
		IndexButton = new Button("Index");
		IndexButton.setWidth("100px");
		IndexButton.setHeight("75px");
		IndexButton.addStyleName("borderless icon-on-top");
		IndexButton.setIcon(new ThemeResource("index.png"));
		IndexButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return IndexButton;
	}
	
	/**
	 * Creates the "Alert" Button to go directly to the Alert.
	 * 
	 * @return Button
	 */
	
	public Button createLinkAlarmButton() {
		AlarmButton = new Button("Alert");
		AlarmButton.setWidth("100px");
		AlarmButton.setHeight("75px");
		AlarmButton.addStyleName("borderless icon-on-top");
		AlarmButton.setIcon(new ThemeResource("alarm.jpg"));
		AlarmButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return AlarmButton;
	}

	
	/**
	 * Creates the "Send Referral" Button and adds a ClickListener to generate the
	 * output referral.txt.
	 * 
	 * @return Button
	 */
	public Button createSendReferralButton() {
		SendReferralButton = new Button("Send");
		SendReferralButton.setWidth("100px");
		SendReferralButton.setHeight("75px");
		SendReferralButton.addStyleName("borderless icon-on-top");
		SendReferralButton.setIcon(new ThemeResource("email.jpg"));
		SendReferralButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			
			public void buttonClick(ClickEvent event) {
			// check whether the input data is valid or not. Update the csv
			// file if
			// the input is correct, and show the error message in the
			// infoArea field
			// if the input is incorrect.
			if(checkInputValues()){
				Object patient = patientComboBox.getValue();
				Object referral = referralComboBox.getValue();
				String dateFrom = getDateFrom();
				String dateTo = getDateTo();
				Object referralType = referralTypeGroup.getValue();
				String referralMessage = textReferralMessage.getValue();
				Object clinic = clinicComboBox.getValue();
				Object doctoroffice = doctorofficeComboBox.getValue();
				Object doctor = doctorComboBox.getValue();
				new CSVCreator(patient + ";" + referral + ";" + dateFrom + ";" + dateTo + ";" + referralType + ";" + 
				referralMessage + ";" + clinic + ";" + doctoroffice + ";" + doctor +";\n", "referral.txt");
				Notification notif = new Notification("The referral message has been sent.",
						Notification.Type.TRAY_NOTIFICATION);
	        	notif.setDelayMsec(5000);
	        	notif.setPosition(Position.BOTTOM_RIGHT);
				notif.show(Page.getCurrent());
				} else {
//					Notification notif = new Notification("The referral message was not sent.",
//							Notification.Type.TRAY_NOTIFICATION);
//		        	notif.setDelayMsec(5000);
//		        	notif.setPosition(Position.BOTTOM_RIGHT);
//					notif.show(Page.getCurrent());}
				}}
		});
		return SendReferralButton;
	}
	
	/**
	 * Creates a ComboBox to choose a Patient for the referral.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createPatientComboBox(){
		patientComboBox = new ComboBox();
		patientComboBox.setInvalidAllowed(false);
		patientComboBox.setNullSelectionAllowed(false);
        
        PatientHandler dbh = new PatientHandler("Patient");
        ArrayList<Patient> list = dbh.getAll();
        
        for(int i = 0; i<list.size(); i++){
        	patientComboBox.addItem(list.get(i).toString());
        }
        return patientComboBox;
	}
	
	/**
	 * Creates a ComboBox to choose a referral reason.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createReferralComboBox(){
		referralComboBox = new ComboBox();
		referralComboBox.addItem("Hospital treatment");
		referralComboBox.addItem("Diagnosis confirmation");
		referralComboBox.setInvalidAllowed(false);
		referralComboBox.setNullSelectionAllowed(false);
		return referralComboBox;
	}

	
	/**
	 * Creates a DateField where you can choose a date from a Popupfield.
	 * The selected date should be the current date (today's date) or a date in the future.
	 * It is not possible to select/enter a date in the past.
	 * 
	 * @return PopupDateField
	 */
	private PopupDateField createDateField1(){
		dateField = new PopupDateField("From:");
		dateField.setInputPrompt("Select a date");
		dateField.setDateFormat("dd.MM.yyyy");
		dateField.setRangeStart(new Date());
		dateField.setWidth("150px");
		return dateField;
	}
	
	
	/**
	 * Returns the value of the datefield-PopupDateField as a String in the format
	 * dd.MM.yyyy
	 * 
	 * @return String
	 */
	private String getDateFrom() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String date = formatter.format(this.dateField.getValue());
		return date;
	}
	
	
	/**
	 * Creates a DateField where you can choose a date from a Popupfield.
	 * The selected date should be the current date (today's date) or a date in the future.
	 * It is not possible to select/enter a date in the past.
	 * 
	 * @return PopupDateField
	 */
	private PopupDateField createDateField2(){
		dateField2 = new PopupDateField("To:");
		dateField2.setInputPrompt("Select a date");
		dateField2.setDateFormat("dd.MM.yyyy");
		dateField2.setRangeStart(new Date());
		dateField2.setWidth("150px");
		return dateField2;
	}
	
	
	/**
	 * Returns the value of the datefield2-PopupDateField as a String in the format
	 * dd.MM.yyyy.
	 * 
	 * @return String
	 */
	private String getDateTo() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String date = formatter.format(this.dateField2.getValue());
		return date;
	}
	
	
	/**
	 * Creates two RadioButtons (OptionGroup) where you can select
	 * 1 Button at the moment.
	 * 
	 * @return OptionGroup
	 */
	private OptionGroup createOptionGroup(){
		referralTypeGroup = new OptionGroup();
		referralTypeGroup.addItem("FU");
		referralTypeGroup.addItem("Normal");
		return referralTypeGroup;
	}
	
	
	/**
	 * Creates a TextArea where you can write additional notes for the referral.
	 * 
	 * @return TextArea
	 */
	private TextArea createMessageTextArea(){
		textReferralMessage = new TextArea();
		textReferralMessage.setInputPrompt("Additional Notes");

		return textReferralMessage;
	}
	
	
	/**
	 * Creates a ComboBox to choose a clinic for the referral.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createClinicComboBox(){
		clinicComboBox = new ComboBox();
		clinicComboBox.setInvalidAllowed(false);
		clinicComboBox.setNullSelectionAllowed(false);
		clinicComboBox.setWidth("150px");		
		clinicComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
					doctorofficeComboBox.setEnabled(false);	
				}
		});
		
        ClinicHandler clinicHandler = new ClinicHandler("Clinic");
        ArrayList<Clinic> list = clinicHandler.getAll();
        
        for(int i = 0; i<list.size(); i++){
        	clinicComboBox.addItem(list.get(i).getName().toString());
        }
        
		return clinicComboBox;	
	}
	
	/**
	 * Creates a ComboBox to choose a doctoroffice for the referral.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createDoctorOfficeComboBox() {
		doctorofficeComboBox = new ComboBox();
		doctorofficeComboBox.setInvalidAllowed(false);
		doctorofficeComboBox.setNullSelectionAllowed(false);
		doctorofficeComboBox.setWidth("150px");
		doctorofficeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
					clinicComboBox.setEnabled(false);	
				}
	        });
		 
		
        DoctorHandler doctorHandler = new DoctorHandler("Doctor");
        ArrayList<Doctor> list = doctorHandler.getAll();
        
        for(int i = 0; i<list.size(); i++){
        	doctorofficeComboBox.addItem(list.get(i).getOfficeName().toString());
        }
        
		return doctorofficeComboBox;
	}	

	
	/**
	 * Creates a ComboBox to choose a doctor which is responsible for the referral 
	 * in the external institution.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createDoctorComboBox() {
		doctorComboBox = new ComboBox();
		doctorComboBox.setInvalidAllowed(false);
		doctorComboBox.setNullSelectionAllowed(false);
		
		DoctorHandler doctorHandler = new DoctorHandler("Doctor");
        ArrayList<Doctor> list = doctorHandler.getAll();
        
        for(int i = 0; i<list.size(); i++){
        	doctorComboBox.addItem(list.get(i).toString());
        }
        
		return doctorComboBox;
	}
				

	/**
	 * This private method returns true if and only if all input is correct. It
	 * checks the if all Fields and ComboBoxes are filled out.
	 * 
	 * @return boolean
	 */
	private boolean checkInputValues() {

		// set defaults for the booleans 
		boolean validpatient = false;
		boolean validreferral = false;
		boolean validdateFrom = false;
		boolean validdateTo = false;
		boolean validreferralType = false;
		boolean validreferralMessage = false;
		boolean validclinicdoctoroffice = false;
		boolean validdoctor = false;

		// check whether one Item of the Patient ComboBox is selected. if yes,
			// the boolean valid referral is set true. if not, a notification
			// is shown.
		if (patientComboBox.getValue() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select one patient from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validpatient = true;
		
		// check whether one Item of the Referral ComboBox is selected. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (referralComboBox.getValue() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select one reason for referral from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validreferral = true;

		
		// check whether the Date Fields From and To are filled 
				// and if the  Date From is before the Date To.
				// if yes, the boolean valid referral is set true. 
				// if not, a notification is shown.
		if (dateField.getValue() == null && dateField2.getValue() == null) {
			Notification notif = new Notification("Input failure",
					"Please select a date",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());}
		else if (!dateField.getValue().before(dateField2.getValue())) {
			Notification notif = new Notification("Input failure",
					"Date From has to be before Date To",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());}	
		 else 
			validdateFrom = true;
			validdateTo = true;

			
		// check whether one Button of the Referral Type is selected. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if(referralTypeGroup.getValue() == null) {
			Notification notif = new Notification("Input failure", 
					"Please select one referral type.",						
					Notification.Type.WARNING_MESSAGE);
	        notif.setDelayMsec(5000);
	       	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else 
			validreferralType = true;
			
		
		// check whether the Referral Message field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (textReferralMessage.getValue().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validreferralMessage = true;

			
		// check whether one Item of the Doctor Office ComboBox is selected. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (doctorofficeComboBox.getValue() == null && clinicComboBox.getValue() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select a Clinic or a Doctor Office from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
	       	notif.setDelayMsec(5000);
	       	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validclinicdoctoroffice = true;
		
			
		// check whether one Item of the Doctor ComboBox is selected. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (doctorComboBox.getValue() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select a Doctor from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(5000);
		   	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validdoctor = true;

		
		// returns true, if and only if both booleans are true.
		return  validpatient && validreferral && validdateFrom && validdateTo && validreferralType && validreferralMessage
				&& validclinicdoctoroffice && validdoctor;
	}
}
