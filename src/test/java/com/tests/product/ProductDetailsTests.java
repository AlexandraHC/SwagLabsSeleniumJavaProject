package com.tests.product;

import com.pageobjects.LoginPage;
import com.pageobjects.ProductDetailsPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTests extends TestBase {

    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    public void setup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression", "smoke"})
    public void productsPage_clickOnAProduct_redirectToTheProductDetails(){
        logger.info("Starting clicking product item functionality");
        ProductDetailsPage product = new ProductDetailsPage(driver);
        product.clickOnAProduct();
        logger.info("Verify product item functionality");
        Assert.assertTrue(product.isBackToProductsBtnDisplayed());
        Assert.assertEquals(product.getCurrentUrl(), "https://www.saucedemo.com/inventory-item.html?id=4");
        Assert.assertTrue(product.getPageSource().contains("Sauce Labs Backpack"));
    }

    @Test(groups = {"regression", "smoke"})
    public void productDetails_clickOnAddToCart_productIsAddedInTheCart(){
        logger.info("Starting add to cart product item functionality");
        ProductDetailsPage product = new ProductDetailsPage(driver);
        product.clickOnAProduct();
        product.clickAddToCartBtn();
        product.clickShoppingCartLink();
        logger.info("Verify add to cart product item functionality");
        Assert.assertTrue(product.isItemInCartItemListDisplayed());
        Assert.assertEquals(product.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(product.getPageSource().contains("Your Cart"));
    }

    @Test(groups = {"regression", "smoke"})
    public void productsDetails_clickOnBackToProducts_redirectToProductsPage(){
        logger.info("Starting back to products functionality");
        ProductDetailsPage product = new ProductDetailsPage(driver);
        product.clickOnAProduct();
        product.clickBackToProductsBtn();
        logger.info("Verify back to products functionality");
        Assert.assertEquals(product.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(product.getPageSource().contains("Products"));
        Assert.assertTrue(product.isSortDropdownDisplayed());
    }
}
