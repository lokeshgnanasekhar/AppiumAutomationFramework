package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomeToFlyingClubScreen extends EventHandler {

    @FindBy(id = "skymiles_number")
    WebElement flyingClubNumberLabel;

    @FindBy(id = "skymiles_congratulations_header_title")
    WebElement welcomeToFlyingClubTitleLabel;


    public WelcomeToFlyingClubScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(welcomeToFlyingClubTitleLabel);
        if (!welcomeToFlyingClubTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Welcome To Flying Club Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Welcome To Flying Club Screen and Account Created Successfully");
        Log.info("Your Flying Club Number" + flyingClubNumberLabel.getText());
    }

}
