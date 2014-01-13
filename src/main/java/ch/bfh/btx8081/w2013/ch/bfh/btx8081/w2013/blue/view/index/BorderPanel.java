package ch.bfh.btx8081.w2013.ch.bfh.btx8081.w2013.blue.view.index;

import com.vaadin.annotations.Theme;
import com.vaadin.ui.Panel;


@Theme("mytheme.scss")
public class BorderPanel extends Panel{
	private static final long serialVersionUID = 1L;
	private static final String WIDTH = "390px";
	private static final String HEIGHT = "700px";

	public BorderPanel(){
		addStyleName("v-panel-borderpanel");
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
	}
	

}
