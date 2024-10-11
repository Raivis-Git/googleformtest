package google;

public interface GoogleFormTestConstants {

    String FORM_URL = "https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform";
    String validMessage = "Thanks for submitting your contact info!";
    String radioXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Select 3rd option')]/ancestor::div[@role='listitem']//div[@role='radiogroup']";
    String nameXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Name')]";
    String emailXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Email')]";
    String addressXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Address')]";
    String phoneNumberXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Phone number')]";
    String commentsXPath = "//div[contains(@class, 'M4DNQ')]//span[contains(text(), 'Comments')]";
    String alertXPath = "//ancestor::div[contains(@role, 'listitem')]//div[contains(@role, 'alert')]//span";

}
