package com.tests;

import com.pageobjects.CartPage;
import com.pageobjects.LoginPage;
import com.pageobjects.SuccessfulLoginPage;
import com.tests.login.LoginTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase {
    protected WebDriver driver;
    protected Logger logger;

    @BeforeMethod(alwaysRun = true)  //the setup method is executed for every test
    @Parameters("browser") //allow me to specify the browser type like Chrome or Firefox directly in my TestSuite xml file
    public void setup(@Optional("chrome")String browser)
    {
        //With this setup, the logger is now an instance variable of the login test class.
        logger = Logger.getLogger(LoginTests.class.getName());
        logger.setLevel(Level.INFO); //it gives me details about info level and what's happened in test methods
        logger.info("Running tests in " + browser);
        switch(browser.toLowerCase())
        {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + "is missing and running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void teardown()
    {
        driver.quit();
        logger.info("Browser is closed");
    }
}
