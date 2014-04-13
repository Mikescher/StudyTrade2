package com.studytrade.studytrade2.pages;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
	
public class RegisterPage extends CommonPage {
	private static final long serialVersionUID = 1L;

	public RegisterPage() {
		//VerticalLayout layout = new VerticalLayout();
		AbsoluteLayout abslayout = new AbsoluteLayout();
		int browser_height =UI.getCurrent().getPage().getBrowserWindowHeight();
		abslayout.setHeight(String.valueOf(browser_height));
		abslayout.setWidth("100%");
		
		Button b = new Button("REGISTER PANEL");
		abslayout.addComponent(b,"top:0px;left:0px;");
		//layout.addComponent(abslayout);
		//layout.setSizeFull();
		setCompositionRoot(abslayout);
		
	}
}
