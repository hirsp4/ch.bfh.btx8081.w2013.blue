package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import java.awt.Dimension;
import java.awt.Rectangle;

import com.vaadin.ui.UI;

public class AlertView {
	
	public static Rectangle ivan;
	
	
	//Kommentar
	public AlertView(){
		
	}
	
	private Dimension getWindowSize(){
		return new Dimension(UI.getCurrent().getPage().getBrowserWindowHeight(), UI.getCurrent().getPage().getBrowserWindowHeight());
	}
}
