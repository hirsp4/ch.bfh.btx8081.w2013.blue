package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Patrick Hirschi
 *
 *
 * PatientView v1.0 29.11.2013
 *
 * The general view if the user has to add informations about a patient.
 * 
 */

public class PatientView extends VerticalLayout implements View{
	

	public PatientView(){
		setSizeFull();
		this.setMargin(true);
		addComponent(new TextFieldPanel()); // create the textfield panel and add it to the
											// patient view.
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}

