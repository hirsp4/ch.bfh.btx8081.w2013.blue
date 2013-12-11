package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.referral;



import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ChameleonTheme;

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

public class ReferralPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private ComboBox referralComboBox;
	private OptionGroup referralTypeGroup;
	private TextField dateField;
	private TextField dateField2;
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

		this.addStyleName(ChameleonTheme.PANEL_LIGHT);
		this.setWidth("500px");
		this.setHeight("950px");
		this.referralComboBox = new ComboBox();
		this.referralComboBox.addItem("Stationery Therapy");
		this.referralComboBox.addItem("Diagnoses");
		this.dateField = new TextField("From:");
		this.dateField.setInputPrompt("dd.mm.yyyy");
		this.dateField2 = new TextField("To:");
		this.dateField2.setInputPrompt("dd.mm.yyyy");
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
		this.doctorofficeComboBox = new ComboBox("Doctor Office:");
		this.doctorofficeComboBox.addItem("Doctor Office - Dr. Rolf Meyer, Basel");
		this.doctorofficeComboBox.addItem("Doctor Office - Group Office Blue");
		this.doctorofficeComboBox.addItem("Doctor Office - Group Office Red");
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
		hlayout.setWidth("390px");
		hlayout.addComponent(this.dateField);
		hlayout.addComponent(this.dateField2);
		content.addComponent(hlayout);
		content.addComponent(this.lab3);
		content.addComponent(this.referralTypeGroup);
		content.addComponent(this.textReferralMessage);
		content.addComponent(lab4);
		hlayout2.setWidth("390px");
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
				String dateFrom = getDateFrom();
				String dateTo = getDateTo();
				String referralType = getReferralType();
				String referralMessage = getReferralMessage();
				String clinic = getClinic();
				String doctoroffice = getDoctorOffice(); 
				String doctor = getDoctor();
				new CSVCreator(referral + ";" + dateFrom + ";" + dateTo + ";" + referralType + ";" + 
				referralMessage + ";" + clinic + ";" + doctoroffice + ";" + doctor +";\n");
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
	private String getDateFrom() {
		return this.dateField.getValue();
	}
	
	/**
	 * Returns the value of the Date Field To.
	 * 
	 * @return Date
	 */
	private String getDateTo() {
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
		boolean validclinic = false;
		boolean validdoctoroffice = false;
		boolean validdoctor = false;

		
		// check whether the Referral field is filled. if yes,
		// the boolean valid referral is set true. if not, a notification
		// is shown.
		if (getReferral().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validreferral = true;

		// check whether the Date field From is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (!getDateFrom().matches("\\d\\d\\p{Punct}\\d\\d\\p{Punct}\\d\\d\\d\\d")) {
			Notification notif = new Notification("Input failure",
					"Please enter a valid date (integer, format: dd.mm.yyyy)",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
			this.dateField.setValue("");
		} else
			validdateFrom = true;
		
		// check whether the Date Field To is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (!getDateTo().matches("\\d\\d\\p{Punct}\\d\\d\\p{Punct}\\d\\d\\d\\d")) {
			Notification notif = new Notification("Input failure",
					"Please enter a valid date (integer, format: dd.mm.yyyy)",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
			this.dateField2.setValue("");
		} else
			validdateTo = true;
		
		// check whether the Referral Message field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getReferralMessage().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validreferralMessage = true;

		// check whether the Clinic field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getClinic().isEmpty()) {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
        	notif.setDelayMsec(5000);
        	notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validclinic = true;
			
		// check whether the DoctorOffice field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getDoctorOffice().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
	       	notif.setDelayMsec(5000);
	       	notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validdoctoroffice = true;
			
		// check whether the Doctor field is filled. if yes,
				// the boolean valid referral is set true. if not, a notification
				// is shown.
		if (getDoctor().isEmpty())  {
			Notification notif = new Notification("Input failure", "Text fields can't be empty.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(5000);
		   	notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validdoctor = true;

		// returns true, if and only if both booleans are true.
		return validreferral && validdateFrom && validdateTo && validreferralMessage && validclinic
				&& validdoctoroffice && validdoctor;
	}
}

