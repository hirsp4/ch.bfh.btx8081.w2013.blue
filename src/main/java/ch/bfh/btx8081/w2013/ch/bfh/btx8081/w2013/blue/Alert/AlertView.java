package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.Alert;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.countdown.Counter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

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
	private final Slider slider;
	private Counter counter;
	
	/**
	 * Add the different Components the View need, and make a Counterinstance
	 */
	public AlertView(){
		this.counter = new Counter();
		VerticalLayout layout = new VerticalLayout();
		setSizeFull();
		this.setMargin(true);
		this.slider = createSlider();
		layout.addComponent(this.slider);
		layout.addComponent(createButton());
		addComponent(layout);
	}
	
	/**
	 * Make the Slide and sets it always back to 0 if yu didnt push it to the max value.
	 * And if and only if the value is max, the counter get started and an Notification is shown
	 * @return the Slider
	 */
	private Slider createSlider(){
		final Slider s = new Slider();
		s.setImmediate(true);
		s.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("deprecation")
			@Override
			public void valueChange(ValueChangeEvent event) {
		        double value = (Double) s.getValue();
		        if(value<s.getMax()){
		        	s.setValue(0.0);
		        }else{
		        	Notification.show("Alert will be released in "
		        			+ "5 seconds" + "\n Press Button to stop", 
		        			Notification.TYPE_WARNING_MESSAGE);
		        	counter.start();
		        }
		    }
		});
		
		s.setIcon(new ThemeResource("alert.png"));
		s.setWidth("260");
		return s;
	}
	
	/**
	 * make the Stopbutton to cancel the Counter. 
	 * @return the Stop-Alert-Button
	 */
	private Button createButton(){
		Button btn = new Button("Stop Alert");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				counter.interrupt();
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return btn;
	}
	


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
