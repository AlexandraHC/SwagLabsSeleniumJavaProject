package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    private By usernameInputLocator = By.id("user-name");
    private By passwordInputLocator = By.id("password");
    private By loginBtnLocator = By.name("login-button");
    private By validationErrorMessageLocator = By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3");

    public void navigate(){
        super.navigate("https://www.saucedemo.com/");
    }

    public void enterUsername(String username){
        driver.findElement(usernameInputLocator).clear();
        driver.findElement(usernameInputLocator).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordInputLocator).clear();
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickLoginBtn(){
        driver.findElement(loginBtnLocator).click();
    }

    public SuccessfulLoginPage executeLogin(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
        return new SuccessfulLoginPage(driver);
    }

    public String getErrorMessage(){
        return driver.findElement(validationErrorMessageLocator).getText();
    }

    public boolean isLoggedOut(){
        return isDisplayed(usernameInputLocator);
    }
}
