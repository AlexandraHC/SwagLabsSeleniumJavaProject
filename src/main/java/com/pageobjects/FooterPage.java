package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterPage extends BasePage{
    public FooterPage(WebDriver driver) {
        super(driver);
    }

    private By facebookSocialMediaLinkLocator = By.xpath("//*[@data-test='social-facebook']");
    private By linkedinSocialMediaLinkLocator = By.xpath("//*[@data-test='social-linkedin']");
    private By twitterSocialMediaLinkLocator = By.xpath("//*[@data-test='social-twitter']");

    private void clickOnSocialMediaLink(By locator){
        driver.findElement(locator).click();
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    private String getCurrentTabUrl(){
        return driver.getCurrentUrl();
    }

    public String clickOnFacebookSocialMediaLinkAndGetTheUrl(){
        clickOnSocialMediaLink(facebookSocialMediaLinkLocator);
        return getCurrentTabUrl();
    }

    public String clickOnTwitterSocialMediaLinkAndGetTheUrl(){
        clickOnSocialMediaLink(twitterSocialMediaLinkLocator);
        return getCurrentTabUrl();
    }

    public String clickOnLinkedInSocialMediaLinkAndGetTheUrl(){
        clickOnSocialMediaLink(linkedinSocialMediaLinkLocator);
        return getCurrentTabUrl();
    }
}
