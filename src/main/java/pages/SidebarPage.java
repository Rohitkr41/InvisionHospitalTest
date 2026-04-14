package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SidebarPage {

    WebDriver driver;
    WebDriverWait wait;

    public SidebarPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ======================
    // LOCATORS
    // ======================

    private By hospitalMenu = By.xpath("//span[contains(text(),'Hospital')]");
    
    private By CommunityClinicMenu = By.xpath("//span[contains(text(),'Community Clinic')]");
    private By registrationMenu = By.xpath("//span[normalize-space()='Registration']");
    private By patientReceiptMenu = By.xpath("//span[normalize-space()='Print Reciept']");
    private By eyeExaminationMenu = By.xpath("//*[@id=\"asidebar-nav\"]/li[5]/a/span");

    private By spectacleMenu = By.xpath("//span[contains(text(),'Spectacle')]");
    private By spectacleBookingMenu = By.xpath("//span[contains(text(),'Spectacle Booking')]");
    
    By serviceBilling = By.xpath("//span[contains(text(),'Service Billing')]");
    By billing = By.xpath("//span[normalize-space()='Billing']");
    By billingReceipt = By.xpath("//*[starts-with(@id,'mod_')]//span[normalize-space()='Billing Receipt']");
    
 // ======================
 // REPORT MENU
 // ======================

 private By reportMenu = By.xpath("//span[normalize-space()='Report']");
 private By reportDashboard = By.xpath("(//span[normalize-space()='Dashboard'])[2]");
//======================
//SURVEY REPORTS
//======================

private By surveyRegistration = By.xpath("//button[.='Registration']");

    public void openServiceBilling() {

        wait.until(ExpectedConditions.elementToBeClickable(serviceBilling)).click();
        wait.until(ExpectedConditions.elementToBeClickable(serviceBilling)).click();
    }
    
    // ======================
    // BILLING RECEIPT PAGE
    // ======================

    public void openBillingReceipt() {

        safeClick(serviceBilling);

        wait.until(ExpectedConditions.visibilityOfElementLocated(billingReceipt));

        safeClick(billingReceipt);
    }
    

    // ======================
    // COMMON SAFE CLICK
    // ======================

    private void safeClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        try {

            element.click();

        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    // ======================
    // VISION CENTER MENU
    // ======================

    public void clickHospital() {
        safeClick(hospitalMenu);
    }

    // ======================
    // REGISTRATION
    // ======================

    public void clickRegistration() {
        safeClick(registrationMenu);
    }

    // ======================
    // PRINT RECEIPT
    // ======================

    public void clickPatientReceipt() {
        safeClick(patientReceiptMenu);
    }

    // ======================
    // OPEN EYE EXAMINATION
    // ======================

    public void openEyeExamination() {

    	clickHospital();
        safeClick(eyeExaminationMenu);
    }

    // ======================
    // OPEN SPECTACLE BOOKING
    // ======================

    public void openSpectacleBooking() {

        safeClick(spectacleMenu);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(spectacleBookingMenu));

        safeClick(spectacleBookingMenu);
    }
    
 // ======================
 // OPEN REPORT DASHBOARD
 // ======================

 public void openReport() {

     safeClick(reportMenu);

     wait.until(ExpectedConditions
             .visibilityOfElementLocated(reportDashboard));

     safeClick(reportDashboard);
 }
//======================
//OPEN SURVEY REGISTRATION REPORT
//======================

public void clickSurveyRegistration() {

  wait.until(ExpectedConditions.visibilityOfElementLocated(surveyRegistration));

  safeClick(surveyRegistration);
}

public void clickCommunityClinic() {
	// TODO Auto-generated method stub
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(CommunityClinicMenu));

	  safeClick(CommunityClinicMenu);
	
}
}