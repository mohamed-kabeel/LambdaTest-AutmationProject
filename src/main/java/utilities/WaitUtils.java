package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.function.Function;

public class WaitUtils {
    public static FluentWait<WebDriver> fluentWait(WebDriver driver, By locator, int time) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(time))
                .pollingEvery(Duration.ofMillis(200))
                .withMessage("element isn't visible");
    }

    public static WebDriverWait explicitWait(WebDriver driver, By locator, int time) {
        return new WebDriverWait(driver, Duration.ofMillis(time));
    }
}
