package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;


/**
 * 
 * @author Rafael Kapp
 *
 *
 *AlertView v1.0 29.11.2013
 *
 *This is the mainView  for the Alert thing
 *
 */
public class AlertView extends VerticalLayout implements View{
	
	/**
	 * Default seralVersssonUID cause the warning was annoying!
	 * Slider used to release the Alert
	 */
	private static final long serialVersionUID = 1L;	
	public AlertView(){
		setMargin(true);
		AlertPanel alertPanel = new AlertPanel();
		addComponent(alertPanel);
		this.setComponentAlignment(alertPanel, Alignment.TOP_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
