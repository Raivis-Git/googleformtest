package google;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.*;

public class GoogleFormTestUtil implements TestStepLogger {

    static WebDriver driver = new ChromeDriver(
            new ChromeOptions()
            .addArguments("--remote-allow-origins=*")
    );

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    GoogleFormPage googleFormPage = new GoogleFormPage(driver);

    void setRequiredFields(String name, String email, String address) {
        enterName(name);
        logStep("Name is set to: " + name);
        enterEmail(email);
        logStep("Email is set to: " + email);
        enterAddress(address);
        logStep("Address is set to: " + address);
    }

    void instantiateForm() {
        driver.get(GoogleFormPage.FORM_URL);
        waitUntilVisibilityOfName();
        logStep("Navigated to form URL");
    }

    //Name
    public void enterName(String name) {
        googleFormPage.getNameField().clear();
        googleFormPage.getNameField().sendKeys(name);
    }

    public boolean nameFieldHasValue() {
        return !googleFormPage.getNameField().getText().isEmpty();
    }

    public String getNameFieldValue() {
        return googleFormPage.getNameField().getAttribute("data-initial-value");
    }

    public boolean isNameErrorDisplayed() {
        return isFieldDisplayed(googleFormPage.getNameFieldError());
    }

    //Email
    public void enterEmail(String email) {
        googleFormPage.getEmailField().clear();
        googleFormPage.getEmailField().sendKeys(email);
    }

    public boolean emailFieldHasValue() {
        return !googleFormPage.getEmailField().getText().isEmpty();
    }

    public String getEmailFieldValue() {
        return googleFormPage.getEmailField().getAttribute("data-initial-value");
    }

    public boolean isEmailErrorDisplayed() {
        return isFieldDisplayed(googleFormPage.getEmailFieldError());
    }

    //Address
    public void enterAddress(String address) {
        googleFormPage.getAddressField().clear();
        googleFormPage.getAddressField().sendKeys(address);
    }

    public boolean addressFieldHasValue() {
        return ! googleFormPage.getAddressField().getText().isEmpty();
    }

    public String getAddressFieldValue() {
        return googleFormPage.getAddressField().getAttribute("data-initial-value");
    }

    public boolean isAddressErrorDisplayed() {
        return isFieldDisplayed( googleFormPage.getAddressFieldError());
    }

    //Phone number
    public void enterPhoneNumber(String phoneNumber) {
        googleFormPage.getPhoneNumberField().clear();
        googleFormPage.getPhoneNumberField().sendKeys(phoneNumber);
        logStep("Phone number is set to: " + phoneNumber);
    }

    public boolean phoneNumberFieldHasValue() {
        return !googleFormPage.getPhoneNumberField().getText().isEmpty();
    }

    public String getPhoneNumberFieldValue() {
        return googleFormPage.getPhoneNumberField().getAttribute("data-initial-value");
    }

    public boolean isPhoneNumberErrorDisplayed() {
        return isFieldDisplayed(googleFormPage.getPhoneNumberFieldError());
    }

    //Comments
    public void enterComments(String comments) {
        googleFormPage.getCommentsField().clear();
        googleFormPage.getCommentsField().sendKeys(comments);
        logStep("Comments are set to: " + comments);
    }

    public boolean commentsFieldHasValue() {
        return !googleFormPage.getCommentsField().getText().isEmpty();
    }

    public String getCommentsFieldValue() {
        return googleFormPage.getCommentsField().getAttribute("data-initial-value");
    }

    //Radio
    public void selectRadioButtonByIndex(int index) {
        googleFormPage.getRadioButtons().get(index).click();
        logStep("Radio button choose option: " + ++index);

    }

    public WebElement getSelectedRadioButton() {
        return googleFormPage.getRadioButtons().stream()
                .filter(radioButton -> radioButton.getAttribute("aria-checked").equals("true"))
                .findFirst().orElse(null);
    }

    public boolean radioDisplaysError() {
        return isFieldDisplayed(googleFormPage.getRadioGroupError());
    }

    public void radioInputFieldEnterText(String text) {
        googleFormPage.getRadioInputField().clear();
        googleFormPage.getRadioInputField().sendKeys(text);
        logStep("Radio input field set to: " + text);
    }

    public boolean radioInputFieldHasValue() {
        return !googleFormPage.getRadioInputField().getAttribute("data-initial-value").isEmpty();
    }

    public String getRadioInputFieldValue() {
        return googleFormPage.getRadioInputField().getAttribute("data-initial-value");
    }

    //Button
    public void clickSubmitButton() {
        googleFormPage.getSubmitButton().click();
        logStep("Submit button is clicked");
    }

    public void clickClearFormButton() {
        googleFormPage.getClearFormButton().click();
        logStep("Clear form button clicked");
    }

    public void clickConfirmClearFormButton() {
        googleFormPage.getConfirmClearFormButton().click();
        logStep("Confirmed to clear form");
    }

    public void clickClearRadioButton() {
        googleFormPage.getClearRadioButton().click();
        logStep("Clear radio button clicked");
    }

    public boolean isClearRadioButtonDisplayed() {
        return isFieldDisplayed(googleFormPage.getClearRadioButton());
    }

    public void clickEditAnswersButton() {
        googleFormPage.getEditAnswersButton().click();
        logStep("Edit answers button clicked");
        waitUntilVisibilityOfName();
    }

    public boolean isSuccessfulSubmitFieldDisplayed() {
        return isFieldDisplayed(googleFormPage.getSuccessfulSubmitField());
    }

    public boolean isFieldDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    private void waitUntilVisibilityOfName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(googleFormPage.NAME_FIELD_CSS)));
    }
}
