package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class HComplaintOcularPage extends BasePage {

    public HComplaintOcularPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================
    private final By complaintMenu =
            By.xpath("//*[@id='side-box-nav']/li[2]/a");

    // =============================
    // CHIEF COMPLAINT
    // =============================
    private final By chiefComplaintField =
            By.xpath("//input[@data-dropdown='complaintDropdown']");

    private final By eyeRE =
            By.id("RM_rdbREs");

    private final By periodField =
            By.id("numberInput");

    private final By durationDropdown =
            By.xpath("//*[@id='box-main']//select");

    private final By saveChiefComplaint =
            By.id("RM_btnSubmit");

    // =============================
    // OCULAR HISTORY
    // =============================
    private final By ocularHistoryField =
            By.xpath("(//input[@data-dropdown='ocularDropdown'])[1]");

    private final By ocularEyeRE =
            By.id("RM_rdbRE");

    private final By previousTreatment =
            By.xpath("(//input[@data-dropdown='ocularDropdown'])[2]");

    private final By remarksField =
            By.xpath("//label[text()='Remarks']/following::input[1]");

    private final By saveOcularHistory =
            By.xpath("(//button[@id='RM_btnSubmit'])[2]");

    // =============================
    // ALERT
    // =============================
    private final By duplicateAlertMsg =
            By.xpath("//div[contains(@class,'alert-body')]//p[contains(text(),'Cheif complaint already exist')]");

    private final By duplicateAlertOkBtn =
            By.xpath("//div[contains(@class,'alert-body')]//button[normalize-space()='OK']");

    // =============================
    // MENU
    // =============================
    public void clickComplaintMenu() {
        waitUntilModalGone();

        WebElement menu =
                wait.until(ExpectedConditions.elementToBeClickable(complaintMenu));

        safeClick(menu);
    }

    // =============================
    // CHIEF COMPLAINT
    // =============================
    public void addChiefComplaint() {

        waitUntilModalGone();

        selectFromDropdown(chiefComplaintField, "BLUR VISION");

        safeClick(waitVisible(eyeRE));

        WebElement period = waitVisible(periodField);
        period.clear();
        period.sendKeys("2");

        waitVisible(durationDropdown).sendKeys("Days");

        safeClick(waitVisible(saveChiefComplaint));

        handleDuplicateAlert();

        waitUntilModalGone();
    }

    // =============================
    // OCULAR HISTORY
    // =============================
   public void addOcularHistory() {

    System.out.println("Step 1 : Ocular History");

    selectFromDropdown(ocularHistoryField, "CATARACT");

    System.out.println("Step 2 : Eye Selection");

    safeClick(waitVisible(ocularEyeRE));

    System.out.println("Step 3 : Previous Treatment");

    selectFromDropdown(previousTreatment, "GLASSES");

    System.out.println("Step 4 : Remarks");

    WebElement remarks = waitVisible(remarksField);
    remarks.clear();
    remarks.sendKeys("No major issue");

    System.out.println("Step 5 : Save");

    WebElement save =
            wait.until(ExpectedConditions.elementToBeClickable(saveOcularHistory));

    safeClick(save);
}

    // =============================
    // DROPDOWN HANDLER
    // =============================
    private void selectFromDropdown(By inputLocator, String value) {

        WebElement input = waitVisible(inputLocator);

        input.click();
        input.clear();
        input.sendKeys(value);

        By suggestionList =
                By.xpath("//ul[contains(@class,'suggestions-list')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));

        By option = By.xpath(
                "//li[contains(@class,'suggestion-item') and contains(normalize-space(),'"
                        + value + "')]");

        WebElement item =
                wait.until(ExpectedConditions.elementToBeClickable(option));

        safeClick(item);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                input);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
                input);
    }

    // =============================
    // ALERT HANDLER
    // =============================
    private void handleDuplicateAlert() {

        try {

            WebElement alert =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateAlertMsg));

            if (alert.isDisplayed()) {

                WebElement okButton =
                        wait.until(ExpectedConditions.elementToBeClickable(duplicateAlertOkBtn));

                safeClick(okButton);

                wait.until(ExpectedConditions.invisibilityOf(alert));
            }

        } catch (TimeoutException ignored) {
            // Alert not displayed
        }
    }

    // =============================
    // COMMON METHODS
    // =============================
    private WebElement waitVisible(By locator) {

        waitUntilModalGone();

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void safeClick(WebElement element) {

        try {

            wait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();

        } catch (Exception e) {

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }
}
