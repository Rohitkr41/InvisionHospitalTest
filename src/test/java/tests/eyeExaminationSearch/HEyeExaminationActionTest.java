package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.HEyeExaminationActionPage;
import utils.ConfigReader;

public class HEyeExaminationActionTest extends BaseTest{
	private HEyeExaminationActionPage page;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN EYE EXAMINATION MODULE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // INITIALIZE PAGE OBJECT
        page = new HEyeExaminationActionPage(driver);
    }

    @Test
    public void testAdvanceSearchAndClickPlus() {

        // SEARCH USING DATE FILTER
        page.searchByDate("14-04-2026", "15-04-2026");

        // CLICK FIRST ROW PLUS ICON
        page.clickFirstRowPlusIcon();

        // DEBUG (OPTIONAL)
        System.out.println("Plus icon clicked successfully");
    }


}
