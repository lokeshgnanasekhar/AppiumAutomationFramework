package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import modules.EventHandler;
import modules.Log;
import modules.ProjectConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightStatusScreen extends EventHandler {

    @AndroidFindBy(id = "default_spinner_target")
    MobileElement flightDateSpinner;

    @AndroidFindBy(id = "flt_status_text_title")
    @iOSFindBy(id = "Flight Status")
    MobileElement flightStatusTitle;

    @AndroidFindBy(id = "flight_number_edit_text_flt_status")
    @iOSFindBy(accessibility = "SearchByFlightNumber")
    MobileElement flightNumberField;

    @AndroidFindBy(id = "flight_status_search_btn")
    @iOSFindBy(id = "search_button")
    MobileElement viewStatusBtn;

    @iOSFindBy(id = "DONE")
    MobileElement doneBtn;

    public FlightStatusScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);

        waitForElementToBeVisible(flightStatusTitle);

        if (!flightStatusTitle.isDisplayed()) {
            throw new IllegalStateException("Flight Status Screen is expected, but not displayed!");
        }
        Log.info("Navigated to Flight Status Screen");

    }

    public FlightStatusScreen setFlightDateAndNumber(String flightDay, String flightNumber){
        if(new ProjectConfigReader().getPlatformName().equals("android")) {
            setTextInSpinnerView(flightDateSpinner, flightDay);
        }
        Log.info("Selected Date");
        EnterFlightNumber(flightNumber);
        return this;
    }

    public void EnterFlightNumber(String text){
        enterText(flightNumberField,text);
        if(new ProjectConfigReader().getPlatformName().equals("ios")) {
            click(doneBtn);
        }
        Log.info("Entered Flight Info");
    }

    public void submitFlightDetails(){
        click(viewStatusBtn);
        Log.info("Submitted Flight Details");
    }

}

