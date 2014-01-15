package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.addpatient;

import java.io.IOException;
import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PatientHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patientadmission.PatientAdmissionView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Address;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Dangerous;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.Normal;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.statedesign.State;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.ReferralView;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

/**
 * 
 * @author Rafael Kapp
 * 
 *         Shows a complete list of all patients stored in the database. After
 *         choosing a specific patient, information on it will be shown below in
 *         the corresponding textfields
 */

public class AddPatientPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;

	private PatientHandler patienthandler;
	private TextField PatientSurName;
	private TextField PatientFirstName;
	private TextField PatientStreet;
	private TextField PatientStreetNB;
	private TextField PatientZIPCode;
	private TextField PatientCity;
	private ComboBox PatientGender;
	private TextField PatientBirthdate;
	private TextField PatientPID;
	private ComboBox PatientState;
	private Label lab1;
	private Panel panel;

	private Button pavButton;

	public AddPatientPanel() {

		this.patienthandler = new PatientHandler("Patient");

		this.panel = new Panel(" Add new patient");
		this.panel.setIcon(new ThemeResource("PatientView-klein.png"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();

		this.lab1 = new Label("Create the patient:");
		this.lab1.addStyleName("h3");

		FormLayout flayout = new FormLayout();

		flayout.addComponent(lab1);

		GridLayout gridlayout2 = new GridLayout(2, 8);
		gridlayout2.setHeight("250px");
		gridlayout2.setWidth("360px");

		gridlayout2.addComponent(this.createFirstNameField());
		gridlayout2.addComponent(this.createSurNameField());
		gridlayout2.addComponent(this.createStreetField());
		gridlayout2.addComponent(this.createStreetNoField());
		gridlayout2.addComponent(this.createZIPField());
		gridlayout2.addComponent(this.createCityField());
		gridlayout2.addComponent(this.createGenderField());
		gridlayout2.addComponent(this.createBDateField());
		gridlayout2.addComponent(this.createPIDField());
		gridlayout2.addComponent(this.createStateField());
		flayout.addComponent(gridlayout2);

		HorizontalLayout hlayoutButtons = new HorizontalLayout();

		hlayoutButtons.setWidth("340px");
		hlayoutButtons.addComponent(this.createIndexButton());
		hlayoutButtons.addComponent(this.createPavButton());
		hlayoutButtons.addComponent(this.createaddPatientButton());
		hlayoutButtons.addComponent(this.createAlertButton());
		flayout.addComponent(hlayoutButtons);

		flayout.setSizeUndefined();

		this.panel.setContent(flayout);
		setContent(panel);

	}

	private TextField createZIPField() {
		PatientZIPCode = new TextField("ZIP:");
		return PatientZIPCode;
	}

	private TextField createCityField() {
		PatientCity = new TextField("City:");
		return PatientCity;
	}

	private TextField createStreetNoField() {
		PatientStreetNB = new TextField("Street No:");
		return PatientStreetNB;
	}

	private TextField createStreetField() {
		PatientStreet = new TextField("Street:");
		return PatientStreet;
	}

	private ComboBox createGenderField() {
		PatientGender = new ComboBox("Gender:");
		PatientGender.addItem("Female");
		PatientGender.addItem("Male");
		return PatientGender;
	}

	private TextField createBDateField() {
		PatientBirthdate = new TextField("Birthdate:");
		return PatientBirthdate;
	}

	private TextField createSurNameField() {
		PatientSurName = new TextField("Name:");
		return PatientSurName;
	}

	private TextField createFirstNameField() {
		PatientFirstName = new TextField("Forename:");
		return PatientFirstName;
	}

	private TextField createPIDField() {
		PatientPID = new TextField("PID:");
		return PatientPID;
	}

	private ComboBox createStateField() {
		PatientState = new ComboBox("State:");
		PatientState.addItem("DANGEROUS");
		PatientState.addItem("NORMAL");
		return PatientState;
	}

	public Button createaddPatientButton() {
		Button addPatientBtn = new Button("Add");
		addPatientBtn.setWidth("100px");
		addPatientBtn.setHeight("75px");
		addPatientBtn.addStyleName("borderless icon-on-top");
		addPatientBtn.setIcon(new ThemeResource("add.png"));
		addPatientBtn.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				Patient p = null;
				State s = null;
				if (PatientState.getValue().equals("DANGEROUS")) {
					s = new Dangerous(p);
				} else {
					s = new Normal(p);
				}

				p = new Patient(Integer.parseInt(PatientPID.getValue()),
						PatientSurName.getValue(), PatientFirstName.getValue(),
						new Address(PatientStreet.getValue(), Integer
								.parseInt(PatientStreetNB.getValue()), Integer
								.parseInt(PatientZIPCode.getValue()),
								PatientCity.getValue()), ((String)PatientGender
								.getValue()).charAt(0), PatientBirthdate
								.getValue(), s);

				try {
					patienthandler.addPatient(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("clicked");

				MyVaadinUI.setPatientAdmissionView(new PatientAdmissionView());
			}
		});
		return addPatientBtn;
	}

	private Button createPavButton(){
		pavButton = new Button("Back");
		pavButton.setWidth("100px");
		pavButton.setHeight("75px");
		pavButton.addStyleName("borderless icon-on-top");
		pavButton.setIcon(new ThemeResource("back.png"));
		pavButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setPatientAdmissionView(new PatientAdmissionView());
			}
		});
		return pavButton;
	}

	public Button createAlertButton() {
		Button AlertButton = new Button("Alert");
		AlertButton.setWidth("100px");
		AlertButton.setHeight("75px");
		AlertButton.addStyleName("borderless icon-on-top");
		AlertButton.setIcon(new ThemeResource("AlertView-mittel.png"));
		AlertButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());

			}
		});

		return AlertButton;

	}

	public Button createIndexButton() {
		Button btn = new Button("Home");
		btn.setWidth("100px");
		btn.setHeight("75px");
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("IndexView-mittel.png"));
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn;
	}
	
}
