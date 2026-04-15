package com.saucedemo.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.qa.base.TestBase;

public class ProductPage extends TestBase {

	@FindBy(xpath = "//img[@class = 'inventory_details_img']")
	@CacheLookup
	WebElement productImage;

	@FindBy(xpath = "//div[@class = 'inventory_details_name large_size']")
	@CacheLookup
	WebElement productName;

	@FindBy(xpath = "//button[text() = 'Add to cart']")
	@CacheLookup
	WebElement AddToCartBtn;

	@FindBy(xpath = "//button[text() = 'Remove']")
	@CacheLookup
	WebElement RemoveBtn;

	@FindBy(name = "back-to-products")
	@CacheLookup
	WebElement BackBtn;

	@FindBy(xpath = "//a[@class = 'shopping_cart_link']")
	@CacheLookup
	WebElement CartBtn;

	@FindBy(xpath = "//div[@class='inventory_details_price']")
	@CacheLookup
	WebElement productPrice;

	// Actions
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public String ValidateProductName() {
		return productName.getText();
	}

	public boolean ValidateProductImage() {
		return productImage.isDisplayed();
	}

	public String ValidateProductPrice() {
		return productPrice.getText();
	}

	public void ClickOnAddToCartBtn() {
		AddToCartBtn.click();
	}

	public void ClickOnRemoveBtn() {
		RemoveBtn.click();
	}

	public HomePage ClickOnBackBtn() {
		BackBtn.click();
		return new HomePage();
	}

	public CartPage ClickOnCartBtn() {
		CartBtn.click();
		return new CartPage();
	}
}
