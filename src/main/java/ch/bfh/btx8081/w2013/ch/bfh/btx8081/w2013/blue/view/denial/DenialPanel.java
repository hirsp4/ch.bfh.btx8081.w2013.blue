package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import java.text.SimpleDateFormat;
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
	 *         DenialPanel v1.0 08.12.2013/v2.0 18.12.2013
	 * 
	 *         Creates the panel for our DenialView with the description when
	 *         does the patient deny a drug and which drug. Button "Add to
	 *         medical report" writes the information from the field when and
	 *         what as a String in the "denial.txt" file out.
	 */
	private static final long serialVersionUID = 1L;
	private PopupDateField whenField;
	private TextField whatField;
	private TextArea infoArea;

	public DenialPanel() {
		FormLayout layout = new FormLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.setSpacing(true);

		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setWidth("150px");
		hlayout.addComponent(createWhenField());

		HorizontalLayout hlayout2 = new HorizontalLayout();
		hlayout2.setWidth("150px");
		hlayout2.addComponent(createWhatField());

		layout.addComponent(hlayout);
		layout.addComponent(hlayout2);
		layout.addComponent(createAddtoMedicalReportButton());
		layout.addComponent(createBackButton());
		layout.addComponent(createInfoArea());
		setContent(layout);
	}

	/**
	 * Creates a PopupDatefield for the whenField. 
	 * The doctor can choose a date or enter one (format dd.MM.yyy). 
	 * The selected/entered Date should be the current date (today's date) or a date in the past. 
	 * It is not possible to select/enter a Date in the future.
	 * 
	 * @return PopupDateField
	 */
	private PopupDateField createWhenField() {
		this.whenField = new PopupDateField("Date of denial:");
		this.whenField.setInputPrompt("Select a date");
		this.whenField.setDateFormat("dd.MM.yyyy");
		this.whenField.setRangeEnd(new Date());
		this.whenField.setWidth("150px");
		return whenField;
	}

	/**
	 * Creates a TextField for the whatField.
	 * 
	 * @return TextField 
	 */
	private TextField createWhatField() {
		this.whatField = new TextField("Name of denied medicine:");
		this.whatField.setWidth("150px");
		return whatField;
	}

	/**
	 * Creates a TextArea for the information if the database is updated
	 * successfully or not.
	 * 
	 * @return TextArea
	 */
	private TextArea createInfoArea() {
		this.infoArea = new TextArea();
		return infoArea;
	}

	/**
	 * Returns the value of the whenField-PopupDateField as a String in the format
	 * dd.MM.yyyy.
	 * 
	 * @return String
	 */
	private String getwhen() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String date = formatter.format(this.whenField.getValue());
		return date;
	}

	/**
	 * Creates the "Index" Button to go back to the Index-View.
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
	 * Creates the "Add to medical report" Button and adds a ClickListener to
	 * generate the output to the denial.txt file if the inputs are valid (checkInputValues()).
	 * To generate the output the CSVCreator class is used.
	 *
	 * @return Button
	 */
	public Button createAddtoMedicalReportButton() {
		Button addtoMedicalReportButton = new Button("Add to medical report");
		addtoMedicalReportButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// check whether the input data is valid or not. Update the CSV
				// file (denial.txt) if the input is correct, and show the error message in
				// the infoArea field when the input is incorrect.
				if (checkInputValues()) {
					String when = getwhen();
					String what = whatField.getValue();
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
	 * checks the when and the what field, if they are filled.
	 * 
	 * @return boolean
	 */
	private boolean checkInputValues() {

		// set defaults for the booleans validwhen, validwhat
		boolean validwhen = false;
		boolean validwhat = false;

		// check whether the when field is filled like the format dd.mm.yyyy 
		// if yes, the boolean validwhen is set true. If not, a notification
		// is shown and the field will be set empty.
		if (getwhen() == null) {
			Notification notif = new Notification("Input failure",
					"Please select a date", Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(5000);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validwhen = true;

		if (whatField.getValue().isEmpty()) {
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
