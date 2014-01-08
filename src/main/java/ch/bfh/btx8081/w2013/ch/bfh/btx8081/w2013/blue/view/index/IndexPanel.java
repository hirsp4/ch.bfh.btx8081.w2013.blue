package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.alert.AlertView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.denial.DenialView;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.referral.ReferralView;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Button.ClickEvent;
/**
 * 
 * 
 *
 */

public class IndexPanel extends BorderPanel {
	
	private static final long serialVersionUID = 1L;
	private Panel panel;
	private Button avButton;
	private Button dvButton;
	private Button rvButton;
	
	public IndexPanel() {
		this.panel = new Panel(" iDoctor");
		this.panel.setStyleName("borderless");
		this.panel.setIcon(new ThemeResource("doctor-icon.png"));
		this.panel.setSizeFull();
		
		FormLayout fLayout = new FormLayout();
		GridLayout gLayout = new GridLayout(2,2);
		gLayout.addComponent(this.createAvButton());
		gLayout.addComponent(this.createDvButton());
		gLayout.addComponent(this.createRvButton());
		
		gLayout.setHeight("400px");
		gLayout.setWidth("360px");

		fLayout.addComponent(gLayout);
		fLayout.setSizeFull();
		
		this.panel.setContent(fLayout);
		setContent(panel);
	}
	
	public Button createAvButton(){
		avButton = new Button("Alert");
		avButton.addStyleName("borderless icon-on-top");
		avButton.setIcon(new ThemeResource("alarm-icon.jpg"));
		avButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setAlertView(new AlertView());
			}
		});
		return avButton;
	}

	private Button createDvButton(){
		dvButton = new Button("Denial");
		dvButton.addStyleName("borderless icon-on-top");
		dvButton.setIcon(new ThemeResource("prescription_drugs.png"));
		dvButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setDenialView(new DenialView());
			}
		});
		return dvButton;
	}

	private Button createRvButton(){
		rvButton = new Button("Referral");
		rvButton.addStyleName("borderless icon-on-top");
		rvButton.setIcon(new ThemeResource("inscripcion_manual.png"));
		rvButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setReferralView(new ReferralView());
			}
		});
		return rvButton;
	}
}


