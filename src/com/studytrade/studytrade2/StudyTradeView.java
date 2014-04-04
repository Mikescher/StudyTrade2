package com.studytrade.studytrade2;


import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class StudyTradeView extends Panel {
	private static final long serialVersionUID = 1L;

	private RegisterPage pnlRegister = new RegisterPage();
	private LoginPage pnlLogin = new LoginPage();
	
	public void Init() {
		VerticalLayout layout = new VerticalLayout();
		
		layout.addComponent(pnlRegister);
		layout.addComponent(pnlLogin);
		
		setContent(layout);
		
		setCurrentPanel(pnlLogin);
	}
	
	public void setCurrentPanel(Panel p) {
		pnlRegister.setVisible(false);
		pnlLogin.setVisible(false);
		
		p.setVisible(true);
	}
}
