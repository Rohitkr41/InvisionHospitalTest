package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class HVisualAcuityRefractionPage extends BasePage {

    public HVisualAcuityRefractionPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================
    By visualAcuityMenu = By.xpath("//*[@id='side-box-nav']/li[4]/a");

    // =============================
    // VISUAL ACUITY SECTION
    // =============================
    By wearingGlassesYes = By.id("RM_rdbYes");
    By periodField = By.id("numberInput");
    By durationDropdown = By.xpath("//*[@id='box-main']//div[2]/select");

    By ableCheckVisionYes = By.id("RM_rdbyess");
    By ableCheckVisionNo = By.id("RM_rdbnoss");

    By remarksField = By.xpath("(//label[contains(text(),'Remarks')]/following::input[1])[1]");
    By visionWithGlassesNo = By.xpath("(//*[@id='box-main']//div[2]/label/input)[3]");

    // =============================
    // PRESENTING VISION
    // =============================
    By reDVA = By.xpath("(//*[@id='box-main']//div[5]//input)[1]");
    
    By rePinhole = By.xpath("(//div[label[contains(text(),'Pinhole')]]//input)[1]");
    By reNVA = By.xpath("(//div[label[contains(text(),'NVA')]]//input)[1]");

    By leDVA = By.xpath("(//*[@id='box-main']//div[6]//div/input)[1]");
   
    By lePinhole = By.xpath("(//div[label[contains(text(),'Pinhole')]]//input)[2]");
    By leNVA = By.xpath("(//div[label[contains(text(),'NVA')]]//input)[2]");

    By saveVisualAcuity = By.xpath("//button[contains(text(),'Save Visual Acuity')]");

    // =============================
    // REFRACTION (RE only for now)
    // =============================
 // =============================
    // RIGHT EYE (RE)
    // =============================

    By rePrevSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[1]");
    By rePrevCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[1]");
    By rePrevAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[1]");
//    By rePrevADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[1]");
    By rePrevADD = By.xpath("(//*[@id=\"box-main\"]//div[1]/div[4]/div/input)[1]");

    By reDrySPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[2]");
    By reDryCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[2]");
    By reDryAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[2]");

    By reWetSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[3]");
    By reWetCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[3]");
    By reWetAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[3]");

    By reAccSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[4]");
    By reAccCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[4]");
    By reAccAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[4]");

    By reBCVA = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[2]");
    By reADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[5]");
    By reNVA1 = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[5]");
    By reNPC = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[5]");
    By reRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[6]");

    By reIOPTime = By.xpath("(//input[@type='time'])[1]");
    By reIOPValue = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[6]");
    By reIOPRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[8]//input)[1]");
    
    
    // =============================
    // LEFT EYE (LE)
    // =============================

    By lePrevSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[1]");
    By lePrevCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[7]");
    By lePrevAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[6]");
    By lePrevADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[3]");

    By leDrySPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[2]");
    By leDryCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[8]");
    By leDryAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[7]");

    By leWetSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[3]");
    By leWetCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[9]");
    By leWetAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[8]");

    By leAccSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[4]");
    By leAccCYL = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[10]");
    By leAccAXIS = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[9]");

    By leBCVA = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]//div[1]//div[4]/div/input)[4]");
    By leADD = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[5]");
    By leNVA1 = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[11]");
    By leNPC = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[10]");
    By leRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[6]");

    By leIOPTime = By.xpath("(//input[@type='time'])[2]");
    By leIOPValue = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[12]");
    By leIOPRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[8]//input)[2]");

    By saveRefraction = By.xpath("//button[contains(text(),'Save Refraction')]");

    // =============================
    // CLICK MENU
    // =============================
    public void clickVisualAcuityMenu() {
        waitUntilModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(visualAcuityMenu)).click();
    }

    // =============================
    // ADD VISUAL ACUITY
    // =============================
    public void addVisualAcuity() {

        waitUntilModalGone();

        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(wearingGlassesYes)));

        WebElement period = waitUntilModalGoneAndVisible(periodField);
        period.clear();
        period.sendKeys("2");

        driver.findElement(durationDropdown).sendKeys("Days");

        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(visionWithGlassesNo)));

        // 🔥 IMPORTANT WAIT (fix for your issue)
        wait.until(ExpectedConditions.elementToBeClickable(reDVA));

        // RE (Correct order as per your XPath)
        enterValue(reDVA, "6/6");
        
        enterValue(rePinhole, "6/18");
        enterValue(reNVA, "2/10");

        // LE
        enterValue(leDVA, "6/6");
        
        enterValue(lePinhole, "6/18");
        enterValue(leNVA, "2/10");

        clickWhenModalGone(wait.until(ExpectedConditions.elementToBeClickable(saveVisualAcuity)));

        waitUntilModalGone();
    }

    // =============================
    // ADD REFRACTION
    // =============================
    public void addRefraction() {
        
    	enterDropdownValue(rePrevSPH, "-1.00");
    	enterDropdownValue(rePrevCYL, "-0.50");
    	enterDropdownValue(rePrevAXIS, "160");
//        selectPreviousADD(rePrevADD, "2.50");
    	enterDropdownValue(rePrevADD, "2.25");


    	enterDropdownValue(reDrySPH, "-1.25");
    	enterDropdownValue(reDryCYL, "-0.50");
    	enterDropdownValue(reDryAXIS, "170");

    	enterDropdownValue(reWetSPH, "-1.00");
    	enterDropdownValue(reWetCYL, "-0.25");
    	enterDropdownValue(reWetAXIS, "175");

    	enterDropdownValue(reAccSPH, "-1.00");
    	enterDropdownValue(reAccCYL, "-0.50");
    	enterDropdownValue(reAccAXIS, "160");

    	enterDropdownValue(reBCVA, "2.75");
    	enterDropdownValue(reADD, "3.25");
    	enterDropdownValue(reNVA1, "2/8");
    	enterDropdownValue(reNPC, "20");

        enterValue(reRemarks, "Right eye normal");
        enterDropdownValue(reIOPValue, "20.6");
        enterValue(reIOPRemarks, "IOP normal");

        // 🔥 MID SCROLL (important for LE section)
        scrollToBottom();

        // =============================
        // LEFT EYE (LE)
        // =============================

        enterDropdownValue(lePrevSPH, "-1.25");
        enterDropdownValue(lePrevCYL, "-0.75");
        enterDropdownValue(lePrevAXIS, "170");
        enterDropdownValue(lePrevADD, "10");

        enterValue(leDrySPH, "-1.50");
        enterValue(leDryCYL, "-0.50");
        enterValue(leDryAXIS, "165");

        enterValue(leWetSPH, "-1.25");
        enterValue(leWetCYL, "-0.25");
        enterValue(leWetAXIS, "170");

        enterDropdownValue(leAccSPH, "-1.25");
        enterDropdownValue(leAccCYL, "-0.50");
        enterDropdownValue(leAccAXIS, "170");

        enterDropdownValue(leBCVA, "2.75");
        enterValue(leADD, "2.25");
        enterValue(leNVA1, "2/8");
        enterDropdownValue(leNPC, "3");

        enterValue(leRemarks, "Left eye normal");
        enterDropdownValue(leIOPValue, "20.6");
        enterValue(leIOPRemarks, "IOP normal");

        // 🔥 FINAL SCROLL before SAVE
        scrollToElement(saveRefraction);
        System.out.println("SPH: " + driver.findElement(rePrevSPH).getAttribute("value"));
        System.out.println("CYL: " + driver.findElement(rePrevCYL).getAttribute("value"));
        System.out.println("AXIS: " + driver.findElement(rePrevAXIS).getAttribute("value"));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveRefraction));
        saveBtn.click();

        //Alert hande
        handleVisualAcuityAlert();

    }
    
  //🔥 ScrollTill Bottom
  	public void scrollToBottom() {
  	 ((JavascriptExecutor) driver).executeScript(
  	     "window.scrollTo(0, document.body.scrollHeight)"
  	 );
  	}

    
    /**
     * Handle Visual Acuity / Refraction modal alert
     * @return alert message text
     */
    public String handleVisualAcuityAlert() {
        String message = "";

        // 🔹 Alert container
        By alertContainer = By.xpath("//p[contains(text(),'updated successfully!')]");

        // 🔹 OK button inside modal
        By okButton = By.xpath("(//button[contains(text(),'OK')])[3]");

        try {
            // 🔹 Wait for alert to appear
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(alertContainer));

            // 🔹 Read message
            message = alert.getText().trim();
            System.out.println("✅ ALERT MESSAGE: " + message);

            // 🔹 Click OK using JS to avoid overlay issues
            WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(okButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);

            // 🔹 Wait until alert disappears
            wait.until(ExpectedConditions.invisibilityOfElementLocated(alertContainer));

        } catch (TimeoutException e) {
            System.out.println("⚠️ Alert did not appear");
        } catch (Exception e) {
            System.out.println("⚠️ Error handling alert: " + e.getMessage());
        }

        return message;
    }



    // =============================
    // COMMON HELPERS
    // =============================
    
    
  private void enterValue(By locator, String value) {

    WebElement el = waitUntilModalGoneAndVisible(locator);

    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

    wait.until(ExpectedConditions.elementToBeClickable(el));

    // 🔥 SMALL WAIT (UI settle hone ke liye)
    try { Thread.sleep(300); } catch (Exception ignored) {}

    // 🔥 NATIVE SETTER + EVENTS
    ((JavascriptExecutor) driver).executeScript(
        "const element = arguments[0];" +
        "const val = arguments[1];" +
        "const setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
        "setter.call(element, val);" +
        "element.dispatchEvent(new Event('input', { bubbles: true }));" +
        "element.dispatchEvent(new Event('change', { bubbles: true }));" +
        "element.dispatchEvent(new Event('blur', { bubbles: true }));",
        el, value
    );

    // 🔥 WAIT UNTIL VALUE IS ACTUALLY SET (CRITICAL)
    int attempts = 0;
    while (attempts < 5) {
        String current = el.getAttribute("value");

        if (value.equals(current)) {
            break;
        }

        try { Thread.sleep(200); } catch (Exception ignored) {}
        attempts++;
    }

    // 🔥 EXTRA BLUR (safety for Angular)
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].blur();", el
    );

    // 🔥 FINAL SMALL WAIT
    try { Thread.sleep(300); } catch (Exception ignored) {}
}
  
  
	private void enterDropdownValue(By locator, String value) {

    WebElement el = waitUntilModalGoneAndVisible(locator);
    wait.until(ExpectedConditions.elementToBeClickable(el));

    el.click();

    // clear
    el.sendKeys(Keys.CONTROL + "a");
    el.sendKeys(Keys.DELETE);

    // type value
    el.sendKeys(value);

    // 🔥 WAIT FOR ANY DROPDOWN OPTIONS (not exact text)
    By options = By.xpath("//div[contains(@class,'option') or contains(@class,'menu') or contains(@class,'item')]");

    wait.until(ExpectedConditions.visibilityOfElementLocated(options));

    try {
        // 🔥 TRY CLICK EXACT MATCH (flexible)
        By exactOption = By.xpath("//*[contains(text(),'" + value + "')]");

        WebElement optionEl = wait.until(ExpectedConditions.visibilityOfElementLocated(exactOption));

        optionEl.click();

    } catch (Exception e) {

        // 🔥 FALLBACK (MOST RELIABLE)
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.ENTER);
    }

    // 🔥 FINAL BLUR
    el.sendKeys(Keys.TAB);

    try { Thread.sleep(300); } catch (Exception ignored) {}
}

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
}