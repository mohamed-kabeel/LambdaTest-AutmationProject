package tests;

import driver.DriverManger;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;
@Listeners(SuiteListener.class)
public class HomePageTests {
    HomePage homePage;
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
      homePage = new HomePage(driver);
    }
    @BeforeMethod
    public void setupMethod(){
        driver.get("https://ecommerce-playground.lambdatest.io/");

    }
    @Test
    public void testCollectionAddToCart(){
        homePage
                .clickHomeBtn()
                .hoverCollectionProduct("Palm Treo Pro")
                .clickCollAdd("Palm Treo Pro")
                .clickAlertViewCart();
        Assert.assertTrue(driver.getCurrentUrl()
                .contains("route=checkout/cart"),"user should be redirect to cart page");
    }
    @Test
    public void testTopProductAddToCart()  {
        //HTC Touch HD
        homePage
                .clickHomeBtn()
                 .hoverTopProduct("iMac")
                .clickTopAdd("iMac")
                .clickAlertViewCart();
        Assert.assertTrue(driver.getCurrentUrl()
                .contains("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart"),
                "user can't redirect to cart page");
    }
    @Test
    public void testNavigateToBlogAuthor(){
        homePage
                .clickHomeBtn()
                .clickBlogAuthor(1);
        Assert.assertTrue(driver.getCurrentUrl()
                        .contains("route=extension/maza/blog/author&author_id="),
                "user can't redirect to Author page");
    }
    @Test
    public void testNavigatToBlogPage(){
        String blogName = homePage.getBlogName(3);
        homePage
                .clickHomeBtn()
                .clickBlogNameAndLink(3);
        Assert.assertTrue(driver.getCurrentUrl()
                        .contains("route=extension/maza/blog/article&article_id"),
                "user can't redirect to Author page");
        Assert.assertEquals(blogName,new SpecificBlogPage(driver).getBlogName());
    }

    /*@Test
    public void testTopCollction(){
        LoginTest login = new LoginTest();
        login.validLogin(driver);
        new HomePage(driver)
                .clickHomeBtn()
                .clickCollProduct("iPod Nano")
                .clickAddToCartBtn()
                .clickViewCartPage()
                .clickCheckoutBtn()
                .clickBillingAgreebtn()
                .clickContinueBtn()
                .clickConfirmBtn();
    }*/
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("homepage");
        deleteFolderCompletely("test-outputs/screen-records/homepage");
        //driver.close();
    }

}
