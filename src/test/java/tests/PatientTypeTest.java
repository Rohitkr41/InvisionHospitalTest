package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientTypePage;
import utils.ConfigReader;

public class PatientTypeTest extends BaseTest {

    @Test
    public void verifyPatientTypeSelection() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Sidebar → Registration
        SidebarPage sidebarPage = new SidebarPage(driver);
        sidebarPage.clickRegistration();

        // Patient Type Page
        PatientTypePage patientTypePage = new PatientTypePage(driver);

        patientTypePage.selectWalkIn();



        // Select Patient Types
        patientTypePage.selectWalkIn();
        patientTypePage.selectFollowUp();
        patientTypePage.selectReferral();
    }
}
