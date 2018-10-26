package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressScreen extends EventHandler {

    @FindBy(id = "text_input_addressLine1")
    WebElement addressLine1TextField;

    @FindBy(id = "text_input_addressLine2")
    WebElement addressLine2TextField;

    @FindBy(id = "text_input_addressLine4")
    WebElement cityTextField;

    @FindBy(id = "dropdown_picker_addressLine7")
    WebElement stateDropDownButton;

    @FindBy(id = "text_input_addressLine9")
    WebElement postalTextField;

    @FindBy(id = "button_8")
    WebElement contiuneButton;

    @FindBy(id = "title_label")
    WebElement addressTitleLabel;

    @FindBy(id = "dropdown_done")
    WebElement done;

    public AddressScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(addressTitleLabel);
        if (!addressTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Address Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Address Screen");
    }

    public AccountSecurityScreen fillAddressDetails(String address1, String address2, String city , String state, String postal) {

        click(addressLine1TextField);
        Log.info("Clicked on Address Line1 TextField");
        enterText(addressLine1TextField, address1);
        Log.info("Entered " + address1 + " in Address Line1 field");
        enterText(addressLine2TextField, address2);
        Log.info("Entered " + address2 + " in Address Line2 field");
        enterText(cityTextField, city);
        Log.info("Entered " + city + " in City field");
        click(stateDropDownButton);
        Log.info("Clicked on State");
        setTextInPickerView(state);
        Log.info("Chosen " + state + " in State Picker");
        click(done);
        Log.info("Closed State Picker");
        click(postalTextField);
        Log.info("Clicked on POstal TextField");
        enterText(postalTextField, postal);
        Log.info("Entered " + postal + " in Postal field");
        closeKeyboard();
        click(contiuneButton);
        Log.info("Clicked on Continue Button");
        return  new AccountSecurityScreen(driver);

    }

}



