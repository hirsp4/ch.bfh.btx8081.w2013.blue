package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import java.text.SimpleDateFormat;
import java.util.Date;


import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.CSVCreator;

import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author Saskia Basler
 * 
 *         DenialPanel v1.0 08.12.2013/v2.0 18.12.2013
 * 
 *         Creates the panel for our DenialView with the description when does
 *         the patient deny a drug and which drug. Button "Add to medical
 *         report" writes the information from the field when and what as a
 *         String in the "denial.txt" file out.
 */

public class DenialPanel extends BorderPanel {

	private static final long serialVersionUID = 1L;
	private PopupDateField dateField;
	private ComboBox medicineComboBox;
	private Panel panel;

	public DenialPanel() {
		this.panel = new Panel(" Denial");
		this.panel.setIcon(new ThemeResource("drug-icon.png"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();

		Label label = new Label("Date of denial:");
		label.addStyleName("h3");
		Label label2 = new Label("Name of denied medicine:");
		label2.addStyleName("h3");

		FormLayout layout = new FormLayout();
		layout.setStyleName("ChameleonTheme");
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.setSpacing(true);

		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setWidth("340px");
		hlayout.addComponent(createDateField());

		HorizontalLayout hlayout2 = new HorizontalLayout();
		hlayout2.setWidth("340px");
		hlayout2.addComponent(createMedicineComboBox());

		HorizontalLayout hlayout3 = new HorizontalLayout();
		hlayout3.setWidth("340px");
		hlayout3.addComponent(createIndexButton());
		hlayout3.addComponent(createAddtoMedicalReportButton());
		hlayout3.addComponent(createAlertButton());

		layout.addComponent(label);
		layout.addComponent(hlayout);
		layout.addComponent(label2);
		layout.addComponent(hlayout2);
		layout.addComponent(hlayout3);
		this.panel.setContent(layout);
		setContent(panel);
	}

	/**
	 * Creates a PopupDatefield for the dateField. The doctor can choose a date
	 * or enter one (format dd.MM.yyy). The selected/entered Date should be the
	 * current date (today's date) or a date in the past. It is not possible to
	 * select/enter a Date in the future.
	 * 
	 * @return PopupDateField
	 */
	private PopupDateField createDateField() {
		this.dateField = new PopupDateField();
		this.dateField.setInputPrompt("Select a date");
		this.dateField.setDateFormat("dd.MM.yyyy");
		this.dateField.setRangeEnd(new Date());
		this.dateField.setWidth("150px");
		return dateField;
	}

	/**
	 * Creates a ComboBox for the MedicineComboBox.
	 * 
	 * @return ComboBox
	 */
	private ComboBox createMedicineComboBox() {
		medicineComboBox = new ComboBox();
			
		for (String list: MedicineObjects.Medicines)
		{
			medicineComboBox.addItem(list);
		}
		
		medicineComboBox.setInvalidAllowed(false);
		medicineComboBox.setNullSelectionAllowed(false);
		return medicineComboBox;
	}

	/**
	 * Returns the value of the whenField-PopupDateField as a String in the
	 * format dd.MM.yyyy.
	 * 
	 * @return String
	 */
	private String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String date = formatter.format(this.dateField.getValue());
		return date;
	}

	/**
	 * Returns the value of the medicineComboBox.
	 * 
	 * @return Object
	 */
	private Object getMedicine() {
		Object medicine = medicineComboBox.getValue();
		return medicine;
	}

	/**
	 * Creates the "Index" Button to go back to the Index-View.
	 * 
	 * @return Button
	 */
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

	/**
	 * Creates the "Alert" Button to go to the Alert-View.
	 * 
	 * @return Button
	 */
	public Button createAlertButton() {
		Button btn = new Button("Alert");
		btn.setWidth("100px");
		btn.setHeight("75px");
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("alarm.jpg"));
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return btn;
	}

	/**
	 * Creates the "Add to medical report" Button and adds a ClickListener to
	 * generate the output to the denial.txt file if the inputs are valid
	 * (checkInputValues()). To generate the output the CSVCreator class is
	 * used.
	 * 
	 * @return Button
	 */

	public Button createAddtoMedicalReportButton() {
		Button btn = new Button("Add");
		btn.setWidth("100px");
		btn.setHeight("75px");
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("add-icon.png"));
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				// check whether the input data is valid or not.
				// Update the csv file, if the input is correct,
				// and show the error message, if the input is incorrect.
				if (checkInputValues()) {
					String date = getDate();
					Object medicine = getMedicine();
					new CSVCreator(date + ";" + medicine + ";\n", "denial.txt");
					Notification notif = new Notification(
							"The database update was successful.",
							Notification.Type.TRAY_NOTIFICATION);
					notif.setDelayMsec(1000);
					notif.setPosition(Position.BOTTOM_RIGHT);
					notif.show(Page.getCurrent());
				} else {
//					Notification notif = new Notification(
//							"The database update failed.",
//							Notification.Type.TRAY_NOTIFICATION);
//					notif.setDelayMsec(1000);
//					notif.setPosition(Position.BOTTOM_RIGHT);
//					notif.show(Page.getCurrent());
				}
			}
		});
		return btn;
	}

	/**
	 * This private method returns true if and only if all input is correct. It
	 * checks the dateField and the medicineComboBox, if they are filled.
	 * 
	 * @return boolean
	 */
	private boolean checkInputValues() {

		// set defaults for the booleans
		boolean validDate = false;
		boolean validMedicine = false;

		// check whether one Item of the dateField is selected. if yes,
		// the boolean validDate is set true. if not, a notification
		// is shown.
		if (dateField.getValue() == null) {
			Notification notif = new Notification("Input failure",
					"Please select a date.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(1000);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.show(Page.getCurrent());
		} else
			validDate = true;

		// check whether one Item of the medicineComboBox is selected. if yes,
		// the boolean validMedicine is set true. if not, a notification
		// is shown.
		if (medicineComboBox.getValue() == null) {
			Notification notif = new Notification("Input failure",
					"Please select a medicine.",
					Notification.Type.WARNING_MESSAGE);
			notif.setDelayMsec(1000);
			notif.setPosition(Position.MIDDLE_LEFT);
			notif.show(Page.getCurrent());
		} else
			validMedicine = true;
	
		// returns true, if and only if both booleans are true.
		return validDate && validMedicine;
	}

}
