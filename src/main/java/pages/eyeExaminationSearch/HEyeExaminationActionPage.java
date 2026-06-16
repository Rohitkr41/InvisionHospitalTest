package pages.eyeExaminationSearch;

import java.util.NoSuchElementException;

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

    // =============================
    // CLICK FIRST ROW PLUS ICON
    // =============================
   public void clickFirstRowPlusIcon() throws InterruptedException {

    By rowsLocator = By.xpath("//*[@id='h-din']//tbody//tr");
    wait.until(ExpectedConditions.presenceOfElementLocated(rowsLocator));

    int rowCount = driver.findElements(rowsLocator).size();
    System.out.println("Total Rows : " + rowCount);

    for (int i = 1; i <= rowCount; i++) {
        String patientType = "";
        String status      = "";

        // ✅ FIXED: td[6] = patientType, td[9] = status (was swapped before)
        try {
            patientType = getTextSafely(
                    "//*[@id='h-din']//tbody//tr[" + i + "]//td[6]",
                    i, "patientType");
        } catch (Exception e) {
            System.out.println("⚠️ Skipping Row " + i
                    + " [patientType td[6] failed]: "
                    + e.getMessage().split("\n")[0]);
            continue;
        }

        try {
            status = getTextSafely(
                    "//*[@id='h-din']//tbody//tr[" + i + "]//td[9]",
                    i, "status");
        } catch (Exception e) {
            System.out.println("⚠️ Skipping Row " + i
                    + " [status td[9] failed]: "
                    + e.getMessage().split("\n")[0]);
            continue;
        }

        System.out.println("Row " + i
                + " | Patient Type = " + patientType
                + " | Status = "       + status);

        // ✅ FIXED: not post-op check on patientType
        boolean validPatient = !patientType.contains("post-op")
                            && !patientType.contains("postop");

        // ✅ FIXED: use equals() not contains()
        // contains("new") would match "walk-in/new" as status — WRONG
        // equals() ensures EXACT match only
        boolean validStatus = status.equals("new")
                           || status.equals("in-progress")
                           || status.equals("in progress")
                           || status.equals("inprogress");

        System.out.println("   validPatient=" + validPatient
                         + " | validStatus=" + validStatus);

        if (!validPatient || !validStatus) {
            System.out.println("   ⏭️ Skipping — not a valid match");
            continue;
        }

        // Click Eye Examination icon with retry
        By eyeIcon = By.xpath(
                "(//*[@id='h-din']//tbody//tr[" + i + "]"
                + "//i[@data-access='Examination'"
                + " and @title='Eye Examination'])[1]");

        int clickAttempts = 0;
        boolean clicked   = false;

        while (clickAttempts < 3 && !clicked) {
            try {
                WebElement icon = wait.until(
                        ExpectedConditions.presenceOfElementLocated(eyeIcon));

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", icon);
                Thread.sleep(500);

                icon = wait.until(
                        ExpectedConditions.elementToBeClickable(eyeIcon));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", icon);

                System.out.println("✅ Eye Examination clicked successfully"
                        + " for Row "          + i
                        + " | Patient Type = "  + patientType
                        + " | Status = "        + status);

                clicked = true;
                return;

            } catch (StaleElementReferenceException se) {
                clickAttempts++;
                System.out.println("🔄 Stale on click — retry "
                        + clickAttempts + "/3 for Row " + i);
                Thread.sleep(400);

            } catch (Exception e) {
                System.out.println("⚠️ Click failed for Row " + i
                        + ": " + e.getMessage().split("\n")[0]);
                break;
            }
        }

        if (!clicked) {
            System.out.println("⚠️ Could not click Row " + i
                    + " after 3 attempts — trying next valid row");
        }
    }

    throw new RuntimeException(
            "❌ No valid patient found (Status = New/In-Progress and not Post-Op). "
            + "Total rows scanned: " + rowCount);
}

    // =============================
    // HELPER: read cell text safely
    // =============================
    private String getTextSafely(String xpath, int rowIndex, String cellName) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                return driver
                        .findElement(By.xpath(xpath))
                        .getText()
                        .trim()
                        .toLowerCase();

            } catch (StaleElementReferenceException se) {
                attempts++;
                System.out.println("🔄 Stale on " + cellName
                        + " Row " + rowIndex
                        + " — retry " + attempts + "/3");
                try { Thread.sleep(300); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

            } catch (NoSuchElementException ne) {
                throw new RuntimeException(
                        cellName + " cell not found at: " + xpath);
            }
        }

        throw new RuntimeException(cellName
                + " still stale after 3 retries at row " + rowIndex);
    }
}
