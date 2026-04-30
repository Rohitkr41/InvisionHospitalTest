
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        // Create Login Page Object
        LoginPage login = new LoginPage(driver);

        // Get credentials from config file
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        // Perform Login
        login.login(username, password);

        // Wait for dashboard page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("adminDashboard"));

        // Dashboard Header Locator
        By dashboardHeader = By.xpath("//h4[contains(text(),'Admin Dashboard')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));

        // Assertion
        Assert.assertTrue(
                driver.findElement(dashboardHeader).isDisplayed(),
                "❌ Login Failed - Dashboard not visible"
        );

        System.out.println("✅ Login Successful - Admin Dashboard Loaded");
    }
}
