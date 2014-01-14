package ch.bfh.btx2081.w2013.ch.bfh.btx8081.w2013.blue.view.login;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;

import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.main.MyVaadinUI;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.BorderPanel;
import ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index.IndexView;

/**
* 
* @author Maja Kelterborn
* 
* 
*         LoginPanel v1.0  14.01.2014
* 
*        Creates the Panel for our "login" view. 
*        The panel consists of a button to enter in the application
*        and the logo.
* 
*/

public class LoginPanel extends BorderPanel{
	
	private static final long serialVersionUID = 1L;
	private Button IndexButton;
	private Label label;
	private Image labelicon;
	private Panel panel;
	
	public LoginPanel() {
		
		this.label = new Label("iDoctor");
		this.label.setStyleName("v-label-labelstyle");
		this.label.setWidth(null);
		this.labelicon = new Image();
		this.labelicon.setIcon(new ThemeResource("doctor.png"));
		this.panel = new Panel();
		this.panel.setStyleName("borderless");
		this.panel.setSizeFull();
		
		GridLayout glayout = new GridLayout(1,3);
		FormLayout flayout = new FormLayout();
		glayout.addComponent(this.label);
		glayout.setComponentAlignment(this.label, Alignment.TOP_CENTER);
		glayout.addComponent(this.labelicon);
		glayout.setComponentAlignment(this.labelicon, Alignment.MIDDLE_CENTER);
		glayout.addComponent(createLinkIndexButton());
		glayout.setComponentAlignment(IndexButton, Alignment.BOTTOM_CENTER);
		
		glayout.setWidth("360px");
		glayout.setHeight("550px");
		
		flayout.addComponent(glayout);
		flayout.setSizeFull();
		this.panel.setContent(flayout);
		setContent(panel);
	}
	
	/**
	 * Creates the "Enter" Button to enter the application.
	 * 
	 * @return Button
	 */
	
	public Button createLinkIndexButton() {
		IndexButton = new Button("Enter");
		IndexButton.addStyleName("big");
		IndexButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID =1L;
			public void buttonClick(ClickEvent event) {
				MyVaadinUI.setIndexView(new IndexView());
			}
		});
		return IndexButton;
	}
}
