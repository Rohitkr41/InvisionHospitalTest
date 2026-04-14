package pages.billing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;
import utils.ExcelUtils;

public class HServiceBillingPage extends BasePage {

	public HServiceBillingPage(WebDriver driver) {
		super(driver);
	    
	    }

	    // =============================
	    // TOP SEARCH LOCATORS
	    // =============================

	    By regSearch = By.name("inputValueofMedicalNo");
	    By phoneSearch = By.name("inputValueofPhoneNo");
	    By nameSearch = By.name("inputValueofCampName");

	    By topSearchButton = By.xpath("(//*[@id='top-headings']//form//img)[1]");

	    // =============================
	    // ADVANCE SEARCH
	    // =============================

	    By advanceSearchBtn = By.xpath("//*[@id='bbssss']/i");

	    By fromDate = By.xpath("//label[contains(text(),'From')]/following::input[1]");
	    By toDate = By.xpath("//label[contains(text(),'To')]/following::input[1]");

	    By searchButton = By.xpath("//a[contains(text(),'Search')]");

	    // =============================
	    // RESULT TABLE
	    // =============================

	    By resultRow = By.xpath("//table//tbody//tr");

	    // =============================
	    // TOP SEARCH METHODS
	    // =============================

	    public void searchByRegistration(String regNo) {

	        wait.until(ExpectedConditions.visibilityOfElementLocated(regSearch)).clear();
	        driver.findElement(regSearch).sendKeys(regNo);
	    }

	    public void searchByPhone(String phone) {

	        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneSearch)).clear();
	        driver.findElement(phoneSearch).sendKeys(phone);
	    }

	    public void searchByName(String name) {

	        wait.until(ExpectedConditions.visibilityOfElementLocated(nameSearch)).clear();
	        driver.findElement(nameSearch).sendKeys(name);
	    }

	    public void clickTopSearch() {

	        wait.until(ExpectedConditions.elementToBeClickable(topSearchButton)).click();
	        sleep(2000);
	    }

	    // =============================
	    // OPEN ADVANCE SEARCH
	    // =============================

	    public void openAdvanceSearch() {

	        wait.until(ExpectedConditions.elementToBeClickable(advanceSearchBtn)).click();
	        sleep(1000);
	    }

	    // =============================
	    // ADVANCE SEARCH BY REGISTRATION
	    // =============================

	    public void advanceSearchByRegistration(String regNo, String from, String to) {

	        openAdvanceSearch();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(regSearch)).clear();
	        driver.findElement(regSearch).sendKeys(regNo);

	        setDate(from, to);

	        driver.findElement(searchButton).click();

	        sleep(2000);
	    }

	    // =============================
	    // ADVANCE SEARCH BY PHONE
	    // =============================

	    public void advanceSearchByPhone(String phone, String from, String to) {

	        openAdvanceSearch();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneSearch)).clear();
	        driver.findElement(phoneSearch).sendKeys(phone);

	        setDate(from, to);

	        driver.findElement(searchButton).click();

	        sleep(2000);
	    }

	    // =============================
	    // ADVANCE SEARCH BY NAME
	    // =============================

	    public void advanceSearchByName(String name, String from, String to) {

	        openAdvanceSearch();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(nameSearch)).clear();
	        driver.findElement(nameSearch).sendKeys(name);

	        setDate(from, to);

	        driver.findElement(searchButton).click();

	        sleep(2000);
	    }

	    // =============================
	    // ADVANCE SEARCH BY DATE
	    // =============================

	    public void advanceSearchByDate(String from, String to) {

	        openAdvanceSearch();

	        setDate(from, to);

	        driver.findElement(searchButton).click();

	        sleep(2000);
	    }

	    // =============================
	    // DATE METHOD
	    // =============================

	    public void setDate(String from, String to) {

	        if (!from.isEmpty()) {

	            wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate)).clear();
	            driver.findElement(fromDate).sendKeys(from);
	        }

	        if (!to.isEmpty()) {

	            wait.until(ExpectedConditions.visibilityOfElementLocated(toDate)).clear();
	            driver.findElement(toDate).sendKeys(to);
	        }

	        sleep(1000);
	    }

	    // =============================
	    // VERIFY RESULT
	    // =============================

	    public boolean isResultDisplayed() {

	        sleep(1000);

	        return driver.findElements(resultRow).size() > 0;
	    }

	    // =============================
	    // EXPORT TABLE DATA TO EXCEL
	    // =============================

	    public void exportTableDataToExcel(String fileName) {

	        String sheetName = "ServiceBillingGrid";

	        ExcelUtils.exportTableToExcel(driver, resultRow, fileName, sheetName);
	    }

	    // =============================
	    // COMMON SLEEP METHOD
	    // =============================

	    public void sleep(int time) {

	        try {
	            Thread.sleep(time);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

}
