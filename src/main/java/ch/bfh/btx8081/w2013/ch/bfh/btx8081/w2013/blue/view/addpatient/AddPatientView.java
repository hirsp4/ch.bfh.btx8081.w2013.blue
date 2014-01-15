package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.addpatient;

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
public class AddPatientView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;	
	public AddPatientView(){
		setMargin(true);
		AddPatientPanel addPatientPanel = new AddPatientPanel();
		addComponent(addPatientPanel); // create the Patient panel and
										 // add it to the patient admission view.
		this.setComponentAlignment(addPatientPanel, Alignment.TOP_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
