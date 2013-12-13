package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.patient;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Patrick Hirschi
 * 
 * 
 *         PatientView v1.0 29.11.2013
 * 
 *         The general view if the user has to add informations about a patient.
 * 
 */

public class PatientView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public PatientView() {
		setMargin(true);
		PatientPanel patientPanel = new PatientPanel();
		addComponent(patientPanel); // create the textfield panel and
											// add it to the patient view.
		this.setComponentAlignment(patientPanel, Alignment.TOP_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
