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
    public void setupPage() throws InterruptedException {

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
        actionPage.searchByDate("15-06-2026", "30-06-2026");

        // OPEN PATIENT
        actionPage.clickFirstRowPlusIcon();
    }

    @Test
    public void testVisualAcuityRefraction() throws InterruptedException {

        visualPage.clickVisualAcuityMenu();
        visualPage.addVisualAcuity();
        System.out.println(driver.switchTo().activeElement().getAttribute("value"));
        visualPage.addRefraction();
    }
}
