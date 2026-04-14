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

//    By remarksField = By.xpath("(//*[@id='box-main']//div/input)[2]");
    By remarksField = By.xpath("(//label[contains(text(),'Remarks')]/following::input[1])[1]");
    By visionWithGlassesNo = By.xpath("(//*[@id='box-main']//div[2]/label/input)[3]");
    
    // =============================
    // PRESENTING VISION
    // =============================

    By reDVA = By.xpath("(//*[@id='box-main']//div[5]//input)[1]");
    By reNVA = By.xpath("(//*[@id='box-main']//div[5]//input)[2]");
    By rePinhole = By.xpath("(//*[@id='box-main']//div[5]//input)[3]");

    By leDVA = By.xpath("(//*[@id='box-main']//div[6]//div/input)[1]");
    By leNVA = By.xpath("(//*[@id='box-main']//div[6]//div/input)[2]");
    By lePinhole = By.xpath("(//*[@id='box-main']//div[6]//div/input)[3]");

    By saveVisualAcuity = By.xpath("//button[contains(text(),'Save Visual Acuity')]");

    // =============================
    // RIGHT EYE (RE)
    // =============================

    By rePrevSPH = By.xpath("(//*[@id=\"box-main\"]/div/div[2]/div/div[1]/div[2]//div[1]/div/input)[1]");
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
    // CLICK VISUAL ACUITY MENU
    // =============================

    public void clickVisualAcuityMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(visualAcuityMenu)).click();
    }

    // =============================
    // ADD VISUAL ACUITY
    // =============================

    public void addVisualAcuity() {

    wait.until(ExpectedConditions.elementToBeClickable(wearingGlassesYes)).click();

    // 🔥 FIXED
    clearAndType(periodField, "2");

    driver.findElement(durationDropdown).sendKeys("Week");

    wait.until(ExpectedConditions.elementToBeClickable(ableCheckVisionYes)).click();

    // 🔥 FIXED
    setAngularInput(reRemarks, "Vision normal");

    wait.until(ExpectedConditions.elementToBeClickable(visionWithGlassesNo)).click();

    selectFromAutocomplete(reDVA, "6/6");
    selectFromAutocomplete(reNVA, "2/8");
    selectFromAutocomplete(rePinhole, "6/9");

    selectFromAutocomplete(leDVA, "6/6");
    selectFromAutocomplete(leNVA, "2/8");
    selectFromAutocomplete(lePinhole, "6/6");

    // 🔥 SAVE FIX
    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveVisualAcuity));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

    // 🔥 ALERT FIX
    handleVisualAcuityAlert();
   
   

//    System.out.println("Refraction Alert: " + message);

    wait.until(ExpectedConditions.visibilityOfElementLocated(rePrevSPH));
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
    // ADD REFRACTION
    // =============================

  public void addRefraction() {

    waitForVisibility(rePrevSPH);

    // 🔥 SCROLL to refraction section start
    scrollToElement(rePrevSPH);

    // =============================
    // RIGHT EYE (RE)
    // =============================

    selectFromAutocomplete(rePrevSPH, "-1.00");
    selectFromAutocomplete(rePrevCYL, "-0.50");
    selectFromAutocomplete(rePrevAXIS, "180");
//    selectPreviousADD(rePrevADD, "2.50");
    selectPreviousADD(rePrevADD, "2.25");


    selectFromAutocomplete(reDrySPH, "-1.25");
    selectFromAutocomplete(reDryCYL, "-0.50");
    selectFromAutocomplete(reDryAXIS, "170");

    selectFromAutocomplete(reWetSPH, "-1.00");
    selectFromAutocomplete(reWetCYL, "-0.25");
    selectFromAutocomplete(reWetAXIS, "175");

    selectFromAutocomplete(reAccSPH, "-1.00");
    selectFromAutocomplete(reAccCYL, "-0.50");
    selectFromAutocomplete(reAccAXIS, "180");

    fastType(reBCVA, "2.75");
    fastType(reADD, "3.25");
    selectFromAutocomplete(reNVA1, "2/8");
    selectFromAutocomplete(reNPC, "20");

    fastType(reRemarks, "Right eye normal");
    selectFromAutocomplete(reIOPValue, "20.6");
    fastType(reIOPRemarks, "IOP normal");

    // 🔥 MID SCROLL (important for LE section)
    scrollToBottom();

    // =============================
    // LEFT EYE (LE)
    // =============================

    selectFromAutocomplete(lePrevSPH, "-1.25");
    selectFromAutocomplete(lePrevCYL, "-0.75");
    selectFromAutocomplete(lePrevAXIS, "170");
    setValueByJS(lePrevADD, "1.00");

    selectFromAutocomplete(leDrySPH, "-1.50");
    selectFromAutocomplete(leDryCYL, "-0.50");
    selectFromAutocomplete(leDryAXIS, "165");

    selectFromAutocomplete(leWetSPH, "-1.25");
    selectFromAutocomplete(leWetCYL, "-0.25");
    selectFromAutocomplete(leWetAXIS, "170");

    selectFromAutocomplete(leAccSPH, "-1.25");
    selectFromAutocomplete(leAccCYL, "-0.50");
    selectFromAutocomplete(leAccAXIS, "170");

    fastType(leBCVA, "2.75");
    selectFromAutocomplete(leADD, "2.25");
    selectFromAutocomplete(leNVA1, "2/8");
    selectFromAutocomplete(leNPC, "20");

    fastType(leRemarks, "Left eye normal");
    forceSetValue(leIOPValue, "20.6");
    fastType(leIOPRemarks, "IOP normal");

    // 🔥 FINAL SCROLL before SAVE
    scrollToElement(saveRefraction);

    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveRefraction));
    saveBtn.click();

    //Alert hande
    handleVisualAcuityAlert();

}


    
  public void selectFromAutocomplete(By locator, String value) {

    int attempts = 0;

    while (attempts < 3) {

        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator));

            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

            input.click();
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);
            input.sendKeys(value);

            // 🔥 wait dropdown
            By optionsLocator = By.xpath("//mat-option//span | //ul//li | //div[@role='option']");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionsLocator));

            Thread.sleep(500);

            for (WebElement option : driver.findElements(optionsLocator)) {

                String text = option.getText().trim();
                System.out.println("Option: " + text);

                // ✅ flexible match
                if (text.equalsIgnoreCase(value) || text.contains(value)) {
                    option.click();
                    return;
                }
            }

            // 🔥 FALLBACK (keyboard selection)
            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);

            return;

        } catch (Exception e) {
            System.out.println("Retrying autocomplete for value: " + value);
            attempts++;
        }
    }

    throw new RuntimeException("Value not found after retries: " + value);
}



	//Safe click with scroll
	public void safeClick(By locator) {
	 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	
	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	
	 try {
	     element.click();
	 } catch (Exception e) {
	     ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	 }
	}
	
	//🔥 ScrollTill Bottom
	public void scrollToBottom() {
	 ((JavascriptExecutor) driver).executeScript(
	     "window.scrollTo(0, document.body.scrollHeight)"
	 );
	}
	
	public void setValueByJS(By locator, String value) {
	    WebElement element = driver.findElement(locator);
	
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].value='" + value + "';", element);
	
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].dispatchEvent(new Event('change'));", element);
	}
	
	public void forceSetValue(By locator, String value) {
	    WebElement element = driver.findElement(locator);
	
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].value='" + value + "';", element);
	
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].dispatchEvent(new Event('input'));", element);
	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].dispatchEvent(new Event('change'));", element);
	}
	
	
	public void selectPreviousADD(By locator, String value) {
	
	    WebElement field = wait.until(ExpectedConditions.elementToBeClickable(locator));
	
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center'});", field);
	
	    // 🔥 STEP 1: click to focus
	    field.click();
	
	    // 🔥 STEP 2: SET VALUE DIRECTLY (CRITICAL)
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].value='" + value + "';", field);
	
	    // 🔥 STEP 3: TRIGGER ANGULAR EVENTS (VERY IMPORTANT)
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].dispatchEvent(new Event('input'));", field);
	
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].dispatchEvent(new Event('change'));", field);
	
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].dispatchEvent(new Event('blur'));", field);
	
	    // 🔥 STEP 4: WAIT FOR VALUE BIND
	    wait.until(driver -> {
	        String val = field.getAttribute("value");
	        return val != null && val.contains(value);
	    });
	
	    System.out.println("✅ FORCED ADD VALUE: " + field.getAttribute("value"));
	}

	public void clearAndType(By locator, String value) {

    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block:'center'});", element);

    element.click();

    // 🔥 HARD CLEAR (MULTIPLE LEVEL)
    element.sendKeys(Keys.CONTROL + "a");
    element.sendKeys(Keys.DELETE);

    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].value='';", element);

    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].dispatchEvent(new Event('input'));", element);

    // 🔥 TYPE
    element.sendKeys(value);

    // 🔥 VERIFY
    String val = element.getAttribute("value");
    if (val == null || val.trim().isEmpty()) {
        throw new RuntimeException("❌ Value not entered: " + value);
    }
}

	public void setAngularInput(By locator, String value) {
    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(locator));

    // Scroll element into view
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block:'center'});", input);

    // Clear field completely
    ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", input);
    input.sendKeys(Keys.CONTROL + "a");
    input.sendKeys(Keys.DELETE);

    // Set value
    ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "';", input);

    // Trigger Angular events
    ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('input'));", input);
    ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", input);
    ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('blur'));", input);

    // Wait until value is correctly set
    wait.until(driver -> {
        String val = input.getAttribute("value");
        return val != null && val.equals(value);
    });

    System.out.println("✅ Input set for: " + locator + " = " + value);
}
}
