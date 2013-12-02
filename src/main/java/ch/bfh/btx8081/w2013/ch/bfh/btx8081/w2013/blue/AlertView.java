package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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
	 */
	private static final long serialVersionUID = 1L;	
	
	private MyPanel myPanel;

	public AlertView(){
		setSizeFull();
		this.setMargin(true);
		myPanel = new MyPanel();
		addComponent(myPanel);
	}
	
	public void update(){
		myPanel.update();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
