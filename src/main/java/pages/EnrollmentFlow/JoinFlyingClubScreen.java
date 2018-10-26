package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinFlyingClubScreen extends EventHandler {

    @FindBy(id = "text_input_emailAddress")
    WebElement emailAddressTextField;

    @FindBy(id = "password_input_password")
    WebElement passwordTextField;

    @FindBy(id = "confirm_password_input_password")
    WebElement confirmPasswordTextField;

    @FindBy(id = "button_3")
    WebElement contiuneButton;

    @FindBy(id = "title_label")
    WebElement joinFlyingClubTitleLabel;


    public JoinFlyingClubScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(joinFlyingClubTitleLabel);
        if (!joinFlyingClubTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Login Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Join Flying Club Screen");
    }

    public CreateYourProfileScreen fillJoinFlyingClubScreenDetails(String email, String password) {

        click(emailAddressTextField);
        Log.info("Clicked on Email TextField");
        enterText(emailAddressTextField, email);
        Log.info("Entered " + email + " in Email field");
        enterText(passwordTextField, password);
        Log.info("Entered " + password + " in Password field");
        enterText(confirmPasswordTextField, password);
        Log.info("Entered " + password + " in Confirm Password field");
        click(contiuneButton);
        Log.info("Clicked on Continue Button");
        return new CreateYourProfileScreen(driver);
    }

}

