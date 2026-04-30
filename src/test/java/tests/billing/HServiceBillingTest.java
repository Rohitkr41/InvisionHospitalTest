package tests.billing;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.billing.HServiceBillingPage;
import utils.ConfigReader;

public class HServiceBillingTest extends BaseTest{
	private HServiceBillingPage page;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN SERVICE BILLING PAGE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openServiceBilling();

        // PAGE OBJECT
        page = new HServiceBillingPage(driver);
    }

    // ======================
    // TOP SEARCH - REGISTRATION
    // ======================

    @Test
    public void searchByRegistrationNumber() {

        page.searchByRegistration("IH-26-0085");
        page.clickTopSearch();

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found for Registration");

        if (result) {
//            page.exportTableDataToExcel("RegistrationSearch");
        }
    }

    // ======================
    // TOP SEARCH - PHONE
    // ======================

    @Test
    public void searchByPhoneNumber() {

        page.searchByPhone("734986743969834");
        page.clickTopSearch();

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found for Phone");

        if (result) {
//            page.exportTableDataToExcel("PhoneSearch");
        }
    }

    // ======================
    // TOP SEARCH - NAME
    // ======================

    @Test
    public void searchByPatientName() {

        page.searchByName("RAHUL");
        page.clickTopSearch();

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found for Name");

        if (result) {
//            page.exportTableDataToExcel("NameSearch");
        }
    }

    // ======================
    // ADVANCE SEARCH - REGISTRATION
    // ======================

    @Test
    public void advanceSearchByRegistration() {

        page.advanceSearchByRegistration(
                "IH-26-0085",
                "07-04-2026",
                "10-04-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Registration");

        if (result) {
//            page.exportTableDataToExcel("AdvanceRegistration");
        }
    }

    // ======================
    // ADVANCE SEARCH - PHONE
    // ======================

    @Test
    public void advanceSearchByPhone() {

        page.advanceSearchByPhone(
                "1234567891",
                "07-04-2026",
                "10-04-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Phone");

        if (result) {
//            page.exportTableDataToExcel("AdvancePhone");
        }
    }

    // ======================
    // ADVANCE SEARCH - NAME
    // ======================

    @Test
    public void advanceSearchByName() {

        page.advanceSearchByName(
                "RAHUL",
                "07-04-2026",
                "10-04-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Name");

        if (result) {
//            page.exportTableDataToExcel("AdvanceName");
        }
    }

    // ======================
    // ADVANCE SEARCH - DATE
    // ======================

    @Test
    public void advanceSearchByDate() {

        page.advanceSearchByDate(
        		 "07-03-2026",
                 "10-04-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Date");

        if (result) {
            page.exportTableDataToExcel("AdvanceDateSearch");
        }
    }

}
