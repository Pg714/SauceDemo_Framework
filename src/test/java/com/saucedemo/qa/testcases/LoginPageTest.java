package com.saucedemo.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.qa.base.TestBase;
import com.saucedemo.qa.pages.HomePage;
import com.saucedemo.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		Initialization();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void LoginPageTitleTest() {
		String title = loginpage.ValidateLoginPageTitle();
		Assert.assertEquals(title, "Swag Labs");
	}

	@Test(priority = 2)
	public void LoginPageHeadingTest() {
		String heading = loginpage.ValidateLoginPageHeading();
		Assert.assertEquals(heading, "Swag Labs");
	}

	@Test(priority = 3)
	public void ValidLoginTest() {
		homepage = loginpage.ValidLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 4)
	public void InvalidLoginTest() {
		String error = loginpage.InvalidLogin("test", "test123");
		Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
