package com.studytrade.studytrade2.pages;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class RegisterPage extends CommonPage {
	private static final long serialVersionUID = 1L;

	public RegisterPage() {
		VerticalLayout layout = new VerticalLayout();
		Button b = new Button("REGISTER PANEL");
		layout.addComponent(b);
		setCompositionRoot(layout);
		
	}
}
