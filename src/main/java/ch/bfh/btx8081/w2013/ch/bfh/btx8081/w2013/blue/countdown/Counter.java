package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.countdown;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient.PatientView;

/**
 * 
 * @author Patrick Hirschi
 *
 *
 * Counter v1.0 29.11.2013
 *
 * This class allows to build a Counter-Thread that will 
 * go to sleep for 5 seconds.
 *
 */
public class Counter extends Thread{
	
	public Counter(){
		// empty Constructor
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void run() {
			try {
				// let the Thread sleep for 5 seconds and
				// then change the view to PatientView.
				Thread.sleep(5000);
				MyVaadinUI.getCurrent().markAsDirty();
				MyVaadinUI.setPatientView(new PatientView());
				// Information for the user
				Notification notif = new Notification("ALERT IS RELEASED" + "\n "
						+ "You can now update the PatientData.", Notification.TYPE_ERROR_MESSAGE);
	        	//display for 5 seconds
				notif.setDelayMsec(5000);
	        	notif.setPosition(Position.BOTTOM_RIGHT);
				notif.show(Page.getCurrent());
				// push the changes to the client side.
				MyVaadinUI.getCurrent().push();
			} catch (InterruptedException e) {
			}
	
    }

}
