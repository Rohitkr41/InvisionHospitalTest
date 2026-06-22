package pages.eyeExaminationSearch;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

	private final By ocularMotilityField = By.xpath("//label[contains(text(),'Ocular Motility')]/following::input[2]");

	private final By movementDropdown = By.xpath("//label[contains(text(),'Movement')]/following::select[1]");

	private final By remarksField = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");

	private final By saveOcularMotilityBtn = By.xpath("//button[contains(text(),'Save Ocular Motility')]");

	// Refraction Type Radio Buttons
	By slitLampRadio = By.name("RM_optionsRefraction");

	By torchLightRadio = By
			.xpath("//input[@name='RM_optionsRefraction']/following-sibling::span[contains(text(),'Torchlight')]");

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

		System.out.println("Step 1 : Ocular Motility");

		selectFromDropdown(ocularMotilityField, "HYPOPHORIA");

		System.out.println("Step 2 : Movement");

		Select movement = new Select(wait.until(ExpectedConditions.elementToBeClickable(movementDropdown)));

		movement.selectByVisibleText("Full");
		System.out.println("Step 3 : Remarks");

		WebElement remarks = wait.until(ExpectedConditions.visibilityOfElementLocated(remarksField));

		remarks.clear();
		remarks.sendKeys("Eye movement normal");

		System.out.println("Step 4 : Save");

		WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveOcularMotilityBtn));

		safeClick(save);

		handleAnyPopup();
	}

	private void selectFromDropdown(By inputLocator, String value) {

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputLocator));

		input.click();
		input.clear();
		input.sendKeys(value);

		By suggestionList = By.xpath("//ul[contains(@class,'suggestions-list')]");

		wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));

		By option = By
				.xpath("//li[contains(@class,'suggestion-item') and contains(normalize-space(),'" + value + "')]");

		WebElement item = wait.until(ExpectedConditions.elementToBeClickable(option));

		safeClick(item);

		((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
				input);

		((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
				input);
	}

	public void selectRefractionType(String type) {

		By locator;

		if (type.equalsIgnoreCase("SlitLamp")) {
			locator = slitLampRadio;
		} else {
			locator = torchLightRadio;
		}

		WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(locator));

		try {
			radio.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
		}
	}

	// =============================
	// HANDLE ALERT
	// =============================

	private void handleAnyPopup() {

		try {

			// Wait for popup message
			WebElement alertMsg = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Ocular Motility already exist!')]")));

			System.out.println("✅ Popup Found: " + alertMsg.getText());

			// Click OK button
			WebElement okBtn = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
					.elementToBeClickable(By.xpath("(//button[normalize-space()='OK' or normalize-space()='Ok'])[3]")));

			try {
				okBtn.click();
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
			}

			// Wait for popup to disappear
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOf(alertMsg));

			System.out.println("✅ Popup handled successfully");

		} catch (Exception e) {
			System.out.println("⚠️ No 'Ocular Motility already exist!' popup detected");
		}
	}

// =============================
// CLICK SET NORMAL VALUE (with wait before click)
// =============================
	public void clickSetNormalValue() {

		handleAnyPopup(); // ensure modal closed

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

		WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveOcularExaminationBtn));

		saveBtn.click();

	}
}
