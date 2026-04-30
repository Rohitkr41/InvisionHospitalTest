package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Patient Registration Heading
    By patientRegistrationHeading =
            By.xpath("//h4[contains(text(),'Patient Registration')]");

    public boolean isRegistrationPageDisplayed() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(patientRegistrationHeading)
        );
        return driver.findElement(patientRegistrationHeading).isDisplayed();
    }
}