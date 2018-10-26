package testutils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirstRun {

    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("platformVersion", "11.1");
        desiredCapabilities.setCapability("deviceName", "iPhone 7");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("app", "/Users/ctsuser1/Desktop/Delta/automation/FlyVirginAtlantic.app");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new IOSDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        //(new TouchAction(driver)).tap(341, 42).perform();
        //(new TouchAction(driver)).tap(new PointOption(341,42)).
         //       self.driver.tap((128.90625, 335.90625 ));
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("DONE");
       // el1.click();
        driver.findElement(By.id("DONE")).click();

        MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("username_field");
        el2.click();
        el2.sendKeys("9890909890");
        MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("password_field");
        el3.click();
        el3.sendKeys("pass4321");




        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}