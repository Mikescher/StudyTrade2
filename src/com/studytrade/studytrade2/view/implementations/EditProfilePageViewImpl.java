package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.EditProfilePageView;
import com.studytrade.studytrade2.view.interfaces.EditProfilePageViewListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EditProfilePageViewImpl extends CustomStudyTradeComponent implements EditProfilePageView {
	private static final long serialVersionUID = -2103599367448946610L;
	
	private List<EditProfilePageViewListener> listeners = new ArrayList<>();

	private NativeButton btnEdit;
	private TextField edMail;
	private TextField edDirection;
	private NativeSelect edUniversity;
	private TextField edCity;
	private TextField edLastname;
	private TextField edForename;
	private Label lblMessage;
	
	public EditProfilePageViewImpl(StudyTradeUser usr) {
		super(usr);
		
		Init();
	}
	
	@Override
	public void addListener(EditProfilePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");
		
		lblMessage = new Label();
		lblMessage.setValue("???");
		lblMessage.setVisible(false);
		mainLayout.addComponent(lblMessage);
		
		mainLayout.addComponent(new Label("Edit Profile of " + User.Nickname));
		
		edForename = new TextField();
		edForename.setCaption("Vorname");
		edForename.setId("selendebug_EdPg_ed_forename");
		edForename.setValue(User.Forename);
		mainLayout.addComponent(edForename);

		edLastname = new TextField();
		edLastname.setCaption("Nachname");
		edLastname.setId("selendebug_EdPg_ed_lastname");
		edLastname.setValue(User.Lastname);
		mainLayout.addComponent(edLastname);

		edCity = new TextField();
		edCity.setCaption("Wohnort");
		edCity.setId("selendebug_EdPg_ed_city");
		edCity.setValue(User.City);
		mainLayout.addComponent(edCity);

		edUniversity = new NativeSelect();
		edUniversity.setCaption("Hochschule");
		StudyTradeDefinitions.addSelectItems_Places(edUniversity);
		edUniversity.setId("selendebug_EdPg_cbx_university");
		edUniversity.setValue(User.University);
		mainLayout.addComponent(edUniversity);

		edDirection = new TextField();
		edDirection.setCaption("Studienrichtung");
		edDirection.setId("selendebug_EdPg_ed_direction");
		edDirection.setValue(User.Studydirection);
		mainLayout.addComponent(edDirection);

		edMail = new TextField();
		edMail.setCaption("E-Mail");
		edMail.setId("selendebug_EdPg_ed_mail");
		edMail.setValue(User.Email);
		mainLayout.addComponent(edMail);

		btnEdit = new NativeButton();
		btnEdit.setCaption("Edit");
		btnEdit.setId("selendebug_EdPg_btn_edit");
		mainLayout.addComponent(btnEdit);
		btnEdit.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1224460369183535360L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (isInputValid()) {
					onBtnDoRegisterClicked();
				}
				
			}
		});
		
		return mainLayout;
	}

	protected void onBtnDoRegisterClicked() {
		for (EditProfilePageViewListener l : listeners) {
			l.doEditClicked(
					edForename.getValue(), 
					edLastname.getValue(), 
					edCity.getValue(), 
					edUniversity.getItemCaption(edUniversity.getValue()), 
					edDirection.getValue(), 
					edMail.getValue());
		}
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
			lblMessage.setValue("Please insert a study direction");
			lblMessage.setVisible(true);
			return false;
		}
		
		if (edMail.getValue().trim().length() == 0) { // all Whitespace
			lblMessage.setValue("Please insert a E-Mail address");
			lblMessage.setVisible(true);
			return false;
		}
		
		return true;
	}
	
	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (EditProfilePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (EditProfilePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (EditProfilePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (EditProfilePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (EditProfilePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (EditProfilePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (EditProfilePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		forceLoggedIn();
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (EditProfilePageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle artc) {
		for (EditProfilePageViewListener l : listeners)
			l.showArticleClicked(artc);
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (EditProfilePageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (EditProfilePageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
