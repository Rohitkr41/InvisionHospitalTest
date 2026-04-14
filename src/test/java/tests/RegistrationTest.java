package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.RegistrationPage;
import utils.ConfigReader;

public class RegistrationTest extends BaseTest {

    @Test
    public void verifyRegistrationPageOpen() {

        // ✅ Step 1: Login First
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // ✅ Step 2: Sidebar Navigation
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickHospital();
        sidebar.clickRegistration();

        // ✅ Step 3: Verification
        RegistrationPage registrationPage = new RegistrationPage(driver);

        Assert.assertTrue(
                registrationPage.isRegistrationPageDisplayed(),
                "Registration Page is NOT opened"
        );
    }
}