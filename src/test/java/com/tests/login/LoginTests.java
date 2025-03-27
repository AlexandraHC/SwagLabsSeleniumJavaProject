package com.tests.login;

import com.pageobjects.LoginPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test(groups = {"positive", "regression", "smoke"})
    public void login_fillInWithValidCredentials_loginSuccessfullyAndRedirectToTheProductsPage()
    {
       logger.info("Starting test functionality");
       LoginPage loginPage = new LoginPage(driver);
       loginPage.navigate();
       SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
       logger.info("Verify the login functionality");
       Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
       Assert.assertTrue(successfulLoginPage.getPageSource().contains("Products"));
       Assert.assertTrue(successfulLoginPage.isLoggedIn());
    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void login_clickOnLogout_redirectToLoginForm()
    {
        logger.info("Starting test functionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
        successfulLoginPage.clickDropdownBurgerMenu();
        successfulLoginPage.load();
        logger.info("Verify the logout functionality");
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplayed());
        successfulLoginPage.clickLogoutBtn();
        Assert.assertTrue(loginPage.isLoggedOut());

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void login_fillInWithoutAtLeastOneMandatoryField_errorMessageIsDisplayed(String username, String password, String expectedErrorMessage) {
        logger.info("Starting test functionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.executeLogin(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }
}
