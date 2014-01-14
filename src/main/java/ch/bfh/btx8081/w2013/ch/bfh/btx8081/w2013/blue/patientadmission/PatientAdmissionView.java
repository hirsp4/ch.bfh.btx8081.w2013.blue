package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patientadmission;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author René Vielgut
 * 
 * 		PatientView 1.0 09.12.2013
 * 
 * 		This will be the general view to add information about patients.
 * 
 * 
 */
public class PatientAdmissionView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;	
	public PatientAdmissionView(){
		setMargin(true);
		PatientAdmissionPanel patientAdmissionPanel = new PatientAdmissionPanel();
		addComponent(patientAdmissionPanel); // create the denial panel and
										 // add it to the denial view.
		this.setComponentAlignment(patientAdmissionPanel, Alignment.TOP_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
