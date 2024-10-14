package google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleFormPage {

    public static final String FORM_URL = "https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform";
    public final String NAME_FIELD_CSS = "input[aria-labelledby='i23']";

    public GoogleFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='i1']/ancestor::div[@role='listitem']//span[@class='RHiWt']")
    private WebElement radioGroupError;
    @FindBy(xpath = "//div[@role='radio']")
    private List<WebElement> radioButtons;
    @FindBy(css = "input[class='Hvn9fb zHQkBf']")
    private WebElement radioInputField;

    @FindBy(css = "input[aria-labelledby='i23']")
    private WebElement nameField;
    @FindBy(xpath = "//div[@id='i23']/ancestor::div[@role='listitem']//span[@class='RHiWt']")
    private  WebElement nameFieldError;
    @FindBy(css = "input[aria-labelledby='i27']")
    private WebElement emailField;
    @FindBy(xpath = "//div[@id='i27']/ancestor::div[@role='listitem']//span[@class='RHiWt']")
    private WebElement emailFieldError;
    @FindBy(css = "textarea[aria-labelledby='i31']")
    private WebElement addressField;
    @FindBy(xpath = "//div[@id='i31']/ancestor::div[@role='listitem']//span[@class='RHiWt']")
    private  WebElement addressFieldError;
    @FindBy(css = "input[aria-labelledby='i35']")
    private WebElement phoneNumberField;
    @FindBy(xpath = "//div[@id='i35']/ancestor::div[@role='listitem']//span[@class='RHiWt']")
    private  WebElement phoneNumberFieldError;
    @FindBy(css = "textarea[aria-labelledby='i39']")
    private WebElement commentsField;
    @FindBy(css = "div[aria-label='Submit']")
    private WebElement submitButton;
    @FindBy(xpath = "(//div[@role='button' and @data-id='EBS5u'])[2]")
    private WebElement confirmClearFormButton;
    @FindBy(css = "div[class='uArJ5e UQuaGc kCyAyd l3F1ye TFBnVe']")
    private WebElement clearFormButton;
    @FindBy(xpath = "//div[@id='i5']/ancestor::div[@role='listitem']//div[@role='button']")
    private WebElement clearRadioButton;
    @FindBy(xpath = "//div[@class='c2gzEf']//a")
    private WebElement editAnswersButton;
    @FindBy(css = "div[class='vHW8K']")
    private WebElement successfulSubmitField;

    public WebElement getRadioGroupError() {
        return radioGroupError;
    }

    public List<WebElement> getRadioButtons() {
        return radioButtons;
    }

    public WebElement getRadioInputField() {
        return radioInputField;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getNameFieldError() {
        return nameFieldError;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getEmailFieldError() {
        return emailFieldError;
    }

    public WebElement getAddressField() {
        return addressField;
    }

    public WebElement getAddressFieldError() {
        return addressFieldError;
    }

    public WebElement getPhoneNumberField() {
        return phoneNumberField;
    }

    public WebElement getPhoneNumberFieldError() {
        return phoneNumberFieldError;
    }

    public WebElement getCommentsField() {
        return commentsField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getConfirmClearFormButton() {
        return confirmClearFormButton;
    }

    public WebElement getClearFormButton() {
        return clearFormButton;
    }

    public WebElement getClearRadioButton() {
        return clearRadioButton;
    }

    public WebElement getEditAnswersButton() {
        return editAnswersButton;
    }

    public WebElement getSuccessfulSubmitField() {
        return successfulSubmitField;
    }
}
