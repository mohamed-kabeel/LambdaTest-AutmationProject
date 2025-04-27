package tests;
import com.github.javafaker.Faker;
import driver.DriverManger;
import io.qameta.allure.*;
import io.qameta.allure.internal.shadowed.jackson.databind.ser.Serializers;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Driver;

import static pages.BasePage.softAssert;
import static utilities.FileUtilsCustom.*;
import static utilities.JsonUtils.getJsonValue;
import static utilities.JsonUtils.updateJsonValue;

import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.LoginPage;
import pages.SingUpPage;
import pages.SingUpPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.*;

@Listeners(SuiteListener.class)
public class SingUpTests {

    String path = "src/test/resources/signUp.json";
    //SoftAssert softAssert = new SoftAssert();
    WebDriver driver;
    ScreenRecorderUtils.ScreenRecorder recorder;

    /*@BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");

    }*/
    @BeforeClass
    public void setup(){
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        recorder=  ScreenRecorderUtils.start(driver,"signup");

    }
    @BeforeMethod
    public void setupMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    @Test(dataProvider = "validSignUpData")
    public void validSignUpTest(String dataKey) {
        //if (dataKey.equals("validData1")) {
            updateJsonValue(path, dataKey + ".email", new Faker().internet().emailAddress());
       // }
        fillData(dataKey);
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/success");
    }

    @Test(dataProvider = "invalidSignUpData")
    public void invalidSignUpTest(String dataKey) {
        fillData(dataKey);
        assestErrorMesgs();

    }
    @Test(dataProvider = "validSignUpData")
    public void signUpWithExistingEmail(String dataKey) {
        fillData(dataKey);
        Assert.assertNotEquals(driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/success");
    }
    @AfterMethod
    public void quitMethod() {
        driver.manage().deleteAllCookies();
    }
    @DataProvider(name = "validSignUpData")
    public Object[][] validData() {
        return new Object[][]{{"validData1"}, {"validData2"}};

    }
    @DataProvider(name = "invalidSignUpData")
    public Object[][] invalidData() {
        return new Object[][]{{"invalidData1"}, {"invalidData2"}, {"invalidData3"}, {"invalidData4"}};
    }

    public void fillData(String object) {
        new SingUpPage(driver)
                .clickMyAccountBtnBase()
                .clickRegisterBtn()
                .enterFirstName(getJsonValue(path, object + ".firstName"))
                .enterLastName(getJsonValue(path, object + ".lastName"))
                .enterEmail(getJsonValue(path, object + ".email"))
                .enterTelphone(getJsonValue(path, object + ".telephone"))
                .enterPassword(getJsonValue(path, object + ".password"))
                .enterConfirmPassword(getJsonValue(path, object + ".confirmPassword"))
                .clickInputAgree()
                .clickContinueBtn();
    }

    public void assestErrorMesgs() {
        softAssert = new SoftAssert();
        new SingUpPage(driver)
                .verifyEmailError()
                .verifyPasswordError()
                .verifyFirstNameError()
                .verifyLastNameError()
                .verifyTelphoneError();
        softAssert.assertAll();
    }
   /* @AfterMethod
    public void tearDown() {
        getDriver().close();
    }*/
    @AfterClass
    public void endClass(){
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("signup");
        deleteFolderCompletely("test-outputs/screen-records/signup");
        driver.close();
    }
}

