package com.saucedemo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Object Repositories
	@FindBy(xpath = "//input[@id = 'user-name']")
	@CacheLookup
	WebElement username;

	@FindBy(xpath = "//input[@id = 'password']")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//input[@id = 'login-button']")
	@CacheLookup
	WebElement LoginBtn;

	@FindBy(xpath = "//div[@class = 'login_logo']")
	@CacheLookup
	WebElement heading;

	@FindBy(xpath = "//h3")
	WebElement error;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String ValidateLoginPageTitle() {
		return driver.getTitle();
	}

	public String ValidateLoginPageHeading() {
		return heading.getText();
	}

	public HomePage ValidLogin(String usr, String pwd) {
		username.sendKeys(usr);
		password.sendKeys(pwd);
		LoginBtn.click();

		return new HomePage();
	}

	public String InvalidLogin(String usr, String pwd) {
		username.sendKeys(usr);
		password.sendKeys(pwd);
		LoginBtn.click();

		return error.getText();
	}
}
