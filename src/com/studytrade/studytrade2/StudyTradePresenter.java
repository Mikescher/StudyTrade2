package com.studytrade.studytrade2;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class StudyTradePresenter extends Panel{
	StudyTradeModel model;
	StudyTradeView view;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public StudyTradePresenter(StudyTradeModel _model, StudyTradeView _view) {
		 model = _model;
		 view = _view;
		 
	}
	public void armin(){
		view.test();
	}
	

}
