package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.referral;




import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Maja Kelterborn
 *
 *
 * PatientView v1.0 06.12.2013
 *
 * The general view if the user has to add informations about a patient.
 * 
 */

public class ReferralView extends VerticalLayout implements View{
	private static final long serialVersionUID = 1L;
	public ReferralView(){
		setMargin(true);
		addComponent(new ReferralPanel()); // create the referral panel and add it to the	
	}

	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	

}



