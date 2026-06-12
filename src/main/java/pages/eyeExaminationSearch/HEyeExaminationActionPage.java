package pages.eyeExaminationSearch;

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
	}

	// =============================
	// PAGE HEADER
	// =============================

	By pageHeader = By.xpath("//div[@class='sc-nav']//a[@href='/EYEHOSPITAL/EYEEXAMINATIONEH/EYEEXAMINATION']");

	// =============================
	// ADVANCE SEARCH
	// =============================

	By advanceSearchBtn = By.id("bbssss");
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

	By plusIcon = By.xpath(
			"(//*[@id='h-din']//tbody//tr[\" + i + \"]//i[@data-access='Examination' and @title='Eye Examination'])[2]");

	// =============================
	// SAFE CLICK
	// =============================

	public void safeClick(By locator) {

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		try {
			element.click();
		} catch (Exception e) {

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	// =============================
	// OPEN ADVANCE SEARCH
	// =============================

	public void openAdvanceSearch() throws InterruptedException {

	    WebElement btn = driver.findElement(By.id("bbssss"));

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView(true);", btn);

	    Thread.sleep(1000);

	    try {
	        btn.click();
	    } catch (Exception e) {

	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].click();", btn);
	    }

	    Thread.sleep(3000);

	    System.out.println("Advance Search Click Attempted");
	}

	public void openEyeExaminationPage() {

		WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(pageHeader));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", menu);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);

		wait.until(ExpectedConditions.urlContains("EYEEXAMINATION"));
	}

	// =============================
	// SET DATE
	// =============================

	public void setDate(String from, String to) {

		WebElement fromField = wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));

		fromField.clear();
		fromField.sendKeys(from);

		WebElement toField = wait.until(ExpectedConditions.visibilityOfElementLocated(toDate));

		toField.clear();
		toField.sendKeys(to);
	}

	// =============================
	// SEARCH BY DATE
	// =============================

	public void searchByDate(String from, String to) throws InterruptedException {

    openAdvanceSearch();
    setDate(from, to);

    // old first row reference
    WebElement oldRow = null;

    try {
        oldRow = driver.findElement(
                By.xpath("//*[@id='h-din']//tbody//tr[1]"));
    } catch (Exception ignored) {
    }

    safeClick(searchBtn);

    if (oldRow != null) {
        wait.until(ExpectedConditions.stalenessOf(oldRow));
    }

    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id='h-din']//tbody//tr[1]")));

    System.out.println("✅ Search results loaded");
}

	// =============================
	// CLICK FIRST ROW PLUS ICON
	// =============================

	public void clickFirstRowPlusIcon() {

    By rowsLocator = By.xpath("//*[@id='h-din']//tbody//tr");

    wait.until(ExpectedConditions.presenceOfElementLocated(rowsLocator));

    int rowCount = driver.findElements(rowsLocator).size();

    System.out.println("Total Rows : " + rowCount);

    for (int i = 1; i <= rowCount; i++) {

        try {

            String patientType = driver
                    .findElement(By.xpath("//*[@id='h-din']//tbody//tr[" + i + "]//td[6]"))
                    .getText()
                    .trim()
                    .toLowerCase();

            String status = driver
                    .findElement(By.xpath("//*[@id='h-din']//tbody//tr[" + i + "]//td[9]"))
                    .getText()
                    .trim()
                    .toLowerCase();

            System.out.println(
                    "Row " + i +
                    " | Patient Type = " + patientType +
                    " | Status = " + status);

            // Skip Post-Op
            boolean validPatient = !patientType.contains("post-op");

            // Only New or In-Progress
            boolean validStatus =
                    status.contains("new")
                    || status.contains("in-progress")
                    || status.contains("in progress")
                    || status.contains("inprogress");

            if (validPatient && validStatus) {

                By eyeIcon = By.xpath(
                        "(//*[@id='h-din']//tbody//tr[" + i + "]//i[@data-access='Examination' and @title='Eye Examination'])[1]"
                );

                WebElement icon = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(eyeIcon));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", icon);

                Thread.sleep(1000);

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(icon));
                    icon.click();
                } catch (StaleElementReferenceException e) {

                    i--; // same row retry

                    continue;
                }

                System.out.println(
                        "✅ Eye Examination clicked successfully for Row "
                                + i
                                + " | Patient Type = "
                                + patientType
                                + " | Status = "
                                + status);

                return;
            }

        } catch (Exception e) {

            System.out.println(
                    "⚠️ Skipping Row "
                            + i
                            + " : "
                            + e.getMessage());
        }
    }

    throw new RuntimeException(
            "❌ No valid patient found (Status = New/In-Progress and not Post-Op)");
}

}
