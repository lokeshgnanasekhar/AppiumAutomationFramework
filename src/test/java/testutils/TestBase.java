package testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSFindBy;
import modules.EventHandler;
import modules.ProjectConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginScreen;
import modules.Log;

import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class TestBase {

    AppiumDriver driver;
    ProjectConfigReader projectConfigReader;
    ExtentHtmlReporter htmlReporter;
    ExtentReports extentReports;
    ExtentTest test;


    @BeforeMethod
    public void beforeTestExecution(Method method) {
        initialize(method.getName());
        createDriver();
    }

    @AfterMethod
    public void afterTestExecution(ITestResult result, Method method) {

            if (result.getStatus() == ITestResult.FAILURE) {
                String screenShotPath = null;
                try {
                    screenShotPath = capture(driver, "capture" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date()));

                    test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(screenShotPath));
                    test.fail(result.getThrowable());

                    test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Testcase PASSED", ExtentColor.GREEN));
            } else {
                test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Testcase SKIPPED", ExtentColor.ORANGE));
                test.skip(result.getThrowable());
            }
          ;
            extentReports.flush();

        driver.quit();
        Log.endTestCase(method.getName());
    }

    public void initialize(String testcaseName) {

        projectConfigReader = new ProjectConfigReader();
        Logger logger = Logger.getLogger(projectConfigReader.getURL());
        configReporter();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("ProjectConfig.properties").getFile());
            InputStream input = new FileInputStream(file);
            PropertyConfigurator.configure(input);
            Log.startTestCase(testcaseName);
            test = extentReports.createTest(testcaseName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createDriver() {

        String platformName = projectConfigReader.getPlatformName();
        String platformVersion = projectConfigReader.getPlatformVersion();
        String deviceName = projectConfigReader.getDeviceName();
        String automationName = projectConfigReader.getAutomationName();
        String appPath = projectConfigReader.getAppPath();
        String remoteUrl = projectConfigReader.getRemoteURL();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("automationName", automationName);
        desiredCapabilities.setCapability("app", appPath);


        URL url = null;
        try {
            url = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (platformName.equalsIgnoreCase("ios")) {
            driver = new IOSDriver(url, desiredCapabilities);
        }
        else{
            driver = new AndroidDriver(url, desiredCapabilities);
        }




    }

    public void configReporter() {
        htmlReporter = new ExtentHtmlReporter("./src/test/java/testresults/reports/report" + new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date())+ ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public String capture(WebDriver driver, String screenShotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String dest = "./src/test/java/testresults/snapshots/snap" +screenShotName+ ".jpg";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }

    public LoginScreen launchApp() {
        Log.info("Opening App " + projectConfigReader.getAppType());
        //resetApp("si");
        return new LoginScreen(driver);
    }

    public void resetApp(String environment){
        EventHandler handler = new EventHandler();
        driver.findElementById("DONE").click();
        handler.waitFor(3);
        driver.findElementByAccessibilityId("continue_as_guest_button").click();
        handler.waitFor(3);

        try {
            handler.click((WebElement) driver.findElementById("Allow"));
        }
        catch(Exception ex){

        }
        driver.findElementByAccessibilityId("More").click();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        driver.executeScript("mobile:scroll", scrollObject);
        driver.findElementByAccessibilityId("SETTINGS >").click();
        driver.findElementByAccessibilityId("automation").click();
        handler.waitFor(2);
        driver.findElementByXPath("//XCUIElementTypePicker[@name=\"DropdownButton-Picker\"]/XCUIElementTypePickerWheel").sendKeys(environment);
        driver.findElementById("DONE").click();
        driver.findElementByAccessibilityId("BACK").click();
        driver.findElementByAccessibilityId("LOG IN").click();
        handler.waitFor(10);

    }



}
