package com.studytrade.studytrade2.view.interfaces;

import com.studytrade.studytrade2.model.StudyTradeUser;

public interface UserPageView {
	public void addListener(UserPageViewListener listener);
	public void onAfterInit();
	
	public StudyTradeUser getDisplayUser();
}
