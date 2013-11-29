package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AlertView extends VerticalLayout implements View{
	
	private Window window;
	private MyLabel lbl;
	

	public AlertView(){
		setSizeFull();
		this.setMargin(true);
		
		this.window = new Window();
		this.lbl = new MyLabel("Stroke me", window);
		addComponent(this.lbl);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
