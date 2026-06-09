package tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HWalkInRegistrationPage;
import pages.LoginPage;
import pages.PatientTypePage;
import pages.SidebarPage;
import utils.ConfigReader;

public class HWalkInRegistrationTest extends BaseTest {


	@Test
	public void walkInRegistration() throws InterruptedException {

		// Login
		LoginPage login = new LoginPage(driver);
		login.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password")

		);

		int registrationCount = 2;

		for (int i = 1; i <= registrationCount; i++) {

		    System.out.println("Registration Number : " + i);

		    SidebarPage sidebar = new SidebarPage(driver);
		    sidebar.clickHospital();
		    sidebar.clickRegistration();

		    PatientTypePage pt = new PatientTypePage(driver);
		    pt.selectWalkIn();

		    HWalkInRegistrationPage reg = new HWalkInRegistrationPage(driver);
		    reg.registerWalkInPatient();

		 // Registration complete hone ke baad refresh
		    driver.navigate().refresh();

		}
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshotOnFailure(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE && driver != null) {

			try {

				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				FileUtils.copyFile(src, new File("./Screenshots/" + result.getName() + ".png"));

			} catch (Exception e) {

				System.out.println("Screenshot failed: " + e.getMessage());
			}
		}
	}

}
