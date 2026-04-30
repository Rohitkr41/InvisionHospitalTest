package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HReferralRegistrationPage;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import utils.ConfigReader;

public class HReferralRegistrationTest extends BaseTest {
	
	@Test
    public void referralRegistration() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Open Vision Center → Registration
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickHospital();
        sidebar.clickRegistration();

        // Select Patient Type → Referral
        PatientTypePage patientType = new PatientTypePage(driver);
        patientType.selectReferral();

        // Referral Registration Page Actions
        HReferralRegistrationPage referral = new HReferralRegistrationPage(driver);

        referral.selectSurvey();   // OR referral.selectCommunityClinic()

//        referral.enterPatientFirstName("Rohit");
        referral.enterMemberNumber("228-14-10-08");
//        referral.enterPhoneNumber("9876543210");

        referral.clickSearch();
        referral.clickFetch();
        referral.clickYes();
        
        referral.clickDiscountCheckbox();

        referral.enterDiscountAmount("10");

        referral.selectDiscountRemark("Poor Patient");

        referral.selectMode("UPI");

        referral.enterTransactionId("TXN123456");
        
//        referral.clickRegisterPatient();

        System.out.println("Referral Registration Search Completed");
    }
}


