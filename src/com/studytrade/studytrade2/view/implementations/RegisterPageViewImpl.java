package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.RegisterPageView;
import com.studytrade.studytrade2.view.interfaces.RegisterPageViewListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegisterPageViewImpl extends CustomStudyTradeComponent implements RegisterPageView {
	private static final long serialVersionUID = -2103599367448946610L;

	private List<RegisterPageViewListener> listeners = new ArrayList<>();

	private List<String> usrNames;
	
	private NativeButton btnRegister;
	private PasswordField edPassword_2;
	private PasswordField edPassword_1;
	private TextField edMail;
	private TextField edDirection;
	private NativeSelect edUniversity;
	private TextField edCity;
	private TextField edNickname;
	private TextField edLastname;
	private TextField edForename;
	private Label lblCaption;
	private Label lblMessage;

	public RegisterPageViewImpl(StudyTradeUser usr, List<String> takenUsernames) {
		super(usr);
		
		this.usrNames = takenUsernames;

		Init();
	}

	@Override
	public void addListener(RegisterPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();

		Panel p = new Panel();
		VerticalLayout l = new VerticalLayout();

		lblMessage = new Label();
		lblMessage.setValue("???");
		lblMessage.setVisible(false);
		l.addComponent(lblMessage);
		
		lblCaption = new Label();
		lblCaption.setValue("Registrierung");
		l.addComponent(lblCaption);

		edForename = new TextField();
		edForename.setCaption("Vorname");
		l.addComponent(edForename);

		edLastname = new TextField();
		edLastname.setCaption("Nachname");
		l.addComponent(edLastname);

		edNickname = new TextField();
		edNickname.setCaption("Nickname");
		l.addComponent(edNickname);

		edCity = new TextField();
		edCity.setCaption("Wohnort");
		l.addComponent(edCity);

		edUniversity = new NativeSelect();
		edUniversity.setCaption("Hochschule");
		{
			edUniversity.setItemCaption(edUniversity.addItem("DH Karlsruhe"), "DH Karlsruhe");
			edUniversity.setItemCaption(edUniversity.addItem("DH Freiburg"), "DH Freiburg");
			edUniversity.setItemCaption(edUniversity.addItem("Uni Offenburg"), "Uni Offenburg");
			edUniversity.setItemCaption(edUniversity.addItem("Other"), "Other");
		}
		l.addComponent(edUniversity);

		edDirection = new TextField();
		edDirection.setCaption("Studienrichtung");
		l.addComponent(edDirection);

		edMail = new TextField();
		edMail.setCaption("E-Mail");
		l.addComponent(edMail);

		edPassword_1 = new PasswordField();
		edPassword_1.setCaption("Passwort");
		l.addComponent(edPassword_1);

		edPassword_2 = new PasswordField();
		l.addComponent(edPassword_2);

		btnRegister = new NativeButton();
		btnRegister.setCaption("Registrieren");
		l.addComponent(btnRegister);
		btnRegister.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1224460369183535360L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (isInputValid()) {
					onBtnDoRegisterClicked();
				}
				
			}
		});

		p.setContent(l);

		mainLayout.addComponent(p);

		return mainLayout;
	}

	private boolean isInputValid() {
		if (edForename.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a forename");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edLastname.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a lastname");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edNickname.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a Nickname");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (isUsernameTaken(edNickname.getValue())) { // all Whitespace
			lblMessage.setValue("Nickname already taken");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edCity.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a city");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edUniversity.getValue() == null) {
			lblMessage.setValue("Please choose a university");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edDirection.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a StudyDirection");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edMail.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a E-Mail address");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edPassword_1.getValue().trim().length() < 8) {
			lblMessage.setValue("Please insert a password with a minimum length of 8 characters");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (! edPassword_1.getValue().equals(edPassword_2.getValue())) {
			lblMessage.setValue("Passwords do not match");
			lblMessage.setVisible(true);
			return false;
		}
		
		return true;
	}
	
	private boolean isUsernameTaken(String value) {
		for (String us : usrNames)
			if (us.toLowerCase().equals(value.toLowerCase()))
				return true;
		
		return false;
	}

	private void onBtnDoRegisterClicked() {
		for (RegisterPageViewListener l : listeners) {
			l.doRegisterClicked(
					edForename.getValue(), 
					edLastname.getValue(), 
					edNickname.getValue(), 
					edCity.getValue(), 
					edUniversity.getItemCaption(edUniversity.getValue()), 
					edDirection.getValue(), 
					edMail.getValue(), 
					edPassword_1.getValue());
		}
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (RegisterPageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (RegisterPageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (RegisterPageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (RegisterPageViewListener l : listeners)
			l.logOffClicked();
	}
}
