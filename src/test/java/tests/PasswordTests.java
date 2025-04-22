package tests;

import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v130.fedcm.model.Account;
import org.testng.annotations.*;
import pages.AddressPage;
import pages.PasswordPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;
import static utilities.JsonUtils.getJsonValue;

@Listeners(SuiteListener.class)
public class PasswordTests {
    ScreenRecorderUtils.ScreenRecorder recorder;

    String path = "src/test/resources/password.json";
    WebDriver driver;
    PasswordPage passwordPage ;
    /*@BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");

    }*/
    @BeforeClass
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        passwordPage = new PasswordPage(driver);
        getDriver().manage().window().maximize();
        getDriver().get("https://ecommerce-playground.lambdatest.io/");
       recorder =  ScreenRecorderUtils.start(driver,"password");

        new LoginTest().validLogin(driver);
    }

    @BeforeMethod
    public void setupMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
        passwordPage.clickMyAccountBtnBase();
        passwordPage
                .clickPasswrdBtn();
    }

    @Test
    public void testValidData1() {
        fillData("validData");
        passwordPage.verifysuccessifulAlert();
    }

    @Test
    public void testInvalidData1() {
        fillData("invalidData1");
        passwordPage.verifyPasswordError();

    }
    @Test
    public void testInvalidData2() {
        fillData("invalidData2");
        passwordPage.verifyConfirmPasswordError();

    }
    @Test
    public void testInvalidData3() {
        fillData("invalidData3");
        passwordPage.verifyPasswordError();
    }
    @Test
    public void testInvalidData4() {
        fillData("invalidData4");
        passwordPage.verifyPasswordError();
    }
    public void fillData(String object){
      passwordPage
                .enterPassword(getJsonValue(path,object+".password"))
                .enterConfirmPassword(getJsonValue(path,object+".confirm"))
                .clickContinueBtn();
    }
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("password");
        deleteFolderCompletely("test-outputs/screen-records/password");

        driver.close();
    }

}
