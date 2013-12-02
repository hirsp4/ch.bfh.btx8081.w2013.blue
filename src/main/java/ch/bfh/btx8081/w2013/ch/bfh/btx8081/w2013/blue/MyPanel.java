package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import com.vaadin.ui.AbsoluteLayout;


public class MyPanel extends AbsoluteLayout {
	
	/**
	 * Default seralVersssonUID cause the warning was annoying!
	 */
	private static final long serialVersionUID = 1L;
	private MyLabel lbl;
	private Window window;
	
	private MouseHandler mouseHandler;
	
	public MyPanel(){
		this.window = new Window();
		this.lbl = new MyLabel("Stroke me", window);
		addComponent(this.lbl, "left: " + lbl.getPosX() + "px; top: " + lbl.getPosY() + "px;");
		mouseHandler = new MouseHandler(lbl);
	}
	
	public void update(){
		lbl.setPosX(mouseHandler.getMouseX());
		removeComponent(this.lbl);
		addComponent(this.lbl, "left: " + lbl.getPosX() + "px; top: " + lbl.getPosY() + "px;");
	}
}
