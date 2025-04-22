package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static utilities.ElementUtils.hover;
import static utilities.ElementUtils.scrollElement;
import static utilities.WaitUtils.fluentWait;

public class ElementActions {

    public static void enterText(WebDriver driver, By locator, String s) {

        fluentWait(driver, locator, 15000)
                .until(x -> (ElementUtils.checkElementVisibility(driver, locator))&&driver.findElement(locator).isDisplayed()&&driver.findElement(locator).isEnabled());
        fluentWait(driver,locator,15000).until(ExpectedConditions.elementToBeClickable(locator));
        hover(driver,locator);
        fluentWait(driver, locator, 15000)
                .until(x -> (ElementUtils.checkElementVisibility(driver, locator))&&driver.findElement(locator).isDisplayed()&&driver.findElement(locator).isEnabled());
        driver.findElement(locator).sendKeys(s);
    }

    public static void enterTextAndEnter(WebDriver driver, By locator, String data) {

        fluentWait(driver, locator, 15000)
                .until(x -> (ElementUtils.checkElementVisibility(driver, locator))&&driver.findElement(locator).isDisplayed()&&driver.findElement(locator).isEnabled());
        fluentWait(driver,locator,15000).until(ExpectedConditions.elementToBeClickable(locator));
        hover(driver,locator);
      // System.out.println("data entered");
        fluentWait(driver,locator,15000).until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver)
                .sendKeys(driver.findElement(locator), data)
                .keyDown(Keys.ENTER)
                .keyUp(Keys.ENTER)
                .build()
                .perform();
    }

    public static void selectDropListElement(WebDriver driver, By locator, int index) {
        fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        //scrollElement(driver, locator);
        new Select(driver.findElement(locator)).selectByIndex(index);
    }

    public static void chooseElementFromDropList(WebDriver driver, By locator, String value) {
        fluentWait(driver,locator,15000).until(ExpectedConditions.elementToBeClickable(locator));

        new Select(driver.findElement(locator)).selectByValue(value);
    }

    public static void clearText(WebDriver driver, By locator) {

       /* WebElement element = driver.findElement(locator);
       /*fluentWait(driver, locator, 15000)
                .until(driver1 -> element.isEnabled() && element.isDisplayed());*/
        fluentWait(driver,locator,15000).until(ExpectedConditions.elementToBeClickable(locator));

        hover(driver, locator);
        driver.findElement(locator).clear();
       System.out.println("yes");
    }

    public static void clickButton(WebDriver driver, By locator) {
       fluentWait(driver, locator, 20000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        scrollElement(driver,locator);
        hover(driver, locator);
        fluentWait(driver, locator, 20000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        /*try {
            fluentWait(driver, locator, 20000)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            //scrollElement(driver, locator);
            hover(driver, locator);
            fluentWait(driver, locator, 20000)
                    .until(ExpectedConditions.elementToBeClickable(locator));
            driver.findElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted, using JS click instead");
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }*/
    }
    public static void clickButtonWithoutScroll(WebDriver driver, By locator) {
        fluentWait(driver, locator, 20000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        //scrollElement(driver, locator);
        hover(driver, locator);
        fluentWait(driver, locator, 20000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    public static void clickingCheckBox(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public static void clickAndMove(WebDriver driver, By locator, int dist) {
        fluentWait(driver, locator, 10000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).clickAndHold(driver.findElement(locator))
                .moveByOffset((int) Math.round(dist * 0.11), 0)
                .release()
                .perform();
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        System.out.println("yes");
        return driver.findElements(locator);
    }

    public static void enterTextInWebElement(WebDriver driver, WebElement element, String s) {
        element.sendKeys(s);
    }

    public static void clickOnWebElement(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element);
        element.click();
    }

    public static void clearTextInWebElement(WebElement element) {

        element.clear();
    }

    public static String getDataFromElement(WebDriver driver, By locator) {
        fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator).getText();
    }
}
