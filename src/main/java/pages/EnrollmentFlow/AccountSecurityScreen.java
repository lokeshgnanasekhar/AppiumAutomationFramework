package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSecurityScreen extends EventHandler {

    @FindBy(id = "dropdown_picker_question1")
    WebElement securityQuestion1Button;

    @FindBy(id = "text_input_answer1")
    WebElement securityAnswer1TextField;

    @FindBy(id = "dropdown_picker_question2")
    WebElement securityQuestion2Button;

    @FindBy(id = "text_input_answer2")
    WebElement securityAnswer2TextField;

    @FindBy(id = "checkbox_privacyPolicy")
    WebElement privacyPolicyCheckbox;

    @FindBy(id = "dropdown_done")
    WebElement done;

    @FindBy(id = "button_5")
    WebElement createAccountButton;

    @FindBy(id = "title_label")
    WebElement accountSecurityTitleLabel;


    public AccountSecurityScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(accountSecurityTitleLabel);
        if (!accountSecurityTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Account Security Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Account Security Screen");
    }

    public WelcomeToFlyingClubScreen fillAccountSecurityDetails(String question1, String answer1,String question2, String answer2 ) {

        click(securityQuestion1Button);
        Log.info("Clicked on Security Question1");
        setTextInPickerView(question1);
        Log.info("Chosen " + question1 + " in Question Picker");
        click(done);
        Log.info("Closed question1 Picker");
        click(securityAnswer1TextField);
        Log.info("Clicked on Security Answer1 TextField");
        enterText(securityAnswer1TextField, answer1);
        Log.info("Entered " + answer1 + " in Security answer1 field");
        closeKeyboard();
        click(securityQuestion2Button);
        Log.info("Clicked on Security Question2");
        setTextInPickerView(question2);
        Log.info("Chosen " + question2 + " in Question Picker");
        click(done);
        Log.info("Closed question2 Picker");
        click(securityAnswer2TextField);
        Log.info("Clicked on Security Answer2 TextField");
        enterText(securityAnswer2TextField, answer2);
        Log.info("Entered " + answer2 + " in Security answer2 field");
        closeKeyboard();
        click(privacyPolicyCheckbox);
        Log.info("Clicked on Privacy Policy Checkbox");
        click(createAccountButton);
        Log.info("Clicked on create Account Button");
        return new WelcomeToFlyingClubScreen(driver);

    }

}


