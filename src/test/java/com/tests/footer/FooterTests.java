package com.tests.footer;

import com.pageobjects.FooterPage;
import com.pageobjects.LoginPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTests extends TestBase {
    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    public void setup(){
        //user needs to be logged in before
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("standard_user", "secret_sauce");
    }

    @Test(groups = {"regression", "smoke"})
    public void footer_clickOnLinkedinLink_openANewTabWithTheRightUrl(){
        logger.info("Starting social media link functionality");
        FooterPage footer = new FooterPage(driver);
        String actualUrl = footer.clickOnLinkedInSocialMediaLinkAndGetTheUrl();
        logger.info("Verify current url of social media link");
        String expectedUrl = "https://www.linkedin.com/company/sauce-labs/";
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(groups = {"regression", "smoke"})
    public void footer_clickOnFacebookLink_openANewTabWithTheRightUrl(){
        logger.info("Starting social media link functionality");
        FooterPage footer = new FooterPage(driver);
        String actualUrl = footer.clickOnFacebookSocialMediaLinkAndGetTheUrl();
        logger.info("Verify current url of social media link");
        String expectedUrl = "https://www.facebook.com/saucelabs";
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test(groups = {"regression", "smoke"})
    public void footer_clickOnTwitterLink_openANewTabWithTheRightUrl(){
        logger.info("Starting social media link functionality");
        FooterPage footer = new FooterPage(driver);
        String actualUrl = footer.clickOnTwitterSocialMediaLinkAndGetTheUrl();
        logger.info("Verify current url of social media link");
        String expectedUrl = "https://x.com/saucelabs";
        Assert.assertEquals(actualUrl, expectedUrl);
    }

}
