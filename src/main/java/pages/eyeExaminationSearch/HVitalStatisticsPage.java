package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import pages.BasePage;

public class HVitalStatisticsPage extends BasePage {

    public HVitalStatisticsPage(WebDriver driver) {
        super(driver);
    }

    // MENU
    By vitalMenu = By.xpath("//p[.='Vital Statistics']");

    // FIELDS
    By systolicBP = By.xpath("//label[contains(text(),'Systolic Blood Pressure')]/following::input[1]");
    By diastolicBP = By.xpath("//label[contains(text(),'Diastolic Blood Pressure')]/following::input[1]");
    By pulseRate = By.xpath("//label[contains(text(),'Pulse Rate')]/following::input[1]");
    By height = By.xpath("//label[contains(text(),'Height')]/following::input[1]");
    By weight = By.xpath("//label[contains(text(),'Weight')]/following::input[1]");
    By bloodGlucose = By.xpath("//label[contains(text(),'Blood Glucose')]/following::input[1]");
    By remarks = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");

    // BUTTON
    By saveButton = By.xpath("//button[contains(text(),'Save Vital Statistics')]");

    // =============================
    // CLICK VITAL MENU
    // =============================

    public void clickVitalMenu() {

        waitForVisibility(vitalMenu);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(vitalMenu));

        click(vitalMenu);
    }

    // =============================
    // ENTER DATA
    // =============================

    public void enterSystolicBP(String value) {
        type(systolicBP, value);
    }

    public void enterDiastolicBP(String value) {
        type(diastolicBP, value);
    }

    public void enterPulseRate(String value) {
        type(pulseRate, value);
    }

    public void enterHeight(String value) {
        type(height, value);
    }

    public void enterWeight(String value) {
        type(weight, value);
    }

    public void enterBloodGlucose(String value) {
        type(bloodGlucose, value);
    }

    public void enterRemarks(String value) {
        type(remarks, value);
    }

    // =============================
    // SAVE
    // =============================

    public void saveVitalStatistics() {
    	
    	click(saveButton);
        waitForModalToDisappear();   // wait for popup
        closeSuccessAlert();

        
    }


    // =============================
    // COMPLETE FLOW
    // =============================

    public void addVitalStatistics() {

        enterSystolicBP("120");
        enterDiastolicBP("80");
        enterPulseRate("72");
        enterHeight("170");
        enterWeight("65");
        enterBloodGlucose("95");
        enterRemarks("Normal");

        saveVitalStatistics();
    }
}

