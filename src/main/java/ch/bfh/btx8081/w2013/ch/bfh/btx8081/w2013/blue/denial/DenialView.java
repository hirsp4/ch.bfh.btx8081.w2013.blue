package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.denial;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class DenialView extends VerticalLayout implements View{

	/**
	 * Default seralVersssonUID cause the warning was annoying!
	 * Slider used to release the Alert
	 */
	private static final long serialVersionUID = 1L;	
	public DenialView(){
		setMargin(true);
		
		addComponent(new DenialPanel());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
