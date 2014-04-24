package com.studytrade.studytrade2.testing.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class LoginTest {
	
	@Before
    public void setUp() throws Exception {
        ///NOP
    }
    
	@Test
    public void testTempscript() throws Exception {
		WebDriver driver = new FirefoxDriver();

		WebDriverBackedSelenium selenium = new WebDriverBackedSelenium(driver, "http://localhost:8080/");

		selenium.open("StudyTrade2/");

		selenium.setTimeout("1000");
		
		selenium.waitForPageToLoad("1000");
		
		selenium.type("selendebug_CmnPg_ed_username", "Mikescher");
		selenium.type("selendebug_CmnPg_ed_passw", "test");
		
		selenium.click("selendebug_CmnPg_btn_login");

		selenium.waitForPageToLoad("1000");
		
		Assert.assertTrue(selenium.isTextPresent("Mikescher"));
		Assert.assertTrue(selenium.isTextPresent("Log Off"));
    }
}
