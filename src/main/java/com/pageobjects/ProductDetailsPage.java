package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By productItemNameLocator = By.xpath("//*[@data-test='inventory-item-name']");
    private By backToProductsBtnLocator = By.id("back-to-products");
    private By addToCartBtn_BackpackLocator = By.id("add-to-cart");
    private By shoppingCartLinkLocator = By.xpath("//*[@data-test='shopping-cart-link']");
    private By backpackItemInCartItemListLocator = By.xpath("//*[@data-test='inventory-item-name']");
    private By sortDropdownLocator = By.xpath("//*[@data-test='product-sort-container']");

    public void clickOnAProduct(){
        driver.findElement(productItemNameLocator).click();
    }

    public boolean isBackToProductsBtnDisplayed(){
        return isDisplayed(backToProductsBtnLocator);
    }

    public boolean isItemInCartItemListDisplayed(){
        return driver.findElement(backpackItemInCartItemListLocator).isDisplayed();
    }

    public void clickAddToCartBtn(){
        driver.findElement(addToCartBtn_BackpackLocator).click();
    }

    public void clickShoppingCartLink(){
        driver.findElement(shoppingCartLinkLocator).click();
    }

    public void clickBackToProductsBtn(){
        driver.findElement(backToProductsBtnLocator).click();
    }

    public boolean isSortDropdownDisplayed(){
        return driver.findElement(sortDropdownLocator).isDisplayed();
    }
}
