package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient.PatientView;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Slider;
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
	private final Slider slider;
	
	public AlertView(){
		setSizeFull();
		this.setMargin(true);
		this.slider = createSlider();
		addComponent(this.slider);
	}
	
	private Slider createSlider(){
		final Slider s = new Slider();
		s.setImmediate(true);
		s.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
		        double value = (Double) s.getValue();
		        if(value<s.getMax()){
		        	
		        }else{
		        	MyVaadinUI.setPatientView(new PatientView());
		        }
		    }
		});
		return s;
	}
	
	


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
