package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.implementations.UserPageViewImpl;
import com.studytrade.studytrade2.view.interfaces.UserPageView;
import com.studytrade.studytrade2.view.interfaces.UserPageViewListener;
import com.vaadin.ui.Component;

public class UserPagePresenter extends CustomPresenter implements UserPageViewListener {
    private UserPageView  view;
    
    public UserPagePresenter(Studytrade2UI ui, StudyTradeModel m, UserPageView  v) {
    	super(ui, m);
    	
        this.view  = v;

        view.addListener(this);
        
        changeView((Component)view);
    }

	@Override
	public void loginClicked(String username, String password) {
		onLoginClicked(username, password, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserPagePresenter(UI, Model, new UserPageViewImpl(Model.getLogedInUser(), view.getDisplayUser()));
			}
		});
	}

	@Override
	public void searchClicked(String searchstring) {
		onSearchClicked(searchstring);
	}

	@Override
	public void advancedSearchClicked() {
		onAdvancedSearchClicked();
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
	public void onShowMessage(String msg) {
		showMessagePageToMainWindow(msg);
	}

	@Override
	public void sendUserMessageClickded(StudyTradeUser sender, StudyTradeUser target, String header, String text) {
		// TODO SEND MSG
	}
}
