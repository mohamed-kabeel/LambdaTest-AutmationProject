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
  

}
