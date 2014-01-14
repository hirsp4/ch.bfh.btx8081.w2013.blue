package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Saskia Basler
 * @version 1.0 08.12.2013
 * 
 *         DenialView
 * 
 *         Creates the view from the DenialPanel.
 *         In this view the doctor can add informations about the denial.
 * 
 */
public class DenialView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;	
	public DenialView(){
		setMargin(true);
		DenialPanel denialPanel = new DenialPanel();
		addComponent(denialPanel); // create the denial panel and
										 // add it to the denial view.
		this.setComponentAlignment(denialPanel, Alignment.TOP_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
