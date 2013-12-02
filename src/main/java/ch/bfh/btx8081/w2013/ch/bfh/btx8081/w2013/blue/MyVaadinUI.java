package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
	private Navigator navi;
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
    	getPage().setTitle("Alert Baby!");
    	
    	navi = new Navigator(this, this);
    	
    	AlertView alertView = new AlertView();
    	
    	navi.addView("Alert", alertView);
    	navi.navigateTo("Alert");
    	
    	while(true){
    		alertView.update();
    	}
    }

}
