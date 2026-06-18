package pages.eyeExaminationSearch;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	By wearingGlassesNo = By.id("RM_rdbNo");

	By periodField = By.id("numberInput");

	By durationDropdown = By.xpath("//*[@id='box-main']//div[2]/select");

	By visionWithGlassesNo = By.xpath("(//*[@id='box-main']//div[2]/label/input)[3]");

	// ✅ RE — inputs 1,2,3
	By reDVA     = By.xpath("(//input[@data-dropdown='complaintDropdown'])[1]");
	By rePinhole = By.xpath("(//input[@data-dropdown='complaintDropdown'])[2]");
	By reNVA     = By.xpath("(//input[@data-dropdown='complaintDropdown'])[3]");

	// ✅ LE — inputs 4,5,6
	By leDVA     = By.xpath("(//input[@data-dropdown='complaintDropdown'])[4]");
	By lePinhole = By.xpath("(//input[@data-dropdown='complaintDropdown'])[5]");
	By leNVA     = By.xpath("(//input[@data-dropdown='complaintDropdown'])[6]");

	By saveVisualAcuity = By.xpath("//button[contains(text(),'Save Visual Acuity')]");
	
	// Is eye deferred?
	By yesDeferredRadio = By.name("RM_IsDefferedEyeYes");
	By noDeferredRadio  = By.name("RM_IsDefferedEyeNo");

	// Deferred Eye
	By reDeferredEye = By.name("RM_optionsRadiosGenderRE");
	By leDeferredEye = By.id("RM_rdbLE");
	By beDeferredEye = By.name("RM_optionsRadiosGenderBE");

	// =============================
	// RIGHT EYE (RE)
	// =============================

	By reSection = By.xpath("//h6[contains(.,'RE')]/ancestor::div[contains(@class,'row')]");

	By rePrevSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[1]");
	By rePrevCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[1]");
	By rePrevAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[1]");
	By rePrevADD = By.xpath("(//label[normalize-space()='ADD']/following::input[1])[1]");

	By reDrySPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[2]");
	By reDryCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[2]");
	By reDryAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[2]");

	By reWetSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[3]");
	By reWetCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[3]");
	By reWetAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[3]");

	By reAccSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[4]");
	By reAccCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[4]");
	By reAccAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[4]");

	By reBCVA = By.xpath("(//label[normalize-space()='BCVA']/following::input[1])[1]");

	By reADD = By.xpath("(//label[normalize-space()='ADD']/following::input[1])[2]");

	By reNPC = By.xpath("(//label[normalize-space()='NPC']/following::input[1])[1]");

	By reRemarks = By.xpath("(//label[contains(.,'Remarks')]/following::input[1])[3]");

	By reIOPTime = By.xpath("(//input[@type='time'])[1]");
	By reIOPValue = By.xpath("(//*[contains(text(),'IOP Value')]/following-sibling::input)[1]");

	By reIOPRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[8]//input)[1]");

	// =============================
	// LEFT EYE (LE)
	// =============================

	By lePrevSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[5]");
	By lePrevCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[5]");
	By lePrevAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[5]");
	By lePrevADD = By.xpath("(//label[normalize-space()='ADD']/following::input[1])[3]");

	By leDrySPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[6]");
	By leDryCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[6]");
	By leDryAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[6]");

	By leWetSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[7]");
	By leWetCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[7]");
	By leWetAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[7]");

	By leAccSPH = By.xpath("(//label[normalize-space()='SPH']/following::input[1])[8]");
	By leAccCYL = By.xpath("(//label[normalize-space()='CYL']/following::input[1])[8]");
	By leAccAXIS = By.xpath("(//label[normalize-space()='AXIS']/following::input[1])[8]");

	By leBCVA = By.xpath("(//label[normalize-space()='BCVA']/following::input[1])[2]");
	By leADD = By.xpath("(//label[normalize-space()='ADD']/following::input[1])[4]");
	By leNVA1 = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[2]//div[2]/div/input)[11]");
	By leNPC = By.xpath("(//*[@id=\"box-main\"]/div/div[2]//div[1]/div[2]//div[3]/div/input)[10]");
	By leRemarks = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[2]//div[1]/div[1]/div/input)[6]");

	By leIOPTime = By.xpath("(//input[@type='time'])[2]");
	By leIOPValue = By.xpath("(//*[contains(text(),'IOP Value')]/following-sibling::input)[2]");
	By leIOPRemarks = By.xpath("(//label[normalize-space()='Remarks']/following-sibling::input)[4]");

	By saveRefraction = By.xpath("//button[contains(text(),'Save Refraction')]");

	// =============================
	// CLICK MENU
	// =============================
	public void clickVisualAcuityMenu() throws InterruptedException {
		waitUntilModalGone();
		wait.until(ExpectedConditions.elementToBeClickable(visualAcuityMenu)).click();

		// ✅ ADD THIS DEBUG — prints exact page structure after menu click
		Thread.sleep(2000); // wait for page to load

		// Print all h6 text
		List<WebElement> h6s = driver.findElements(By.tagName("h6"));
		System.out.println("=== ALL H6 TEXT ON PAGE ===");
		for (WebElement h6 : h6s) {
			System.out.println("H6: [" + h6.getText() + "]");
		}

		// Print all complaintDropdown inputs
		List<WebElement> inputs = driver.findElements(By.xpath("//input[@data-dropdown='complaintDropdown']"));
		System.out.println("=== TOTAL complaintDropdown inputs: " + inputs.size());
		for (int i = 0; i < inputs.size(); i++) {
			WebElement inp = inputs.get(i);
			String label = (String) ((JavascriptExecutor) driver).executeScript(
					"const el = arguments[0];" + "const label = el.closest('.form-group')?.querySelector('label');"
							+ "return label ? label.textContent.trim() : 'no-label';",
					inp);
			System.out.println("Input[" + (i + 1) + "] label=[" + label + "]");
		}
	}

	// =============================
// ADD VISUAL ACUITY — main method
// =============================
	public void addVisualAcuity() throws InterruptedException {

		waitUntilModalGone();

		// ── Wearing Glasses = No ─────────────────────────────────────────
		WebElement wearingNo = wait.until(ExpectedConditions.elementToBeClickable(wearingGlassesNo));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", wearingNo);
		Thread.sleep(300);

		// ── Period ───────────────────────────────────────────────────────
		WebElement period = wait.until(ExpectedConditions.visibilityOfElementLocated(periodField));
		period.clear();
		period.sendKeys("2");

		// ── Duration ─────────────────────────────────────────────────────
		Select duration = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(durationDropdown)));
		duration.selectByVisibleText("Days");
		Thread.sleep(300);

		// ── RE Fields ────────────────────────────────────────────────────
		selectDropdownVision(reDVA, "6/6");
		selectDropdownVision(rePinhole, "6/18");
		selectDropdownVision(reNVA, "2/10");

		// ── LE Fields ────────────────────────────────────────────────────
		selectDropdownVision(leDVA, "6/6");
		selectDropdownVision(lePinhole, "6/18");
		selectDropdownVision(leNVA, "2/10");

		// ── Debug print ──────────────────────────────────────────────────
		System.out.println("RE DVA     : " + driver.findElement(reDVA).getAttribute("value"));
		System.out.println("RE Pinhole : " + driver.findElement(rePinhole).getAttribute("value"));
		System.out.println("RE NVA     : " + driver.findElement(reNVA).getAttribute("value"));
		System.out.println("LE DVA     : " + driver.findElement(leDVA).getAttribute("value"));
		System.out.println("LE Pinhole : " + driver.findElement(lePinhole).getAttribute("value"));
		System.out.println("LE NVA     : " + driver.findElement(leNVA).getAttribute("value"));

		// ── Save ─────────────────────────────────────────────────────────
		WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveVisualAcuity));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
		Thread.sleep(300);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

		System.out.println("✅ Visual Acuity Saved");
	}

// =============================
// FINAL selectDropdownVision
// =============================
	private void selectDropdownVision(By inputLocator, String value)
        throws InterruptedException {

    int maxRetries = 3;

    for (int attempt = 1; attempt <= maxRetries; attempt++) {
        try {
            waitUntilModalGone();

            // ── STEP 1: Locate and scroll to input ──────────────────
            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(inputLocator));

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            input);
            Thread.sleep(500);

            // ── STEP 2: Clear existing value ─────────────────────────
            String existing = input.getAttribute("value");
            if (existing != null && !existing.trim().isEmpty()) {
                System.out.println("🧹 Clearing: [" + existing + "]");
                ((JavascriptExecutor) driver).executeScript(
                        "const s = Object.getOwnPropertyDescriptor("
                        + "window.HTMLInputElement.prototype,'value').set;"
                        + "s.call(arguments[0],'');"
                        + "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));"
                        + "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                        input);
                Thread.sleep(200);
            }

            // ── STEP 3: Re-locate fresh then use Actions for real click
          
            input = wait.until(
                    ExpectedConditions.elementToBeClickable(inputLocator));

            Actions actions = new Actions(driver);
            actions.moveToElement(input)
                   .pause(Duration.ofMillis(200))
                   .click()
                   .perform();

            System.out.println("🖱️ Actions click fired for [" + value + "]");
            Thread.sleep(1000);

            // ── STEP 4: Check dropdown appeared ──────────────────────
            String debugResult = (String) ((JavascriptExecutor) driver).executeScript(
                    "const ul = document.querySelector('ul.suggestions-list');" +
                    "if (!ul) return 'NOT IN DOM';" +
                    "return 'FOUND | display=' + window.getComputedStyle(ul).display +" +
                    "' | children=' + ul.children.length;");
            System.out.println("🔍 Dropdown status: " + debugResult);

            // ── STEP 5: Find option in suggestions-list ───────────────
            By optionLocator = By.xpath(
                    "//ul[contains(@class,'suggestions-list')]"
                    + "//li[contains(@class,'suggestion-item')"
                    + "    and normalize-space(text())='" + value + "']");

            WebElement option = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(optionLocator));

            System.out.println("✅ Option found: [" + option.getText() + "]");

            // ── STEP 6: Click option via Actions ─────────────────────
            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'nearest'});",
                            option);
            Thread.sleep(200);

            // Re-locate option fresh before clicking
            option = wait.until(
                    ExpectedConditions.elementToBeClickable(optionLocator));

            actions = new Actions(driver);
            actions.moveToElement(option)
                   .pause(Duration.ofMillis(100))
                   .click()
                   .perform();

            Thread.sleep(400);

            // ── STEP 7: Verify value in input ─────────────────────────
            String actual = driver
                    .findElement(inputLocator)
                    .getAttribute("value");

            System.out.println("🔍 Field value after selection: [" + actual + "]");

            if (actual == null || actual.trim().isEmpty()) {
                ((JavascriptExecutor) driver).executeScript(
                        "const s = Object.getOwnPropertyDescriptor("
                        + "window.HTMLInputElement.prototype,'value').set;"
                        + "s.call(arguments[0],arguments[1]);"
                        + "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));"
                        + "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));"
                        + "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
                        driver.findElement(inputLocator), value);

                actual = driver.findElement(inputLocator).getAttribute("value");
                System.out.println("⚡ Forced via JS. Final: [" + actual + "]");
            }

            System.out.println("✅ Done [" + value + "] → verified: [" + actual + "]");
            return;

        } catch (Exception e) {
            System.out.println("⚠️ Attempt " + attempt + "/3 failed for ["
                    + value + "]: " + e.getMessage().split("\n")[0]);

            if (attempt == maxRetries) {
                throw new RuntimeException(
                        "❌ selectDropdownVision failed for [" + value
                        + "] after " + maxRetries + " attempts", e);
            }

            // Close dropdown before retry
            try {
                new Actions(driver).sendKeys(Keys.ESCAPE).perform();
                Thread.sleep(300);
            } catch (Exception ignored) {}

            Thread.sleep(800);
        }
    }
}

// =============================
// FIXED selectVisionValue METHOD
// =============================
	

	// 🔥 ScrollTill Bottom
	public void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Handle Visual Acuity / Refraction modal alert
	 * 
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

	/**
	 * @param isDeferred  true = Yes, false = No
	 * @param eye         RE / LE / BE
	 */
	public void handleDeferredEye(boolean isDeferred, String eye) {

	    if (isDeferred) {

	        // Select YES
	        wait.until(ExpectedConditions.elementToBeClickable(yesDeferredRadio))
	                .click();

	        // Wait until Deferred Eye section enabled
	        wait.until(ExpectedConditions.elementToBeClickable(getDeferredEyeLocator(eye)));

	        switch (eye.toUpperCase()) {

	            case "RE":
	                driver.findElement(reDeferredEye).click();
	                System.out.println("✅ Deferred Eye Selected : RE");
	                break;

	            case "LE":
	                driver.findElement(leDeferredEye).click();
	                System.out.println("✅ Deferred Eye Selected : LE");
	                break;

	            case "BE":
	                driver.findElement(beDeferredEye).click();
	                System.out.println("✅ Deferred Eye Selected : BE");
	                break;

	            default:
	                throw new IllegalArgumentException(
	                        "Invalid Eye Type. Allowed Values : RE, LE, BE");
	        }

	    } else {

	        // Select NO
	        wait.until(ExpectedConditions.elementToBeClickable(noDeferredRadio))
	                .click();

	        System.out.println("✅ Eye Deferred : NO");

	        // Optional Validation
	        WebElement reEye =
	                wait.until(ExpectedConditions.presenceOfElementLocated(reDeferredEye));

	        if (reEye.isEnabled()) {
	            throw new AssertionError(
	                    "Deferred Eye options should be disabled when NO is selected");
	        }
	    }
	}
	
	private By getDeferredEyeLocator(String eye) {

	    switch (eye.toUpperCase()) {

	        case "RE":
	            return reDeferredEye;

	        case "LE":
	            return leDeferredEye;

	        case "BE":
	            return beDeferredEye;

	        default:
	            throw new IllegalArgumentException("Invalid Eye Type : " + eye);
	    }
	}
	
	
	// =============================
	// ADD REFRACTION — FIXED
	// =============================
	public void addRefraction() throws InterruptedException {
		 handleDeferredEye(true, "RE");

	    // ── RE Fields ────────────────────────────────────────────────────
	    enterDropdownValue(rePrevSPH,  "-1.00");
	    enterDropdownValue(rePrevCYL,  "-0.50");
	    enterDropdownValue(rePrevAXIS, "35");
	    enterDropdownValue(rePrevADD,  "2.25");

	    enterDropdownValue(reDrySPH,  "-4.25");
	    enterDropdownValue(reDryCYL,  "-1.50");
	    enterDropdownValue(reDryAXIS, "35");

	    enterDropdownValue(reWetSPH,  "-1.00");
	    enterDropdownValue(reWetCYL,  "-0.25");
	    enterDropdownValue(reWetAXIS, "175");

	    enterDropdownValue(reAccSPH,  "-1.00");
	    enterDropdownValue(reAccCYL,  "-0.50");
	    enterDropdownValue(reAccAXIS, "160");

	    enterDropdownValue(reBCVA, "6/9P");
	    enterDropdownValue(reADD,  "3.25");
//	    enterDropdownValue(reNVA,  "2/8");
//	    enterDropdownValue(reNPC,  "20");
	    System.out.println("After RE Acceptance");

	    Long y = (Long) ((JavascriptExecutor) driver)
	            .executeScript("return window.pageYOffset;");

	    System.out.println("Scroll Position = " + y);

	    enterDropdownValue(reRemarks,    "Right eye normal");
	    enterDropdownValue(reIOPValue, "20.6");
//	    enterDropdownValue(reIOPRemarks, "IOP normal");

	 // Close any opened dropdown
	    new Actions(driver)
	            .sendKeys(Keys.ESCAPE)
	            .perform();

	    // Scroll to LE section
	    scrollToElement(lePrevSPH);

	    // Wait until clickable
	    wait.until(ExpectedConditions.elementToBeClickable(lePrevSPH));

	    System.out.println("✅ LE Section Visible");

	    // ── LE Fields ────────────────────────────────────────────────────
	    enterDropdownValue(lePrevSPH,  "-3.50");
	    enterDropdownValue(lePrevCYL,  "-0.75");
	    enterDropdownValue(lePrevAXIS, "170");
	    enterDropdownValue(lePrevADD,  "3.50");

	    enterDropdownValue(leDrySPH,  "-1.50");
	    enterDropdownValue(leDryCYL,  "-0.50");
	    enterDropdownValue(leDryAXIS, "165");

	    enterDropdownValue(leWetSPH,  "-1.25");
	    enterDropdownValue(leWetCYL,  "-0.25");
	    enterDropdownValue(leWetAXIS, "170");

	    enterDropdownValue(leAccSPH,  "-1.25");
	    enterDropdownValue(leAccCYL,  "-0.50");
	    enterDropdownValue(leAccAXIS, "170");

	    enterDropdownValue(leBCVA, "6/9P");
	    enterDropdownValue(leADD,  "2.25");
//	    enterDropdownValue(leNVA1, "2/8");
//	    enterDropdownValue(leNPC,  "3");

//	    enterDropdownValue(leRemarks,    "Left eye normal");
	    enterDropdownValue(leIOPValue, "20.6");
	    enterDropdownValue(leIOPRemarks, "IOP normal");

	    // 🔥 FINAL SCROLL before Save
	    scrollToElement(saveRefraction);
	    Thread.sleep(500);

	    // Debug print
	    System.out.println("SPH:  " + driver.findElement(rePrevSPH).getAttribute("value"));
	    System.out.println("CYL:  " + driver.findElement(rePrevCYL).getAttribute("value"));
	    System.out.println("AXIS: " + driver.findElement(rePrevAXIS).getAttribute("value"));

	    // Save
	    WebElement saveBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(saveRefraction));
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", saveBtn);

	    System.out.println("✅ Refraction Saved");

	    // Handle alert
	    handleVisualAcuityAlert();
	}
	
	public WebElement scrollToElement(By locator) {

	    WebElement element = wait.until(
	            ExpectedConditions.presenceOfElementLocated(locator));

	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center',inline:'nearest'});",
	            element);

	    wait.until(ExpectedConditions.visibilityOf(element));

	    try {
	        Thread.sleep(500);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		return element;
	}

	// =============================
	// FIXED enterDropdownValue
	// Uses Actions — same fix as selectDropdownVision
	// =============================
	private void enterDropdownValue(By locator, String value)
	        throws InterruptedException {

	    int maxRetries = 3;

	    for (int attempt = 1; attempt <= maxRetries; attempt++) {
	        try {
	            waitUntilModalGone();

	            // ── STEP 1: Scroll and wait for visibility ───────────────
	            WebElement el = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(locator));

	            ((JavascriptExecutor) driver)
	                    .executeScript(
	                            "arguments[0].scrollIntoView({block:'center'});",
	                            el);
	            Thread.sleep(300);

	            // ── STEP 2: Clear existing value via JS ──────────────────
	            String existing = el.getAttribute("value");
	            if (existing != null && !existing.trim().isEmpty()) {
	                ((JavascriptExecutor) driver).executeScript(
	                        "const s = Object.getOwnPropertyDescriptor("
	                        + "window.HTMLInputElement.prototype,'value').set;"
	                        + "s.call(arguments[0],'');"
	                        + "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));"
	                        + "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
	                        el);
	                Thread.sleep(200);
	            }

	            // ── STEP 3: Re-locate fresh then Actions click ───────────
	            el = wait.until(
	                    ExpectedConditions.elementToBeClickable(locator));

	            // ✅ Actions click — same fix that worked for Visual Acuity
	            new Actions(driver)
	                    .moveToElement(el)
	                    .pause(Duration.ofMillis(200))
	                    .click()
	                    .perform();

	            Thread.sleep(500);

	            // ── STEP 4: Check if suggestions-list appeared ────────────
	            String dropdownStatus = (String) ((JavascriptExecutor) driver)
	                    .executeScript(
	                            "const ul = document.querySelector('ul.suggestions-list');"
	                            + "if(!ul) return 'NOT IN DOM';"
	                            + "return 'FOUND|display='"
	                            + "+ window.getComputedStyle(ul).display"
	                            + "+'|children='+ ul.children.length;");

	            System.out.println("🔍 [" + value + "] dropdown: " + dropdownStatus);

	            if (dropdownStatus.contains("FOUND")) {
	                // ── STEP 5a: Dropdown opened — find and click option ──
	                By optionLocator = By.xpath(
	                        "//ul[contains(@class,'suggestions-list')]"
	                        + "//li[contains(@class,'suggestion-item')"
	                        + "    and normalize-space(text())='" + value + "']");

	                WebElement option = wait.until(
	                        ExpectedConditions.visibilityOfElementLocated(
	                                optionLocator));

	                ((JavascriptExecutor) driver)
	                        .executeScript(
	                                "arguments[0].scrollIntoView({block:'nearest'});",
	                                option);
	                Thread.sleep(200);

	                option = wait.until(
	                        ExpectedConditions.elementToBeClickable(optionLocator));

	                new Actions(driver)
	                        .moveToElement(option)
	                        .pause(Duration.ofMillis(100))
	                        .click()
	                        .perform();

	                System.out.println("✅ Selected from dropdown: [" + value + "]");

	            } else {
	                // ── STEP 5b: No dropdown — type value + ARROW_DOWN + ENTER
	                // (for plain input fields like Remarks, IOP etc.)
	                el = wait.until(
	                        ExpectedConditions.elementToBeClickable(locator));

	                el.sendKeys(value);
	                Thread.sleep(300);

	                // Check again if dropdown appeared after typing
	                dropdownStatus = (String) ((JavascriptExecutor) driver)
	                        .executeScript(
	                                "const ul = document.querySelector('ul.suggestions-list');"
	                                + "if(!ul) return 'NOT IN DOM';"
	                                + "return 'FOUND|display='"
	                                + "+ window.getComputedStyle(ul).display"
	                                + "+'|children='+ ul.children.length;");

	                if (dropdownStatus.contains("FOUND")) {
	                    By optionLocator = By.xpath(
	                            "//ul[contains(@class,'suggestions-list')]"
	                            + "//li[contains(@class,'suggestion-item')"
	                            + "    and normalize-space(text())='" + value + "']");
	                    try {
	                        WebElement option = new WebDriverWait(driver,
	                                Duration.ofSeconds(5))
	                                .until(ExpectedConditions
	                                        .visibilityOfElementLocated(optionLocator));
	                        new Actions(driver)
	                                .moveToElement(option)
	                                .click()
	                                .perform();
	                        System.out.println("✅ Selected after typing: [" + value + "]");
	                    } catch (Exception ignored) {
	                        el.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	                    }
	                } else {
	                    el.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	                    System.out.println("✅ Entered via keyboard: [" + value + "]");
	                }
	            }

	            Thread.sleep(300);

	            // ── STEP 6: Verify value committed ───────────────────────
	            String actual = driver
	                    .findElement(locator)
	                    .getAttribute("value");

	            if (actual == null || actual.trim().isEmpty()) {
	                // Force via JS as last resort
	                ((JavascriptExecutor) driver).executeScript(
	                        "const s = Object.getOwnPropertyDescriptor("
	                        + "window.HTMLInputElement.prototype,'value').set;"
	                        + "s.call(arguments[0],arguments[1]);"
	                        + "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));"
	                        + "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));"
	                        + "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
	                        driver.findElement(locator), value);

	                actual = driver.findElement(locator).getAttribute("value");
	                System.out.println("⚡ Forced via JS: [" + actual + "]");
	            }

	            System.out.println("✅ Done [" + value + "] → verified: [" + actual + "]");
	            return;

	        } catch (Exception e) {
	            System.out.println("⚠️ Attempt " + attempt + "/3 failed for ["
	                    + value + "]: " + e.getMessage().split("\n")[0]);

	            if (attempt == maxRetries) {
	                throw new RuntimeException(
	                        "❌ enterDropdownValue failed for [" + value
	                        + "] after " + maxRetries + " attempts", e);
	            }

	            try {
	                new Actions(driver).sendKeys(Keys.ESCAPE).perform();
	                Thread.sleep(300);
	            } catch (Exception ignored) {}

	            Thread.sleep(600);
	        }
	    }
	}
}
