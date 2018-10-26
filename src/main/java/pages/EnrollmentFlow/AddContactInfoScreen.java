package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddContactInfoScreen extends EventHandler {


    @FindBy(id = "text_input_phoneNumber")
    WebElement phoneNumberTextField;

    @FindBy(id = "checkbox_newsAndSpecialOffers")
    WebElement newAndOffersCheckbox;

    @FindBy(id = "button_4")
    WebElement contiuneButton;

    @FindBy(id = "title_label")
    WebElement addContactInfoLabel;

    @FindBy(id = "DONE")
    WebElement doneButton;

    public AddContactInfoScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(addContactInfoLabel);
        if (!addContactInfoLabel.isDisplayed()) {
            throw new IllegalStateException("Add Contact Info Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Add Contact Info Screen");
    }

    public AddressScreen fillContactInfoDetails(String phoneNumber, boolean doSubscribe) {

        click(phoneNumberTextField);
        Log.info("Clicked on Phone Number TextField");
        enterText(phoneNumberTextField, phoneNumber);
        Log.info("Entered " + phoneNumber + " in Phone Number field");
        click(doneButton);

        if(doSubscribe) {
            click(newAndOffersCheckbox);
            Log.info("Opted for News and Offers");
        }
        click(contiuneButton);
        Log.info("Clicked on Continue Button");
        return new AddressScreen(driver);

    }

}
