package com.tests.products;

import com.pageobjects.CartPage;
import com.pageobjects.LoginPage;
import com.pageobjects.ProductsPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsTests extends TestBase {

    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    public void setup(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression", "smoke"})
    public void productsDropdownFilter_sortDescendingByPrice_productsSortedDescendingByPrice(){
        logger.info("Starting sort dropdown functionality");
        ProductsPage products = new ProductsPage(driver);
        products.filterProductsByPrice("Price (high to low)");
        logger.info("Verify the sorting dropdown functionality by descending price");
        var productsPriceList = products.getProductsPrice();
        Assert.assertTrue(products.areTheNumbersOrdered(productsPriceList, true));
    }

    @Test(groups = {"regression", "smoke"})
    public void productsDropdownFilter_sortAscendingByPrice_productsSortedAscendingByPrice(){
        logger.info("Starting sort dropdown functionality");
        ProductsPage products = new ProductsPage(driver);
        products.filterProductsByPrice("Price (low to high)");
        logger.info("Verify the sorting dropdown functionality by ascending price");
        var productsPriceList = products.getProductsPrice();
        Assert.assertTrue(products.areTheNumbersOrdered(productsPriceList, false));
    }

    @Test(groups = {"regression", "smoke"})
    public void products_clickOnAddToCart_productIsAddedInTheCart(){
        logger.info("Starting add to cart functionality");
        ProductsPage products = new ProductsPage(driver);
        products.clickAddToCartBtn();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickShoppingCartLink();
        logger.info("Verify cart item list functionality");
        Assert.assertTrue(products.isItemInCartItemListDisplayed());
    }



}
