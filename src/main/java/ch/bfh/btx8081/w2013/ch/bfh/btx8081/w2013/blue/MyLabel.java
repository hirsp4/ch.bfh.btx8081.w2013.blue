package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;


import java.awt.Rectangle;
import com.vaadin.ui.Label;
/**
 * 
 * @author Rafael Kapp
 *
 *MyLabel v1.0 29.11.2013
 *
 *Was written for the drag "Button" in the Alert thing
 *Just small different to Label witch the class extends
 */
public class MyLabel extends Label{
	
	/**
	 * Default seralVersssonUID cause the warning was annoying!
	 */
	private static final long serialVersionUID = 1L;
	private int height;
	private int width;
	private int posX = 10;	//There where the Label stays in the beginning
	private int posY = 10;
	private Rectangle bounds;	//The bounds for the Mouselistener
	
	public MyLabel(String inText, Window window){
		super(inText);
		
		bounds = new Rectangle();
		setVisible(true);
		
		this.height = window.getHeight() / 5;
		this.width = window.getWidth() / 3;
		bounds.height = height;
		bounds.width = width;
		
		setHeight(Integer.toString(this.height));
		setWidth(Integer.toString(this.width));
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
		bounds.y = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
		bounds.x = posX;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
}
