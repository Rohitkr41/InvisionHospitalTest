package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class HComplaintOcularPage extends BasePage{
	
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
	    By chiefComplaintField = By.xpath("(//*[@id='box-main']//input)[1]");
	    By eyeRE = By.xpath("(//*[@id=\"box-main\"]/div//div[2]//div[2]//div[1]/label)[1]");
	    By periodField = By.id("numberInput");
	    By durationDropdown = By.xpath("//*[@id='box-main']//select");
	    By saveChiefComplaint = By.id("RM_btnSubmit");

	    // =============================
	    // OCULAR HISTORY
	    // =============================
	    By ocularHistoryField = By.xpath("//*[@id=\"box-main\"]//div[2]//div[2]//div[1]/input");
	    By ocularEyeRE = By.id("RM_rdbRE");
	    By previousTreatment = By.xpath("//*[@id=\"box-main\"]//div[2]/div/div[3]/input");
	    By remarksField = By.xpath("//*[@id=\"box-main\"]//div[2]/div/div[4]/input");
	    By saveOcularHistory = By.xpath("(//*[@id=\"RM_btnSubmit\"])[2]");

	    // =============================
	    // ALERT / MODAL
	    // =============================
	    By chiefComplaintAlert = By.xpath("//*[contains(text(),'Chief Complaint already exist!')]");
	    By modal = By.cssSelector(".custom-modal");

	    // =============================
	    // CLICK COMPLAINT MENU
	    // =============================
	    public void clickComplaintMenu() {
	        waitUntilModalGone();
	        wait.until(ExpectedConditions.elementToBeClickable(complaintMenu)).click();
	    }

	    // =============================
	    // ULTRA STABLE AUTOCOMPLETE SELECT
	    // =============================
	    public void selectChiefComplaint(String complaint) {
	        waitUntilModalGone();
	        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(chiefComplaintField));
	        field.clear();
	        field.sendKeys(complaint);

	        By suggestion = By.xpath("//li[contains(text(),'" + complaint + "')]");
	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);

	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	        } catch (Exception e) {
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	        }
	    }

	    // =============================
	    // ADD CHIEF COMPLAINT
	    // =============================
	    public void addChiefComplaint() {
	        selectChiefComplaint("Eye strain");

	        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(eyeRE)));

	        WebElement period = waitUntilModalGoneAndVisible(periodField);
	        period.clear();
	        period.sendKeys("2");

	        driver.findElement(durationDropdown).sendKeys("Days");
	        driver.findElement(saveChiefComplaint).click();

	        waitUntilModalGone();
	    }

	 public void addOcularHistory() {
	    // Ensure no modal is blocking
	    waitUntilModalGone();

	    // 1️⃣ Enter Ocular History
	    WebElement history = waitUntilModalGoneAndVisible(ocularHistoryField);
	    history.clear();
	    history.sendKeys("Conjunctivitis");

	    // Optional: click suggestion if exists
	    try {
	        By suggestion = By.xpath("//li[contains(text(),'Conjunctivitis')]");
	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    } catch (Exception ignored) {}

	    // 2️⃣ Click RE radio button (after modal gone)
	    WebElement radio = waitUntilModalGoneAndVisible(ocularEyeRE);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);

	 // Enter Previous Treatment
	    WebElement treatment = waitUntilModalGoneAndVisible(previousTreatment);
	    treatment.clear();
	    treatment.sendKeys("Glasses");

	    // Wait for the suggestion to appear and click it
	    try {
	        By suggestion = By.xpath("//li[contains(text(),'Glasses')]");
	        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    } catch (Exception e) {
	        throw new RuntimeException("Previous Treatment suggestion 'Glasses' not found");
	    }

	    // 4️⃣ Enter Remarks
	    WebElement remarks = waitUntilModalGoneAndVisible(remarksField);
	    remarks.clear();
	    remarks.sendKeys("No major issue");

	    WebElement save = waitUntilModalGoneAndVisible(saveOcularHistory);
	    clickWhenModalGone(save);

	    // 🔥 Force wait for DOM update (important)
	    try { Thread.sleep(800); } catch (Exception ignored) {}

	    handleAnyPopup();

	    
	    // 6️⃣ Final wait for any modal/alert
	    waitUntilModalGone();
	    
	    
	}

	    // =============================
	    // MODAL HANDLING
	    // =============================
	 
	    private WebElement waitUntilModalGoneAndVisible(By locator) {
	        waitUntilModalGone();
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    private void clickWhenModalGone(WebElement element) {
	        int attempts = 0;
	        while (attempts < 5) {
	            try {
	                waitUntilModalGone();
	                element.click();
	                return;
	            } catch (Exception e) {
	                try { Thread.sleep(200); } catch (Exception ignored) {}
	            }
	            attempts++;
	        }
	    }
	    
	    private void handleAnyPopup() {

	        By alertMsg = By.xpath(
	                "//div[contains(@class,'alert') and not(contains(@style,'display: none'))] | " +
	                "//div[contains(@class,'swal2-popup')] | " +
	                "//div[contains(@class,'toast')] | " +
	                "//p[contains(text(),'successfully') or contains(text(),'exist') or contains(text(),'Ocular')]"
	        );

	        By okBtn = By.xpath("(//button[normalize-space()='OK' or normalize-space()='Ok'])[3]");
	        By yesBtn = By.xpath("//button[normalize-space()='Yes']");

	        for (int i = 0; i < 5; i++) {
	            try {
	                WebElement msg = driver.findElement(alertMsg);

	                if (msg.isDisplayed()) {
	                    System.out.println("✅ Popup Found: " + msg.getText());

	                    // Try OK
	                    try {
	                        WebElement ok = driver.findElement(okBtn);
	                        if (ok.isDisplayed()) {
	                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ok);
	                            return;
	                        }
	                    } catch (Exception ignored) {}

	                    // Try YES
	                    try {
	                        WebElement yes = driver.findElement(yesBtn);
	                        if (yes.isDisplayed()) {
	                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yes);
	                            return;
	                        }
	                    } catch (Exception ignored) {}

	                    return;
	                }

	            } catch (Exception e) {
	                try { Thread.sleep(400); } catch (Exception ignored) {}
	            }
	        }

	        System.out.println("⚠️ No popup detected");
	    }

}
