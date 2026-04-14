package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class HEyeExaminationActionPage extends BasePage {

	public HEyeExaminationActionPage(WebDriver driver) {
		 super(driver);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
	    }

	    // =============================
	    // PAGE HEADER
	    // =============================

	    By pageHeader = By.xpath("//h4[.='Eye Examination']");

	    // =============================
	    // ADVANCE SEARCH
	    // =============================

	    By advanceSearchBtn = By.xpath("//form//a[2]");
	    By fromDate = By.name("fromDatePres");
	    By toDate = By.name("toDatePres");

	    By searchBtn = By.xpath("//form//a[contains(text(),'Search')]");

	    // =============================
	    // RESULT TABLE
	    // =============================

	    By resultRow = By.xpath("//*[@id='h-din']//tbody//tr");

	    // =============================
	    // PLUS ICON (FIRST ROW ACTION)
	    // =============================

	    By plusIcon = By.xpath("//*[@id='h-din']//tbody//tr[1]//td[10]//i[1]");

	    // =============================
	    // SAFE CLICK
	    // =============================

	    public void safeClick(By locator) {

	        WebElement element = wait.until(
	                ExpectedConditions.elementToBeClickable(locator));

	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	        try {
	            element.click();
	        } catch (Exception e) {

	            ((JavascriptExecutor) driver)
	                    .executeScript("arguments[0].click();", element);
	        }
	    }

	    // =============================
	    // OPEN ADVANCE SEARCH
	    // =============================

	    public void openAdvanceSearch() {

	        safeClick(advanceSearchBtn);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));
	    }

	    // =============================
	    // SET DATE
	    // =============================

	    public void setDate(String from, String to) {

	        WebElement fromField = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(fromDate));

	        fromField.clear();
	        fromField.sendKeys(from);

	        WebElement toField = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(toDate));

	        toField.clear();
	        toField.sendKeys(to);
	    }

	    // =============================
	    // SEARCH BY DATE
	    // =============================

	    public void searchByDate(String from, String to) {

	        openAdvanceSearch();
	        setDate(from, to);

	        safeClick(searchBtn);

	        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRow, 0));
	    }

	    // =============================
	    // CLICK FIRST ROW PLUS ICON
	    // =============================

	   public void clickFirstRowPlusIcon() {
	    // Wait for table to have at least one row
	    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultRow, 0));

	    // Retry logic for stale elements
	    int attempts = 0;
	    boolean clicked = false;

	    while (attempts < 3 && !clicked) {
	        try {
	            // Re-locate the first row plus icon dynamically
	            By dynamicPlusIcon = By.xpath("//*[@id='h-din']//tbody//tr[1]//td[10]//i[1]");

	            WebElement icon = wait.until(
	                    ExpectedConditions.elementToBeClickable(dynamicPlusIcon));

	            // Scroll into view
	            ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView({block:'center'});", icon);

	            // Try normal click
	            icon.click();
	            clicked = true; // success
	        } catch (org.openqa.selenium.StaleElementReferenceException e) {
	            attempts++;
	        } catch (Exception e) {
	            // Fallback: JS click if normal click fails
	            try {
	                By dynamicPlusIcon = By.xpath("//*[@id='h-din']//tbody//tr[1]//td[10]//i[1]");
	                WebElement icon = driver.findElement(dynamicPlusIcon);
	                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", icon);
	                clicked = true;
	            } catch (Exception ignored) {
	            }
	        }
	    }

	    if (!clicked) {
	        throw new RuntimeException("Failed to click the first row plus icon after 3 attempts");
	    }
	}

}
