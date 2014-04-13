package com.studytrade.studytrade2;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.studytrade.studytrade2.pages.LandingPage;
import com.studytrade.studytrade2.pages.SearchResultPage;
import com.vaadin.ui.Panel;

public class StudyTradeView extends Panel {
	private static final long serialVersionUID = 1L;

	private LandingPage pnlLanding;
	private SearchResultPage pnlSearchResult;

	private StudyTradePresenter presenter;
	
	public StudyTradeView(StudyTradePresenter presenter) {
		this.presenter = presenter;
	}

	public void Init() throws SQLException {
		/*Hier pages initialisieren*/
		
		pnlLanding = new LandingPage(presenter);
		pnlLanding.setVisible(false);
	}

	/* Methoden für bestimmte Pages */
	public void Landing() {
		setContent(pnlLanding);
		pnlLanding.setVisible(true);
	}
	public void SearchResult(ResultSet rs) throws SQLException{
		pnlSearchResult = new SearchResultPage(rs);
		pnlLanding.setVisible(false);
		pnlSearchResult.setVisible(true);
	}

}
