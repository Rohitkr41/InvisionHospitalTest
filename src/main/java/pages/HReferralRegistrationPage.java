package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HReferralRegistrationPage extends BasePage{
	 public HReferralRegistrationPage(WebDriver driver) {
	        super(driver);
	    }

	    // Patient Type
	    By surveyRadio = By.xpath("//input[@value='survey']");
	    By communityClinicRadio = By.xpath("//input[@value='cc']");
	    By visionCentreRadio = By.xpath("//input[@value='vc']");

	    // Patient Search
	    By patientFirstName = By.name("searchRegisterDomain.PatientName");
	    By memberNumber = By.name("searchRegisterDomain.MemberNo");
	    By phoneNumber = By.name("searchRegisterDomain.PhoneNumber");
	    By searchButton = By.xpath("(//*[@id='main']//form//a)[2]");

	    // Fetch
	    By fetchdata = By.xpath("//table//tbody//tr[1]//td[7]//i");

	    // Confirm Yes
	    By yesbtn = By.xpath("//button[.='Yes']");

	    // Discount
	    By discountCheckbox = By.xpath("//*[@id='main']//div[3]//div[2]//div[4]//input");
	    By discountTextField = By.xpath("(//*[@id='main']//form//div[5]//input)[5]");

	    // Dropdowns
	 // Discount Remark Dropdown
	    By discountRemarkDropdown = By.xpath("//label[contains(text(),'Discount Remark')]/following::select[1]");

	    // Mode Dropdown
	    By modeDropdown = By.xpath("//label[contains(text(),'Mode')]/following::select[1]");

	    // Transaction Id Textfield
	    By transactionId = By.xpath("(//*[@id='main']//div[8]//input)[2]");

	    // Register Patient
	    By registerPatient = By.id("RM_btnSubmit");



	    // ===== ACTION METHODS =====

	    public void selectSurvey() {
	        click(surveyRadio);
	    }

	    public void selectCommunityClinic() {
	        click(communityClinicRadio);
	    }
	    
	    public void selectVisioncentra() {
	        click(visionCentreRadio);
	    }

	    public void enterPatientFirstName(String name) {
	        type(patientFirstName, name);
	    }

	    public void enterMemberNumber(String member) {
	        type(memberNumber, member);
	    }

	    public void enterPhoneNumber(String phone) {
	        type(phoneNumber, phone);
	    }

	    public void clickSearch() {
	        click(searchButton);
	    }

	    public void clickFetch() {
	        click(fetchdata);
	    }

	    public void clickYes() {
	        click(yesbtn);
	    }

	    public void clickDiscountCheckbox() {
	        click(discountCheckbox);
	    }

	    public void enterDiscountAmount(String amount) {
	        type(discountTextField, amount);
	    }

	 // Select Discount Remark (Dynamic & Safe)
	    public void selectDiscountRemark(String remark) {

	        waitForVisibility(discountRemarkDropdown);
//	        waitForVisible(discountRemarkDropdown);

	        Select dropdown = new Select(driver.findElement(discountRemarkDropdown));

	        List<WebElement> options = dropdown.getOptions();

	        for (WebElement option : options) {

	            String optionText = option.getText().trim();

	            if (optionText.equalsIgnoreCase(remark)) {
	                option.click();
	                return;
	            }
	        }

	        throw new RuntimeException("Discount Remark not found: " + remark);
	    }

	    // Select Mode
	    public void selectMode(String modeType) {
	        selectDropdown(modeDropdown, modeType);
	    }

	    // Enter Transaction ID
	    public void enterTransactionId(String txnId) {
	        type(transactionId, txnId);
	    }

	    public void clickRegisterPatient() {
//	        click(registerPatient);
	    }

	}