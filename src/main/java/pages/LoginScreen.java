package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EnrollmentFlow.JoinFlyingClubScreen;

import java.util.concurrent.TimeUnit;


/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LoginScreen extends EventHandler {

    @FindBy(id = "DONE")
    WebElement doneInWelcomePage;

    @AndroidFindBy(id = "title_login")
    @iOSFindBy(accessibility = "LoginPageHeaderLabel")
    WebElement loginLabel;

    @FindBy(id = "username_field")
    WebElement userNameTextField;

    @FindBy(id = "password_field")
    WebElement passwordTextField;

    @FindBy(id = "Join skymiles")
    WebElement joinFlyingClub;

    @AndroidFindBy(id = "continue_as_guest_login")
    @iOSFindBy(id = "continue_as_guest_button")
    MobileElement continueAsGuestButton;

    @AndroidFindBy(id = "action_done")
    @iOSFindBy(accessibility = "DONE")
    MobileElement dlTickMarkWelcomePage;

    public LoginScreen(AppiumDriver driver) {

        this.driver = driver;
        // PageFactory.initElements(driver, this); -- for General web elements
        // For Mobile elements whennever  you use @androidFindBy or @iOSFindBy
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        closeWelcomeScreen();
        waitForElementToBeVisible(loginLabel);
        if (!loginLabel.isDisplayed()) {
            throw new IllegalStateException("Login Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Login Screen");
    }

    public void login(String userName, String password) {

        click(userNameTextField);
        Log.info("Clicked on User Name TextField");
        enterText(userNameTextField, userName);
        Log.info("Entered " + userName + " in username field");
        enterText(passwordTextField, password);
        Log.info("Entered " + password + " in Password field");
    }

    public JoinFlyingClubScreen tapJoinFlyingClub() {
        click(joinFlyingClub);
        return new JoinFlyingClubScreen(driver);
    }

    public BookAFlightScreen continueAsAGuest() {
        click(continueAsGuestButton );
        return new BookAFlightScreen(driver);
    }


    public void closeWelcomeScreen() {
        waitFor(5);
        if(isElementDisplayed(dlTickMarkWelcomePage)) {
            click(dlTickMarkWelcomePage);
        }
    }
}
