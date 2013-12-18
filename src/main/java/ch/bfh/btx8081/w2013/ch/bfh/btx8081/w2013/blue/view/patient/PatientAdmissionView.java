package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

public class PatientAdmissionView extends VerticalLayout implements View {
		private static final long serialVersionUID = 1L;
		
		public PatientAdmissionView() {
			setMargin(true);
			addComponent(new PatientAdmissionPanel()); // creates the necessary forms and add it to the PatientAdmissionView
												
		}
		
		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub

		}


}
