package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static utilities.ElementActions.*;
import static utilities.ElementUtils.checkElementVisibility;

public class SpecificProductPage extends BasePage<SpecificProductPage> {
    private By brand = By.xpath("(//*[contains(text(),\"Brand\")]/ancestor::li//a)");
    private By viewed = By.xpath("(//span[contains(text(),\"Viewed\")]/ancestor::li//span)[2]");
    private By availability = By.xpath("(//*[contains(text(),\"Availability\")]/ancestor::li//span)[2]");
    private By plusBtn = By.cssSelector("#entry_216841 [class=\"fas fa-plus-circle\"]");
    private By minusBtn = By.cssSelector("#entry_216841 [class=\"fas fa-minus-circle\"]");
    private By addToCartBtn = By.cssSelector("#entry_216842 button");
    private By BuyNowBtn = By.cssSelector("#entry_216843 button");
    private By compareBtn = By.cssSelector("#entry_216844 button");
    private By reviewName = By.id("input-name");
    private By reviewText = By.id("input-review");
    private By writeReviewBtn = By.cssSelector("button-review");
    private By reviewRating = By.cssSelector("label[for=\"rating-5-216860\"]");
    private By reviewerror = By.cssSelector("[class=\"alert alert-danger alert-dismissible\"]");
    private By viewCartBtn = By.cssSelector("[class=\"btn btn-primary btn-block\"]");
    private By checkoutBtn = By.cssSelector("[class=\"btn btn-secondary btn-block\"]");



    public SpecificProductPage(WebDriver driver) {
        super(driver);
    }


    @Step("Get the Brand of the product")
    public void getBrand() {
        getDataFromElement(driver, brand);
    }

    @Step("Get the number of times the product has been viewed")
    public void getViewed() {
        getDataFromElement(driver, viewed);
    }

    @Step("Get the availability status of the product")
    public void getAvailability() {
        getDataFromElement(driver, availability);
    }

    @Step("Click on the Plus button to increase the quantity")
    public void clickPlusBtn() {
        clickButton(driver, plusBtn);
    }

    @Step("Click on the Minus button to decrease the quantity")
    public void clickMinusBtn() {
        clickButton(driver, minusBtn);
    }

    @Step("Click on the Add to Cart button")
    public SpecificProductPage clickAddToCartBtn() {
        clickButton(driver, addToCartBtn);
        return this;
    }

    @Step("Click on the Buy Now button")
    public void clickBuyNowBtn() {
        clickButton(driver, BuyNowBtn);
    }

    @Step("Click on the Compare button")
    public SpecificProductPage clickCompareBtn() {
        clickButton(driver, compareBtn);
        return this;
    }

    @Step("Enter a name for the review")
    public SpecificProductPage enterReviewName(String s) {
        enterText(driver, reviewName, s);
        return this;
    }

    @Step("Enter a review text")
    public SpecificProductPage enterReviewText(String s) {
        enterText(driver, reviewText, s);
        return this;
    }

    @Step("Click on the Write Review button")
    public SpecificProductPage clickWriteReviewBtn() {
        clickButton(driver, writeReviewBtn);
        return this;
    }

    @Step("Select a rating for the review")
    public SpecificProductPage clickReviewRating() {
        clickButton(driver, reviewRating);
        return this;
    }

    @Step("Navigate to the Shopping Cart page")
    public ShoppingCartPage clickViewCartPage() {
        clickButton(driver, viewCartBtn);
        return new ShoppingCartPage(driver);
    }

    @Step("Navigate to the Checkout page")
    public CheckoutPage clickCheckoutBtn() {
        clickButton(driver, checkoutBtn);
        return new CheckoutPage(driver);
    }

    @Step("Verify the visibility of the review error message")
    public void verifyReviewError() {
        Assert.assertTrue(checkElementVisibility(driver, reviewerror));
    }


}
