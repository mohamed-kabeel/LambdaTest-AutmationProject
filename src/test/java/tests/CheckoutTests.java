package tests;

import driver.DriverManger;
import io.qameta.allure.*;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ShoppingCartPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static pages.BasePage.softAssert;
import static utilities.FileUtilsCustom.*;
import static utilities.JsonUtils.getJsonValue;



@Epic("E-Commerce Checkout")
@Listeners(SuiteListener.class)
public class CheckoutTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    String path = "src/test/resources/billingAddress.json";
    WebDriver driver;
    CheckoutPage checkoutPage;
    LoginTest login;
    @BeforeClass
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        login = new LoginTest();
        checkoutPage = new CheckoutPage(driver);
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
       recorder =  ScreenRecorderUtils.start(driver,"checkout");
        login.validLogin(driver);
    }
    @BeforeMethod
    public void setupMethod(){
        driver.get("https://ecommerce-playground.lambdatest.io/");
        //login.validLogin();
        navigateToCheckoutPage();
    }

    @Feature("Checkout with valid login")
    @Story("User checks out with already logged-in credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in and completes checkout with default billing address")
    @Test
    public void checkoutWithSameLoginData() {

        checkoutPage
                .clickBillingAgreebtn()
                .clickContinueBtn()
                .waitForNextPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm");
    }

    @Feature("Checkout with valid address")
    @Story("User fills in another valid address")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User logs in and fills a different valid billing address")
    @Test
    public void checkoutWithAnothorValid1Address() {

        fillBillingData("validData1");
        checkoutPage
                .clickBillingAgreebtn()
                .clickContinueBtn()
                .waitForNextPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm");
    }

    @Feature("Checkout with valid address")
    @Story("User fills in another valid address")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User logs in and fills another different valid billing address")
    @Test
    public void checkoutWithAnothorValid2Address() {
        fillBillingData("validData2");
        checkoutPage
                .clickBillingAgreebtn()
                .clickContinueBtn()
                .waitForNextPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm");
    }

    @Feature("Checkout with invalid address")
    @Story("User tries invalid billing data")
    @Severity(SeverityLevel.NORMAL)
    @Description("Checks that checkout fails with incomplete billing data")
    @Test
    public void checkoutWithAnothorInValid1Address() {
        fillBillingData("invalidData1");
        checkoutPage
                .clickBillingAgreebtn()
                .clickContinueBtn();
        driver.navigate().refresh();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm");
    }

    @Feature("Checkout with invalid address")
    @Story("User tries another invalid billing data")
    @Severity(SeverityLevel.NORMAL)
    @Description("Checks that checkout fails when fields are missing")
    @Test
    public void checkoutWithAnothorInValid2Address() {
        fillBillingData("invalidData2");
        checkoutPage
                .clickBillingAgreebtn()
                .clickContinueBtn();
        driver.navigate().refresh();
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm");
    }



    @Feature("Cart Quantity")
    @Story("User changes quantity in cart")
    @Severity(SeverityLevel.MINOR)
    @Description("User updates the quantity in the shopping cart")
    @Test
    public void changeQuantitye() {
        new CheckoutPage(driver)
                .enterQuantity("3", 1)
                .clickQuantityUpdateBtn(1);
    }

    @Feature("Cart Management")
    @Story("User removes item from cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("User removes item from shopping cart")
    @Test
    public void removeElement() {
        new CheckoutPage(driver)
                .clickRemoveBtn(1);
    }

    @Feature("Product Price Validation")
    @Story("User verifies product pricing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks unit and total price equality for single item")
    @Test
    public void testProductPrices() {
        int unit = (int) Double.parseDouble(new CheckoutPage(driver).getUnitPrice(1).replace("$", ""));
        int total = (int) Double.parseDouble(new CheckoutPage(driver).getTotalPrice(1).replace("$", ""));
        Assert.assertEquals(unit, total);
    }
    @Step("Navigating to checkout page")
    public void navigateToCheckoutPage() {
        new HomePage(driver)
                .clickHomeBtn()
                .clickTopProduct("HTC Touch HD")
                .clickAddToCartBtn()
                .clickViewCartPage()
                .clickCheckoutBtn();
    }
    @Step("Filling billing data for {object}")
    public void fillBillingData(String object) {
        int x = (int) Double.parseDouble(getJsonValue(path, object + ".state"));
        System.out.println(x);
        checkoutPage
                .clickNewBillingAddressBtn()
                .enterBillingFirstName(getJsonValue(path, object + ".firstName"))
                .enterBillingLastName(getJsonValue(path, object + ".lastName"))
                .enterBillingCompany(getJsonValue(path, object + ".company"))
                .enterBillingAddress1(getJsonValue(path, object + ".address1"))
                .enterBillingAddress2(getJsonValue(path, object + ".address2"))
                .enterBillingCity(getJsonValue(path, object + ".country"))
                .enterBillingPostcode(getJsonValue(path, object + ".state"))
                .selectBillingState((int) Double.parseDouble(getJsonValue(path, object + ".country")));
    }
    @AfterClass(alwaysRun = true)
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("checkout");
        deleteFolderCompletely("test-outputs/screen-records/checkout");
        driver.close();
    }
}