package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import com.vaadin.ui.Label;

public class MyLabel extends Label{
	
	private Label lbl;
	private int height;
	private int width;
	private String inText;
	
	
	public MyLabel(String inText, Window window){
		super(inText);
		this.inText = inText;
		
		
		setVisible(true);
		
		this.height = window.getHeight() / 5;
		this.width = window.getWidth() / 3;
		
		setHeight(Integer.toString(this.height));
		setWidth(Integer.toString(this.width));
	}

	
}
