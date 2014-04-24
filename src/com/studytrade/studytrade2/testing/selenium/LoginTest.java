package com.studytrade.studytrade2.testing.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class LoginTest {
	
	@Before
    public void setUp() throws Exception {
        //setUp("http://localhost:8080/StudyTrade2/", "*iexplore");
    }
    
	@Test
    public void testTempscript() throws Exception {
		WebDriver driver = new FirefoxDriver();

		WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(driver, "http://localhost:8080/");

		selenium.open("StudyTrade2/");

		selenium.click("debg_Mainpage_btn_00");
    }
}
