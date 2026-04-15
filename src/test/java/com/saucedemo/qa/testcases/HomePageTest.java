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

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ProductPage productPage;
	CartPage cartPage;
	
	String sheetName_Products = "Products";
	String sheetName_Filters = "Sort";

	public HomePageTest() {
		super();
	}
	
	@DataProvider
	public Object[][] getSauceDemoTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName_Products);
		return data;
	}
	
	@DataProvider
	public Object[][] getSauceDemoFilterTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName_Filters);
		return data;
	}

	@BeforeMethod
	public void setup() {
		Initialization();
		loginPage = new LoginPage();
		homePage = loginPage.ValidLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void ValidateHomePageHeadingTest() {
		String heading = homePage.ValidatePageHeading();
		Assert.assertEquals(heading, "Products", "Home Page Title dosen't match");
	}

	@Test(priority = 2, dataProvider="getSauceDemoFilterTestData")
	public void SelectSortFilterTest(String sortFilterValue) {
		homePage.SelectSortFilter(sortFilterValue);
	}

	@Test(priority = 3, dataProvider="getSauceDemoTestData")
	public void ValidateProductImageTest(String productName, String productPrice) {
		Assert.assertTrue(homePage.ValidateProductImage(productName), "Product Image not displayed");
	}

	@Test(priority = 4, dataProvider="getSauceDemoTestData")
	public void ClickOnProductNameTest(String productName, String productPrice) {
		productPage = homePage.clickOnProductName(productName);
	}

	@Test(priority = 5, dataProvider="getSauceDemoTestData")
	public void VerifyAddToCartBtnTextTest(String productName, String productPrice) {
		String btnText = homePage.VerifyAddToCartBtnText(productName);
		Assert.assertEquals(btnText, "Add to cart");
	}

	@Test(priority = 6, dataProvider="getSauceDemoTestData")
	public void ClickAddToCartBtnTest(String productName, String productPrice) {
		homePage.ClickOnAddtoCartBtn(productName);
		String btnText = homePage.VerifyAddToCartBtnText(productName);
		Assert.assertEquals(btnText, "Remove");
	}

	@Test(priority = 7)
	public void ClickOnCartBtnTest() {
		cartPage = homePage.clickOnCartBtn();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
