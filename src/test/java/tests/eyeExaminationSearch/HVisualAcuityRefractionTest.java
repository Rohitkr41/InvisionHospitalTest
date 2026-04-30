package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.HEyeExaminationActionPage;
import pages.eyeExaminationSearch.HVisualAcuityRefractionPage;
import utils.ConfigReader;

public class HVisualAcuityRefractionTest extends BaseTest {

    private HEyeExaminationActionPage actionPage;
    private HVisualAcuityRefractionPage visualPage;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN MODULE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // PAGE OBJECT
        actionPage = new HEyeExaminationActionPage(driver);
        visualPage = new HVisualAcuityRefractionPage(driver);

        // SEARCH PATIENT
        actionPage.searchByDate("14-04-2026", "15-04-2026");

        // OPEN PATIENT
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testVisualAcuityRefraction() {

        visualPage.clickVisualAcuityMenu();
//        visualPage.addVisualAcuity();
        visualPage.addRefraction();
    }
}
