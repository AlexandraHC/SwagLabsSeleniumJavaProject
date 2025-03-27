package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameInputLocator = By.id("first-name");
    private By lastNameInputLocator = By.id("last-name");
    private By zipCodeInputLocator = By.id("postal-code");
    private By continueCheckoutBtnLocator = By.id("continue");
    private By cancelBtnLocator = By.id("cancel");
    private By checkoutTitleStepTwoLocator = By.xpath("//*[@id='header_container']/div[2]/span");
    private By errorMessageContainerLocator = By.className("error-message-container");
    private By errorMessageElementLocator = By.xpath("//*[@data-test='error']");
    private By finishCheckoutBtnLocator = By.id("finish");
    private By backToHomeBtnLocator = By.id("back-to-products");
    private By productsLocator = By.xpath("//*[@data-test='title']");


    public void enterFirstName(String firstName){
        driver.findElement(firstNameInputLocator).clear();
        driver.findElement(firstNameInputLocator).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        driver.findElement(lastNameInputLocator).clear();
        driver.findElement(lastNameInputLocator).sendKeys(lastName);
    }

    public void enterZipCode(String zipCode){
        driver.findElement(zipCodeInputLocator).clear();
        driver.findElement(zipCodeInputLocator).sendKeys(zipCode);
    }

    public void executeCheckout(String firstName, String lastName, String zipCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void clickContinueCheckoutButton(){
        driver.findElement(continueCheckoutBtnLocator).click();
    }

    public void clickCancelButton(){
        driver.findElement(cancelBtnLocator).click();
    }

    public boolean isStepTwoOfCheckoutDisplayed(){
        return isDisplayed(checkoutTitleStepTwoLocator);
    }

    public boolean isBackToHomeBtnDisplayed(){
        return isDisplayed(backToHomeBtnLocator);
    }

    public boolean isProductsPageDisplayed()
    {
        return isDisplayed(productsLocator);
    }

    public void clickFinishBtn(){
        driver.findElement(finishCheckoutBtnLocator).click();
    }

    public void clickBackToHomeBtn(){
        driver.findElement(backToHomeBtnLocator).click();
    }

    public String getErrorMessage(){
        WebElement errorMessageContainer = waitForElement(this.errorMessageContainerLocator);
        WebElement errorMessageElement = errorMessageContainer.findElement(errorMessageElementLocator);
        return errorMessageElement.getText();
    }
}
