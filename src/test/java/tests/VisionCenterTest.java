package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import utils.ConfigReader;

public class VisionCenterTest extends BaseTest {

    @Test
    public void openVisionCenter() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Navigation
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickHospital();
    }
}
