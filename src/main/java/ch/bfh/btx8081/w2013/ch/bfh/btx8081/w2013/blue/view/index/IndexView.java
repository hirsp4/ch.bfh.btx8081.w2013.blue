package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class IndexView extends VerticalLayout implements View {

	
	private static final long serialVersionUID = 1L;
	
	
	
	public IndexView() {
		this.setSizeFull();
		this.setMargin(true);
		IndexPanel indexPanel = new IndexPanel();
		addComponent(indexPanel);
		this.setComponentAlignment(indexPanel, Alignment.MIDDLE_CENTER);
	}
	
	
	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}

