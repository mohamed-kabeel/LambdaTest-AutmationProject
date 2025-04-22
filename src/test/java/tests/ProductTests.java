package tests;

import com.github.javafaker.Faker;
import driver.DriverManger;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoryPage;
import pages.HomePage;
import pages.SpecificProductPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import java.util.List;

import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.deleteFolderCompletely;

@Listeners(SuiteListener.class)
public class ProductTests {
    Faker faker = new Faker();
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;
    SpecificProductPage productPage ;
    @BeforeClass
    public void setup() throws Exception {
        setDriver("edge");
        driver = DriverManger.getDriver();
        driver.manage().window().maximize();
        recorder = ScreenRecorderUtils.start(driver, "product");
        productPage = new SpecificProductPage(driver);
    }
    @BeforeMethod
    public void setupMethod(){
        driver.get("https://ecommerce-playground.lambdatest.io/");//url
        
    }

    @Test(dataProvider = "reviewDataProvider")
    public void testAddingReview(String name, String reviewText, boolean shouldSucceed) {
        new HomePage(driver).clickHomeBtn()
                .clickTopProduct("HTC Touch HD")
                .clickReviewRating()
                .enterReviewName(name)
                .enterReviewText(reviewText)
                .clickWriteReviewBtn();

        if (shouldSucceed) {
            productPage.verifySuccessReview();
        } else {
            productPage.verifyReviewError();
        }
    }

    @Test
    public void changQuantity(){
       int quantity =  new  HomePage(driver)
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .clickinStock()
                .clickProduct("iPod Nano")
                .clickPlusBtn()
                .getQuanity();
       Assert.assertEquals(quantity,2);
       productPage.enterQuantityText(3);
        Assert.assertEquals(productPage.getQuanity(),3);
        productPage.clickMinusBtn();
        Assert.assertEquals(productPage.getQuanity(),2);
    }
    @Test
    public void testAddToCartFromSpecificPage(){
        new  HomePage(driver)
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .clickinStock()
                .clickProduct("iPod Nano")
                .clickAddToCartBtn()
                .clickAlertViewCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("route=checkout/cart"));

    }
    @Test
    public void testcompareFromSpecificPage(){
        new  HomePage(driver)
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .clickinStock()
                .clickProduct("iPod Nano")
                .clickCompareBtn()
                .clickAlertCompareBtn();
        Assert.assertTrue(driver.getCurrentUrl().contains("route=product/compare"));
    }



    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("product");
        deleteFolderCompletely("test-outputs/screen-records/product");
        driver.close();
    }

    @DataProvider(name = "reviewDataProvider")
    public Object[][] reviewDataProvider() {
        return new Object[][]{
                // valid input
                {faker.lorem().characters(10), faker.lorem().characters(40), true},

                // minimum characters
                {faker.lorem().characters(3), faker.lorem().characters(10), false},

                // maximum characters
                {faker.lorem().characters(50), faker.lorem().characters(3000), false},

                // empty fields
                {"", "", false},
        };
    }

}

