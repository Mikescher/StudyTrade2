package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeArticle;
import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.EditArticlePageView;
import com.studytrade.studytrade2.view.interfaces.EditArticlePageViewListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EditArticlePageViewImpl extends CustomStudyTradeComponent implements EditArticlePageView {
	private static final long serialVersionUID = -2103599367448946610L;
	
	private List<EditArticlePageViewListener> listeners = new ArrayList<>();
	private StudyTradeArticle article;
	
	private TextField edName;
	private TextField edPrice;
	private NativeSelect edCondition;
	private NativeSelect cbxPlace;
	private TextArea edDescription;
	private Label lblPriceNonInt;
	
	private Button btnUpdate;
	private Button btnDelete;
	
	public EditArticlePageViewImpl(StudyTradeUser usr, StudyTradeArticle art) {
		super(usr);
		
		this.article = art;
		
		Init();
	}
	
	@Override
	public void addListener(EditArticlePageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");
		
		lblPriceNonInt = new Label("Could not parse price");
		lblPriceNonInt.setVisible(false);
		
		edName = new TextField();
		edName.setCaption("Name");
		edName.setValue(article.Name);
		
		edPrice = new TextField();
		edPrice.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 8185727033283969217L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				lblPriceNonInt.setVisible(false);
			}
		});
		edPrice.setCaption("Price");
		edPrice.setValue(article.Price.toString());
		
		edCondition = new NativeSelect();
		StudyTradeDefinitions.addSelectItems_Condition(edCondition);
		edCondition.setCaption("Condition");
		edCondition.setValue(StudyTradeDefinitions.CONDITIONS[article.Condition]);
		
		cbxPlace = new NativeSelect();
		StudyTradeDefinitions.addSelectItems_Places(cbxPlace);
		cbxPlace.setCaption("Place");
		cbxPlace.setValue(article.Place);
		
		edDescription = new TextArea();
		edDescription.setCaption("Description");
		edDescription.setValue(article.Description);
		
		HorizontalLayout layout_btn_bottom = new HorizontalLayout();
		
		btnUpdate = new Button("Update");
		btnUpdate.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnUpdateClicked(edName.getValue(), edPrice.getValue(), (String)edCondition.getValue(), cbxPlace.getItemCaption(cbxPlace.getValue()), edDescription.getValue());
			}
		});
		
		btnDelete = new Button("Delete");
		btnDelete.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				onBtnDeleteClicked();
			}
		});
		
		mainLayout.addComponent(lblPriceNonInt);
		mainLayout.addComponent(edName);
		mainLayout.addComponent(edPrice);
		mainLayout.addComponent(edCondition);
		mainLayout.addComponent(cbxPlace);
		mainLayout.addComponent(edDescription);
		layout_btn_bottom.addComponent(btnUpdate);
		layout_btn_bottom.addComponent(btnDelete);
		mainLayout.addComponent(layout_btn_bottom);
		
		return mainLayout;
	}

	private void onBtnDeleteClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.deleteArticle(article);
	}

	private void onBtnUpdateClicked(String name, String price, String cond, String place, String desc) {
		Float fprice;
		try {
			fprice = Float.valueOf(price);
		} catch (NumberFormatException e) {
			lblPriceNonInt.setVisible(true);
			return;
		}
		
		if (name.isEmpty() || cond == null || place.isEmpty() || desc.isEmpty())
			return;
		
		for (EditArticlePageViewListener l : listeners)
			l.updateArticle(article, name, fprice, StudyTradeDefinitions.getConditionIndex(cond), place, desc);
	}

	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (EditArticlePageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		for (EditArticlePageViewListener l : listeners)
			l.searchClicked(searchstring);
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.advancedSearchClicked();
	}

	@Override
	protected void onShowMessage(String msg) {
		for (EditArticlePageViewListener l : listeners)
			l.onShowMessage(msg);
	}

	@Override
	protected void onButtonProfileClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.buttonProfileClicked();
	}

	@Override
	protected void onBtnAddArticleClicked() {
		for (EditArticlePageViewListener l : listeners)
			l.onAddArticle();
	}

	@Override
	public void onAfterInit() {
		forceLoggedIn();
		if (User != null && User.ID != article.Owner.ID) {
			onShowMessage("You must be the owner of this article to edit it.");
		}
	}

	@Override
	public StudyTradeArticle getArticle() {
		return article;
	}

	@Override
	protected void onArticleClicked(StudyTradeArticle artc) {
		for (EditArticlePageViewListener l : listeners)
			l.showArticleClicked(artc);
	}

	@Override
	protected void onFilterCategorieClicked(int cat) {
		for (EditArticlePageViewListener l : listeners)
			l.filterArticleByCondClicked(cat);
	}

	@Override
	protected void onFilterPlacesClicked(int plc) {
		for (EditArticlePageViewListener l : listeners)
			l.filterArticleByPlaceClicked(plc);
	}
}
