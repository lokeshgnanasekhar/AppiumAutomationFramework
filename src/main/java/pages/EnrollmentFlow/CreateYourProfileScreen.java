package pages.EnrollmentFlow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class CreateYourProfileScreen extends EventHandler {

    @FindBy(id = "text_input_firstName")
    WebElement firstNameTextField;

    @FindBy(id = "text_input_middleName")
    WebElement middleNameTextField;

    @FindBy(id = "text_input_lastName")
    WebElement lastNameTextField;

    @FindBy(id = "button_5")
    WebElement contiuneButton;

    @FindBy(id = "title_label")
    WebElement createYourProfileTitleLabel;

    @FindBy(id = "Day")
    WebElement dayButton;

    @FindBy(id = "Month")
    WebElement monthButton;

    @FindBy(id = "Year")
    WebElement yearButton;

    @FindBy(id = "dropdown_done")
    WebElement done;

    @FindBy(id = "dropdown_picker_gender")
    WebElement genderButton;

    public CreateYourProfileScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitForElementToBeVisible(createYourProfileTitleLabel);
        if (!createYourProfileTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Login Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Create Youre Profile Screen");
    }

    public AddContactInfoScreen fillCreateYourProfileDetails(String firstName, String middleName, String lastName ,String day, String month, String year, String gender) {

        click(firstNameTextField);
        Log.info("Clicked on First Name TextField");
        enterText(firstNameTextField, firstName);
        Log.info("Entered " + firstName + " in First Name field");
        enterText(middleNameTextField, middleName);
        Log.info("Entered " + middleName + " in Middle Name field");
        enterText(lastNameTextField, lastName);
        Log.info("Entered " + lastName + " in Last Name field");
        click(dayButton);
        Log.info("Clicked on Day");
        setTextInPickerView(day);
        Log.info("Chosen " + day + " in Day Picker");
        click(done);
        Log.info("Closed Day Picker");
        click(monthButton);
        Log.info("Clicked on Month");
        setTextInPickerView(month);
        Log.info("Chosen " + month + " in Month Picker");
        click(done);
        Log.info("Closed Month Picker");
        click(yearButton);
        Log.info("Clicked on Year");
        setTextInPickerView(year);
        Log.info("Chosen " + year + " in Year Picker");
        click(done);
        Log.info("Closed Year Picker");
        click(genderButton);
        Log.info("Clicked on Gender");
        setTextInPickerView(gender);
        Log.info("Chosen " + gender + " in Gender Picker");
        click(done);
        Log.info("Closed Gender Picker");
        click(contiuneButton);
        Log.info("Clicked on Continue Button");
        return new AddContactInfoScreen(driver);

    }

}


