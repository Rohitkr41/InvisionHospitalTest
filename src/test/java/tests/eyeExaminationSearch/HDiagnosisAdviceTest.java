package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.HDiagnosisAdvicePage;
import pages.eyeExaminationSearch.HEyeExaminationActionPage;
import utils.ConfigReader;

public class HDiagnosisAdviceTest extends BaseTest {

    private HEyeExaminationActionPage actionPage;
    private HDiagnosisAdvicePage diagnosisPage;

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
        diagnosisPage = new HDiagnosisAdvicePage(driver);

        // SEARCH PATIENT
        actionPage.searchByDate("16-03-2026", "25-03-2026");
        actionPage.clickFirstRowPlusIcon();
        
    }

    @Test
    public void testDiagnosisAdvice() {
   
    	diagnosisPage.openDiagnosisMenu();
    	
    	diagnosisPage.addDiagnosis("Conjunctival cysts","Initial Stage");

    	diagnosisPage.addTeleconsultation(
    	        "V3M",
    	        "Invision Hospital",
    	        "Doctor");

    	diagnosisPage.addMedicine(
    	        "Drop",
    	        "Paracetamol",
    	        "10ml",
    	        "Twice a day",
    	        "5",
    	        "Use regularly");

    	diagnosisPage.addGlasses("Single lens");

    	diagnosisPage.addHospitalReferral(
    	        "Eye operation",
    	        "Invision hospital",
    	        "20-03-2026",
    	        "Urgent Case");

    	diagnosisPage.completeExamination();

    }
}
