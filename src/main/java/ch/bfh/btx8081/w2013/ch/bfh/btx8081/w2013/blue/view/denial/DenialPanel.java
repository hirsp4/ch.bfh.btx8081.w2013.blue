package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import java.util.Date;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;




import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.CSVCreator;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class DenialPanel extends BorderPanel {

	/**
	 * 
	 * @author Saskia Basler
	 * 
	 * 
	 *         DenialPanel v1.0 08.12.2013
	 * 
	 *         Creates the panel for our DenialView with the description 
	 *         when does the patient deny a drug and which drug.
	 *         Button add to medical report writes the information from the
	 *         field when and what as a String in a txt file out.  
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PopupDateField whenField;
	private TextField whatField;
	private TextArea infoArea;


	public DenialPanel() {

		this.whenField = new PopupDateField("Date of denial:");
		this.whenField.setInputPrompt("Select a date");
		this.whenField.setDateFormat("dd.MM.yyyy");
		this.whenField.setWidth("150px");
		this.whatField = new TextField("Name of denied medicine:");
		this.whatField.setWidth("150px");
		this.infoArea = new TextArea();

		FormLayout layout = new FormLayout();
		HorizontalLayout hlayout = new HorizontalLayout();
		HorizontalLayout hlayout2 = new HorizontalLayout();
		HorizontalLayout hlayout3 = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.setSpacing(true);
		hlayout.setWidth("350px");
		hlayout.addComponent(createAddtoMedicalReportButton());
		hlayout2.setWidth("150px");
		hlayout2.addComponent(whenField);
		hlayout3.setWidth("150px");
		hlayout3.addComponent(whatField);
		hlayout3.addComponent(whatField);
		layout.addComponent(hlayout2);
		layout.addComponent(hlayout3);
		layout.addComponent(this.infoArea);
		layout.addComponent(hlayout);
		layout.addComponent(createBackButton());
		setContent(layout);

	}

	/**
	 * makes the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
	private Button createBackButton() {
		Button btn = new Button("Index");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn;
	}

	/**
	 * Returns the value of the When-TextField.
	 * 
	 * @return String
	 */
	private Date getwhen() {
		return this.whenField.getValue();
	}
	
	/**
	 * Returns the value of the What-TextField.
	 * 
	 * @return String
	 */
	private String getwhat() {
		return this.whatField.getValue();
	}
	
	/**
	 * Creates the "Add to medical report" Button and adds a ClickListener to
	 * generate the output.
	 * 
	 * @return Button
	 */
	public Button createAddtoMedicalReportButton() {
		Button addtoMedicalReportButton = new Button("Add to medical report");
		addtoMedicalReportButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// check whether the input data is valid or not. Update the csv
				// file if
				// the input is correct, and show the error message in the
				// infoArea field
				// if the input is incorrect.
				if (checkInputValues()) {
					Date when = getwhen();
					String what = getwhat();
					new CSVCreator(when + ";" + what + ";\n", "denial.txt");
					infoArea.setValue("the database update was successful.");
				} else {
					infoArea.setValue("the database update failed.");
				}
			}
		});
		return addtoMedicalReportButton;
	}
	
	/**
	 * This private method returns true if and only if all input is correct. It
	 * checks the When and the What Field, if they are filled.
	 * 
	 * @return boolean
	 */
	private boolean checkInputValues() {

		// set defaults for the booleans validPID, validStrings
		boolean validwhen = false;
		boolean validwhat = false;

		// check whether the when field is filled like the format dd.mm.yyyy. if yes,
				// the boolean validwhen is set true. if not, a notification
				// is shown and the field will be set empty.
				if (getwhen() == null) {
					Notification notif = new Notification("Input failure",
							"Please select a date",
							Notification.Type.WARNING_MESSAGE);
		        	notif.setDelayMsec(5000);
		        	notif.setPosition(Position.BOTTOM_RIGHT);
					notif.show(Page.getCurrent());		
				} else
					validwhen = true;

		if (getwhat().isEmpty()) {
			Notification notif = new Notification("Input failure",
					"Text field can't be empty.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(5000);
			notif.setPosition(Position.MIDDLE_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validwhat = true;

		// returns true, if and only if both booleans are true.
		return validwhen && validwhat;
	}

}
