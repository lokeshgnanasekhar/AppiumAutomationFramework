package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import modules.EventHandler;
import modules.Log;
import modules.ProjectConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.HashMap;

public class BookAFlightScreen extends EventHandler {

    @AndroidFindBy(accessibility = "Book A Flight")
    @iOSFindBy(accessibility = "Book A Flight")
    MobileElement bookAFlightTitleLabel;

    @FindBy(id = "Allow")
    WebElement allowButton;

    @FindBy(css = ".origin")
    WebElement orginTextField;

    @FindBy(css = ".destination")
    WebElement destinationTextField;

    @FindBy(id = "oneway_tab")
    WebElement oneWayTab;

    @FindBy(id = "search")
    WebElement findFlightsBtn;

    @iOSFindBy(className = "SearchField")
    WebElement airportSearchTextField;

    @FindBy(xpath = "//*[contains(@placeholder,\"Date\")]")
    WebElement dateTextField;

    @AndroidFindBy(id = "android:id/up")
    @iOSFindBy(accessibility = "More")
    MobileElement menuButton;

    @iOSFindBy(accessibility = "ApplyActive")
    MobileElement applyDateBtn;

    @AndroidFindBy(xpath = "//*[@text='Flight Status']")
    @iOSFindBy(id = "Flight Status")
    MobileElement menuFlightStatus;

    @AndroidFindBy(xpath = "//*[@text='Contact Us']")
    @iOSFindBy(id = "Contact Us")
    MobileElement menuContactUs;

    public BookAFlightScreen(AppiumDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if (isElementDisplayed(allowButton)) {
            click(allowButton);
        }

        waitForElementToBeVisible(bookAFlightTitleLabel);

        if (!bookAFlightTitleLabel.isDisplayed()) {
            throw new IllegalStateException("Book a Flight Screen is expected, but not displayed!");
        }
        Log.info("Navigated to Book A Flight Screen");

    }

    public BookAFlightScreen clickMenuForMoreOptions() {
        waitFor(5);
        click(menuButton);
        Log.info("Clicked on Menu");
        return this;
    }

    public FlightStatusScreen clickFlightStatusMenu() {
        menuFlightStatus.click();
        Log.info("Clicked on Menu-- Flight Status");
        return new FlightStatusScreen(driver);
    }

    public void clickContactUsMenu() {


        if (new ProjectConfigReader().getPlatformName().equals("android")) {
             /* MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.delta.mobile.android.alpha:id/drawer_items_list\")).getChildByText("
                        + "new UiSelector().className(\"android.widget.TextView\"), \"Parking Reminder\")"));
        element.click();*/
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                    ".scrollIntoView(new UiSelector().text(\"Contact Us\"));"))
                    .click();

            Log.info("Clicked on Menu-- Contact Us");

        } else {
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "down");
            driver.executeScript("mobile:scroll", scrollObject);

            menuContactUs.click();

        }
    }

    public BookAFlightScreen setOrigin(String originCityCode){
       // switchToContext("WEB");
        click(orginTextField);
        switchToContext("NATIVE");
        enterText(airportSearchTextField,originCityCode);
        //click(driver.findElement(By.xpath("//UIAStaticText[contains(@name, 'ATL')]")));
        click(driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,"+originCityCode+")]")));
        return this;

    }

    public BookAFlightScreen setDestination(String destinationCityCode){
        switchToContext("WEB");
        click(destinationTextField);
        switchToContext("NATIVE");
        enterText(airportSearchTextField,destinationCityCode);
        //click(driver.findElement(By.xpath("//UIAStaticText[contains(@name, 'ATL')]")));
        click(driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,"+destinationCityCode+")]")));
        return this;

    }

    public BookAFlightScreen setTripType(String tripType){
        switchToContext("WEB");
        click(oneWayTab);
        waitFor(5);
        return this;

    }

    public BookAFlightScreen selectDate()
    {
        switchToContext("WEB");
        click(dateTextField);
        switchToContext("NATIVE");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Day of the Month = "+day);
        click(driver.findElementByXPath("//XCUIElementTypeButton[@name='"+(day+1)+"']"));
        click(applyDateBtn);
        return  this;

    }

    public FlightSearchResultsScreen searchFlight()
    {
        switchToContext("WEB");
        click(findFlightsBtn);
        switchToContext("NATIVE");
        return new FlightSearchResultsScreen(driver);
    }


}
