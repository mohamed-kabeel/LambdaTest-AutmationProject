package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ElementActions {

    public static void enterText(WebDriver driver, By locator, String s) {
        WaitUtils.fluentWait(driver, locator, 10000)
                .until(x -> ElementUtils.checkElementVisibility(driver, locator));
        ElementUtils.scrollElement(driver, locator);
        driver.findElement(locator).sendKeys(s);
    }

    public static void enterTextAndEnter(WebDriver driver, By locator, String data) {
        WaitUtils.fluentWait(driver, locator, 10000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver)
                .sendKeys(driver.findElement(locator), data)
                .keyDown(Keys.ENTER)
                .keyUp(Keys.ENTER)
                .build()
                .perform();
    }

    public static void selectDropListElement(WebDriver driver, By locator, int index) {
        WaitUtils.fluentWait(driver, locator, 10000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        ElementUtils.scrollElement(driver, locator);
        new Select(driver.findElement(locator)).selectByIndex(index);
    }

    public static void chooseElementFromDropList(WebDriver driver, By locator, String value) {
        new Select(driver.findElement(locator)).selectByValue(value);
    }

    public static void clearText(WebDriver driver, By locator) {
        WaitUtils.fluentWait(driver, locator, 10000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        ElementUtils.scrollElement(driver, locator);
        driver.findElement(locator).clear();
    }

    public static void clickButton(WebDriver driver, By locator) {
        WaitUtils.fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.elementToBeClickable(locator));
        ElementUtils.hover(driver, locator);
        driver.findElement(locator).click();
    }

    public static void clickingCheckBox(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public static void clickAndMove(WebDriver driver, By locator, int dist) {
        WaitUtils.fluentWait(driver, locator, 10000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).clickAndHold(driver.findElement(locator))
                .moveByOffset((int) Math.round(dist * 0.11), 0)
                .release()
                .perform();
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        WaitUtils.fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
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
        WaitUtils.fluentWait(driver, locator, 15000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
}
