package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral;



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
 *         TextFieldPanel v1.0 07.12.2013
 * 
 *         Creates the panel for our ReferralView. The panel consists of several
 *         text fields and dropdown to add referral informations, a button to send the referral
 *         to the doctor, which is selected in the dropdown menu, 
 *         which creates a "referral"(.txt - file).
 * 
 */

public class ReferralPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;
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
		this.referralComboBox = new ComboBox();
		this.referralComboBox.addItem("Stationery Therapy");
		this.referralComboBox.addItem("Diagnoses");
		this.dateField = new PopupDateField("From:");
		this.dateField.setInputPrompt("Selcet a date");
		this.dateField.setDateFormat("dd.MM.yyyy");
		this.dateField.setRangeStart(new Date());
		this.dateField.setWidth("150px");
		this.dateField2 = new PopupDateField("To:");
		this.dateField2.setInputPrompt("Select a date");
		this.dateField2.setDateFormat("dd.MM.yyyy");
		this.dateField2.setRangeStart(new Date());
		this.dateField2.setWidth("150px");
		this.referralTypeGroup = new OptionGroup();
		referralTypeGroup.addItem("FU");
		referralTypeGroup.addItem("Normal");
		this.lab1 = new Label("Reason for referral:");
		this.lab2 = new Label("Possible Timeframe:");
		this.lab3 = new Label("Referral type:");
		this.lab4 = new Label("Choose referral Clinic or Doctor Office:");
		this.lab5 = new Label("Choose responsible Doctor:");
		this.textReferralMessage = new TextArea();
		this.textReferralMessage.setInputPrompt("Additional Notes");
		this.infoArea = new TextArea();
		this.clinicComboBox = new ComboBox("Clinic:");
		this.clinicComboBox.addItem("Psychiatrische Klinik Bern");
		this.clinicComboBox.addItem("Psychiatrische Klink Basel");
		this.clinicComboBox.setWidth("150px");
		this.doctorofficeComboBox = new ComboBox("Doctor Office:");
		this.doctorofficeComboBox.addItem("Doctor Office - Dr. Rolf Meyer, Basel");
		this.doctorofficeComboBox.addItem("Doctor Office - Group Office Blue");
		this.doctorofficeComboBox.addItem("Doctor Office - Group Office Red");
		this.doctorofficeComboBox.setWidth("150px");
		this.doctorComboBox = new ComboBox();
		this.doctorComboBox.addItem("Dr. Helen Fischer");
		this.doctorComboBox.addItem("Dr. Markus Vetsch");
		this.doctorComboBox.addItem("Dr. Rolf Meyer");
		
		
		FormLayout content = new FormLayout();
		HorizontalLayout hlayout = new HorizontalLayout();
		HorizontalLayout hlayout2 = new HorizontalLayout();
		HorizontalLayout hlayout3 = new HorizontalLayout();
		content.setSizeUndefined();
		content.setMargin(true);
		content.setSpacing(true);
		content.addComponent(lab1);
		content.addComponent(this.referralComboBox);
		content.addComponent(this.lab2);
		hlayout.setWidth("360px");
		hlayout.addComponent(this.dateField);
		hlayout.addComponent(this.dateField2);
		content.addComponent(hlayout);
		content.addComponent(this.lab3);
		content.addComponent(this.referralTypeGroup);
		content.addComponent(this.textReferralMessage);
		content.addComponent(lab4);
		hlayout2.setWidth("360px");
		hlayout2.addComponent(this.clinicComboBox);
		hlayout2.addComponent(this.doctorofficeComboBox);
		content.addComponent(hlayout2);
		content.addComponent(this.lab5);
		content.addComponent(this.doctorComboBox);
		hlayout3.setWidth("130px");
		hlayout3.addComponent(this.createLinkIndexButton());
		hlayout3.addComponent(this.createSendReferralButton());
		content.addComponent(hlayout3);
		content.addComponent(this.infoArea);
		this.setContent(content);
		
		clinicComboBox.setImmediate(true);
		clinicComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				doctorofficeComboBox.setEnabled(false);
			}
		});
		
		doctorofficeComboBox.setImmediate(true);
		doctorofficeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				clinicComboBox.setEnabled(false);
			}
		});
	}

	
	/**
	 * Creates the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
	public Button createLinkIndexButton() {
		Button btn2 = new Button("Index");
		btn2.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn2;
	}

	/**
	 * Creates the "Send Referral" Button and adds a ClickListener to generate the
	 * output.
	 * 
	 * @return Button
	 */
	public Button createSendReferralButton() {
		Button SendReferralButton = new Button("Send referral");
		SendReferralButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			
			public void buttonClick(ClickEvent event) {
			// check whether the input data is valid or not. Update the csv
			// file if
			// the input is correct, and show the error message in the
			// infoArea field
			// if the input is incorrect.
			if (checkInputValues()) {
				String referral = getReferral();
				Date dateFrom = getDateFrom();
				Date dateTo = getDateTo();
				String referralType = getReferralType();
				String referralMessage = getReferralMessage();
				String clinic = getClinic();
				String doctoroffice = getDoctorOffice(); 
				String doctor = getDoctor();
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
	 * Returns the value of the Referral ComboBox.
	 * 
	 * @return String
	 */
	private String getReferral() {
		return (String) this.referralComboBox.getValue();
	}

	/**
	 * Returns the value of the Date Field From.
	 * 
	 * @return Date
	 */
	private Date getDateFrom() {
		return this.dateField.getValue();
	}
	
	/**
	 * Returns the value of the Date Field To.
	 * 
	 * @return Date
	 */
	private Date getDateTo() {
		return this.dateField2.getValue();
	}
	
	/**
	 * Returns the value of the referralTypeGroup Buttons.
	 * 
	 * @return String
	 */
	private String getReferralType() {
		return (String) this.referralTypeGroup.getValue();
	}
	
	/**
	 * Returns the value of the Text Area Referral Message.
	 * 
	 * @return String
	 */
	private String getReferralMessage() {
		return this.textReferralMessage.getValue();
	}
	
	/**
	 * Returns the value of the Clinic ComboBox.
	 * 
	 * @return String
	 */
	private String getClinic() {
		return (String) this.clinicComboBox.getValue();
	}

	/**
	 * Returns the value of the Doctoroffice ComboBox.
	 * 
	 * @return String
	 */
	private String getDoctorOffice() {
		return (String) this.doctorofficeComboBox.getValue();
	}
	
	/**
	 * Returns the value of the Doctor ComboBox.
	 * 
	 * @return String
	 */
	private String getDoctor() {
		return (String) this.doctorComboBox.getValue();
	}
	
	

	/**
	 * This private method returns true if and only if all input is correct. It
	 * checks the if all Fields are filled out.
	 * 
	 * @return boolean
	 */
	private boolean checkInputValues() {

		// set defaults for the booleans 
		boolean validreferral = false;
		boolean validdateFrom = false;
		boolean validdateTo = false;
		boolean validreferralMessage = false;
		boolean validclinicdoctoroffice = false;
		boolean validdoctor = false;

		
		// check whether the Referral field is filled. if yes,
		// the boolean valid referral is set true. if not, a notification
		// is shown.
		if (getReferral() == null)  {
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
		if (getDateFrom() == null && getDateTo() == null) {
			Notification notif = new Notification("Input failure",
					"Please select a date",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());}
		else if (!getDateFrom().before(getDateTo())) {
			Notification notif = new Notification("Input failure",
					"Date From has to be before Date To",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());}	
		 else {
			validdateFrom = true;
			validdateTo = true;
		}
		
		
		// check whether the Referral Message field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getReferralMessage().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validreferralMessage = true;

			
		// check whether the DoctorOffice field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getDoctorOffice() == null && getClinic() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select a Clinic or a Doctor Office from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
	       	notif.setDelayMsec(5000);
	       	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validclinicdoctoroffice = true;
		
			
		// check whether the Doctor field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getDoctor() == null)  {
			Notification notif = new Notification("Input failure", 
					"Select a Doctor from the dropdown menu.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(5000);
		   	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validdoctor = true;

		// returns true, if and only if both booleans are true.
		return validreferral && validdateFrom && validdateTo && validreferralMessage
				&& validclinicdoctoroffice && validdoctor;
	}
}

