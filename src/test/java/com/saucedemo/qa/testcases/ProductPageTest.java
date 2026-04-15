package com.saucedemo.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.qa.base.TestBase;
import com.saucedemo.qa.pages.CartPage;
import com.saucedemo.qa.pages.HomePage;
import com.saucedemo.qa.pages.LoginPage;
import com.saucedemo.qa.pages.ProductPage;
import com.saucedemo.qa.util.TestUtil;

public class ProductPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CartPage cartPage;
	
	String sheetName = "Products";
	
	public ProductPageTest() {
		super();
	}
	
	@DataProvider
	public Object[][] getSauceDemoTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@BeforeMethod
	public void setup() {
		Initialization();
		loginPage = new LoginPage();
		homePage = loginPage.ValidLogin(prop.getProperty("username"), prop.getProperty("password"));
//		productPage = homePage.clickOnProductName("Sauce Labs Backpack");
	}
	
	@Test(priority = 1, dataProvider="getSauceDemoTestData")
	public void ValidateProductNameTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		String name = productPage.ValidateProductName();
		Assert.assertEquals(name, productName);
	}
	
	@Test(priority = 2, dataProvider="getSauceDemoTestData")
	public void ValidateProductImageTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		Assert.assertTrue(productPage.ValidateProductImage());
	}
	
	@Test(priority = 3, dataProvider="getSauceDemoTestData")
	public void ValidateProductPriceTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		String price = productPage.ValidateProductPrice();
		Assert.assertEquals(price, productPrice);
	}
	
	@Test(priority = 4, dataProvider="getSauceDemoTestData")
	public void ClickOnAddToCartBtnTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		productPage.ClickOnAddToCartBtn();
	}
	
	@Test(priority = 5, dataProvider="getSauceDemoTestData")
	public void ClickOnRemoveBtnTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		productPage.ClickOnAddToCartBtn();
		productPage.ClickOnRemoveBtn();
	}
	
	@Test(priority = 6, dataProvider="getSauceDemoTestData")
	public void ClickOnBackButtonTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		homePage = productPage.ClickOnBackBtn();
	}
	
	@Test(priority = 7,  dataProvider="getSauceDemoTestData")
	public void ClickOnCartButtonTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
		cartPage = productPage.ClickOnCartBtn();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
