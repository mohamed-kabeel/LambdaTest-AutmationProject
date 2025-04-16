package tests;

import driver.DriverManger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;

public class E2ETest {
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;
    @BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }
    @BeforeClass
    public void setup() throws Exception {
        setDriver("edge");
        driver = DriverManger.getDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");//url
        recorder =   ScreenRecorderUtils.start(driver,"homepage");
    }
    @Test
    public void e2dTest(){

        LoginTest login = new LoginTest();
        login.validLogin(driver);
        new HomePage(driver)
                .clickHomeBtn()
                .clickTopProduct("HTC Touch HD")
                .clickAddToCartBtn()
                .clickViewCartPage()
                .clickCheckoutBtn()
                .clickBillingAgreebtn()
                .clickContinueBtn()
                .clickConfirmBtn();
    }
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("homepage");
        deleteFolderCompletely("test-outputs/screen-records/homepage");
        driver.close();
    }
}
