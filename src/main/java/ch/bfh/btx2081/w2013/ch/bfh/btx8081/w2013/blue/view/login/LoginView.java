package ch.bfh.btx2081.w2013.ch.bfh.btx8081.w2013.blue.view.login;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;

	public LoginView() {
		setMargin(true);
		LoginPanel loginPanel = new LoginPanel();
		addComponent(loginPanel); // create the "login" panel and add it to the login view.
		this.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}