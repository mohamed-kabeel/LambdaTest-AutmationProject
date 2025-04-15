package tests;

import driver.DriverManger;
import org.apache.commons.exec.ShutdownHookProcessDestroyer;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ShoppingCartPage;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;


import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

@Epic("E-Commerce")
@Feature("Shopping Cart Management")
public class ShoppingCartTests {
    ScreenRecorderUtils.ScreenRecorder recorder;

    WebDriver driver;
    ShoppingCartPage cartPage;
    @BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");

    }
    @BeforeClass
    @Step("Setup WebDriver and perform login with adding product to cart")
    public void setup() throws Exception {
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        recorder = ScreenRecorderUtils.start(driver,"cart");

        new LoginTest().validLogin(driver);
        cartPage = new ShoppingCartPage(driver);
    }
    @BeforeMethod
    public void setupMethod(){
        new HomePage(driver)
                .clickHomeBtn()
                .clickTopProduct("HTC Touch HD")
                .clickAddToCartBtn()
                .clickViewCartPage();
    }
    @Test(description = "Change quantity of an item in the cart",priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can change the quantity of a product in the shopping cart")
    public void changeQuantity() {
        cartPage.enterQuantity("3", 1)
                .clickQuantityUpdateBtn(1)
                .validateQuantityUpdate();
    }

    @Test(description = "Remove item from the cart",priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user can remove a product from the shopping cart")
    public void removeElement()  {

        cartPage.clickRemoveBtn(1)
                .validateRemovingElement();
    }

    @Test(description = "Validate price for all products in the cart",priority = 3)
    @Severity(SeverityLevel.MINOR)
    @Description("Check that each product in the cart displays a valid price")
    public void testProductPrices() {
        int productCount = cartPage.getNumberOfProducts();
        for (int i = 1; i <= productCount; i++) {
            cartPage.validateElementPrice(i);
        }
    }

    @AfterClass
    @Step("Closing WebDriver")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("cart");
        deleteFolderCompletely("test-outputs/screen-records/cart");

        driver.close();
    }
}

