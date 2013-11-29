package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import java.awt.Dimension;

import com.vaadin.ui.UI;

public class Window {
	
	private int width;
	private int height;
	
	public Window(){
		this.width = UI.getCurrent().getPage().getBrowserWindowHeight();
		this.height = UI.getCurrent().getPage().getBrowserWindowHeight();
	}
	
	
	
	public Dimension getWindowSize(){
		return new Dimension(width, height);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
}
