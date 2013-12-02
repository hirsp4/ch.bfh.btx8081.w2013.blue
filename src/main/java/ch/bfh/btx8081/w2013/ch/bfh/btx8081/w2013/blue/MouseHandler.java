package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
/**
 * 
 * @author Rafael Kapp
 *
 *
 *MousesHandler v1.0 29.11.2013
 *
 *Just for the Alert thing
 *Not really public cause of that
 *
 *But i don't know yet if we need a MouseHandler in other Sprints
 */
public class MouseHandler implements MouseListener, MouseMotionListener{// MouseDownHandler, MouseUpHandler, MouseMoveHandler{

	private boolean isDown;
	private MyLabel lbl;
	
	private int relPosX = 0; 	//Relative Mouse Position to the Label
	private int realPosX = 0;
	
	public MouseHandler(MyLabel lbl){
		this.lbl = lbl;
	}
	
//	@Override
//	public void onMouseUp(MouseUpEvent event) {
//		isDown = false;			//Its a drag system, if and only if the mouse is released the value is false
//	}
//
//	@Override
//	public void onMouseDown(MouseDownEvent event) {
//		if(lbl.getBounds().intersects(event.getClientX(), event.getClientY(), 0, 0)){
//			isDown = true;		//Set the value true if and only if the mouse is in the Label bounds
//		}
//	}
//
//	@Override
//	public void onMouseMove(MouseMoveEvent event) {
//		if(isDown){
//			realPosX = event.getClientX() - relPosX; 	//If the drag started it will just take the different from where it was clicked
//														//and the new Position
//		}else{
//			relPosX = event.getClientX();				//When its not clicked it updates the relativePosition
//		}	
//	}
	
	public int getMouseX(){
		return realPosX;
	}
	
	public boolean mouseIsDown(){
		return isDown;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent event) {
		if(lbl.getBounds().intersects(event.getX(), event.getY(), 0, 0)){
			isDown = true;		//Set the value true if and only if the mouse is in the Label bounds
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		isDown = false;			//Its a drag system, if and only if the mouse is released the value is false
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {	}

	@Override
	public void mouseMoved(MouseEvent event) {
		if(isDown){
			realPosX = event.getX() - relPosX; 	//If the drag started it will just take the different from where it was clicked
														//and the new Position
		}else{
			relPosX = event.getX();				//When its not clicked it updates the relativePosition
		}	
	}
}
