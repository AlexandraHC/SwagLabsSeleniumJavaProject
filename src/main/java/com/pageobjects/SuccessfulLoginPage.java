package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulLoginPage extends BasePage {

    private By logoutBtnLocator = By.linkText("Logout");
    private By productsDisplayedLocator = By.xpath("//*[@data-test='title']");
    private By dropdownBurgerMenuLocator = By.id("react-burger-menu-btn");

    public SuccessfulLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn()
    {
        return isDisplayed(productsDisplayedLocator);
    }

    public boolean isLogoutButtonDisplayed()
    {
        return isDisplayed(logoutBtnLocator);
    }

    public void clickDropdownBurgerMenu(){
        driver.findElement(dropdownBurgerMenuLocator).click();
    }

    public void load(){
        waitForElement(logoutBtnLocator);
    }

    public void clickLogoutBtn(){
        driver.findElement(logoutBtnLocator).click();
    }
}
