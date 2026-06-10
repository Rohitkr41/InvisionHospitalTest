package pages.eyeExaminationSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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

	    By plusIcon = By.xpath("//i[@data-access='Examination' and @title='Eye Examination']");

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

    By rowsLocator = By.xpath("//*[@id='h-din']//tbody//tr");

    // ✅ Wait for table rows
    wait.until(ExpectedConditions.presenceOfElementLocated(rowsLocator));

    // ✅ 🔥 MOST IMPORTANT: wait for actual data (td)
    wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//*[@id='h-din']//tbody//tr[1]//td")
    ));

    int rowCount = driver.findElements(rowsLocator).size();
    System.out.println("Total rows: " + rowCount);

    for (int i = 1; i <= rowCount; i++) {

        try {
            // ✅ Retry mechanism (handle dynamic loading)
            int retry = 0;
            String status = "";
            String patientType = "";

            while (retry < 3) {

                List<WebElement> statusEl = driver.findElements(
                        By.xpath("//*[@id='h-din']//tbody//tr[" + i + "]//td[9]")
                );

                List<WebElement> typeEl = driver.findElements(
                        By.xpath("//*[@id='h-din']//tbody//tr[" + i + "]//td[6]")
                );

                if (!statusEl.isEmpty() && !typeEl.isEmpty()) {
                    status = statusEl.get(0).getText().trim().toLowerCase();
                    patientType = typeEl.get(0).getText().trim().toLowerCase();
                    break;
                }

                Thread.sleep(500); // ⏳ wait for data render
                retry++;
            }

            if (status.isEmpty() || patientType.isEmpty()) {
                System.out.println("⚠️ Skipping row " + i + " (data not loaded)");
                continue;
            }

            System.out.println("Row " + i + " => Status: [" + status + "] | Type: [" + patientType + "]");

            boolean validStatus = status.contains("in-progress") || status.contains("new");
            boolean notPostOp = !patientType.contains("post-op");

            if (validStatus && notPostOp) {

                By iconLocator = By.xpath(
                        "//*[@id='h-din']//tbody//tr[" + i + "]//i[@data-access='Examination' and @title='Eye Examination']"
                );

                WebElement icon = wait.until(ExpectedConditions.elementToBeClickable(iconLocator));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", icon);

                try {
                    icon.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", icon);
                }

                System.out.println("✅ Clicked Eye Examination for row: " + i);
                return;
            }

        } catch (StaleElementReferenceException e) {
            System.out.println("⚠️ Retrying row " + i + " (stale)");
            i--; // 🔥 retry same row
        } catch (Exception e) {
            System.out.println("⚠️ Skipping row " + i + " due to: " + e.getMessage());
        }
    }

    throw new RuntimeException("❌ No valid patient found");
}
	   
}  