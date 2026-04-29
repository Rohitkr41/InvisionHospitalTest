

package pages.eyeExaminationSearch;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class HOcularMotilityPage extends BasePage {

    public HOcularMotilityPage(WebDriver driver) {
        super(driver);
    }

    By ocularMenu = By.xpath("//*[@id='side-box-nav']/li[5]");

    By ocularMotilityField = By.xpath("(//input[@data-dropdown='complaintDropdown'])[1]");
    By movementDropdown = By.xpath("//label[contains(text(),'Movement')]/following::select[1]");
    By remarksField = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");
    By saveOcularMotilityBtn = By.xpath("//button[contains(text(),'Save Ocular Motility')]");

    By setNormalValueCheckbox = By.id("flexCheckChecked");
    By saveOcularExaminationBtn = By.xpath("(//*[@id='RM_btnSubmit'])[2]");

    // ✅ FIXED ALERT LOCATORS
    By modal = By.cssSelector(".custom-modal");
    By alertText = By.cssSelector(".modal-body.alert-body p");
    By okButton = By.xpath("(//div[contains(@class,'alert-body')]//button[normalize-space()='OK'])[3]");
    
    By Torchlight = By.xpath("//span[text()='Torchlight']");
    By SlitLamp = By.xpath("//span[text()='SlitLamp']");
    

    public void clickOcularMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(ocularMenu)).click();
    }

    public void addOcularMotility() {

        WebElement ocular = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ocularMotilityField));

        ocular.clear();
        ocular.sendKeys("EXOTROPIA");

        By suggestion = By.xpath("//*[text()='Exotropia']");
        wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();

        WebElement movement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(movementDropdown));
        movement.sendKeys("Full");

        driver.findElement(remarksField).sendKeys("Eye movement normal");

        wait.until(ExpectedConditions.elementToBeClickable(saveOcularMotilityBtn)).click();

        // 🔥 MUST HANDLE POPUP
        handleModalUntilGone();
    }

    // ✅ FINAL MODAL HANDLER (MOST IMPORTANT)
    public void handleModalUntilGone() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) { // handle multiple popups
            try {
                WebElement modalElement = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(modal));

                // print message
                try {
                    WebElement msg = driver.findElement(alertText);
                    System.out.println("Alert: " + msg.getText());
                } catch (Exception ignored) {}

                WebElement okBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(okButton));

                try {
                    okBtn.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", okBtn);
                }

                wait.until(ExpectedConditions.invisibilityOf(modalElement));

            } catch (TimeoutException e) {
                break;
            }
        }
    }

    // ✅ ENSURE NO OVERLAY BEFORE ACTION
    public void waitForNoOverlay() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(modal));
    }
    
    public void clickExaminationRadioBtn() {
    	 handleModalUntilGone(); // 🔥 MUST
         waitForNoOverlay();

         WebElement clickRadioBtn = wait.until(
                 ExpectedConditions.elementToBeClickable(Torchlight));

         try {
        	 clickRadioBtn.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver)
                     .executeScript("arguments[0].click();", clickRadioBtn);
         }
    }

    public void clickSetNormalValue() {

        handleModalUntilGone(); // 🔥 safety
        waitForNoOverlay();

        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(setNormalValueCheckbox));

        try {
            checkbox.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", checkbox);
        }
    }

    public void saveOcularExamination() {

        handleModalUntilGone(); // 🔥 MUST
        waitForNoOverlay();

        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(saveOcularExaminationBtn));

        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", saveBtn);
        }
        System.out.println("test change");
    }
   
}

