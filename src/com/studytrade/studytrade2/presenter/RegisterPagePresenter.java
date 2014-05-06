package com.studytrade.studytrade2.presenter;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.interfaces.RegisterPageView;
import com.studytrade.studytrade2.view.interfaces.RegisterPageViewListener;
import com.vaadin.ui.Component;

public class RegisterPagePresenter extends CustomPresenter implements RegisterPageViewListener {
    private RegisterPageView  view;
    
    public RegisterPagePresenter(Studytrade2UI ui, StudyTradeModel m, RegisterPageView v) {
    	super(ui, m);
    	
        this.view  = v;

        view.addListener(this);
        
        changeView((Component)view);
    }

	@Override
	public void loginClicked(String username, String password) {
		onLoginClicked(username, password);
	}

	@Override
	public void searchClicked(String searchstring) {
		onSearchClicked(searchstring);
	}

	@Override
	public void logOffClicked() {
		onLogOffClicked();
	}

	@Override
	public void registerClicked() {
		onRegisterClicked();
	}

	@Override
	public void doRegisterClicked(String forename, String lastname, String nickname, String place, String university, String studydirection, String email, String password) {
		Model.register(forename, lastname, nickname, place, university, studydirection, email, password);
		
		onLoginClicked(nickname, password);
	}
}