
package pages.PatientReceipt;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class PatientReceiptPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public PatientReceiptPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class);
    }

    // ======================
    // LOCATORS
    // ======================

    By registrationSearch = By.name("searchRegisterDomain.MedicalNo");
    By phoneSearch = By.name("searchRegisterDomain.PhoneNumber");
    By firstNameSearch = By.name("searchRegisterDomain.PatientName");

    By searchBtn = By.xpath("//*[@id='top-headings']//form//a[1]");
    By advanceFilterBtn = By.xpath("//*[@id='bbssss']/i");

    By modalHeader = By.xpath("//h5[.='Search Registration Receipt']");

    By fromDate = By.name("fromDate");
    By toDate = By.name("toDate");
    By villageField = By.name("selectedAreaforSearch.AreaName");

    By popupSearchBtn = By.xpath("//a[text()= 'Search']");

    By resultRows = By.xpath("//table/tbody/tr");

//    By alertOk = By.xpath("(//button[.='OK'])[3]");
    By alertOk = By.xpath("//*[@id='main']//button[normalize-space()='OK']");

    // ======================
    // SAFE ELEMENT FINDER
    // ======================

    private WebElement get(By locator) {

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ======================
    // SAFE TYPE
    // ======================

    private void type(By locator, String text) {

        WebElement el = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        el.clear();
        el.sendKeys(text);
    }

    // ======================
    // SAFE CLICK
    // ======================

    private void click(By locator) {

        WebElement el = wait.until(
                ExpectedConditions.elementToBeClickable(locator));

        try {

            el.click();

        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", el);
        }
    }

    // ======================
    // ALERT HANDLER
    // ======================

    public void closeAlertIfPresent() {

        try {

            WebDriverWait shortWait =
                    new WebDriverWait(driver, Duration.ofSeconds(2));

            WebElement ok = shortWait.until(
                    ExpectedConditions.elementToBeClickable(alertOk));

            ok.click();

            wait.until(ExpectedConditions.invisibilityOf(ok));

            System.out.println("Alert closed");

        } catch (Exception e) {
            // ignore if no alert
        }
    }

    // ======================
    // RESULT CHECK
    // ======================

    public boolean isResultPresent() {

        try {

            List<WebElement> rows = driver.findElements(resultRows);

            return rows.size() > 0;

        } catch (Exception e) {

            return false;
        }
    }

    // ======================
    // TOP SEARCH
    // ======================

    public void searchByRegistration(String regNo) {

        type(registrationSearch, regNo);
    }

    public void searchByPhone(String phone) {

        type(phoneSearch, phone);
    }

    public void searchByFirstName(String name) {

        type(firstNameSearch, name);
    }

    public void clickSearch() {

        closeAlertIfPresent();

        click(searchBtn);

        waitAfterSearch();
    }

    // ======================
    // ADVANCE SEARCH
    // ======================

    public void openAdvanceSearch() {

        closeAlertIfPresent();

        click(advanceFilterBtn);

        waitForModal();
    }

    public void searchByDateVillage(String from, String to, String village) {

        waitForModal();

        type(fromDate, from);

        type(toDate, to);

        type(villageField, village);

        get(villageField).sendKeys(Keys.ENTER);
    }

    public void clickPopupSearch() {

        closeAlertIfPresent();

        click(popupSearchBtn);

        waitAfterSearch();
    }

    // ======================
    // MODAL WAIT
    // ======================

    private void waitForModal() {

        WebDriverWait modalWait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        modalWait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));

        modalWait.until(ExpectedConditions.elementToBeClickable(fromDate));

        modalWait.until(ExpectedConditions.elementToBeClickable(toDate));
    }

    // ======================
    // WAIT AFTER SEARCH
    // ======================

    private void waitAfterSearch() {

        try {

            wait.until(ExpectedConditions.or(

                    ExpectedConditions.presenceOfElementLocated(resultRows),

                    ExpectedConditions.presenceOfElementLocated(alertOk)

            ));

        } catch (Exception e) {}

        closeAlertIfPresent();
    }
}