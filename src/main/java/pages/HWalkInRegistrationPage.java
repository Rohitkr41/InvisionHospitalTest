package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.IndianFakerData;
import utils.AlertConfirmationPopup;

public class HWalkInRegistrationPage extends BasePage {

	AlertConfirmationPopup popup;

	public HWalkInRegistrationPage(WebDriver driver) {
		super(driver);
		popup = new AlertConfirmationPopup(driver);
	}

	// Basic Details

	By firstName = By.xpath("//*[@id='R_txtFirstName']/input");
	By lastName = By.xpath("//*[@id='main']//div[4]//input");

	By genderMale = By.xpath("//*[@id='R_rdoGender']/div/div[1]/label");

	By dob = By.xpath("//*[@id='main']//form//div[6]//input");

	By nextOfKin = By.xpath("//*[@id='main']//form//div[8]//input");
	By phoneNumber = By.id("phoneNumberInput");

	// Occupation Dropdown
	By occupationDropdown = By.id("RM_ddlCategory");

	// Education Dropdown
	By educationDropdown = By.xpath("//label[contains(text(),'Education')]/following::select[1]");

	// Identity Type
	By identityType = By.xpath("//label[contains(text(),'Identity Type')]/following::select[1]");
	By identityNumber = By.xpath("//*[@id='main']//form//div[13]//input");

	// Address
	By houseNo = By.xpath("//*[@id='main']//div[2]//div[2]//input");

	// Village Field
	By villageField = By.name("selectedRegistrationDomainEH.AreaName");

	// Discount Checkbox
	By discountCheckbox = By.xpath("//*[@id='main']//div[3]//div[2]//div[4]//input");

	// Discount Amount TextField
	By discountTextField = By.xpath("(//*[@id='main']//form//div[5]//input)[5]");

	// Discount Remark Dropdown
	By discountRemarkDropdown = By.xpath("//label[contains(text(),'Discount Remark')]/following::select[1]");

	// Mode Dropdown
	By modeDropdown = By.xpath("//label[contains(normalize-space(),'Mode')]/following::select[1]");

	// Transaction Id
	By transactionId = By.xpath("(//*[@id='main']//div[8]//input)[2]");

	// registrationBtn
	By registrationBtn = By.id("RM_btnSubmit");

	// Occupation Select
	public void selectOccupation() {

		wait.until(ExpectedConditions.elementToBeClickable(occupationDropdown));

		Select occupation = new Select(driver.findElement(occupationDropdown));

		boolean found = false;

		for (WebElement option : occupation.getOptions()) {

			String text = option.getText().trim();

			if (text.equalsIgnoreCase("Private service")) {
				occupation.selectByVisibleText(option.getText());
				found = true;
				break;
			}
		}

		if (!found) {
			throw new RuntimeException("Occupation option 'Private service' not found in dropdown");
		}
	}

	// Identity Type Select
	public void selectIdentityType() {

		wait.until(ExpectedConditions.elementToBeClickable(identityType));

		Select idType = new Select(driver.findElement(identityType));
		idType.selectByVisibleText("NATIONAL ID");
	}

	// Village Auto Select
	public void selectVillage(String villageName) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(villageField));

		driver.findElement(villageField).sendKeys(villageName);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(villageField).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(villageField).sendKeys(Keys.ENTER);
	}

	// Apply Discount
	public void applyDiscount(String amount) {

		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(discountCheckbox));
		checkbox.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement discountField = wait.until(ExpectedConditions.elementToBeClickable(discountTextField));

		discountField.clear();
		discountField.sendKeys(amount);
	}

	// Select Discount Remark
	public void selectDiscountRemark(String remark) {

		wait.until(ExpectedConditions.elementToBeClickable(discountRemarkDropdown));

		Select remarkDropdown = new Select(driver.findElement(discountRemarkDropdown));
		remarkDropdown.selectByVisibleText(remark);
	}

	// Mode Select
	    public void selectMode(String modeType) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(modeDropdown));

        Select mode = new Select(dropdown);

        // wait for options
        wait.until(driver -> mode.getOptions().size() > 1);

        mode.selectByVisibleText(modeType);

        // ✅ verify selection
        wait.until(driver ->
                mode.getFirstSelectedOption().getText().equalsIgnoreCase(modeType));

        // 🔥 trigger frontend events
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                "arguments[0].dispatchEvent(new Event('blur',{bubbles:true}));",
                dropdown
        );

        // 🔥 focus out (very important)
        dropdown.sendKeys(Keys.TAB);
    }


	// Walk-In Registration
	public void registerWalkInPatient() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

		String firstNameData = IndianFakerData.getFirstName();
		String lastNameData = IndianFakerData.getLastName();
		String nextOfKinData = IndianFakerData.getNextOfKin();
		String phoneData = IndianFakerData.getPhoneNumber();
		String addressData = IndianFakerData.getHouseAddress();
		String aadhaarData = IndianFakerData.getAadhaarNumber();
		String dobData = IndianFakerData.getDOBFromAge();
		
		type(firstName, firstNameData);
		type(lastName, lastNameData);

		click(genderMale);

		type(dob, dobData);

		type(nextOfKin, nextOfKinData);
		type(phoneNumber, phoneData);

		type(identityNumber, aadhaarData);

		type(houseNo, addressData);

		// Occupation
		selectOccupation();

		// Education
		driver.findElement(educationDropdown).sendKeys("Graduate");

		// Identity
		selectIdentityType();
		WebElement identityField = wait.until(
		        ExpectedConditions.elementToBeClickable(identityNumber));

		identityField.click();
		identityField.sendKeys(Keys.CONTROL + "a");
		identityField.sendKeys(Keys.DELETE);

		((JavascriptExecutor) driver).executeScript(
		        "arguments[0].value = arguments[1];" +
		        "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
		        "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
		        identityField,
		        aadhaarData);

		System.out.println("Generated Aadhaar : " + aadhaarData);

		// Address
		type(houseNo, addressData);
		

		
		// Village
		selectVillage("Rampur");

		// Apply Discount
		applyDiscount("10");

		// Mode
		selectMode("UPI");

		// Transaction Id
		driver.findElement(transactionId).sendKeys("gpayr373677343");

		// Discount Remark
		selectDiscountRemark("FREE VISIT");
		System.out.println("First Name : " + firstNameData);
		System.out.println("Last Name : " + lastNameData);
		System.out.println("DOB : " + dobData);
		System.out.println("Next Of Kin : " + nextOfKinData);
		System.out.println("Phone : " + phoneData);
		System.out.println("Address : " + addressData);
		System.out.println("Aadhaar : " + aadhaarData);

		// registrationBtn
		click(registrationBtn);

		// wait for popup
		wait.until(driver -> driver
				.findElements(By.xpath("//div[contains(@class,'alert') or contains(@class,'swal2-popup')]"))
				.size() > 0);

		// handle popups
		popup.handlePopupFast();

		String alertMsg = popup.handlePopupFast();
		System.out.println(alertMsg);

	}

}
