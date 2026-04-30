package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HFollowUpRegistrationPage extends BasePage {
	
	public HFollowUpRegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Patient Search
    By patientFirstName = By.name("searchRegisterDomain.PatientName");
    By RegistrationNo = By.name("searchRegisterDomain.MedicalNo");
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
    By discountRemarkDropdown = By.xpath("//label[contains(text(),'Discount Remark')]/following::select[1]");
    By modeDropdown = By.xpath("//label[contains(text(),'Mode')]/following::select[1]");

    // Transaction Id
    By transactionId = By.xpath("(//*[@id='main']//div[8]//input)[2]");

    // Register Patient
    By registerPatient = By.id("RM_btnSubmit");


    // ===== ACTION METHODS =====

    public void enterPatientFirstName(String name) {
        type(patientFirstName, name);
    }

    public void enterMemberNumber(String member) {
        type(RegistrationNo, member);
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

    public void selectDiscountRemark(String remark) {

        waitForVisibility(discountRemarkDropdown);

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

    public void selectMode(String modeType) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(modeDropdown));

        Select mode = new Select(dropdown);

        // wait for options
        wait.until(driver -> mode.getOptions().size() > 1);

        mode.selectByVisibleText(modeType);

        // ✅ verify selection
        wait.until(driver ->
                mode.getFirstSelectedOption().getText().equalsIgnoreCase(modeType));

        // 🔥 trigger frontend events
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
                dropdown
        );

        // 🔥 focus out (very important)
        dropdown.sendKeys(Keys.TAB);
    }

    public void enterTransactionId(String txnId) {
        type(transactionId, txnId);
    }

    public void clickRegisterPatient() {
        click(registerPatient);
    }
}

