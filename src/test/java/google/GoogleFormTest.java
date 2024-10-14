package google;

import io.github.bonigarcia.wdm.WebDriverManager;
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
        Reporter.log("We used Google Chrome Ver 129.0.6668.100 for this test");
        WebDriverManager.chromedriver().browserVersion("129.0.6668.100").setup();
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
    public void testAllFieldsFilledWithValidData() {
        selectRadioButtonByIndex(2);
        setRequiredFields("Name", "example@email.com", "address");
        enterPhoneNumber("1234567890");
        enterComments("sample text");
        clickSubmitButton();

        Assert.assertTrue(isSuccessfulSubmitFieldDisplayed(), "Form submission should be successful with all fields filled");
        logStep("Form submission was successful");
    }

    @Test
    public void testOnlyRequiredFieldsFilled() {
        setRequiredFields("Name", "example@email.com", "address");
        clickSubmitButton();

        Assert.assertTrue(isSuccessfulSubmitFieldDisplayed(), "Form submission should be successful with required fields filled");
        logStep("Form submission was successful");
    }

    @Test
    public void testInvalidEmptyFields() {
        clickSubmitButton();

        Assert.assertFalse(isSuccessfulSubmitFieldDisplayed(), "Form submission should not be successful with empty fields");
        logStep("Form submission failed because required fields are empty");
        Assert.assertTrue(isNameErrorDisplayed(),
                "Name should show an error message if submitted empty");
        Assert.assertTrue(isEmailErrorDisplayed(),
                "Email should show an error message if submitted empty");
        Assert.assertTrue(isAddressErrorDisplayed(),
                "Address should show an error message if submitted empty");
        logStep("Required fields showed an error");
    }

    @Test
    public void testInvalidPhoneNumberField() {
        setRequiredFields("Name", "example@email.com", "address");
        enterPhoneNumber("phone");
        clickSubmitButton();

        Assert.assertTrue(isPhoneNumberErrorDisplayed(),
                "Phone number must be a number");
        logStep("Phone number field showed an error");
    }

    @Test
    public void testInvalidEmailField() {
        setRequiredFields("Name", "email123", "address");

        clickSubmitButton();
        Assert.assertTrue(isEmailErrorDisplayed(),
                "Email must be in the following format 'example@example.com'");
        logStep("Email field showed an error");
    }

    @Test
    public void testRadioButtonOption1() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(0);
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button should only accept 3rd option");
        logStep("Radio field showed an error");
    }

    @Test
    public void testRadioButtonOption2() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(1);
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button should only accept 3rd option");
        logStep("Radio field showed an error");
    }

    @Test
    public void testRadioButtonOption3() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(2);
        clickSubmitButton();
        Assert.assertFalse(radioDisplaysError(),
                "Radio button should accept 3rd option");
        Assert.assertTrue(isSuccessfulSubmitFieldDisplayed(), "Form submission should be successful");
        logStep("Form submission was successful");
    }

    @Test
    public void testRadioButtonOption4() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(3);
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button should only accept 3rd option");
        logStep("Radio field showed an error");
    }

    @Test
    public void testRadioButtonOption5() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(4);
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button should only accept 3rd option");
        logStep("Radio field showed an error");
    }

    @Test
    public void testRadioButtonOption6() {
        setRequiredFields("Name", "example@email.com", "address");

        selectRadioButtonByIndex(5);
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button with input should show an error if no text provided");

        radioInputFieldEnterText("radio text");
        clickSubmitButton();
        Assert.assertTrue(radioDisplaysError(),
                "Radio button should only accept 3rd option");
        logStep("Radio field showed an error");
    }

    @Test
    public void testClearFormButton() {
        setRequiredFields("Name", "example@email.com", "address");
        selectRadioButtonByIndex(5);
        radioInputFieldEnterText("radio text");
        enterPhoneNumber("123456789");
        enterComments("sample text");
        clickClearFormButton();
        clickConfirmClearFormButton();

        Assert.assertNull(getSelectedRadioButton(), "Radio button shouldn't be selected");
        Assert.assertFalse(radioInputFieldHasValue(), "Radio input field should be empty");
        Assert.assertFalse(nameFieldHasValue(), "Name field should be empty");
        Assert.assertFalse(emailFieldHasValue(), "Email field should be empty");
        Assert.assertFalse(addressFieldHasValue(), "Address field should be empty");
        Assert.assertFalse(phoneNumberFieldHasValue(), "Phone number field should be empty");
        Assert.assertFalse(commentsFieldHasValue(), "Comments field should be empty");
        logStep("All fields are cleared");
    }

    @Test
    public void testEditAnswersButton() {
        setRequiredFields("Name", "example@email.com", "address");
        selectRadioButtonByIndex(5);
        radioInputFieldEnterText("radio text");
        enterPhoneNumber("123456789");
        enterComments("sample text");
        clickSubmitButton();
        clickEditAnswersButton();

        Assert.assertEquals(getNameFieldValue(), "Name");
        Assert.assertEquals(getEmailFieldValue(), "example@email.com");
        Assert.assertEquals(getAddressFieldValue(), "address");
        Assert.assertEquals(getPhoneNumberFieldValue(), "123456789");
        Assert.assertEquals(getCommentsFieldValue(), "sample text");
        Assert.assertNotNull(getSelectedRadioButton());
        Assert.assertEquals(getRadioInputFieldValue(), "radio text");
        logStep("Filled fields are saved upon edit");
    }

    @Test
    public void testClearSelectionButton() {
        setRequiredFields("Name", "example@email.com", "address");
        logStep("Check if radio clear button is hidden");
        Assert.assertFalse(isClearRadioButtonDisplayed(), "Radio clear button should be hidden");
        selectRadioButtonByIndex(5);
        radioInputFieldEnterText("radio text");
        logStep("Check if radio clear button is visible");
        Assert.assertTrue(isClearRadioButtonDisplayed(), "Radio clear button should be visible");
        clickClearRadioButton();
        Assert.assertNull(getSelectedRadioButton(), "Radio button shouldn't be selected");
        Assert.assertFalse(radioInputFieldHasValue(), "Radio input field should be empty");
        Assert.assertFalse(isClearRadioButtonDisplayed(), "Radio button should be hidden");
    }

}
