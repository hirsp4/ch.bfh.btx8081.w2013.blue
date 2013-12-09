package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.alert;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.countdown.Counter;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

public class AlertPanel extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Slider slider;
	private Counter counter;
	
	public AlertPanel(){
		
		this.addStyleName(ChameleonTheme.PANEL_BORDERLESS);
		this.counter = new Counter();
		this.slider = createSlider();
		this.setWidth("290px");
		this.setHeight("450px");

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.addComponent(this.slider);
		layout.addComponent(createButton());
		setContent(layout);

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
		        	Notification notif = new Notification("Alert will be released in "
		        			+ "5 seconds" + "\n Press Button to stop", 
		        			Notification.TYPE_WARNING_MESSAGE);
		        	notif.setDelayMsec(1000);
		        	notif.setPosition(Position.BOTTOM_RIGHT);
		        	notif.show(Page.getCurrent());
		        	counter.start();
		        }
		    }
		});
		
		s.setIcon(new ThemeResource("alert.jpg"));
		s.setWidth("260");
		return s;
	}
	
	/**
	 * make the Stopbutton to cancel the Counter. 
	 * @return the Stop-Alert-Button
	 */
	private Button createButton(){
		Button btn = new Button("Stop Alert");
		btn.setStyleName(ChameleonTheme.BUTTON_BIG);
		btn.setWidth("260");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				counter.interrupt();
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return btn;
	}

}
