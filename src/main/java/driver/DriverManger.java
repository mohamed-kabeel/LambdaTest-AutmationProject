package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;


public class DriverManger {
    private static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<WebDriver>();
    public static void setDriver(String driver){
        switch(driver.toLowerCase()){
            case "firefox":
                threadLocalDriver.set(new FirefoxDriver(setFireFoxOption()));
                break;
            case "chrome":
                threadLocalDriver.set(new ChromeDriver(setChromeOption()));
                break;
            default:
                threadLocalDriver.set(new EdgeDriver());
                System.out.println("Edge browser is selected");
                break;
        }
    }
    public static WebDriver getDriver(){

        return threadLocalDriver.get();
    }
    static FirefoxOptions setFireFoxOption(){
        FirefoxOptions options =  new FirefoxOptions();
        List<String> firefoxOptions = new ArrayList<>();
        firefoxOptions.add("--headless");
        firefoxOptions.add("--disable-gpu");
        firefoxOptions.add("--window-size=1920,1080");
        firefoxOptions.add("--ignore-certificate-errors");
        firefoxOptions.add("--start-maximized");
        firefoxOptions.add("--disable-infobars");
        firefoxOptions.add("--disable-dev-shm-usage");
        firefoxOptions.add("--no-sandbox");
        firefoxOptions.add("--disable-extensions");
        firefoxOptions.add("--disable-dev-shm-usage");
        firefoxOptions.add("--no-sandbox");
        firefoxOptions.add("--disable-extensions");
        options.addArguments();

        return options;
    }
    static ChromeOptions setChromeOption(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        return options;
    }

    static EdgeOptions setEdgeOption() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");
        return options;

    }

}
