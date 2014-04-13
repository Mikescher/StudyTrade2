package com.studytrade.studytrade2;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.studytrade.studytrade2.pages.CommonPage;
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
		/* Initialisierung des MVP - Models */
		view = new StudyTradeView();
		model = new StudyTradeModel();
		presenter = new StudyTradePresenter(model, view);
		setContent(view);

		/* Auswertung der aufgerufenen URL */
		// TODO Überprüfung auf Cookies
		String path = request.getPathInfo();
		switch (path) {
		case "/": {
			try {
				presenter.InitLanding();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		case "/dev": {
			CommonPage common = new CommonPage();
			setContent(common);

			break;
		}
		case "/search": {
			break;
		}
		default: {
			break;
		}
		}

	}

}