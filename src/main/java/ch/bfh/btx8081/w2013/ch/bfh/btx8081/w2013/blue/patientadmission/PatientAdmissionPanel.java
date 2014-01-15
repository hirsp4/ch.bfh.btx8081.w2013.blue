package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patientadmission;

import java.util.ArrayList;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.database.PatientHandler;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.person.Patient;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.ReferralView;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
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
 * @author René Vielgut
 * 
 *         Shows a complete list of all patients stored in the database. After
 *         choosing a specific patient, information on it will be shown below in
 *         the corresponding textfields
 */

public class PatientAdmissionPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;

	private PatientHandler patienthandler;
	private ListSelect patientAboutList;
	private TextField PatientSurName;
	private TextField PatientFirstName;
	private TextField PatientStreet;
	private TextField PatientStreetNB;
	private TextField PatientZIPCode;
	private TextField PatientCity;
	private TextField PatientGender;
	private TextField PatientBirthdate;
	private TextField PatientPID;
	private TextField PatientState;
	private Label lab1;
	private Panel panel;



	public PatientAdmissionPanel() {

		this.patienthandler = new PatientHandler("Patient");

		this.panel = new Panel(" Patient");
		this.panel.setIcon(new ThemeResource("patient-klein.png"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();

		this.lab1 = new Label("Select the patient:");
		this.lab1.addStyleName("h3");

		FormLayout flayout = new FormLayout();
		GridLayout glayout = new GridLayout(2,1);
		
		flayout.addComponent(lab1);	
		glayout.addComponent(this.createListPatientAboutList());
		glayout.addComponent(createshowButton());
		glayout.setWidth("320px");
		
		GridLayout gridlayout2 = new GridLayout(2,8);
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
		flayout.addComponent(glayout);
		flayout.addComponent(gridlayout2);
	
		HorizontalLayout hlayoutButtons = new HorizontalLayout();

		hlayoutButtons.setWidth("340px");
		hlayoutButtons.addComponent(this.createIndexButton());
		hlayoutButtons.addComponent(this.createAlertButton());
		hlayoutButtons.addComponent(this.createDenialButton());
		hlayoutButtons.addComponent(this.createReferralButton());
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

	private TextField createGenderField() {
		PatientGender = new TextField("Gender:");
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
	
	private TextField createStateField() {
		PatientState = new TextField("State:");
		return PatientState;
	}
	
	
	private ListSelect createListPatientAboutList() {
		this.patientAboutList = new ListSelect();
		this.patientAboutList.setInvalidAllowed(false);
		this.patientAboutList.setNullSelectionAllowed(false);

		ArrayList<Patient> list = this.patienthandler.getAll();

		for (int i = 0; i < list.size(); i++) {
			this.patientAboutList.addItem(list.get(i).toString());

		}

		return patientAboutList;
	}
	
	public Button createshowButton() {
		Button ShowButton = new Button("Show");
		
		ShowButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				
				try {
					Patient patient = patienthandler.getPatient(
							PatientHandler.PID,
							((String) patientAboutList.getValue())
									.split(" ")[2]);
					PatientSurName.setValue(patient.getName());
					PatientFirstName.setValue(patient.getForeName());
					PatientStreet.setValue(patient.getAddress().getStreet());
					PatientStreetNB.setValue(Integer.toString(patient.getAddress().getStreetNr()));
					PatientZIPCode.setValue(Integer.toString(patient
							.getAddress().getZip()));
					PatientCity.setValue(patient.getAddress().getCity());
					PatientGender.setValue(Character.toString(patient.getGender()));
					PatientBirthdate.setValue(patient.getBirthdate());
					PatientPID.setValue(Integer.toString(patient.getPid()));
					PatientState.setValue(patient.getState().getStateDescription());
				} catch (Exception e) {

				}
			}
		});
		return ShowButton;
	}

	public Button createDenialButton() {
		Button DenialButton = new Button("Denial");
		DenialButton.setWidth("100px");
		DenialButton.setHeight("75px");
		DenialButton.addStyleName("borderless icon-on-top");
		DenialButton.setIcon(new ThemeResource("drug-icon-mittel.png"));
		DenialButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setDenialView(new DenialView());
			}
		});
		return DenialButton;
	}

	public Button createAlertButton() {
		Button AlertButton = new Button("Alert");
		AlertButton.setWidth("100px");
		AlertButton.setHeight("75px");
		AlertButton.addStyleName("borderless icon-on-top");
		AlertButton.setIcon(new ThemeResource("alarm.jpg"));
		AlertButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());

			}
		});

		return AlertButton;

	}

	public Button createReferralButton() {
		Button ReferralButton = new Button("Referral");
		ReferralButton.setWidth("100px");
		ReferralButton.setHeight("75px");
		ReferralButton.addStyleName("borderless icon-on-top");
		ReferralButton.setIcon(new ThemeResource(
				"inscripcion_manual-mittel.png"));
		ReferralButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setReferralView(new ReferralView());

			}
		});

		return ReferralButton;

	}
	
	public Button createIndexButton() {
		Button btn = new Button("Index");
		btn.setWidth("100px");
		btn.setHeight("75px");
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("index.png"));
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn;
	}
}
