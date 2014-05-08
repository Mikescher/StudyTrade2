package com.studytrade.studytrade2.view.interfaces;

import java.awt.event.ActionListener;

import com.studytrade.studytrade2.model.StudyTradeMessage;

public interface UserMessagePageView {
	public void addListener(UserMessagePageViewListener listener);
	public void onAfterInit();
	public StudyTradeMessage getMessage();
	public ActionListener getOKEvent();
}
