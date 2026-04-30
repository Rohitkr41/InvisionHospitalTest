package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HFollowUpRegistrationPage;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import utils.ConfigReader;

public class HFollowUpRegistrationTest extends BaseTest{
	@Test
    public void followUpRegistration() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                
                ConfigReader.getProperty("password")
        );

        // Open Registration
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickHospital();
        sidebar.clickRegistration();

        // Select FollowUp Patient Type
        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectFollowUp();

        // FollowUp Registration Flow
        HFollowUpRegistrationPage followup = new HFollowUpRegistrationPage(driver);

//        followup.enterPatientFirstName("Test");
        followup.enterMemberNumber("IH-26-0088");
//        followup.enterPhoneNumber("9876543210");

        followup.clickSearch();
        followup.clickFetch();
        followup.clickYes();

        followup.clickDiscountCheckbox();
        followup.enterDiscountAmount("5");

        followup.selectDiscountRemark("Poor Patient");

        followup.selectMode("UPI");

        followup.enterTransactionId("TXN12345");

        followup.clickRegisterPatient();

        System.out.println("FollowUp Registration Completed");
    }

}


