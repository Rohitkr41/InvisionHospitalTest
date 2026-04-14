package tests.PatientReceipt;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.PatientReceipt.PatientReceiptPage;
import utils.ConfigReader;

public class PatientReceiptTest extends BaseTest {

    public PatientReceiptPage openPatientReceiptPage() {

        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.clickHospital();

        PatientReceiptPage page = new PatientReceiptPage(driver);
        sidebar.clickPatientReceipt(); // sidebar method hona chahiye

        return page;
    }

    // ============================
    // Search By Registration
    // ============================

    @Test
    public void searchByRegistrationNo() {

        PatientReceiptPage page = openPatientReceiptPage();

        page.searchByRegistration("REG12345");

        page.clickSearch();
    }

    // ============================
    // Search By Phone
    // ============================

    @Test
    public void searchByPhone() {

        PatientReceiptPage page = openPatientReceiptPage();

        page.searchByPhone("9876543210");

        page.clickSearch();
    }

    // ============================
    // Search By First Name
    // ============================

    @Test
    public void searchByFirstName() {

        PatientReceiptPage page = openPatientReceiptPage();

        page.searchByFirstName("Rohit");

        page.clickSearch();
    }

    // ============================
    // Advance Search
    // ============================

    @Test
    public void advanceSearchTest() {

        PatientReceiptPage page = openPatientReceiptPage();

        page.openAdvanceSearch();

        page.searchByDateVillage("01-03-2026", "10-03-2026", "Rampur");

        page.clickPopupSearch();
    }

}