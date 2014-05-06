package com.studytrade.studytrade2.view.interfaces;

public interface RegisterPageViewListener {
	public void LoginClicked(String username, String password);
	public void SearchClicked(String searchstring);
	public void LogOffClicked();

	public void RegisterClicked(String forename, String lastname, String nickname, String place, int university, String studydirection, String email, String password);
}
