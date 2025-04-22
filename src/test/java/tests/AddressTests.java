package tests;

import io.qameta.allure.*;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AccountBase;
import pages.AddressPage;
import pages.HomePage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;
import static utilities.JsonUtils.getJsonValue;


@Epic("Address Management")
@Feature("Add New Address")
@Listeners(SuiteListener.class)
public class AddressTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    private final String path = "src/test/resources/billingAddress.json";
    WebDriver driver;
    AddressPage addressPage;
   /* @BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");

    }*/
    @BeforeClass
    public void setup() throws Exception {

        setDriver("edge");
        driver = getDriver();
        addressPage = new AddressPage(driver);
        driver.manage().window().maximize();
         recorder=  ScreenRecorderUtils.start(driver,"address");
        driver.get("https://ecommerce-playground.lambdatest.io/");
        new LoginTest().validLogin(driver);
    }

    @BeforeMethod
    public void navigateToAddressForm() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
        addressPage
                .clickMyAccountBtnBase()
                .clickAddressBtn()
                .clickNewAddress();
    }

    @DataProvider(name = "addressData")
    public Object[][] getAddressData() {
        return new Object[][] {
                {"validData1", true},
                {"validData2", true},
                {"invalidData1", false},
                {"invalidData2", false}
        };
    }

    @Test(dataProvider = "addressData", description = "Test address form with various datasets")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Form Submission Validation")
    @Description("Submits the address form with different JSON datasets and verifies the result")
    public void testAddressForm(String objectKey, boolean shouldPass) {
        fillBillingData(objectKey);

        String expectedUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/address";
        if (shouldPass) {
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Expected valid form submission");
        } else {
            Assert.assertNotEquals(driver.getCurrentUrl(), expectedUrl, "Expected invalid form submission");
        }
    }

    @Step("Fill billing data from JSON for key: {objectKey}")
    public void fillBillingData(String objectKey) {
        addressPage.enterFirstName(getJsonValue(path, objectKey + ".firstName"))
                .enterLastName(getJsonValue(path, objectKey + ".lastName"))
                .enterCompany(getJsonValue(path, objectKey + ".company"))
                .enterAddress1(getJsonValue(path, objectKey + ".address1"))
                .enterAddress2(getJsonValue(path, objectKey + ".address2"))
                .enterCity(getJsonValue(path, objectKey + ".city"))
                .enterZipcode(getJsonValue(path, objectKey + ".postcode"))
                .selectRegion((int) Double.parseDouble(getJsonValue(path, objectKey + ".state")))
                .clickContinueBtn();
    }
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("address");
        deleteFolderCompletely("test-outputs/screen-records/address");
        driver.close();

    }

}