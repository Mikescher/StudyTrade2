package com.studytrade.studytrade2;


import com.studytrade.studytrade2.pages.LoginPage;
import com.studytrade.studytrade2.pages.RegisterPage;
import com.studytrade.studytrade2.pages.StartPage;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


public class StudyTradeView extends Panel {
	private static final long serialVersionUID = 1L;

	private StartPage pnlStartPage = new StartPage();
	private RegisterPage pnlRegister = new RegisterPage();
	private LoginPage pnlLogin = new LoginPage();
	
	public void Init() {
		VerticalLayout layout = new VerticalLayout();
		
		layout.addComponent(pnlRegister);
		layout.addComponent(pnlLogin);
		layout.addComponent(pnlStartPage);
		
		setContent(layout);
		
		setCurrentPanel(pnlStartPage);
	}
	
	public void setCurrentPanel(Panel p) {
		pnlRegister.setVisible(false);
		pnlLogin.setVisible(false);
		pnlStartPage.setVisible(false);
		
		p.setVisible(true);
	}
}
