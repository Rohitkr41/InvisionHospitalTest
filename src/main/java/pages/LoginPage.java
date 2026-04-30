package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ======================
    // LOCATORS
    // ======================

    private By usernameField = By.cssSelector("input[name='loginModel.Username']");
    private By passwordField = By.cssSelector("input[name='loginModel.Password']");
    private By captchaField  = By.cssSelector("input[placeholder='Captcha']");
    private By loginButton   = By.xpath("//button[contains(text(),'Login')]");

    // ======================
    // LOGIN METHOD
    // ======================

    public void login(String username, String password) {

        // Wait for any loading overlay to disappear first
        waitForPageLoad();

        // Wait until the username field is fully interactable
        wait.until(ExpectedConditions.elementToBeClickable(usernameField));

        enterText(usernameField, username);
        enterText(passwordField, password);

        System.out.println("Enter CAPTCHA manually...");

        // Wait until user enters captcha
        wait.until(driver ->
                driver.findElement(captchaField)
                        .getAttribute("value")
                        .length() > 0
        );

        safeClick(loginButton);

        // Wait until dashboard loads
        waitForUrlContains("adminDashboard");
    }

    // ======================
    // WAIT FOR PAGE LOAD
    // ======================

    private void waitForPageLoad() {
        // Wait for document.readyState to be complete
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // Extra buffer for JS frameworks to finish rendering
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ======================
    // STABLE TEXT ENTRY
    // ======================

    private void enterText(By locator, String text) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                // Use elementToBeClickable — stronger than visibilityOfElementLocated
                // It checks visibility + enabled + not obscured
                WebElement element = wait.until(
                        ExpectedConditions.elementToBeClickable(locator));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

                // Small pause after scroll for any scroll-triggered animations
                Thread.sleep(200);

                element.clear();
                element.sendKeys(text);

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;
                if (attempts == 3) {
                    throw new RuntimeException("Stale element, unable to enter text: " + locator);
                }

            } catch (ElementNotInteractableException e) {

                // Element exists but is blocked — try JS injection as fallback
                attempts++;
                if (attempts == 3) {
                    // Last resort: use JavaScript to set value directly
                    WebElement element = driver.findElement(locator);
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].removeAttribute('readonly');" +
                            "arguments[0].removeAttribute('disabled');" +
                            "arguments[0].value = arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                            "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                            element, text
                    );
                    return;
                }

                // Wait a bit before retrying
                try { Thread.sleep(500); } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while entering text: " + locator);
            }
        }
    }

    // ======================
    // STABLE CLICK
    // ======================

    private void safeClick(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {

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

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;
                if (attempts == 3) {
                    throw new RuntimeException("Unable to click element: " + locator);
                }
            }
        }
    }
}