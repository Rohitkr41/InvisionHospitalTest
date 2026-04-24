package pages.billing;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;
import utils.ExcelUtils;

public class HBillingReceiptPage extends BasePage{

	public HBillingReceiptPage(WebDriver driver) {
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
    // ADVANCE SEARCH LOCATORS
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
    // EXPORT TABLE DATA
    // =============================

    public void exportTableDataToExcel(String fileName) {

        String sheetName = "BillingReceiptGrid";

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
    
   public boolean approvePendingDiscountByDateFilter(String fromDate, String toDate, String remarks) {
    advanceSearchByDate(fromDate, toDate);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table/tbody/tr")));

    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

    for (WebElement row : rows) {
        String rowText = row.getText().trim();

        // Skip empty/non-data rows
        if (rowText.isEmpty()) {
            continue;
        }

        // Process only rows that contain Approval Pending
        if (!rowText.toLowerCase().contains("approval pending")) {
            continue;
        }

        WebElement approveIcon = row.findElement(
            By.xpath(".//i[@title='Discount Approved' and contains(@class,'fa-check-circle')]")
        );
        wait.until(ExpectedConditions.elementToBeClickable(approveIcon)).click();

        WebElement discountRemarksField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='main']/article/div[4]/div/div[2]/form/div[1]/input")
            )
        );
        discountRemarksField.clear();
        discountRemarksField.sendKeys(remarks);

        WebElement approveButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and normalize-space()='Approve']")
            )
        );
        approveButton.click();

        handleDiscountApprovedSuccessAlert();
        return true;
    }

    System.out.println("No Approval Pending row found. Approval step skipped.");
    return false;
}


    
    
    public void handleDiscountApprovedSuccessAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMessage = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'modal-body')]//p[normalize-space()='Discount approved successfully!']")
            )
        );

        if (successMessage.isDisplayed()) {
            WebElement okButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[contains(@class,'alert-btn')]//button[normalize-space()='OK'])[3]")
                )
            );
            okButton.click();
        }
    }



}
