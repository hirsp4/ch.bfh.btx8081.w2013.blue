package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.countdown;

import com.vaadin.ui.Notification;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.alert.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient.PatientView;

public class Counter extends Thread{
	
	public Counter(){
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void run() {
			try {
				Thread.sleep(5000);
				MyVaadinUI.getCurrent().markAsDirty();
				MyVaadinUI.setPatientView(new PatientView());
				Notification.show("ALERT IS RELEASED" + "\n "
						+ "You can now update the PatientData.", Notification.TYPE_ERROR_MESSAGE);
				MyVaadinUI.getCurrent().push();
			} catch (InterruptedException e) {
			}
	
    }

}
