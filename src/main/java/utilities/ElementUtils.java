package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class ElementUtils {

    public static void scrollElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
    }

    public static Boolean checkElementVisibility(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public static void hover(WebDriver driver, By locator) {
        WaitUtils.fluentWait(driver, locator, 3000)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        new Actions(driver).moveToElement(driver.findElement(locator)).perform();
    }

    public static void takeElementScreenshot(WebDriver driver, By locator, String path) throws java.io.IOException {
        File src = ((TakesScreenshot) driver.findElement(locator)).getScreenshotAs(OutputType.FILE);
        File dest = new File(path);
        org.apache.commons.io.FileUtils.copyFile(src, dest);
    }
}
