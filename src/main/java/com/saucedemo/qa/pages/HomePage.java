package com.saucedemo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//span[@class = 'title']")
	@CacheLookup
	WebElement pageheading;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	@CacheLookup
	WebElement CartBtn;

	@FindBy(xpath = "//select[@data-test ='product-sort-container']")
	@CacheLookup
	WebElement SortBtn;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String ValidatePageHeading() {
		return pageheading.getText();
	}

	public void SelectSortFilter(String value) {
		Select SortDropdown = new Select(SortBtn);
		SortDropdown.selectByValue(value);
	}

	public ProductPage clickOnProductName(String productName) {
		WebElement product = driver.findElement(By.linkText(productName));
		product.click();
		return new ProductPage();
	}

	public CartPage clickOnCartBtn() {
		CartBtn.click();
		return new CartPage();
	}

	public String VerifyAddToCartBtnText(String productName) {
		String Xpath = "//*[text()='" + productName + "']/ancestor::div[@class='inventory_item_description']//button";
		WebElement addToCartBtn = driver.findElement(By.xpath(Xpath));
		return addToCartBtn.getText();
	}

	public void ClickOnAddtoCartBtn(String productName) {
		String Xpath = "//*[text()='" + productName + "']/ancestor::div[@class='inventory_item_description']//button";
		WebElement addToCartBtn = driver.findElement(By.xpath(Xpath));
		addToCartBtn.click();
	}

	public boolean ValidateProductImage(String productName) {
		String Xpath = "//*[text()='" + productName + "']/ancestor::div[@class='inventory_item']//img";
		WebElement productImage = driver.findElement(By.xpath(Xpath));
		boolean flag = productImage.isDisplayed();
		return flag;
	}
}
