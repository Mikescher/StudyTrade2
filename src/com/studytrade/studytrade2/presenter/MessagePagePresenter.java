package com.studytrade.studytrade2.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.studytrade.studytrade2.Studytrade2UI;
import com.studytrade.studytrade2.model.StudyTradeModel;
import com.studytrade.studytrade2.view.implementations.MessagePageViewImpl;
import com.studytrade.studytrade2.view.interfaces.MessagePageView;
import com.studytrade.studytrade2.view.interfaces.MessagePageViewListener;
import com.vaadin.ui.Component;

public class MessagePagePresenter extends CustomPresenter implements MessagePageViewListener {
    private MessagePageView  view;
    
    public MessagePagePresenter(Studytrade2UI ui, StudyTradeModel m, MessagePageView  v) {
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
				new MessagePagePresenter(UI, Model, new MessagePageViewImpl(Model.getLogedInUser(), view.getMessage(), view.getAction()));
			}
		});
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
}
