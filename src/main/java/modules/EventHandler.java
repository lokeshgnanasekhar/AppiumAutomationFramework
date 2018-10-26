package modules;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class EventHandler {

    public AppiumDriver driver;

    /*
	 * Clicking a Button , Link Text , Partial LinkText
	 */
    public void click(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.click();
        } catch (ElementNotVisibleException expection) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
    }

    /*
     * Sending Text to Input fields
     */
    public void enterText(WebElement element, String textToEnter) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(textToEnter);
           // closeKeyboard();
        } catch (ElementNotVisibleException exception) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
    }

    public boolean waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement elementToWait = wait.until(ExpectedConditions
                    .visibilityOf(element));
            if (elementToWait != null) {
                return true;
            }

        } catch (ElementNotVisibleException exception) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
        return false;

    }

    public void waitFor(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeKeyboard(){
        driver.hideKeyboard();
    }

    public void setTextInPickerView(String text) {
         driver.findElementByClassName("XCUIElementTypePickerWheel")
                            .sendKeys(text);

    }

    public void setTextInSpinnerView(MobileElement spinnerElement,String text) {
        spinnerElement.click();
        driver.findElementByXPath("//*[contains(@text,'"+text+"')]").click();

    }

    public boolean isElementDisplayed(WebElement element){
        try{
            element.isDisplayed();
            return true;
        }catch(Exception ex){
            return false;
        }

    }

    public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    public void switchToContext(String type)
    {
        Set<String> contextNames = driver.getContextHandles();
        /*for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }*/

        if (type.equals("NATIVE")){
            driver.context((String) contextNames.toArray()[0]);
            System.out.println("Switched To Native Context");
        }
        else{
            driver.context((String) contextNames.toArray()[1]);
            System.out.println("Switched To Web Context");
        }


    }

}
