package com.studytrade.studytrade2;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.studytrade.studytrade2.pages.CommonPage;
import com.studytrade.studytrade2.pages.LandingPage;
import com.studytrade.studytrade2.pages.LoginPage;
import com.studytrade.studytrade2.pages.RegisterPage;
import com.studytrade.studytrade2.pages.SearchResultPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("studytrade2")
public class Studytrade2UI extends UI {
	private static final long serialVersionUID = 1L;
	
	public StudyTradeView view;
	public StudyTradeModel model;
	public StudyTradePresenter presenter;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Studytrade2UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@Override
	protected void init(VaadinRequest request) {
		view = new StudyTradeView();
		model = new StudyTradeModel();
		presenter = new StudyTradePresenter(model, view);
		LandingPage landing = new LandingPage();
		
		/* http://localhost:8080/StudyTrade2/dev
		 * hier können verschiedene Einsprungpunkte
		 * erstellt werden !!!
		 */
		
		String pathInfo = request.getPathInfo();
		if("/search".equals(pathInfo)){
			//CommonPage common = new CommonPage();
			SearchResultPage search;
			try {
				search = new SearchResultPage();
				setContent(search);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		if("/dev".equals(pathInfo)){
			//DO STH
		}
		}else{
			
		setContent(view);
		presenter.Init();
		}
		
	}

}