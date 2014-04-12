package com.studytrade.studytrade2;

import com.studytrade.studytrade2.pages.LandingPage;
import com.studytrade.studytrade2.pages.LoginPage;
import com.studytrade.studytrade2.pages.RegisterPage;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class StudyTradeView extends Panel {
	private static final long serialVersionUID = 1L;

	private LandingPage pnlLandingPage = new LandingPage();
	private RegisterPage pnlRegister = new RegisterPage();
	private LoginPage pnlLogin = new LoginPage();
	
	public void Init() {
		VerticalLayout layout = new VerticalLayout();
		
		layout.addComponent(pnlRegister);
		layout.addComponent(pnlLogin);
		layout.addComponent(pnlLandingPage);
		
		setContent(layout);
		
		setCurrentPanel(pnlLandingPage);
	}
	
	public void setCurrentPanel(LandingPage pnlLandingPage) {
		pnlRegister.setVisible(false);
		pnlLogin.setVisible(false);
		pnlLandingPage.setVisible(true);

	}
}
