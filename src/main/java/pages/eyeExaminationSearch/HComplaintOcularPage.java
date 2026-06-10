package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
    By complaintMenu = By.xpath("//*[@id='side-box-nav']/li[2]/a");

    // =============================
    // CHIEF COMPLAINT
    // =============================
    By chiefComplaintField = By.xpath("//input[@data-dropdown='complaintDropdown']");
    By eyeRE = By.id("RM_rdbREs");
    By periodField = By.id("numberInput");
    By durationDropdown = By.xpath("//*[@id='box-main']//select");
    By saveChiefComplaint = By.id("RM_btnSubmit");

    // =============================
    // OCULAR HISTORY
    // =============================
 // FIRST = Ocular History
    By ocularHistoryField = By.xpath("(//input[@data-dropdown='ocularDropdown'])[1]");

    // SECOND = Previous Treatment
    
    By ocularEyeRE = By.id("RM_rdbRE");
    By previousTreatment = By.xpath("(//input[@data-dropdown='ocularDropdown'])[2]");
    By remarksField = By.xpath("//label[text()='Remarks']/following::input[1]");
    By saveOcularHistory = By.xpath("(//button[@id='RM_btnSubmit'])[2]");

    // =============================
    // ALERT
    // =============================
    By duplicateAlertMsg = By.xpath("//div[contains(@class,'alert-body')]//p[contains(text(),'Cheif complaint already exist')]");
    By duplicateAlertOkBtn = By.xpath("//div[contains(@class,'alert-body')]//button[normalize-space()='OK']");

    // =============================
    // CLICK MENU
    // =============================
    public void clickComplaintMenu() {
        waitUntilModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(complaintMenu)).click();
    }

    // =============================
    // 🔥 UNIVERSAL DROPDOWN HANDLER
    // =============================
    private void selectFromDropdown(By inputLocator, String value) {

        WebElement input = waitUntilModalGoneAndVisible(inputLocator);

        input.click();
        input.clear();
        input.sendKeys(value);

        // wait for suggestion list
        By list = By.xpath("//ul[contains(@class,'suggestions-list')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(list));

        // exact match option
        By option = By.xpath("//li[contains(@class,'suggestion-item') and normalize-space()='" + value + "']");
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(option));

        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }

        // trigger blur (important)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", input);
    }

    // =============================
    // ADD CHIEF COMPLAINT
    // =============================
    public void addChiefComplaint() {

        waitUntilModalGone();

        selectFromDropdown(chiefComplaintField, "Eye strain");

        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(eyeRE)));

        WebElement period = waitUntilModalGoneAndVisible(periodField);
        period.clear();
        period.sendKeys("2");

        driver.findElement(durationDropdown).sendKeys("Days");

        driver.findElement(saveChiefComplaint).click();

        handleDuplicateAlert();

        waitUntilModalGone();
    }

    // =============================
    // ADD OCULAR HISTORY
    // =============================
    public void addOcularHistory() {

        waitUntilModalGone();

        selectFromDropdown(ocularHistoryField, "Conjunctivitis");

        WebElement radio = waitUntilModalGoneAndVisible(ocularEyeRE);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);

        selectFromDropdown(previousTreatment, "Glasses");

        WebElement remarks = waitUntilModalGoneAndVisible(remarksField);
        remarks.clear();
        remarks.sendKeys("No major issue");

        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveOcularHistory));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", save);

        try {
            save.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        }

        handleDuplicateAlert();

        waitUntilModalGone();
    }

    // =============================
    // ALERT HANDLER
    // =============================
    private void handleDuplicateAlert() {

        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(duplicateAlertMsg));

            if (alert.isDisplayed()) {

                WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(duplicateAlertOkBtn));

                try {
                    okBtn.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
                }

                wait.until(ExpectedConditions.invisibilityOf(alert));
            }

        } catch (Exception e) {
            // no alert आया तो ignore
        }
    }

    // =============================
    // COMMON METHODS
    // =============================
    private WebElement waitUntilModalGoneAndVisible(By locator) {
        waitUntilModalGone();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void clickWhenModalGone(WebElement element) {
        for (int i = 0; i < 5; i++) {
            try {
                waitUntilModalGone();
                element.click();
                return;
            } catch (Exception e) {
                try { Thread.sleep(200); } catch (Exception ignored) {}
            }
        }
    }
}