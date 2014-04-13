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
	
	public StudyTradeView() {
		// no nop
	}

	public void Init(StudyTradePresenter _presenter) {
		this.presenter = _presenter;
		
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
