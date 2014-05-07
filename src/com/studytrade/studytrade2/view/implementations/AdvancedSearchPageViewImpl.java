package com.studytrade.studytrade2.view.implementations;

import java.util.ArrayList;
import java.util.List;

import com.studytrade.studytrade2.model.StudyTradeDefinitions;
import com.studytrade.studytrade2.model.StudyTradeUser;
import com.studytrade.studytrade2.view.interfaces.AdvancedSearchPageView;
import com.studytrade.studytrade2.view.interfaces.AdvancedSearchPageViewListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class AdvancedSearchPageViewImpl extends CustomStudyTradeComponent implements AdvancedSearchPageView {
	private static final long serialVersionUID = -2103599367448946610L;
	
	private List<AdvancedSearchPageViewListener> listeners = new ArrayList<>();

	private TextField edName;
	private TextField edStudyDirection;
	private TextField edMinPrice;
	private TextField edMaxPrice;
	private TextField edDescription;
	private NativeSelect cbxPlace;
	private NativeSelect cbxCondition;
	
	private Label lblPriceNonInt;
	private Button btnAdvancedSearch;
	
	public AdvancedSearchPageViewImpl(StudyTradeUser usr) {
		super(usr);
		
		Init();
		
		hideSearchbar();
	}
	
	@Override
	public void addListener(AdvancedSearchPageViewListener listener) {
		listeners.add(listener);
	}

	@Override
	protected Layout buildLayout() {
		VerticalLayout mainLayout = new VerticalLayout();
		
		mainLayout.setWidth("100%");
		
		HorizontalLayout tmp;

		mainLayout.addComponent(new Label("Detail Search:"));
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Name contains: "));
			edName = new TextField();
			edName.setId("selendebug_AdvSrPg_ed_name");
			tmp.addComponent(edName);
		}
		mainLayout.addComponent(tmp);
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Study Direction: "));
			edStudyDirection = new TextField();
			edStudyDirection.setId("selendebug_AdvSrPg_ed_direction");
			tmp.addComponent(edStudyDirection);
		}
		mainLayout.addComponent(tmp);
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Price between "));
			
			edMinPrice = new TextField();
			edMinPrice.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(ValueChangeEvent event) {
					lblPriceNonInt.setVisible(false);
				}
			});
			edMinPrice.setId("selendebug_AdvSrPg_ed_minprice");
			tmp.addComponent(edMinPrice);
			
			tmp.addComponent(new Label(" and "));
			
			tmp.addComponent(new Label("Price"));
			edMaxPrice = new TextField();
			edMaxPrice.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(ValueChangeEvent event) {
					lblPriceNonInt.setVisible(false);
				}
			});
			edMaxPrice.setId("selendebug_AdvSrPg_ed_maxprice");
			tmp.addComponent(edMaxPrice);
			
			lblPriceNonInt = new Label("Could not parse price");
			lblPriceNonInt.setVisible(false);
			tmp.addComponent(lblPriceNonInt);
		}
		mainLayout.addComponent(tmp);
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Description contains: "));
			edDescription = new TextField();
			edDescription.setId("selendebug_AdvSrPg_ed_description");
			tmp.addComponent(edDescription);
		}
		mainLayout.addComponent(tmp);
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Place: "));
			cbxPlace = new NativeSelect();
			StudyTradeDefinitions.addSelectItems_Places(cbxPlace);
			cbxPlace.setId("selendebug_AdvSrPg_cbx_place");
			tmp.addComponent(cbxPlace);
		}
		mainLayout.addComponent(tmp);
		
		tmp = new HorizontalLayout();
		{
			tmp.addComponent(new Label("Condition: "));
			cbxCondition = new NativeSelect();
			StudyTradeDefinitions.addSelectItems_Condition(cbxCondition);
			cbxCondition.setId("selendebug_AdvSrPg_cbx_condition");
			tmp.addComponent(cbxCondition);
		}
		mainLayout.addComponent(tmp);
		
		btnAdvancedSearch = new Button("Advanced Search");
		btnAdvancedSearch.setId("selendebug_AdvSrPg_btn_advsearch");
		btnAdvancedSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -4387688246523201319L;

			@Override
			public void buttonClick(ClickEvent event) {
				doAdvancedSearch();
			}
		});
		mainLayout.addComponent(btnAdvancedSearch);
		
		return mainLayout;
	}

	private void doAdvancedSearch() {
		String name = edName.getValue();
		String dir = edStudyDirection.getValue();
		
		String pmin = edMinPrice.getValue();
		String pmax = edMaxPrice.getValue();
		
		String desc = edDescription.getValue();
		String place = cbxPlace.getValue() == null ? "" : cbxPlace.getItemCaption(cbxPlace.getValue());
		String cond = cbxCondition.getValue() == null ? "" : cbxCondition.getItemCaption(cbxCondition.getValue());
		
		Float fmin = null;
		Float fmax = null;
		
		if (! (pmin.isEmpty() && pmax.isEmpty())) {
			try {
				fmin = Float.valueOf(pmin);
				fmax = Float.valueOf(pmax);
			} catch (NumberFormatException e) {
				lblPriceNonInt.setVisible(true);
				return;
			}
		}
		
		for (AdvancedSearchPageViewListener l : listeners)
			l.advancedSearch(name, dir, fmin, fmax, desc, place, cond);
	}
	
	@Override
	protected void onBtnLoginClicked(String username, String password) {
		for (AdvancedSearchPageViewListener l : listeners)
			l.loginClicked(username, password);
	}

	@Override
	protected void onBtnRegisterClicked() {
		for (AdvancedSearchPageViewListener l : listeners)
			l.registerClicked();
	}

	@Override
	protected void onBtnSearchClicked(String searchstring) {
		// Eventless
	}

	@Override
	protected void onBtnLogOffClicked() {
		for (AdvancedSearchPageViewListener l : listeners)
			l.logOffClicked();
	}

	@Override
	protected void onBtnAdvancedSearchClicked() {
		// Eventless
	}
}
