package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main;

import javax.servlet.annotation.WebServlet;

import ch.bfh.btx2081.w2013.ch.bfh.btx8081.w2013.blue.view.login.LoginView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.addpatient.AddPatientView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.patient.PatientView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.patientadmission.PatientAdmissionView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.ReferralView;

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
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {   	
    	getPage().setTitle("iDoctor Web-Application");    	
    	navi = new Navigator(this, this);    	
    	LoginView loginview = new LoginView();
    	navi.addView("Login", loginview);
    	navi.navigateTo("Login"); 	
    }
    
    public static void setIndexView(IndexView iv) {
		iv.setImmediate(true);
		navi.addView("Index", iv);
    	navi.navigateTo("Index");
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

	public static void setDenialView(DenialView dv) {
		dv.setImmediate(true);
		navi.addView("Denial", dv);
    	navi.navigateTo("Denial");
	}
	
	public static void setReferralView(ReferralView rv) {
		rv.setImmediate(true);
		navi.addView("Referral", rv);
		navi.navigateTo("Referral");
	}
	
	public static void setPatientAdmissionView(PatientAdmissionView pav) {
		pav.setImmediate(true);
		navi.addView("Patient Admission", pav);
		navi.navigateTo("Patient Admission");
	}
	
	public static void setAddPatientView(AddPatientView apv) {
		apv.setImmediate(true);
		navi.addView("Patient", apv);
		navi.navigateTo("Patient");
	}

}
