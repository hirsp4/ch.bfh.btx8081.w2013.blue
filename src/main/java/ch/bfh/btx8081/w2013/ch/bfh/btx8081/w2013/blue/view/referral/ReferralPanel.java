package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral;



import java.text.SimpleDateFormat;
import java.util.Date;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author Maja Kelterborn
 * 
 * 
 *         ReferralPanel v2.0  16.12.2013
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
	private ComboBox referralComboBox;
	private OptionGroup referralTypeGroup;
	private PopupDateField dateField;
	private PopupDateField dateField2;
	private TextArea textReferralMessage;
	private TextArea infoArea;
	private ComboBox clinicComboBox;
	private ComboBox doctorofficeComboBox;
	private ComboBox doctorComboBox;
	private Label lab1;
	private Label lab2;
	private Label lab3;
	private Label lab4;
	private Label lab5;
	
	public ReferralPanel() {
		this.lab1 = new Label("Reason for referral:");
		this.lab2 = new Label("Possible Timeframe:");
		this.lab3 = new Label("Referral type:");
		this.lab4 = new Label("Choose referral Clinic or Doctor Office:");
		this.lab5 = new Label("Choose responsible Doctor:");
		this.infoArea = new TextArea();
		
		FormLayout content = new FormLayout();
		HorizontalLayout hlayout = new HorizontalLayout();
		HorizontalLayout hlayout2 = new HorizontalLayout();
		HorizontalLayout hlayout3 = new HorizontalLayout();
		content.addComponent(lab1);
		content.addComponent(this.createReferralComboBox());
		content.addComponent(this.lab2);
		hlayout.setWidth("360px");
		hlayout.addComponent(this.createDateField1());
		hlayout.addComponent(this.createDateField2());
		content.addComponent(hlayout);
		content.addComponent(this.lab3);
		content.addComponent(this.createOptionGroup());
		content.addComponent(this.createMessageTextArea());
		content.addComponent(lab4);
		hlayout2.setWidth("360px");
		hlayout2.addComponent(this.createClinicComboBox());
		hlayout2.addComponent(this.createDoctorOfficeComboBox());
		content.addComponent(hlayout2);
		content.addComponent(this.lab5);
		content.addComponent(this.createDoctorComboBox());
		hlayout3.setWidth("130px");
		hlayout3.addComponent(this.createLinkIndexButton());
		hlayout3.addComponent(this.createSendReferralButton());
		content.addComponent(hlayout3);
		content.addComponent(this.infoArea);
		
		content.setSizeUndefined();
		content.setMargin(true);
		content.setSpacing(true);
		this.setContent(content);
	}
	
	
	/**
	 * Creates the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
	public Button createLinkIndexButton() {
		Button IndexButton = new Button("Index");
		IndexButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return IndexButton;
	}

	
	/**
	 * Creates the "Send Referral" Button and adds a ClickListener to generate the
	 * output referral.txt.
	 * 
	 * @return Button
	 */
	public Button createSendReferralButton() {
		SendReferralButton = new Button("Send referral");
		SendReferralButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			
			public void buttonClick(ClickEvent event) {
			// check whether the input data is valid or not. Update the csv
			// file if
			// the input is correct, and show the error message in the
			// infoArea field
			// if the input is incorrect.
			if(checkInputValues()){
				Object referral = referralComboBox.getValue();
				String dateFrom = getDateFrom();
				String dateTo = getDateTo();
				Object referralType = referralTypeGroup.getValue();
				String referralMessage = textReferralMessage.getValue();
				Object clinic = clinicComboBox.getValue();
				Object doctoroffice = doctorofficeComboBox.getValue();
				Object doctor = doctorComboBox.getValue();
				new CSVCreator(referral + ";" + dateFrom + ";" + dateTo + ";" + referralType + ";" + 
				referralMessage + ";" + clinic + ";" + doctoroffice + ";" + doctor +";\n", "referral.txt");
				infoArea.setValue("the referral message has been sent.");
				} else {
					infoArea.setValue("the referral message was not sent.");
				}
				
			}
		});
		return SendReferralButton;
	}
	
	
	/**
	 * Creates a ComboBox to choose a referral reason.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createReferralComboBox(){
		referralComboBox = new ComboBox();
		referralComboBox.addItem("Stationery Therapy");
		referralComboBox.addItem("Diagnoses");
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
		dateField.setInputPrompt("Selecet a date");
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
		dateField2.setInputPrompt("Selecet a date");
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
		clinicComboBox = new ComboBox("Clinic:");
		clinicComboBox.addItem("Psychiatrische Klinik Bern");
		clinicComboBox.addItem("Psychiatrische Klink Basel");
		clinicComboBox.setInvalidAllowed(false);
		clinicComboBox.setNullSelectionAllowed(false);
		clinicComboBox.setWidth("150px");
		clinicComboBox.setImmediate(true);
		clinicComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				doctorofficeComboBox.setEnabled(false);
				}
		});
		return clinicComboBox;	
	}
	
	
	/**
	 * Creates a ComboBox to choose a doctoroffice for the referral.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createDoctorOfficeComboBox() {
		doctorofficeComboBox = new ComboBox("Doctor Office:");
		doctorofficeComboBox.addItem("Doctor Office - Dr. Rolf Meyer, Basel");
		doctorofficeComboBox.addItem("Doctor Office - Group Office Blue");
		doctorofficeComboBox.addItem("Doctor Office - Group Office Red");
		doctorofficeComboBox.setInvalidAllowed(false);
		doctorofficeComboBox.setNullSelectionAllowed(false);
		doctorofficeComboBox.setWidth("150px");
		doctorofficeComboBox.setImmediate(true);
		doctorofficeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				clinicComboBox.setEnabled(false);
				}
		});
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
		doctorComboBox.addItem("Dr. Helen Fischer");
		doctorComboBox.addItem("Dr. Markus Vetsch");
		doctorComboBox.addItem("Dr. Rolf Meyer");
		doctorComboBox.setInvalidAllowed(false);
		doctorComboBox.setNullSelectionAllowed(false);
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
		boolean validreferral = false;
		boolean validdateFrom = false;
		boolean validdateTo = false;
		boolean validreferralType = false;
		boolean validreferralMessage = false;
		boolean validclinicdoctoroffice = false;
		boolean validdoctor = false;

		
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
		return  validreferral && validdateFrom && validdateTo && validreferralType && validreferralMessage
				&& validclinicdoctoroffice && validdoctor;
	}
}
