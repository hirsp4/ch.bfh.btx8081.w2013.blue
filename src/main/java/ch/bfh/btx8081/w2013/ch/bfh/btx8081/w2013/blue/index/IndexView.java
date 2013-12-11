package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.index;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.referral.ReferralView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class IndexView extends VerticalLayout implements View {

	
	private static final long serialVersionUID = 1L;
	
	
	
	
	public IndexView() {
		VerticalLayout layout = new VerticalLayout();
		setSizeFull();
		this.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(createAvButton());
		layout.addComponent(createDvButton());
		layout.addComponent(createRvButton());
		addComponent(layout);
	}
	
	private Button createAvButton(){
		Button btn = new Button("Alert");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return btn;
	}

	private Button createDvButton(){
		Button btn = new Button("Denial");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setDenialView(new DenialView());
			}
		});
		return btn;
	}

	private Button createRvButton(){
		Button btn2 = new Button("Referral");
		btn2.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setReferralView(new ReferralView());
			}
		});
		return btn2;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
