package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.EnrollmentFlow.JoinFlyingClubScreen;

import java.util.HashMap;


/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class FlightSearchResultsScreen extends EventHandler {

    @iOSFindBy(accessibility = "selectSliceLabel")
    MobileElement selectFlightTitle;

    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"BASIC ECONOMY\"]")
    MobileElement basicEconomy;

    @iOSFindBy(accessibility = "viewDetails_2")
    MobileElement viewDetails;

    public FlightSearchResultsScreen(AppiumDriver driver) {

        this.driver = driver;
        // PageFactory.initElements(driver, this); -- for General web elements
        // For Mobile elements whennever  you use @androidFindBy or @iOSFindBy
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        waitForElementToBeVisible(selectFlightTitle);
        if (!selectFlightTitle.isDisplayed()) {
            throw new IllegalStateException("Flight Search Screen is expected, but not displayed!");
        }

        Log.info("Navigated to Flight Search Results Screen");
    }

    public FlightSearchResultsScreen chooseClass(String classtype){
        switch (classtype){
            case "Economy": click(basicEconomy);
                            break;
            case "Main": break;

        }
        return this;
    }

    public void scrollToViewDetails(String ForFlight){


        String text = "DL4992: JFK ▸ ORD Operated by Endeavor Air DBA Delta Connection";
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("predicateString", "value == '" + text + "'");
        driver.executeScript("mobile:scroll", scrollObject);

        waitFor(10);

    }

    ////*[@accessibilityIdentifier='resultCell_2']//*[@accessibilityLabel='View Details']


}
