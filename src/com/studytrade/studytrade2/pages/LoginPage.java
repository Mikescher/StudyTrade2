package com.studytrade.studytrade2.pages;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class LoginPage extends CommonPage {
	private static final long serialVersionUID = 1L;

	public LoginPage() {
		VerticalLayout layout = new VerticalLayout();
		Button b = new Button("LOGIN PANEL");
		layout.addComponent(b);
		setCompositionRoot(layout);
	}
}
