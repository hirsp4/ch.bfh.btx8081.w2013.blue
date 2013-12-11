package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.denial;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class DenialView extends VerticalLayout implements View{

	/**
	 * 
	 * @author Saskia Basler
	 * 
	 * 
	 *         DenialView v1.0 08.12.2013
	 * 
	 *         Creates the view from the DenialPanel.
	 *         In this view the doctor can add informations about the denial.
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	public DenialView(){
		setMargin(true);
		addComponent(new DenialPanel()); // create the denial panel and
										 // add it to the denial view.
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
