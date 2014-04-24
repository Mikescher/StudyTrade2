package com.studytrade.studytrade2;

import javax.servlet.annotation.WebServlet;

import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.presenter.MainPagePresenter;
import com.studytrade.studytrade2.view.implementations.MainPageViewImpl;
import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("studytrade2")
public class Studytrade2UI extends UI {
	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Studytrade2UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}           
                
	@Override   
	protected void init(VaadinRequest request) {
		StudyTradeModel model = new StudyTradeModel();
		        
//*
		MainPageView view = new MainPageViewImpl(model.GetLogedInUser());
/*/
		MainPageView view = new MainPageViewImpl(new com.studytrade.studytrade2.model.StudyTradeUser("testuser", "A@B.C"));
//*/
		
		new MainPagePresenter(this, model, view);
	}

}