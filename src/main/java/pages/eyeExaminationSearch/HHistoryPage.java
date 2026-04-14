package pages.eyeExaminationSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class HHistoryPage extends BasePage {

    public HHistoryPage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // LEFT MENU
    // =============================
    By historyMenu = By.xpath("//*[@id='side-box-nav']/li[3]/a");

    // =============================
    // SYSTEMIC HISTORY
    // =============================
    By systemicHistoryField = By.xpath("(//*[@id='box-main']//input)[1]");
    By periodField = By.xpath("(//*[@id='box-main']//input)[2]");
    By durationDropdown = By.xpath("(//*[@id='box-main']//select)[1]");
    By controlledRadio = By.xpath("//*[@id='box-main']//div[3]//div[1]/label");
    By uncontrolledRadio = By.xpath("(//*[@id='box-main']//div[3]//label)[3]");
    By remarksField = By.xpath("//*[@id='box-main']//div[4]/input");
    By saveSystemicHistory = By.xpath("//*[@id='box-main']//div[5]/button");

    // =============================
    // FAMILY HISTORY
    // =============================
    By familyHistoryField = By.xpath("(//input[@data-dropdown='complaintDropdown'])[2]");
    By relationField = By.xpath("(//input[@data-dropdown='complaintDropdown'])[3]");
    By familyRemarks = By.xpath("//*[@id='box-main']//div[3]/input");
    By saveFamilyHistory = By.xpath("//*[@id='box-main']//div[4]/button");

    // =============================
    // MODAL / ALERT
    // =============================
    By modal = By.cssSelector(".custom-modal");

    // Duplicate alert specifically for Family History
    By duplicateAlert = By.xpath("//div[contains(@class,'custom-modal')]//p[contains(text(),'already exists')]");
    By duplicateAlertOkButton = By.xpath("//div[contains(@class,'custom-modal')]//button[normalize-space()='OK']");

    // =============================
    // CLICK HISTORY MENU
    // =============================
    public void clickHistoryMenu() {
        waitUntilModalGone();
        wait.until(ExpectedConditions.elementToBeClickable(historyMenu)).click();
    }

    // =============================
    // ADD SYSTEMIC HISTORY
    // =============================
    public void addSystemicHistory(String historyValue, String periodValue, String durationValue, String remarksValue, boolean controlled) {
        waitUntilModalGone();

        if (isSystemicHistoryAlreadyAdded(historyValue)) {
            System.out.println("Systemic History already exists: " + historyValue);
            return;
        }

        selectAutocompleteSingleType(systemicHistoryField, historyValue);
        setInputValue(periodField, periodValue);
        setDropdownValue(durationDropdown, durationValue);
        clickRadio(controlled ? controlledRadio : uncontrolledRadio);
        setInputValue(remarksField, remarksValue);
        clickButton(saveSystemicHistory);

        waitUntilModalGone();
    }

    // =============================
    // ADD FAMILY HISTORY
    // =============================
    public void addFamilyHistory(String familyHistoryValue, String relationValue, String remarksValue) {
        waitUntilModalGone();

        // 0️⃣ Check duplicate in table
        if (isFamilyHistoryAlreadyAdded(familyHistoryValue, relationValue)) {
            System.out.println("Family History already exists in table: " + familyHistoryValue + " / " + relationValue);
            return;
        }

        // 1️⃣ Family History (autocomplete)
        selectAutocompleteSingleType(familyHistoryField, familyHistoryValue);

        // 2️⃣ Relation (autocomplete)
        selectAutocompleteSingleType(relationField, relationValue);

        // 3️⃣ Remarks
        setInputValue(familyRemarks, remarksValue);

        // 4️⃣ Save
        clickButton(saveFamilyHistory);

        // 5️⃣ Handle duplicate alert if shown
        handleDuplicateAlertIfPresent();

        // 6️⃣ Handle any normal modal
        waitUntilModalGone();
    }

    // =============================
    // ULTRA-STABLE AUTOCOMPLETE
    // =============================
    private void selectAutocompleteSingleType(By fieldLocator, String value) {
        waitUntilModalGone();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        field.clear();
        field.click();
        field.sendKeys(value);

        int attempts = 0;
        while (attempts < 10) {
            try {
                By suggestionLocator = By.xpath("//li[contains(text(),'" + value + "')]");
                List<WebElement> suggestions = driver.findElements(suggestionLocator);
                for (WebElement option : suggestions) {
                    if (option.isDisplayed()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                        } catch (Exception e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                        }
                        wait.until(ExpectedConditions.invisibilityOf(option));
                        return;
                    }
                }
            } catch (Exception ignored) {}
            attempts++;
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
        throw new RuntimeException("Unable to select '" + value + "' from autocomplete");
    }

    // =============================
    // DUPLICATE CHECK HELPERS
    // =============================
    private boolean isSystemicHistoryAlreadyAdded(String historyValue) {
        try {
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"box-main\"]/div/div[2]/div/div[3]/div"));
            for (WebElement row : rows) {
                String value = row.findElement(By.xpath(".//td[1]")).getText().trim();
                if (value.equalsIgnoreCase(historyValue)) return true;
            }
        } catch (Exception ignored) {}
        return false;
    }

    private boolean isFamilyHistoryAlreadyAdded(String familyHistoryValue, String relationValue) {
        try {
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='box-main']//table//tr"));
            for (WebElement row : rows) {
                String family = row.findElement(By.xpath(".//td[1]")).getText().trim();
                String relation = row.findElement(By.xpath(".//td[2]")).getText().trim();
                if (family.equalsIgnoreCase(familyHistoryValue) && relation.equalsIgnoreCase(relationValue)) {
                    return true;
                }
            }
        } catch (Exception ignored) {}
        return false;
    }

    // =============================
    // HELPER METHODS
    // =============================
    private void setInputValue(By locator, String value) {
        WebElement element = waitUntilModalGoneAndVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; " +
                        "arguments[0].dispatchEvent(new Event('input')); " +
                        "arguments[0].dispatchEvent(new Event('change')); " +
                        "arguments[0].blur();",
                element, value
        );
    }

    private void setDropdownValue(By locator, String value) {
        WebElement element = waitUntilModalGoneAndVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));",
                element, value
        );
    }

    private void clickRadio(By locator) {
        WebElement element = waitUntilModalGoneAndVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void clickButton(By locator) {
        WebElement element = waitUntilModalGoneAndVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // =============================
    // MODAL HANDLING
    // =============================
//    private void waitUntilModalGone() {
//        try {
//            List<WebElement> modals = driver.findElements(modal);
//            for (WebElement m : modals) {
//                if (m.isDisplayed()) {
//                    List<WebElement> okButtons = m.findElements(By.xpath(".//button[normalize-space()='OK']"));
//                    if (!okButtons.isEmpty()) {
//                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okButtons.get(0));
//                        wait.until(ExpectedConditions.invisibilityOf(m));
//                    }
//                }
//            }
//        } catch (Exception ignored) {}
//    }

    // =============================
    // DEDICATED DUPLICATE ALERT HANDLER
    // =============================
    private void handleDuplicateAlertIfPresent() {
    int attempts = 0;
    while (attempts < 10) { // try for ~3 seconds
        try {
            List<WebElement> alerts = driver.findElements(By.xpath("//*[contains(text(),'already exists')]"));
            for (WebElement alert : alerts) {
                if (alert.isDisplayed()) {
                    System.out.println("Duplicate alert detected: " + alert.getText().trim());
                    try {
                        WebElement okBtn = alert.findElement(By.xpath(".//following::button[normalize-space()='OK']"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);
                        wait.until(ExpectedConditions.invisibilityOf(alert));
                    } catch (Exception ignored) {}
                    return; // exit after handling
                }
            }
        } catch (Exception ignored) {}
        attempts++;
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
    }
}
    private WebElement waitUntilModalGoneAndVisible(By locator) {
        waitUntilModalGone();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
