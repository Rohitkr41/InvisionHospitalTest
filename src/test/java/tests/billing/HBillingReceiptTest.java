package tests.billing;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.billing.HBillingReceiptPage; // ✅ सही import
import utils.ConfigReader;

public class HBillingReceiptTest extends BaseTest {

    private HBillingReceiptPage page; // ✅ FIXED TYPE

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN BILLING RECEIPT PAGE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openBillingReceipt();

        // PAGE OBJECT
        page = new HBillingReceiptPage(driver); // ✅ FIXED OBJECT
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
    }

    // ======================
    // ADVANCE SEARCH - PHONE
    // ======================

    @Test
    public void advanceSearchByPhone() {

        page.advanceSearchByPhone(
                "734986743969834",
                "07-04-2026",
                "10-04-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Phone");
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
    }

    // ======================
    // ADVANCE SEARCH - DATE
    // ======================

    @Test
    public void advanceSearchByDate() {

        page.advanceSearchByDate(
                "10-01-2026",
                "10-03-2026"
        );

        boolean result = page.isResultDisplayed();

        Assert.assertTrue(result, "No result found in Advance Date");

        if (result) {
            page.exportTableDataToExcel("ReceiptAdvanceDateSearch");
        }
    }
}