package com.studytrade.studytrade2;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.studytrade.studytrade2.model.ArticleManagement;
import com.vaadin.ui.Panel;

public class StudyTradePresenter extends Panel {
	private static final long serialVersionUID = 1L;
	
	private boolean isLoggedIn = false;
	
	@SuppressWarnings("unused")
	private StudyTradeModel model;
	private StudyTradeView view;

	public StudyTradePresenter(StudyTradeModel _model, StudyTradeView _view) {
		model = _model;
		view = _view;
		
		view.Init(this);
	}

	/**
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn
	 *            the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/* LandingPage */
	public void InitLanding() throws SQLException {
		if (isLoggedIn) {
			System.out.println("Logged In");
		} else {
			view.Landing();
		}
	}

	/* Searching */
	public void Searching(String search) throws SQLException {
		System.out.println("pres-searching- check!!");
		ArticleManagement articlemanagement = new ArticleManagement();
		ResultSet rs = articlemanagement.ArticleSearch(search, null, null, null, null);
		view.SearchResult(rs);
	}
}
