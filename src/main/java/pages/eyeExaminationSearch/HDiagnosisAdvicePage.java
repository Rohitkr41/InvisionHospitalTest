package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;
import utils.AlertConfirmationPopup;

public class HDiagnosisAdvicePage extends BasePage{

		 public HDiagnosisAdvicePage(WebDriver driver) {
		        super(driver);
		    }
		    
		 
		    // =============================
		    // MENU
		    // =============================

		    private By diagnosisMenu = By.xpath("//p[contains(text(),'Diagnosis')]");

		    // =============================
		    // DIAGNOSIS
		    // =============================

		    private By diagnosisField = By.xpath("//label[contains(text(),'Diagnosis')]/following::input[1]");
		    private By eyeRE = By.xpath("(//input[@name='RM_optionsRadiosEye' and @id='RM_rdbRE'])[1]");
		    private	By eyeLE = By.xpath("//input[@name='RM_optionsRadiosEye' and @id='RM_rdbLE']");
		    private By remarks = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");
		    private By saveDiagnosisBtn = By.xpath("//button[contains(text(),'Save Diagnosis')]");

		    // =============================
		    // TELECONSULTATION
		    // =============================

		    private By teleYes = By.xpath("(//input[@type='radio'])[2]");
		    private By teleName = By.xpath("//label[contains(text(),'Name')]/following::input[1]");
		    private By organization = By.xpath("//label[contains(text(),'Organization')]/following::input[1]");
		    private By designation = By.xpath("//label[contains(text(),'Designation')]/following::input[1]");
		    private By teleSaveBtn = By.xpath("(//button[contains(text(),'Save')])[1]");

		    // =============================
		    // MEDICINE
		    // =============================

//		    private By prescribeMedicineCheckbox = By.xpath("//input[@type='checkbox' and contains(@id,'Pres')]");
		    private By prescribeMedicineCheckbox = By.xpath("//label[contains(text(),'Prescribe')]/preceding-sibling::input");
		    private By drugForm = By.xpath("//label[contains(text(),'Drug Form')]/following::select[1]");
		    private By drugName = By.xpath("//label[contains(text(),'Drug Name')]/following::input[1]");
		    private By dosageStrength = By.xpath("//label[contains(text(),'Dosage Strength')]/following::input[1]");
		    private By eyeREMed = By.id("RM_rdbREs");
		    private By dosageInstructions = By.xpath("//label[contains(text(),'Dosage Instructions')]/following::input[1]");
		    private By period = By.xpath("//label[contains(text(),'Period')]/following::input[1]");
		    private By specialInstructions = By.xpath("//label[contains(text(),'Special Instructions')]/following::input[1]");
		    private By addMedicineBtn = By.xpath("//button[contains(text(),'Add Medicine')]");

		    // =============================
		    // GLASSES
		    // =============================

		    private By glassesCheckbox = By.xpath("//label[contains(text(),'Glasses')]");
		    private By prescribeNew = By.id("RM_rdbPrescribeNEW");
		    private By lensesField = By.xpath("//label[contains(text(),'Lenses')]/following::input[1]");
		    private By glassesSaveBtn = By.xpath("(//button[contains(text(),'Save')])[2]");

		    // =============================
		    // HOSPITAL REFERRAL
		    // =============================

		    private By hospitalReferralCheckbox = By.xpath("//label[contains(text(),'Hospital Referral')]/preceding-sibling::input");
		    private By referralForDropdown = By.id("referralForInput");
		    private By referralCenter = By.xpath("//label[contains(text(),'Referal Center')]/following::select[1]");
		    private By expectedVisitDate = By.xpath("//label[contains(text(),'Expected Visit Date')]/following::input[1]");
		    private By referralRemarks = By.xpath("(//label[contains(text(),'Remarks')]/following::input[1])[2]");
		    private By referralSaveBtn = By.xpath("(//button[contains(text(),'Save')])[4]");

		    // =============================
		    // COMPLETE EXAMINATION
		    // =============================

		    private By saveCompleteExam = By.xpath("//button[contains(text(),'Save & Complete Examination')]");

		    // =========================================================
		    // SCROLL HELPER
		    // =========================================================

		    private void scrollTo(By locator) {

		        WebElement element = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(locator));

		        ((JavascriptExecutor) driver)
		                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		    }

		    // =========================================================
		    // MENU
		    // =========================================================

		    public void openDiagnosisMenu() {

		        scrollTo(diagnosisMenu);

		        click(diagnosisMenu);
		    }

		    // =========================================================
		    // DIAGNOSIS
		    // =========================================================

		    public void addDiagnosis(String diagnosis, String remarkText) {

		        // Wait for diagnosis field visible
		        waitForVisibility(diagnosisField);

		        // Fill diagnosis only if empty
		        String existingDiagnosis = driver.findElement(diagnosisField).getAttribute("value");
		        if (existingDiagnosis == null || existingDiagnosis.trim().isEmpty()) {
		            selectFromAutocomplete(diagnosisField, diagnosis);
		            // Ensure value entered
		            wait.until(ExpectedConditions.attributeContains(diagnosisField, "value", diagnosis));
		        }

		        // Select RE radio if not selected
		        WebElement reRadio = wait.until(ExpectedConditions.elementToBeClickable(eyeRE));
		        if (!reRadio.isSelected()) {
		            reRadio.click();
		        }

		        // Enter remarks
		        fastType(remarks, remarkText);

		        // Click Save button
		        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveDiagnosisBtn));
		        saveBtn.click();

		        // Handle popup (Success / Already Exist / Error)
		        AlertConfirmationPopup popup = new AlertConfirmationPopup(driver);
		        String message = popup.handlePopupFast();

		        System.out.println("Alert: " + message);
		    }

		    // =========================================================
		    // TELECONSULTATION
		    // =========================================================

		    public void addTeleconsultation(String name, String org, String role) {

		        scrollTo(teleYes);

		        click(teleYes);

		        type(teleName, name);

		        type(organization, org);

		        type(designation, role);

		        click(teleSaveBtn);

		        waitForModalToDisappear();

		        closeSuccessAlert();
		    }

		    // =========================================================
		    // MEDICINE
		    // =========================================================

		   public void addMedicine(String form, String name, String strength,
		                        String instruction, String days, String special) {

			   ensurePrescribeMedicineChecked();

		    waitForVisibility(drugForm);

		    selectByVisibleText(drugForm, form);

		    fastType(drugName, name);

		    fastType(dosageStrength, strength);

		    click(eyeREMed);

		    fastType(dosageInstructions, instruction);

		    fastType(period, days);

		    fastType(specialInstructions, special);

		    click(addMedicineBtn);

		    waitForModalToDisappear();

		    closeSuccessAlert();
		}

		    // =========================================================
		    // GLASSES
		    // =========================================================

		    public void addGlasses(String lenses) {

		        scrollTo(glassesCheckbox);

		        click(glassesCheckbox);

		        waitForVisibility(prescribeNew);

		        click(prescribeNew);

		        type(lensesField, lenses);

		        scrollTo(glassesSaveBtn);

		        click(glassesSaveBtn);

		        closeSuccessAlert();
		    }

		    // =========================================================
		    // HOSPITAL REFERRAL
		    // =========================================================

		    public void addHospitalReferral(String option,
		                                    String center,
		                                    String date,
		                                    String remarks) {

		        scrollTo(hospitalReferralCheckbox);

		        click(hospitalReferralCheckbox);

		        waitForVisibility(referralForDropdown);

		        click(referralForDropdown);

		        By optionLocator =
		                By.xpath("//input[@class='form-check-input referral-for-checkbox' and @value='" + option + "']");

		        click(optionLocator);

		        selectByVisibleText(referralCenter, center);

		        type(expectedVisitDate, date);

		        type(referralRemarks, remarks);

		        scrollTo(referralSaveBtn);

		        click(referralSaveBtn);

		        AlertConfirmationPopup popup = new AlertConfirmationPopup(driver);
		        popup.handlePopupFast();
		    }

		    // =========================================================
		    // COMPLETE EXAMINATION
		    // =========================================================

		    public void completeExamination() {

		        scrollTo(saveCompleteExam);

//		        click(saveCompleteExam);

		        waitForModalToDisappear();

		        AlertConfirmationPopup popup = new AlertConfirmationPopup(driver);
		        popup.handlePopupFast();
		    }
		    public void ensurePrescribeMedicineChecked() {

		        WebElement checkbox = wait.until(
		                ExpectedConditions.elementToBeClickable(prescribeMedicineCheckbox));

		        scrollToElement(checkbox);

		        if (!checkbox.isSelected()) {

		            ((JavascriptExecutor) driver)
		                    .executeScript("arguments[0].click();", checkbox);

		            wait.until(ExpectedConditions.elementToBeSelected(checkbox));
		        }
		    }
}