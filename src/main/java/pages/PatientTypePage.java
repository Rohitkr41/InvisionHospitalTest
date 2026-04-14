
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PatientTypePage extends BasePage {

    public PatientTypePage(WebDriver driver) {
        super(driver);
    }

    // Patient Type Dropdown
//    By patientTypeDropdown = By.xpath("//label[contains(text(),'Patient Type')]/following::select[1]");
    By patientTypeDropdown = By.xpath("(//label[contains(.,'Patient Type')]/ancestor::div[contains(@class,'form-group')]//select)[1]");


    // Generic Method (Ultra Stable)
   public void selectPatientType(String type) {

    WebElement dropdown = wait.until(
            ExpectedConditions.elementToBeClickable(patientTypeDropdown));

    Select select = new Select(dropdown);

    // wait for options
    wait.until(driver -> select.getOptions().size() > 1);

    // select value
    select.selectByVisibleText(type);

    // ✅ VERIFY selection
    wait.until(driver ->
            select.getFirstSelectedOption().getText().equalsIgnoreCase(type));

    // 🔥 IMPORTANT: trigger UI events
    JavascriptExecutor js = (JavascriptExecutor) driver;

    js.executeScript(
            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));" +
            "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
            "arguments[0].dispatchEvent(new Event('blur', {bubbles:true}));",
            dropdown
    );

    // 🔥 ALSO DO THIS (very important)
    dropdown.sendKeys(Keys.TAB);
}


    // Walk-In Patient
    public void selectWalkIn() {
        selectPatientType("Walk-In/New");
    }

    // Follow-Up Patient
    public void selectFollowUp() {
        selectPatientType("Followup/Old");
    }

    // Referral Patient
    public void selectReferral() {
        selectPatientType("Referral");
    }
}


