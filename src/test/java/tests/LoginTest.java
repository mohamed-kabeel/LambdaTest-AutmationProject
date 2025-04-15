package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
//import static pages.BasePage.softAssert;
import static utilities.FileUtilsCustom.cleanAllureResults;
import static utilities.FileUtilsCustom.cleanFolderContents;
import static utilities.JsonUtils.getJsonValue;


@Epic("Authentication")
@Feature("Login Feature")
public class LoginTest {
    String path = "src/test/resources/login.json";
    LoginPage login = login = new LoginPage(getDriver());
    WebDriver driver;
    @BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }

    @BeforeMethod
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        //ScreenRecorderUtils.startRecording("login");
        login = new LoginPage(driver);
    }

    @Test(priority = 1)
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test login with valid email and password")
    public void testValidLogin() {

        fillData("validLogin");
        login.waitForNextPage();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/account",
                "Valid login failed: URL mismatch."
        );

    }

    @Test(priority = 2)
    @Story("Invalid Login - Email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test login with invalid email and valid password")
    public void invalidLoginEmail() {
        fillData("invalidEmail");
        login.verifyLoginError();
    }
    @Test(priority = 3)
    @Story("Invalid Login - Password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test login with valid email and invalid password")
    public void invalidLoginPassword() {
        fillData("invalidPassword");
        login.verifyLoginError();
    }
    @Test(priority = 4)
    @Story("test logout")
    @Severity(SeverityLevel.NORMAL)
    @Description("test logout after login")
    public void testLogout(){
        validLogin(driver);
        login.hoverMyAcount()
                .clickSpecificBtn("My account","Logout");
        Assert.assertEquals(
                getDriver().getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/logout",
                "logoutFailed"
        );

    }

    @Step("Fill login form with data key: {dataKey}")
    public void fillData(String dataKey) {
        login.clickMyAccountBtnBase()
                .clickLoginBtn()
                .enterEmail(getJsonValue(path, dataKey + ".email"))
                .enterPassword(getJsonValue(path, dataKey + ".password"))
                .clickLoginBtn();
    }
    public void validLogin(WebDriver driver){
        fillData("validLogin");
        new LoginPage(driver).waitForNextPage();
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/account",
                "Valid login failed: URL mismatch."
        );
    }
    @AfterMethod
    public void closeDriver() throws Exception {
        //ScreenRecorderUtils.stopRecording("login");

        driver.close();
    }
}

