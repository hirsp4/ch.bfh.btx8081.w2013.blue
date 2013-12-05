package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.alert;

import javax.servlet.annotation.WebServlet;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.patient.PatientView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;


@Theme("mytheme")
@SuppressWarnings("serial")
@Push(PushMode.MANUAL)
public class MyVaadinUI extends UI
{
	private static Navigator navi;
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    	
    	getPage().setTitle("iDoctor Web-Application");
    	
    	navi = new Navigator(this, this);
    	
    	AlertView alertView = new AlertView();
    	
    	navi.addView("Alert", alertView);
    	navi.navigateTo("Alert");
    	
    }
    
    public static void setPatientView(PatientView pv){
		pv.setImmediate(true);
    	navi.addView("Patient", pv);
    	navi.navigateTo("Patient");
    }

	public static void setAlertView(AlertView av) {
		av.setImmediate(true);
		navi.addView("Alert", av);
    	navi.navigateTo("Alert");
	}

}
