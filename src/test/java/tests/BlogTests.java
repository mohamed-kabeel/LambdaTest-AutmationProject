package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import listener.SuiteListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.BlogPage;
import pages.LoginPage;
import pages.SpecificBlogPage;
import utilities.ScreenRecorderUtils;
import utilities.VideoUtils;

import static driver.DriverManger.getDriver;
import static driver.DriverManger.setDriver;
import static utilities.FileUtilsCustom.*;
import static utilities.JsonUtils.getJsonValue;
import static utilities.JsonUtils.updateJsonValue;


@Epic("Blog Comments")
@Feature("Submit Comments to Blog Posts")
@Listeners(SuiteListener.class)
public class BlogTests {
    ScreenRecorderUtils.ScreenRecorder recorder;
    private final String path = "src/test/resources/blogComment.json";
    WebDriver driver;
    SpecificBlogPage specificBlogPage;
    @BeforeClass
    public void setUpTest() throws Exception {
        setDriver("edge");
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        specificBlogPage = new SpecificBlogPage(driver);
        recorder = ScreenRecorderUtils.start(driver,"blog");

    }
    @BeforeMethod
    public void navigateToBlog() {
        driver.get("https://ecommerce-playground.lambdatest.io/");
        specificBlogPage.clickBlogBtn();
    }

    @DataProvider(name = "blogData")
    public Object[][] blogDataProvider() {
        return new Object[][]{
                {"validData", true},
                {"invalidData1", false},
                {"invalidData2", false},
                {"invalidData3", false}
        };
    }

    @Test(dataProvider = "blogData", description = "Submit blog comments with various datasets")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Parameterized Blog Comment Test")
    @Description("Submit blog comment form with valid and invalid data from JSON and assert result accordingly")
    public void testBlogComment(String objectKey, boolean isValid) {
        openFirstPopularBlog();

        // Handle dynamic update
        if ("validData".equals(objectKey)) {
            updateJsonValue(path, objectKey + ".email", new Faker().internet().emailAddress());
        }
        if ("invalidData2".equals(objectKey)) {
            updateJsonValue(path, objectKey + ".comment", new Faker().lorem().characters(1100));
        }

        fillBlogForm(objectKey);

        if (isValid) {
            specificBlogPage.validateSuccessfullComment();
        } else {
            SoftAssert softAssert = new SoftAssert();
            specificBlogPage.validateErrors(softAssert);
        }
    }

    @Step("Fill blog form with data key: {object}")
    public void fillBlogForm(String object) {
        specificBlogPage
                .enterName(getJsonValue(path, object + ".name"))
                .enterEmail(getJsonValue(path, object + ".email"))
                .enterComment(getJsonValue(path, object + ".comment"))
                .clickSubmitBtn();
    }

    @Step("Click on the first popular blog post")
    public void openFirstPopularBlog() {
        new BlogPage(driver).clickMostViewdLink(1);
    }

    @AfterClass
    public void exitClass() throws Exception {
        ScreenRecorderUtils.stop(recorder);
        VideoUtils.convertImagesToVideo("blog");
        deleteFolderCompletely("test-outputs/screen-records/blog");
        driver.close();
    }
}

