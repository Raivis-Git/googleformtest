package google;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleFormTest extends GoogleFormTestUtil {

    @BeforeClass
    public void setUpClass() {
        // Specify the exact Chrome version you're using
        Reporter.log("We used Google Chrome Ver 129.0.6668.101 for this test");
        WebDriverManager.chromedriver().browserVersion("129.0.6668.101").setup();
    }

    @BeforeMethod
    public void setUpMethod() {
        clearTestSteps();
        instantiateForm();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testValidAllFieldsFilled() {
        chooseRadioButton(radioXPath, 3);
        logStep("Radio button is set to 3rd option");
        setRequiredFields("Name", "example@email.com", "address");
        inputDataIntoInputField(phoneNumberXPath, "1234567890");
        logStep("Phone number is set to: 1234567890");
        inputDataIntoTextArea(commentsXPath, "sample text");
        logStep("Comments are set to: sample text");
        clickSubmitButton();

        Assert.assertTrue(isSuccessful(), "Form submission should be successful with all fields filled");
        logStep("Form submission was successful");
    }

    @Test
    public void testValidOnlyRequiredFieldsFilled() {
        setRequiredFields("Name", "example@email.com", "address");
        clickSubmitButton();

        Assert.assertTrue(isSuccessful(), "Form submission should be successful with required fields filled");
        logStep("Form submission was successful");
    }

    @Test
    public void testInvalidEmptyFields() {
        clickSubmitButton();

        Assert.assertFalse(isSuccessful(), "Form submission should not be successful with empty fields");
        logStep("Form submission failed because required fields are empty");
        Assert.assertNotNull(getElementIfExists(By.xpath(nameXPath + alertXPath)).orElse(null),
                "Name should show an error message if submitted empty");
        Assert.assertNotNull(getElementIfExists(By.xpath(emailXPath + alertXPath)).orElse(null),
                "Email should show an error message if submitted empty");
        Assert.assertNotNull(getElementIfExists(By.xpath(addressXPath + alertXPath)).orElse(null),
                "Address should show an error message if submitted empty");
        logStep("Required fields showed an error");
    }

    @Test
    public void testInvalidPhoneNumberField() {
        setRequiredFields("Name", "example@email.com", "address");
        inputDataIntoInputField(phoneNumberXPath, "phone");
        logStep("Phone number is set to: phone");
        clickSubmitButton();

        logStep("Check if phone number shows an error");
        Assert.assertNotNull(getElementIfExists(By.xpath(phoneNumberXPath + alertXPath)).orElse(null),
                "Phone number must be a number");
        logStep("Phone number field showed an error");
    }

    @Test
    public void testInvalidEmailField() {
        setRequiredFields("Name", "email123", "address");
        clickSubmitButton();

        Assert.assertNotNull(getElementIfExists(By.xpath(phoneNumberXPath + alertXPath)).orElse(null),
                "Email must be in the following format 'example@example.com'");
        logStep("Email field showed an error");
    }

    @Test
    public void testRadioButtonOptions() {
        setRequiredFields("Name", "example@email.com", "address");

        setRadioButtonOption(1);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should only accept 3rd option");
        logStep("An error has been shown");

        setRadioButtonOption(2);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should only accept 3rd option");
        logStep("An error has been shown");

        setRadioButtonOption(4);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should only accept 3rd option");
        logStep("An error has been shown");

        setRadioButtonOption(5);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should only accept 3rd option");
        logStep("An error has been shown");

        setRadioButtonOption(6);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should only accept 3rd option");
        logStep("An error has been shown");

        setRadioButtonOption(3);
        Assert.assertNotNull(getElementIfExists(By.xpath(radioXPath + alertXPath)).orElse(null),
                "Radio button should accept 3rd option");
        logStep("Form submission was successful");
    }

    @Test
    public void testClearFormButton() {

    }

    @Test
    public void testClearSelectionButton() {

    }

}
