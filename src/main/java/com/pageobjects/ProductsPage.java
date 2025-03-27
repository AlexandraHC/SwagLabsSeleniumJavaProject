package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private By sortDropdownLocator = By.xpath("//*[@data-test='product-sort-container']");
    private By itemPriceContainerLocator = By.className("inventory_item_price");
    private By addToCartBtnLocator = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By itemInCartItemListLocator = By.xpath("//*[@data-test='inventory-item-name']");

    public void filterProductsByPrice(String text){
        Select selectElementByText = new Select(driver.findElement(sortDropdownLocator));
        selectElementByText.selectByVisibleText(text);
    }

    public List<Double> getProductsPrice(){
        List<Double> prices = new ArrayList<>();
        List<WebElement> prods = driver.findElements(itemPriceContainerLocator);
        for(WebElement prod : prods)
        {
            if(!Objects.equals(prod.getText(), "$"))
            {
                String productPrice = prod.getText().replace("$", "");
                Double price = Double.parseDouble(productPrice);
                prices.add(price);
            }
        }
        return prices;
    }

    public boolean areTheNumbersOrdered(List<Double> list, boolean desc) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (desc) { // Check for descending order
                if (list.get(i) < list.get(i + 1)) {
                    return false; // Not sorted in descending order
                }
            } else { // Check for ascending order
                if (list.get(i) > list.get(i + 1)) {
                    return false; // Not sorted in ascending order
                }
            }
        }
        return true; // The list is sorted
    }

    public void clickAddToCartBtn(){
        driver.findElement(addToCartBtnLocator).click();
    }

    public boolean isItemInCartItemListDisplayed(){
        return driver.findElement(itemInCartItemListLocator).isDisplayed();
    }
}

