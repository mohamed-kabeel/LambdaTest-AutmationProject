package tests;

import driver.DriverManger;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;
@Listeners(SuiteListener.class)
public class E2ETest {
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;
    /*@BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }*/
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
