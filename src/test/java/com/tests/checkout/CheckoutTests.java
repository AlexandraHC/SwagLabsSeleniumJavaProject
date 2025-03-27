package com.tests.checkout;

import com.pageobjects.*;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckoutTests extends TestBase {

    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    public void setup(){
        //user needs to be logged in before the add to cart test functionality
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
        //user needs to have products in cart before goes to checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.clickAddToCartBtn();
        cartPage.clickShoppingCartLink();
        cartPage.clickCheckoutBtn();
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkout_fillInWithValidCredentials_redirectToTheNextStep(){
        logger.info("Starting checkout test by filling form with valid credentials");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("Alexandra", "Smith", "705610");
        checkout.clickContinueCheckoutButton();
        logger.info("Verify the checkout functionality");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkout.getPageSource().contains("Checkout: Overview"));
        Assert.assertTrue(checkout.isStepTwoOfCheckoutDisplayed());
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkout_fillInWithInvalidCredentials_redirectToTheNextStep(){
        logger.info("Starting checkout test by filling form with invalid credentials");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("!@", "_)", "#@");
        checkout.clickContinueCheckoutButton();
        logger.info("Verify the checkout functionality");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkout.getPageSource().contains("Checkout: Overview"));
        Assert.assertTrue(checkout.isStepTwoOfCheckoutDisplayed());
    }

    @Parameters({"firstName", "lastName", "zipCode", "expectedErrorMessage"})
    @Test(groups = {"negative","regression"})
    public void checkout_fillInWithoutAtLeastOneMandatoryField_errorMessageIsDisplayed(String firstName, String lastName, String zipCode, String expectedErrorMessage){
        logger.info("Starting checkout test");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout(firstName, lastName, zipCode);
        checkout.clickContinueCheckoutButton();
        Assert.assertEquals(checkout.getErrorMessage(), expectedErrorMessage);
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkout_clickOnFinishCheckout_redirectToCheckoutComplete(){
        logger.info("Finishing the checkout functionality");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("Judith", "Cruise", "300400");
        checkout.clickContinueCheckoutButton();
        checkout.clickFinishBtn();
        logger.info("Verify the completion of checkout functionality");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertTrue(checkout.getPageSource().contains("Checkout: Complete!"));
        Assert.assertTrue(checkout.isBackToHomeBtnDisplayed());
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkoutCompleted_clickOnReturnHome_redirectToProductsPage(){
        logger.info("Finishing the checkout functionality");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("Judith", "Cruise", "300400");
        checkout.clickContinueCheckoutButton();
        checkout.clickFinishBtn();
        checkout.clickBackToHomeBtn();
        logger.info("Verify back to home functionality of checkout");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(checkout.getPageSource().contains("Products"));
        Assert.assertTrue(checkout.isProductsPageDisplayed());
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkout_clickOnCancel_redirectToTheCart(){
        logger.info("Starting cancel functionality from checkout form");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("Sarah", "Amalia", "200400");
        checkout.clickCancelButton();
        CartPage cartPage = new CartPage(driver);
        logger.info("Verify the cancel checkout functionality");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(checkout.getPageSource().contains("Your Cart"));
        Assert.assertTrue(cartPage.isContinueShoppingBtnDisplayed());
    }

    @Test(groups = {"positive","regression", "smoke"})
    public void checkout_clickOnCancelCheckoutStepTwo_redirectToProducts(){
        logger.info("Starting cancel functionality from checkout payment information");
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.executeCheckout("Sarah", "Amalia", "200400");
        checkout.clickContinueCheckoutButton();
        checkout.clickCancelButton();
        ProductsPage products = new ProductsPage(driver);
        logger.info("Verify the cancel checkout functionality");
        Assert.assertEquals(checkout.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(checkout.getPageSource().contains("Products"));
        Assert.assertTrue(checkout.isProductsPageDisplayed());
    }
}
