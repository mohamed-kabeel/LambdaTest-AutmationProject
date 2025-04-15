package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {
    private static ThreadLocal<WebDriver> driverThreadLoacal = new ThreadLocal<>();
    public static void setupDriver(String driver){
        switch (driver.toLowerCase()){
            case "chrome":
                driverThreadLoacal.set(new ChromeDriver());
                break;
            case "firefox":
                driverThreadLoacal.set(new FirefoxDriver());
                break;
            case "safari" :
                driverThreadLoacal.set(new SafariDriver());
            default:
                driverThreadLoacal.set(new EdgeDriver());
        }
    }
    public static WebDriver getDriver(){
        return driverThreadLoacal.get();
    }
}
