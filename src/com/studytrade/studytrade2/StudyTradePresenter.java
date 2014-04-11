package com.studytrade.studytrade2;

import com.vaadin.ui.Panel;

public class StudyTradePresenter extends Panel {
	private static final long serialVersionUID = 1L;

	private StudyTradeModel model;
	private StudyTradeView view;

	public StudyTradePresenter(StudyTradeModel _model, StudyTradeView _view) {
		model = _model;
		view = _view;
	}

	public void Init() {
		view.Init();
	}

}
