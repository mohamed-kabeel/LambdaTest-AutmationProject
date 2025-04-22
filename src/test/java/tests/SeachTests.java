package tests;

import driver.DriverManger;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

import java.util.*;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static pages.BasePage.*;
import static utilities.FileUtilsCustom.*;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import java.util.Collections;
import java.util.List;

@Epic("E-Commerce Search Tests")
@Feature("Search Functionality")
@Listeners(SuiteListener.class)
public class SeachTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();
    CategoryPage categoryPage;
    /*@BeforeSuite
    public void cleanAllures(){
        cleanAllureResults();
        cleanFolderContents("test-outputs/screen-records");
    }*/
    @BeforeClass
    @Step("Setup WebDriver and navigate to homepage")
    public void setup() throws Exception {
        setDriver("edge");
        driver = DriverManger.getDriver();
        driver.manage().window().maximize();
        categoryPage = new CategoryPage(driver);
      recorder =   ScreenRecorderUtils.start(driver,"search");

    }
    @BeforeMethod
    public void setMethod(){
        driver.get("https://ecommerce-playground.lambdatest.io/");

    }

    @Test(description = "Search using valid product name")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search with correct keywords")
    public void searchValidProduct(){

        List<String> names =new  HomePage(driver)
                .clickSearchCategory()
                .clickSearhcspecificCategory("Desktops")
                .enterSearchText("iMac")
                .clickSearchBtn()
                .getProductsName();
        softAssert = new SoftAssert();
        for(String name : names){
            softAssert.assertTrue(name.contains("iMac"), "Expected product name to contain 'iMac', but got: " + name);
        }
        softAssert.assertAll();
    }

    @Test(description = "Search and validate product names with valid key")
    @Severity(SeverityLevel.NORMAL)
    @Story("Validate search results")
    public void searchWithValidKey() {
        List<String> names = categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .enterSearchText("imac")
                .getProductsName();
        softAssert = new SoftAssert();
        for(String name : names){
            softAssert.assertTrue(name.contains("iMac"), "Expected product name to contain 'iMac', but got: " + name);
        }
        softAssert.assertAll();
    }

    @Test(description = "Search using invalid key")
    @Severity(SeverityLevel.NORMAL)
    @Story("Handle invalid search")
    public void searchWithInvalidKey(){
        String msg = categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Desktop")
                .enterSearchText("xxx")
                .getInvalidProductSearch();

        Assert.assertTrue(msg.contains("There are no products"), "Expected message to contain 'There are no products', but got: " + msg);
    }

    @Test(description = "Change max and min prices using sliders")
    @Severity(SeverityLevel.MINOR)
    @Story("Filter using sliders")
    public void changeMaxPriceUsingSlider() {
       categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Desktop")
                .changeMax(1500)
                .changMin(400);

        int min = Integer.parseInt(new CategoryPage(driver).getMinPrice());
        int max = Integer.parseInt(new CategoryPage(driver).getMaxPrice());
        softAssert = new SoftAssert();
        softAssert.assertTrue(min >= 397 && min <= 403, "Expected min price ~400 but got: " + min);
        softAssert.assertTrue(max >= 1497 && max <= 1502, "Expected max price ~1500 but got: " + max);
        softAssert.assertAll();
    }

    @Test(description = "Change max and min prices using text fields")
    @Severity(SeverityLevel.MINOR)
    @Story("Filter using input fields")
    public void changeMaxPriceUsingFileds() {
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .enterMaxPrice("1200")
                .enterMinPrice("50");
        String min = new CategoryPage(driver).getMinPrice();
        String max = new CategoryPage(driver).getMaxPrice();
        softAssert = new SoftAssert();
        softAssert.assertEquals(min, "50", "Expected min to be 50 but was: " + min);
        softAssert.assertEquals(max, "1200", "Expected max to be 1200 but was: " + max);
        softAssert.assertAll();
    }
    @Test(description = "Verify prices within range")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify price filter functionality")
    public void testProductPrices(){
        List<Integer> prices = categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Desktop")
                .getProductsPrice();

        int min = Integer.parseInt(new CategoryPage(driver).getMinPrice());
        int max = Integer.parseInt(new CategoryPage(driver).getMaxPrice());
        softAssert = new SoftAssert();
        for(Integer price : prices){
            softAssert.assertTrue(price >= min && price <= max,
                    "Price " + price + " not in range: [" + min + ", " + max + "]");
        }
        softAssert.assertAll();
    }

    @Test(description = "Check descending name sorting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort products by name")
    public void testReverseNameSorted(){
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .clickSortSDropDown(5);

        List<String> names = new CategoryPage(driver).getProductsName();
        System.out.println(names);
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames, Collections.reverseOrder());
        Assert.assertEquals(names, sortedNames, "Names not sorted in descending order");
    }

    @Test(description = "Check ascending name sorting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort products by name")
    public void testAscNameSorted(){
       categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .clickSortSDropDown(4);

        List<String> names = new CategoryPage(driver).getProductsName();
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);
        Assert.assertEquals(names, sortedNames, "Names not sorted in ascending order");
    }

    @Test(description = "Check ascending price sorting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort products by price")
    public void testAscPriceSorted(){
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .clickSortSDropDown(6);

        List<Integer> prices = new CategoryPage(driver).getProductsPrice();
        List<Integer> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);
        Assert.assertEquals(prices, sortedPrices, "Prices not sorted in ascending order");
    }

    @Test(description = "Check descending price sorting")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sort products by price")
    public void testDescPriceSorted(){
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .clickSortSDropDown(7);

        List<Integer> prices = new CategoryPage(driver).getProductsPrice();
        List<Integer> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        Assert.assertEquals(prices, sortedPrices, "Prices not sorted in descending order");
    }

    @Test(description = "Apply all filters")
    @Severity(SeverityLevel.MINOR)
    @Story("Filter products using all options")
    public void testAll() throws InterruptedException {
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu", "Hard disk")
                .clickManufactureSelector("Headphones")
                .clickSize("L")
                .clickCategory("Laptops")
                .clickColor("Black");
    }

    //@Test(description = "Click cart, wishlist and compare")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Cart and Wishlist operations")
    public void clickProductButtonINCart(){
        categoryPage
                .hoverMegMenu()
                .clickSpecificBtn("Mega Menu","Apple")
                .clickAddToWishListBtn("iPod Touch")
                .clickAddToCartBtn("iMac")
                .clickComporareBtn("iMac");
    }
    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("search");
        deleteFolderCompletely("test-outputs/screen-records/search");
        driver.close();
    }
}


