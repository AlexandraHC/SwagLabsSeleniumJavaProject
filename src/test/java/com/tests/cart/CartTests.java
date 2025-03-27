package com.tests.cart;

import com.pageobjects.CartPage;
import com.pageobjects.LoginPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends TestBase {

    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    public void setup(){
        //user needs to be logged in before the add to cart test functionality
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression", "smoke"})
    public void addToCart_availableProduct_productAddedInTheCart(){
        logger.info("Starting add to cart test");
        CartPage cartPage = new CartPage(driver);
        cartPage.clickAddToCartBtn();
        logger.info("Verify the addToCart functionality");
        Assert.assertTrue(cartPage.isAddToCartBadgeDisplayed());
        Assert.assertTrue(cartPage.isRemoveAddToCartBadgeDisplayed());
    }

    @Test(groups = {"regression", "smoke"})
    public void shoppingCart_clickOnLink_redirectToShoppingCart(){
        CartPage cartPage = new CartPage(driver);
        cartPage.clickShoppingCartLink();
        logger.info("Verify the shoppingCart functionality");
        Assert.assertEquals(cartPage.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.isCartTitleDisplayed(), "Your Cart");
        Assert.assertTrue(cartPage.isCheckoutBtnDisplayed());
    }

    @Test(groups = {"regression", "smoke"})
    public void shoppingCart_clickOnContinueShopping_redirectToProducts(){
        CartPage cartPage = new CartPage(driver);
        cartPage.clickShoppingCartLink();
        cartPage.clickShoppingCartBtn();
        SuccessfulLoginPage successfulLoginPage =  new SuccessfulLoginPage(driver);
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Products"));
    }

    @Test(groups = {"regression", "smoke"})
    public void shoppingCart_clickOnRemoveFromCart_productRemovedFromTheCart(){
        CartPage cartPage = new CartPage(driver);
        cartPage.clickAddToCartBtn();
        cartPage.clickShoppingCartLink();
        Assert.assertTrue(cartPage.isRemoveBtnDisplayed());
        cartPage.clickRemoveBtn();
        cartPage.clickContinueShoppingBtn();
        Assert.assertTrue(cartPage.isAddToCartBtn_BackpackDisplayed());
    }
}
