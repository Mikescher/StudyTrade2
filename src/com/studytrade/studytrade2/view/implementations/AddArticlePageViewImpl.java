package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.AddArticlePageView;
import com.studytrade.studytrade2.view.interfaces.AddArticlePageViewListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddArticlePageViewImpl extends CustomStudyTradeComponent implements AddArticlePageView {
	private static final long serialVersionUID = -2103599367448946610L;
	
	private List<AddArticlePageViewListener> listeners = new ArrayList<>();

	private TextField edName;
	private TextField edPrice;
	private NativeSelect edCondition;
	private TextField edPlace;
	private TextArea edDescription;
	private Label lblPriceNonInt;
	
	private Button btnSend;
	
	public AddArticlePageViewImpl(StudyTradeUser usr) {
		super(usr);
		
		Init();
	}
	
	@Override
	public void addListener(AddArticlePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");
		
		
		edName = new TextField();
		edName.setCaption("Name");
		edName.setId("selendebug_addartg_ed_name");
		
		edPrice = new TextField();
		edPrice.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 8185727033283969217L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				lblPriceNonInt.setVisible(false);
			}
		});
		edPrice.setCaption("Price");
		edPrice.setId("selendebug_addartg_ed_price");
		
		edCondition = new NativeSelect();
		StudyTradeDefinitions.addSelectItems_Condition(edCondition);
		edCondition.setCaption("Condition");
		edCondition.setId("selendebug_addartg_cbx_condition");
		
		edPlace = new TextField();
		edPlace.setCaption("Place");
		edPlace.setId("selendebug_addartg_ed_place");
		
		edDescription = new TextArea();
		edDescription.setCaption("Description");
		edDescription.setId("selendebug_addartg_ed_desc");
		
		btnSend = new Button("Insert");
		btnSend.setId("selendebug_addartg_btn_send");
		btnSend.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnInsertClicked(edName.getValue(), edPrice.getValue(), (String)edCondition.getValue(), edPlace.getValue(), edDescription.getValue());
			}
		});
		
		lblPriceNonInt = new Label("Could not parse price");
		lblPriceNonInt.setVisible(false);
		mainLayout.addComponent(lblPriceNonInt);
		
		mainLayout.addComponent(edName);
		mainLayout.addComponent(edPrice);
		mainLayout.addComponent(edCondition);
		mainLayout.addComponent(edPlace);
		mainLayout.addComponent(edDescription);
		mainLayout.addComponent(btnSend);
		
		return mainLayout;
	}

	protected void onBtnInsertClicked(String name, String price, String cond, String place, String desc) {
		Float fprice;
		try {
			fprice = Float.valueOf(price);
		} catch (NumberFormatException e) {
			edPrice.setValue("");
			lblPriceNonInt.setVisible(true);
			return;
		}
		
		if (name.isEmpty() || cond == null || place.isEmpty() || desc.isEmpty())
			return;
		
		for (AddArticlePageViewListener l : listeners)
			l.addArticleClicked(name, fprice, StudyTradeDefinitions.getConditionIndex(cond), place, desc);
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (AddArticlePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (AddArticlePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (AddArticlePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (AddArticlePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (AddArticlePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (AddArticlePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (AddArticlePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	public void onAfterInit() {
		forceLoggedIn();
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (AddArticlePageViewListener l : listeners)
			l.onAddArticle();
	}
}
