package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.countdown.Counter;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * @author Patrick Hirschi
 *
 *AlertPanel v1.0 29.11.2013
 *
 *This is the panel-Class for our 
 *Alert View. The class generates a Panel
 *with a Slider to start the Counter if a patient
 *gets aggressive (5 seconds till the alertion) and
 *2 Buttons (1 to go back to index View and 1 to
 *stop the alertion.
 */

public class AlertPanel extends BorderPanel{
	
	private static final long serialVersionUID = 1L;
	private final Slider slider;
	private Counter counter;
	private Panel panel;
	private Button btn;
	private Button btn1;
	
	public AlertPanel(){
		this.panel = new Panel(" Alert");
		this.panel.setIcon(new ThemeResource("alarmKlein.jpg"));
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();
		
		this.counter = new Counter();
		this.slider = createSlider();
		this.btn = createButton();
		this.btn1 = createIndexButton();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeUndefined();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(this.slider);
		Label emptyLabel = new Label();
		emptyLabel.setHeight("50px");
		layout.addComponent(emptyLabel);
		layout.setComponentAlignment(slider, Alignment.TOP_CENTER);
		layout.setWidth("370px");
		
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setWidth("220px");
		hlayout.addComponent(btn);
		hlayout.addComponent(btn1);
//		hlayout.setComponentAlignment(btn, Alignment.MIDDLE_LEFT);
//		hlayout.setComponentAlignment(btn1, Alignment.MIDDLE_RIGHT);
		
		layout.addComponent(hlayout);
		VerticalLayout layout2 = new VerticalLayout();
		layout2.addComponent(layout);
		this.panel.setContent(layout);
		setContent(panel);	
	}
	
	/**
	 * Make the Slide and sets it always back to 0 if yu didnt push it to the max value.
	 * And if and only if the value is max, the counter get started and an Notification is shown
	 * @return Slider
	 */
	private Slider createSlider(){
		final Slider s = new Slider();
		// to trigger repaint requests
		s.setImmediate(true);
		// add a change listener
		s.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
		        double value = (Double) s.getValue();
		        // alert is only released if the slider is on 100.0
		        // if not he is set back to zero.
		        // (for security reasons)
		        if(value<s.getMax()){
		        	s.setValue(0.0);
		        }else{
		        	Notification notif = new Notification("Alert will be released in "
		        			+ "5 seconds" + "\n Press Button to stop", 
		        			Notification.Type.WARNING_MESSAGE);
		        	notif.setDelayMsec(4);
		        	notif.setPosition(Position.BOTTOM_RIGHT);
		        	notif.show(Page.getCurrent());
		        	//start the 5 seconds Counter thread.
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
		//create the button
		Button btn = new Button("Stop Alert");
		btn.setWidth("100px");
		btn.setHeight("75px");	
		btn.addStyleName("borderless icon-on-top");
		btn.setIcon(new ThemeResource("StopAlert.png"));
		//add the click listener
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				// if clicked, interrupt thread
				// and change to AlertView
				counter.interrupt();
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return btn;
	}
	/**
	 * Creates a Button. If clicked, view is changed
	 * to index view. 
	 * @return BackButton
	 */
	public Button createIndexButton() {
		Button btn1 = new Button("Index");
		btn1.setWidth("100px");
		btn1.setHeight("75px");	
		btn1.addStyleName("borderless icon-on-top");
		btn1.setIcon(new ThemeResource("index.png"));
		btn1.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return btn1;
	}
	

}
