package com.studytrade.studytrade2.view.interfaces;

public interface RegisterPageViewListener {
	public void LoginClicked(String username, String password);
	public void RegisterCLicked(String forename, String lastname, String nickname, String place, String university, String studydirection, String email, String password);
	public void LogOffClicked();
}
