package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;




import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.CSVCreator;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
//import com.vaadin.server.Page;
//import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
//import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

public class DenialPanel extends Panel {

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
	private TextField whenField;
	private TextField whatField;
	private TextArea infoArea;
	private Label denial;


	public DenialPanel() {

		this.addStyleName(ChameleonTheme.PANEL_LIGHT);
		this.setWidth("450px");
		this.setHeight("450px");
		this.denial = new Label("Denial");
		this.whenField = new TextField("When:");
		this.whenField.setInputPrompt("dd.mm.yyyy");
		this.whenField.setStyleName(ChameleonTheme.TEXTFIELD_BIG);
		this.whatField = new TextField("What:");
		this.whatField.setInputPrompt("dd.mm.yyyy");
		this.whatField.setStyleName(ChameleonTheme.TEXTFIELD_BIG);
		this.infoArea = new TextArea();

		FormLayout layout = new FormLayout();
		HorizontalLayout hlayout = new HorizontalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.setSpacing(true);
		hlayout.setWidth("350px");
		hlayout.addComponent(createAddtoMedicalReportButton());
		layout.addComponent(denial);
		layout.addComponent(this.whenField);
		layout.addComponent(this.whatField);
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
	private String getwhen() {
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
					String when = getwhen();
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
				if (!getwhen().matches("\\d\\d\\p{Punct}\\d\\d\\p{Punct}\\d\\d\\d\\d")) {
					Notification notif = new Notification("Input failure",
							"Please enter a valid date (integer, format: dd.mm.yyyy)",
							Notification.Type.WARNING_MESSAGE);
		        	notif.setDelayMsec(5000);
		        	notif.setPosition(Position.BOTTOM_RIGHT);
					notif.show(Page.getCurrent());
					this.whenField.setValue("");
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
