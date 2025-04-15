package tests;

import driver.DriverManger;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.WishListPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;

@Epic("E-Commerce Wishlist Tests")
@Feature("Wishlist Functionality")
public class WishlistTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;
    @BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }
    @BeforeClass
    @Step("Set up WebDriver and login")
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        recorder = ScreenRecorderUtils.start(driver,"wishlist");

        new LoginTest().validLogin(driver);
    }

    @BeforeMethod
    @Step("Search for iMac and add to wishlist")
    public void setupMethod() {
        new HomePage(driver)
                .enterSearchText("imac")
                .clickSearchBtn()
                .clickAddToWishListBtn("iMac")
                .clickAlertWishListBtn();
    }

    @Test(description = "Remove product from wishlist and validate success alert")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove item from wishlist")
    @Description("Ensure that a product can be removed from wishlist successfully.")
    @Step("Remove product from wishlist")
    public void removeProductFromWishlist() {
        String alert = new WishListPage(driver)
                .clickRemoveBtn(1)
                .getModificatedAlert();

        Assert.assertTrue(alert.contains("Success: You have modified your wish list!"),
                "Expected success alert was not displayed. Actual message: " + alert);
    }

    @Test(description = "Add product to cart from wishlist and go to checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add from wishlist to cart")
    @Description("Validate product is added to cart and redirected to checkout page.")
    @Step("Add product to cart from wishlist")
    public void checkAddCartAndCheckout() {
        new WishListPage(driver)
                .clickAddCartBtn(1)
                .clickAlertCheckoutBtn();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart",
                "User was not redirected to checkout/cart page");
    }

    @Test(description = "Click continue button from wishlist")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigate from wishlist using continue button")
    @Description("Verify clicking 'Continue' button takes user to account page.")
    @Step("Click continue button")
    public void checkContinue() {
        new WishListPage(driver)
                .clickContinueBtn();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=account/account",
                "Continue button did not redirect to the account page.");
    }
    @AfterClass(alwaysRun = true)
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("wishlist");
        deleteFolderCompletely("test-outputs/screen-records/wishlist");
        driver.close();
    }
}
