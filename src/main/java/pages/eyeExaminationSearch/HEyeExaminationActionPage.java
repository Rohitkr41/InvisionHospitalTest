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
    }

    // =============================
    // PAGE HEADER
    // =============================
    By pageHeader = By.xpath(
            "//div[@class='sc-nav']//a[@href='/EYEHOSPITAL/EYEEXAMINATIONEH/EYEEXAMINATION']");

    // =============================
    // ADVANCE SEARCH
    // =============================
    By advanceSearchBtn = By.id("bbssss");
    By fromDate         = By.name("fromDatePres");
    By toDate           = By.name("toDatePres");
    By searchBtn        = By.xpath("//form//a[contains(text(),'Search')]");

    // =============================
    // RESULT TABLE
    // =============================
    By resultRow = By.xpath("//*[@id='h-din']//tbody//tr");

    // =============================
    // SAFE CLICK — generic reusable
    // =============================
    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                // Always re-locate fresh — never reuse cached WebElement
                WebElement element = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));

                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView({block:'center'});",
                                element);

                Thread.sleep(300); // wait for scroll + any CSS animation

                // Re-locate after scroll — DOM may have shifted
                element = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));

                element.click();
                return;

            } catch (StaleElementReferenceException se) {
                attempts++;
                System.out.println("🔄 Stale on safeClick — retry " + attempts + "/3");
                try { Thread.sleep(300); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

            } catch (Exception e) {
                // Normal click failed — try JS click once
                try {
                    WebElement el = driver.findElement(locator);
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", el);
                    return;
                } catch (Exception je) {
                    attempts++;
                    System.out.println("⚠️ safeClick JS fallback failed: "
                            + je.getMessage().split("\n")[0]);
                    try { Thread.sleep(300); } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        throw new RuntimeException("❌ safeClick failed after 3 attempts for: " + locator);
    }

    // =============================
    // OPEN ADVANCE SEARCH  ← FIXED
    // =============================
    public void openAdvanceSearch() {
        int attempts = 0;

        while (attempts < 3) {
            try {
                // ✅ FIX 1: Wait for any loading overlay/spinner to disappear first
                waitUntilModalGone();

                // ✅ FIX 2: Single combined wait — elementToBeClickable
                //    covers presence + visibility + enabled in one call
                WebElement btn = wait.until(
                        ExpectedConditions.elementToBeClickable(advanceSearchBtn));

                // ✅ FIX 3: Scroll into view and pause for CSS transitions
                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView({block:'center'});", btn);
                Thread.sleep(400);

                // ✅ FIX 4: Re-locate after scroll — avoids stale reference
                btn = wait.until(
                        ExpectedConditions.elementToBeClickable(advanceSearchBtn));

                // ✅ FIX 5: Try normal click first, JS only as fallback
                try {
                    btn.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", btn);
                }

                // ✅ FIX 6: Confirm the advance search panel actually opened
                //    Wait for fromDate field to appear as proof
                wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate));

                System.out.println("✅ Advance Search Opened");
                return;

            } catch (StaleElementReferenceException se) {
                attempts++;
                System.out.println("🔄 Stale on advanceSearch btn — retry "
                        + attempts + "/3");
                try { Thread.sleep(400); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

            } catch (Exception e) {
                attempts++;
                System.out.println("⚠️ openAdvanceSearch attempt " + attempts
                        + " failed: " + e.getMessage().split("\n")[0]);
                try { Thread.sleep(500); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        throw new RuntimeException(
                "❌ Failed to open Advance Search after 3 attempts");
    }

    // =============================
    // OPEN EYE EXAMINATION PAGE
    // =============================
    public void openEyeExaminationPage() {
        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(pageHeader));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", menu);

        wait.until(ExpectedConditions.urlContains("EYEEXAMINATION"));

        wait.until(ExpectedConditions.presenceOfElementLocated(advanceSearchBtn));

        System.out.println("✅ Eye Examination Page Loaded");
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

        // Wait for table rows to load after search
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[@id='h-din']//tbody//tr")));

        wait.until(driver ->
                driver.findElements(
                        By.xpath("//*[@id='h-din']//tbody//tr")).size() > 0);

        int rowCount = driver.findElements(
                By.xpath("//*[@id='h-din']//tbody//tr")).size();

        System.out.println("✅ Search completed. Rows found = " + rowCount);
    }
    
    private String getCellText(WebElement row, int columnIndex) {
        try {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            if (columnIndex >= cells.size()) {
                return "";
            }

            return cells.get(columnIndex)
                    .getText()
                    .trim()
                    .toLowerCase();

        } catch (Exception e) {
            return "";
        }
    }
    
    private boolean clickEyeExamIcon(WebElement row) {

        try {

            WebElement icon = row.findElement(
                    By.xpath(".//i[@title='Eye Examination']"));

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            icon);

            wait.until(ExpectedConditions.elementToBeClickable(icon));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", icon);

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Icon click failed : " + e.getMessage());

            return false;
        }
    }

    // =============================
    // CLICK FIRST ROW PLUS ICON
    // =============================
   public void clickFirstRowPlusIcon() {

    By rowsLocator = By.xpath("//*[@id='h-din']//tbody/tr");

    wait.until(
            ExpectedConditions.presenceOfElementLocated(rowsLocator));

    List<WebElement> rows =
            driver.findElements(rowsLocator);

    System.out.println("Total Rows : " + rows.size());

    for (int i = 0; i < rows.size(); i++) {

        try {

            WebElement row =
                    driver.findElements(rowsLocator).get(i);

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            System.out.println(
                    "Row " + (i + 1)
                    + " Column Count = "
                    + cells.size());

            /*
             * Adjust these indexes based on actual table.
             * Start by printing all columns once.
             */
            String patientType =
                    getCellText(row, 5);

            String status =
                    getCellText(row, 8);

            System.out.println(
                    "Row " + (i + 1)
                    + " | Patient Type = "
                    + patientType
                    + " | Status = "
                    + status);

            boolean validPatient =
                    !patientType.contains("post-op")
                    && !patientType.contains("postop");

            boolean validStatus =
                    status.equalsIgnoreCase("new")
                    || status.equalsIgnoreCase("in-progress")
                    || status.equalsIgnoreCase("in progress")
                    || status.equalsIgnoreCase("inprogress");

            if (!validPatient || !validStatus) {

                System.out.println(
                        "Skipping Row "
                        + (i + 1));

                continue;
            }

            if (clickEyeExamIcon(row)) {

                System.out.println(
                        "Eye Examination clicked for Row "
                        + (i + 1));

                return;
            }

        } catch (StaleElementReferenceException e) {

            System.out.println(
                    "Stale Row : "
                    + (i + 1));

        } catch (Exception e) {

            System.out.println(
                    "Error in Row "
                    + (i + 1)
                    + " : "
                    + e.getMessage());
        }
    }

    throw new RuntimeException(
            "No valid patient found");
}

   
}
