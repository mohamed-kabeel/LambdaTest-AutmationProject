package tests;

import driver.DriverManger;
import io.qameta.allure.*;
import listener.SuiteListener;
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
@Listeners(SuiteListener.class)
public class WishlistTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;


    @BeforeClass
    @Step("Set up WebDriver and login")
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        recorder = ScreenRecorderUtils.start(driver,"wishlist");
        driver.get("https://ecommerce-playground.lambdatest.io/");
        new LoginTest().validLogin(driver);
    }

    @BeforeMethod
    @Step("Search for iMac and add to wishlist")
    public void setupMethod() {
        driver.get("https://ecommerce-playground.lambdatest.io/");

        //driver.navigate().refresh();
       /* new HomePage(driver)
                .enterSearchText("imac")
                .clickSearchBtn()
                .clickAddToWishListBtn("iMac")
                .clickAlertWishListBtn();*/

    }

    @Test(description = "Remove product from wishlist and validate success alert",priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove item from wishlist")
    @Description("Ensure that a product can be removed from wishlist successfully.")
    @Step("Remove product from wishlist")
    public void removeProductFromWishlist() {
        //driver.navigate().refresh();
        String alert=   new HomePage(driver)
                .enterSearchText("imac")
                .clickSearchBtn()
                .clickAddToWishListBtn("iMac")
                .clickAlertWishListBtn()
                .clickRemoveBtn(1)
                .getModificatedAlert();

        Assert.assertTrue(alert.contains("Success: You have modified your wish list!"),
                "Expected success alert was not displayed. Actual message: " + alert);
    }

    @Test(description = "Add product to cart from wishlist and go to checkout",priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add from wishlist to cart")
    @Description("Validate product is added to cart and redirected to checkout page.")
    @Step("Add product to cart from wishlist")
    public void checkAddCartAndCheckout() {
        new  HomePage(driver)
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .clickAddToWishListBtn("Apple Cinema 30")
                .clickAlertWishListBtn()
                .clickAddCartBtn(1)
                .clickAlertCheckoutBtn();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart",
                "User was not redirected to checkout/cart page");
    }
    @Test(description = "Click continue button from wishlist",priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigate from wishlist using continue button")
    @Description("Verify clicking 'Continue' button takes user to account page.")
    @Step("Click continue button")
    public void checkContinue() {
       // new WishListPage(driver)
        new  HomePage(driver)
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .clickAddToWishListBtn("Nikon D300")
                .clickAlertWishListBtn()
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

