package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages.BasePage;
import utils.ExcelUtils;

public class HEyeExaminationSearchPage extends BasePage{

	public HEyeExaminationSearchPage(WebDriver driver) {
	    super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    // =============================
    // DEMO SPEED CONTROL
    // =============================

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // =============================
    // PAGE HEADER
    // =============================

    By pageHeader = By.xpath("//h4[.='Eye Examination']");

    // =============================
    // TOP SEARCH
    // =============================

    By regSearch = By.name("inputvalueofMedicalNO");
    By nameSearch = By.name("inputValueofExaminName");
    By screeningStatus = By.id("inputGroupSelect01");

    By topSearchButton = By.xpath("(//*[@id='top-headings']//form/a[1])");

    // =============================
    // ADVANCE SEARCH
    // =============================

    By advanceSearchBtn = By.xpath("//form//a[2]");
    By phoneSearch = By.name("inputValueofPhoneNo");

    By fromDate = By.name("fromDatePres");
    By toDate = By.name("toDatePres");

    By advanceSearchButton = By.xpath("//form//a[contains(text(),'Search')]");

    // =============================
    // RESULT TABLE
    // =============================

    By resultRow = By.xpath("//table//tbody//tr");

    // =============================
    // SAFE CLICK
    // =============================

    public void safeClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        pause(1);

        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }

        pause(1);
    }

    // =============================
    // CHECK ADVANCE SEARCH OPEN
    // =============================

    public boolean isAdvanceSearchOpen() {

        try {
            return driver.findElement(fromDate).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // =============================
    // OPEN ADVANCE SEARCH
    // =============================

    public void openAdvanceSearch() {

        if (!isAdvanceSearchOpen()) {

            safeClick(advanceSearchBtn);

            wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));

            pause(1);
        }
    }

    // =============================
    // TOP SEARCH METHODS
    // =============================

    public void searchByRegistration(String regNo) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(regSearch));

        element.clear();
        element.sendKeys(regNo);

        pause(1);
    }

    public void searchByName(String name) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameSearch));

        element.clear();
        element.sendKeys(name);

        pause(1);
    }

    public void selectScreeningStatus(String status) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(screeningStatus));

        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(status);

        pause(1);
    }

    public void clickTopSearch() {

        safeClick(topSearchButton);

        wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));

        pause(2);
    }

    // =============================
    // PHONE SEARCH
    // =============================

    public void searchByPhone(String phone) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneSearch));

        element.clear();
        element.sendKeys(phone);

        pause(1);
    }

    // =============================
    // DATE SETTER
    // =============================

    public void setDate(String from, String to) {

        if (from != null) {

            WebElement fromField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(fromDate));

            fromField.clear();
            fromField.sendKeys(from);
        }

        if (to != null) {

            WebElement toField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toDate));

            toField.clear();
            toField.sendKeys(to);
        }

        pause(1);
    }

    // =============================
    // ADVANCE SEARCH
    // =============================

    public void advanceSearch(String regNo, String name, String status,
                              String phone, String from, String to) {

        openAdvanceSearch();

        if (regNo != null)
            searchByRegistration(regNo);

        if (name != null)
            searchByName(name);

        if (status != null)
            selectScreeningStatus(status);

        if (phone != null)
            searchByPhone(phone);

        setDate(from, to);

        pause(1);

        safeClick(advanceSearchButton);

        wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));

        pause(2);
    }

    // =============================
    // VERIFY RESULT
    // =============================

    public boolean isResultDisplayed() {

        wait.until(ExpectedConditions.presenceOfElementLocated(resultRow));

        pause(1);

        return driver.findElements(resultRow).size() > 0;
    }
    
    
    // =============================
    // EXPORT TABLE DATA
    // =============================

    public void exportTableDataToExcel(String fileName) {

        String sheetName = "BillingReceiptGrid";

        ExcelUtils.exportTableToExcel(driver, resultRow, fileName, sheetName);
    }

}
