package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.HComplaintOcularPage;
import pages.eyeExaminationSearch.HEyeExaminationActionPage;
import utils.ConfigReader;

public class HComplaintOcularTest extends BaseTest {
	
	 private HEyeExaminationActionPage actionPage;
	    private HComplaintOcularPage ocularPage;

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
	        
	        ocularPage = new HComplaintOcularPage(driver);

	        // SEARCH AND OPEN PATIENT
	        actionPage.searchByDate("10-04-2026", "10-04-2026");
	        actionPage.clickFirstRowPlusIcon();
	    }

	    @Test
	    public void testComplaintAndOcularHistory() {

	        ocularPage.clickComplaintMenu();
	        ocularPage.addChiefComplaint();
	        ocularPage.addOcularHistory();
	    }

}
