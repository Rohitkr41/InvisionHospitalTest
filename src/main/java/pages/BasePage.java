package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By modal = By.cssSelector(".custom-modal");
    private final By loader = By.cssSelector(".loader, .spinner, .loading");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // =========================
    // WAIT METHODS
    // =========================
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    protected void waitForLoaderToDisappear() {
        try { wait.until(ExpectedConditions.invisibilityOfElementLocated(loader)); } catch (Exception ignored) {}
    }

    protected void waitForModalToDisappear() {
        try { wait.until(ExpectedConditions.invisibilityOfElementLocated(modal)); } catch (Exception ignored) {}
    }

    protected void waitUntilModalGone() {
        try {
            List<WebElement> modals = driver.findElements(modal);
            for (WebElement m : modals) {
                if (m.isDisplayed()) {
                    List<WebElement> okButtons = m.findElements(By.xpath(".//button[normalize-space()='OK']"));
                    if (!okButtons.isEmpty()) {
                        jsClick(okButtons.get(0));
                        wait.until(ExpectedConditions.invisibilityOf(m));
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    // =========================
    // SCROLL METHODS
    // =========================
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", element);
    }

    protected WebElement scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        scrollToElement(element);
        return element;
    }

    // =========================
    // CLICK METHODS
    // =========================

    protected void click(By locator) {
        retryAction(() -> {
            waitForLoaderToDisappear();

            WebElement element = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(locator)));

            scrollToElement(element);   // good
            safeClick(element);        // now correct ✅
        });
    }

    public void jsClick(By locator) {
        WebElement element = waitForClickable(locator);
        jsClick(element);
    }

    public void jsClick(WebElement element) {
        scrollToElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void safeClick(WebElement element) {
    ((JavascriptExecutor) driver)
        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

    try {
        element.click();
    } catch (Exception e) {
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", element);
    }
}

    private void retryAction(Runnable action) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                action.run();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    // =========================
    // TYPE METHODS
    // =========================
    protected void type(By locator, String text) {
        retryAction(() -> {
            WebElement element = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(locator)));
            scrollToElement(element);
            safeType(element, text);
        });
    }

    protected void fastType(By locator, String text) {
        safeType(waitForVisibility(locator), text);
    }

    private void safeType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // =========================
    // DROPDOWN METHODS
    // =========================
    protected void selectDropdown(By locator, String visibleText) {
        WebElement element = waitForClickable(locator);
        scrollToElement(element);
        new Select(element).selectByVisibleText(visibleText);
    }

    protected void selectByVisibleText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        scrollToElement(element);
        new Select(element).selectByVisibleText(text);
    }

    // =========================
    // GET TEXT / DISPLAY CHECK
    // =========================
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try { return waitForVisibility(locator).isDisplayed(); } catch (Exception e) { return false; }
    }

    // =========================
    // SUCCESS ALERT
    // =========================
    protected void closeSuccessAlert() {
        try {
            By successOkButton = By.xpath("//*[@id='main']//div[4]//div[2]/button");
            click(successOkButton);
            waitUntilModalGone();
        } catch (Exception e) {
            System.out.println("No success alert present");
        }
    }

    // =========================
    // AUTOCOMPLETE
    // =========================
    protected void selectFromAutocomplete(By fieldLocator, String value) {
        waitUntilModalGone();

        WebElement field = waitForVisibility(fieldLocator);
        scrollToElement(field);

        if (field.getAttribute("value") != null && !field.getAttribute("value").trim().isEmpty()) {
            return;
        }

        safeType(field, value);

        By suggestion = By.xpath("//li[contains(text(),'" + value + "')]");
        WebElement option = waitForVisibility(suggestion);
        scrollToElement(option);
        safeClick(option);
    }
}