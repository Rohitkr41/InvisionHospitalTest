package pages.eyeExaminationSearch;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class HOcularMotilityPage extends BasePage {

    public HOcularMotilityPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================

    By ocularMenu = By.xpath("//*[@id='side-box-nav']/li[5]");

    // =============================
    // OCULAR MOTILITY FORM
    // =============================

    By ocularMotilityField = By.xpath("//label[contains(text(),'Ocular Motility')]/following::input[1]");
    By movementDropdown = By.xpath("//label[contains(text(),'Movement')]/following::select[1]");
    By remarksField = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");
    By saveOcularMotilityBtn = By.xpath("//button[contains(text(),'Save Ocular Motility')]");
    By savedRow = By.xpath("//td[contains(text(),'EXOTROPIA')]");

    // =============================
    // EXAMINATION SECTION
    // =============================

    By setNormalValueCheckbox = By.id("flexCheckChecked");
    By saveOcularExaminationBtn = By.xpath("(//*[@id=\"RM_btnSubmit\"])[2]");

    // =============================
    // ALERT
    // =============================

    By alertMessage = By.xpath("//*[contains(text(),'Ocular Motility already exist')]");
    By alertOkBtn = By.xpath("(//*[@id='main']//div[2]/button)[1]");
    By modal = By.cssSelector(".custom-modal");

    // =============================
    // CLICK OCULAR MENU
    // =============================

    public void clickOcularMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(ocularMenu)).click();
    }

    // =============================
    // ADD OCULAR MOTILITY
    // =============================

    public void addOcularMotility() {

        WebElement ocular = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ocularMotilityField));

        ocular.clear();
        ocular.sendKeys("EXOTROPIA");

        By suggestion = By.xpath("//*[text()='Exotropia']");

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(suggestion));

        option.click();

        WebElement movement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(movementDropdown));

        movement.sendKeys("Full");

        driver.findElement(remarksField).sendKeys("Eye movement normal");

        wait.until(ExpectedConditions.elementToBeClickable(saveOcularMotilityBtn)).click();

        // handle alert if exists
        handleAlertIfPresent();
    }

    // =============================
    // HANDLE ALERT
    // =============================

    public void handleAlertIfPresent() {
    try {
        // 1 second max wait for alert
        WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(alertMessage));

        if (alert.isDisplayed()) {
            WebElement ok = driver.findElement(alertOkBtn);
            ok.click();

            // wait modal disappear quickly
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.invisibilityOfElementLocated(modal));
        }

    } catch (Exception e) {
        // no alert — skip
    }
}

// =============================
// CLICK SET NORMAL VALUE (with wait before click)
// =============================
public void clickSetNormalValue() {
	
    handleAlertIfPresent(); // ensure modal closed

    // wait until label is clickable
    WebElement label = wait.until(ExpectedConditions.elementToBeClickable(setNormalValueCheckbox));

    try {
        label.click(); // click visible label instead of hidden input
    } catch (Exception e) {
        // fallback to JS click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", label);
    }
}
    // =============================
// SAVE OCULAR EXAMINATION (with success alert handling)
// =============================
public void saveOcularExamination() {

    handleAlertIfPresent(); // close any existing error alert

    WebElement saveBtn = wait.until(
            ExpectedConditions.elementToBeClickable(saveOcularExaminationBtn));

    saveBtn.click();

    // =============================
    // HANDLE SUCCESS ALERT
    // =============================
    try {
        // wait briefly for success alert to appear (max 3s)
        By successAlert = By.xpath("//*[contains(text(),'Ocular examination updated successfully')]");
        WebElement alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(successAlert));

        if (alert.isDisplayed()) {
            // find OK button in the modal
            WebElement okBtn = driver.findElement(By.xpath("(//*[@id='main']//div[2]/button)[1]"));
            okBtn.click();

            // wait for modal to disappear
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.invisibilityOfElementLocated(modal));
        }

    } catch (Exception e) {
        // success alert not present — skip
    }
}
}
