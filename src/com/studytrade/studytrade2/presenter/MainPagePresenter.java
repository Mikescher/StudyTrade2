package com.studytrade.studytrade2.presenter;

import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.studytrade.studytrade2.view.interfaces.MainPageViewListener;

public class MainPagePresenter implements MainPageViewListener {
    private StudyTradeModel model;
    private MainPageView  view;
    
    public MainPagePresenter(StudyTradeModel m, MainPageView  v) {
        this.model = m;
        this.view  = v;

        view.addListener(this);
    }

	@Override
	public void LoginClicked() {
		// TODO Auto-generated method stub
		
	}

}
