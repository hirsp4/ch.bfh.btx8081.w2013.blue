package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.ReferralView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ChameleonTheme;

public class IndexView extends VerticalLayout implements View {

	
	private static final long serialVersionUID = 1L;
	private BorderPanel borderPanel;
	
	
	public IndexView() {
		this.setSizeFull();
		this.setMargin(true);
		this.borderPanel = buildPanel();
		this.addComponent(this.borderPanel);
		this.setComponentAlignment(this.borderPanel, Alignment.TOP_CENTER);
	}
	
	private BorderPanel buildPanel(){
		BorderPanel panel = new BorderPanel();
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setMargin(true);
		vLayout.setSpacing(true);
		vLayout.addComponent(createAvButton());
		vLayout.addComponent(createDvButton());
		vLayout.addComponent(createRvButton());
		panel.setContent(vLayout);
		return panel;
	}
	
	private Button createAvButton(){
		Button btn = new Button("Alert");
		btn.addStyleName(ChameleonTheme.BUTTON_BIG);
		btn.setWidth("300px");
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
		btn.addStyleName(ChameleonTheme.BUTTON_BIG);
		btn.setWidth("300px");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setDenialView(new DenialView());
			}
		});
		return btn;
	}

	private Button createRvButton(){
		Button btn = new Button("Referral");
		btn.addStyleName("icon-on-left");
		btn.setIcon(new ThemeResource("Text-Edit-icon.png"));
		btn.setWidth("300px");
		btn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setReferralView(new ReferralView());
			}
		});
		return btn;
	}
	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
