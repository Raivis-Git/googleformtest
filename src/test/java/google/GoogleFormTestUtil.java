package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class GoogleFormTestUtil implements TestStepLogger, GoogleFormTestConstants {

    static WebDriver driver = new ChromeDriver(
            new ChromeOptions()
            .addArguments("--remote-allow-origins=*")
    );
    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    WebElement submitButton;

    void inputDataIntoInputField(String xPath, String inputText) {
        try {
            String id = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath + "/ancestor::div[@role='heading']"))).getAttribute("id");
            WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format("input[aria-labelledby='%s']", id))));
            input.clear();
            input.sendKeys(inputText);
        } catch (Exception ignored) {}
    }

    void inputDataIntoTextArea(String xPath, String inputText) {
        try {
            String id = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath + "/ancestor::div[@role='heading']"))).getAttribute("id");
            WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format("textarea[aria-labelledby='%s']", id))));
            input.clear();
            input.sendKeys(inputText);
        } catch (Exception ignored) {}
    }

    void chooseRadioButton(String xPath, int optionNumber) {
        if (optionNumber == 0)
            throw new IllegalArgumentException("Chosen option must be a positive number");

        optionNumber--;

        try {
            WebElement radioGroup = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
            List<WebElement> radioButtons = radioGroup.findElements(By.xpath(".//div[@role='radio']"));

            if (radioButtons.size() < optionNumber)
                throw new IllegalArgumentException(String.format("Chosen option does not exist: '%s'", optionNumber));

            WebElement radioButtonToClick = radioButtons.get(optionNumber);
            radioButtonToClick.click();
            logStep("");
        } catch (Exception ignored) {}
    }

    Optional<WebElement> getElementIfExists(By locator) {
        try {
            return Optional.ofNullable(
                    wait.until(ExpectedConditions.presenceOfElementLocated(locator)));
        } catch (Exception ignored) {}
        return Optional.empty();
    }

    boolean isSuccessful() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'vHW8K')]")))
                    .getText().equals(validMessage);
        } catch (Exception ignored) {}
        return false;
    }

    void setRequiredFields(String name, String email, String address) {
        inputDataIntoInputField(nameXPath, name);
        logStep("Name is set to: " + name);
        inputDataIntoInputField(emailXPath, email);
        logStep("Email is set to: " + email);
        inputDataIntoTextArea(addressXPath, address);
        logStep("Address is set to: " + address);
    }

    void setRadioButtonOption(int optionNumber) {
        chooseRadioButton(radioXPath, optionNumber);
        logStep("Choose radio button option nr." + optionNumber);
        clickSubmitButton();
    }

    void clickSubmitButton() {
        submitButton.click();
        logStep("Submit button is clicked");
    }

    void instantiateForm() {
        driver.get(FORM_URL);
        logStep("Navigated to form URL");
        submitButton = driver.findElement(By.xpath("//div[contains(@role, 'button')]//span[contains(text(), 'Iesniegt')]/ancestor::div[contains(@role, 'button')]"));
    }
}
