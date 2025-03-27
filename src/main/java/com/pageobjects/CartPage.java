package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By addToCartBtn_BackpackLocator = By.id("add-to-cart-sauce-labs-backpack");
    private By removeItemBtn_BackpackLocator = By.id("remove-sauce-labs-backpack");
    private By addToCartBadgeLocator = By.xpath("//*[@id='shopping_cart_container']/a/span");
    private By shoppingCartLinkLocator = By.className("shopping_cart_link");
    private By checkoutBtnLocator = By.id("checkout");
    private By cartTitleLocator = By.xpath("//*[@id='header_container']/div[2]/span");
    private By continueShoppingBtnLocator = By.id("continue-shopping");
    private By removeBtnLocator = By.id("remove-sauce-labs-backpack");


    public void clickAddToCartBtn(){
        driver.findElement(addToCartBtn_BackpackLocator).click();
    }

    public void clickShoppingCartLink(){
        driver.findElement(shoppingCartLinkLocator).click();
    }

    public void clickCheckoutBtn(){
        driver.findElement(checkoutBtnLocator).click();
    }

    public boolean isAddToCartBadgeDisplayed(){
        return waitForElement(addToCartBadgeLocator).isDisplayed();
    }

    public boolean isRemoveAddToCartBadgeDisplayed(){
        return driver.findElement(removeItemBtn_BackpackLocator).isDisplayed();
    }

    public  boolean isCartTitleDisplayed(){
        return driver.findElement(cartTitleLocator).isDisplayed();
    }

    public boolean isCheckoutBtnDisplayed()
    {
        return isDisplayed(checkoutBtnLocator);
    }

    public void clickShoppingCartBtn(){
        driver.findElement(continueShoppingBtnLocator).click();
    }

    public boolean isRemoveBtnDisplayed()
    {
        return isDisplayed(removeBtnLocator);
    }

    public void clickRemoveBtn(){
        driver.findElement(removeBtnLocator).click();
    }

    public void clickContinueShoppingBtn(){
        driver.findElement(continueShoppingBtnLocator).click();
    }

    public boolean isContinueShoppingBtnDisplayed(){
        return isDisplayed(continueShoppingBtnLocator);
    }

    public boolean isAddToCartBtn_BackpackDisplayed(){
        return isDisplayed(addToCartBtn_BackpackLocator);
    }

}
