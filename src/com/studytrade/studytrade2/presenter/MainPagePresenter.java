package com.studytrade.studytrade2.presenter;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.interfaces.MainPageView;
import com.studytrade.studytrade2.view.interfaces.MainPageViewListener;
import com.vaadin.ui.Component;

public class MainPagePresenter extends CustomPresenter implements MainPageViewListener {
    private MainPageView  view;
    
    public MainPagePresenter(Studytrade2UI ui, StudyTradeModel m, MainPageView  v) {
    	super(ui, m);
    	
        this.view  = v;

        view.addListener(this);
        
        ChangeView((Component)view);
    }

	@Override
	public void loginClicked(String username, String password) {
		OnloginClicked(username, password);
	}

	@Override
	public void searchClicked(String searchstring) {
		OnsearchClicked(searchstring);
	}

	@Override
	public void logOffClicked() {
		OnlogOffClicked();
	}
}
