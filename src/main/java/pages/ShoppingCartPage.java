package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

import static utilities.ElementActions.*;
import static utilities.ElementUtils.checkElementVisibility;

public class ShoppingCartPage extends BasePage<ShoppingCartPage> {
    private By quantityText = By.cssSelector("[class=\"input-group flex-nowrap\"] .form-control");
    private By quantityUpdateBtn = By.xpath("(//*[@class=\"input-group flex-nowrap\"]//*[@class=\"btn btn-primary\"])");
   private By removeBtn = By.cssSelector("[class=\"btn btn-danger\"]");
   private By collapseCouponBtn = By.cssSelector("[data-target= \"#collapse-coupon\"]");
   private By couponText = By.id("input-coupon");
   private By applyCouponBtn = By.id("button-coupon");
   private By collopseEstimatShipping = By.cssSelector("[data-target=\"#collapse-shipping\"]");
   private By coutrySelect = By.id("input-country");
   private By regionSelect = By.id("input-zone");
   private By postCodeText = By.id("input-postcode");
   private By getQuoteBtn = By.id("button-quote");
   private By collopseGift = By.cssSelector("[data-target=\"#collapse-voucher\"]");
   private By giftText = By.id("input-voucher");
   private By applyGiftBtn = By.id("button-voucher");
   private By continueShoppingBtn = By.cssSelector("[class=\"btn btn-lg btn-secondary mr-auto\"]");
   private By checkoutBtn = By.cssSelector("[class=\"btn btn-lg btn-primary\"]");
   private By sucessfulQuantityUpdate = By.cssSelector("[class=\"alert alert-success alert-dismissible\"]");
   private By emptyCartError = By.cssSelector("#error-not-found p");
   private By elementPrice = By.xpath("//th[contains(text(),\"Unit Price\")]/ancestor::table//td[contains(text(),\"$\")]");
   @Step("Enter quantity for product at index {index} as {quantity}")
   public ShoppingCartPage enterQuantity(String quantity, int index) {
       WebElement element = (findElements(driver, quantityText)).get(index-1);

       clearTextInWebElement(element);
       enterTextInWebElement(driver,element, quantity);
       return this;
   }

   @Step("Click update quantity button for product at index {index}")
   public ShoppingCartPage clickQuantityUpdateBtn(int index) {
       WebElement element = (findElements(driver, quantityUpdateBtn)).get(index-1);
       clickOnWebElement(driver,element);
       return this;
   }

   @Step("Click remove button for product at index {index}")
   public ShoppingCartPage clickRemoveBtn(int index) {
       WebElement element = (findElements(driver, removeBtn)).get(index-1);
       clickOnWebElement(driver,element);
       return this;
   }

   @Step("Get unit price for product at index {index}")
   public String getUnitPrice(int index) {
       index = (index - 1) * 2;
       WebElement element = (findElements(driver, elementPrice)).get(index);
       return element.getText();
   }

   @Step("Get total price for product at index {index}")
   public String getTotalPrice(int index) {
       index = (index - 1) * 2 + 1;
       WebElement element = (findElements(driver, elementPrice)).get(index);
       return element.getText();
   }

   @Step("Click collapse coupon button")
   public ShoppingCartPage clickCollapseCouponBtn() {
       clickButton(driver, collapseCouponBtn);
       return this;
   }

   @Step("Enter coupon code as {coupon}")
   public ShoppingCartPage enterCoupon(String coupon) {
       enterText(driver, couponText, coupon);
       return this;
   }

   @Step("Click apply coupon button")
   public ShoppingCartPage clickApplyCouponBtn() {
       clickButton(driver, applyCouponBtn);
       return this;
   }

   @Step("Click collapse estimate shipping button")
   public ShoppingCartPage clickCollopseEstimatShipping() {
       clickButton(driver, collopseEstimatShipping);
       return this;
   }

   @Step("Enter country as {country}")
   public ShoppingCartPage enterCountry(String country) {
       enterText(driver, coutrySelect, country);
       return this;
   }

   @Step("Enter region as {region}")
   public ShoppingCartPage enterRegion(String region) {
       enterText(driver, regionSelect, region);
       return this;
   }

   @Step("Enter post code as {postCode}")
   public ShoppingCartPage enterPostCode(String postCode) {
       enterText(driver, postCodeText, postCode);
       return this;
   }

   @Step("Click get quote button")
   public ShoppingCartPage clickGetQuoteBtn() {
       clickButton(driver, getQuoteBtn);
       return this;
   }

   @Step("Click collapse gift button")
   public ShoppingCartPage clickCollopseGift() {
       clickButton(driver, collopseGift);
       return this;
   }

   @Step("Enter gift code as {gift}")
   public ShoppingCartPage enterGift(String gift) {
       enterText(driver, giftText, gift);
       return this;
   }

   @Step("Click apply gift button")
   public ShoppingCartPage clickApplyGiftBtn() {
       clickButton(driver, applyGiftBtn);
       return this;
   }

   @Step("Click continue shopping button")
   public HomePage clickContinueShoppingBtn() {
       clickButton(driver, continueShoppingBtn);
       return new HomePage(driver);
   }

   @Step("Click checkout button")
   public CheckoutPage clickCheckoutBtn() {
       clickButton(driver, checkoutBtn);
       return new CheckoutPage(driver);
   }
    public int getQuantity(int index) {
        index = (index - 1);
        WebElement element = (findElements(driver, quantityText)).get(index);
        return (int) Double.parseDouble(element.getAttribute("value")) ;
    }
    // validate quantity update message
    @Step("Validate successful quantity update is visible")
    public ShoppingCartPage validateQuantityUpdate() {
        Assert.assertTrue(
                checkElementVisibility(driver, sucessfulQuantityUpdate),
                "Expected successful quantity update message is not visible."
        );
        return this;
    }

    // validate empty cart state
    @Step("Validate empty cart message is shown after removing all items")
    public ShoppingCartPage validateRemovingElement() {
       driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        Assert.assertTrue(
                checkElementVisibility(driver, emptyCartError),
                "Expected empty cart message is not visible."
        );
        return this;
    }

    // get number of products in the cart
    @Step("Get total number of products in cart")
    public int getNumberOfProducts() {
        return findElements(driver, quantityText).size();
    }

    // validate unit price and total price consistency
    @Step("Validate unit price and total price for product at index {index}")
    public ShoppingCartPage validateElementPrice(int index) {
        String unitPriceText = getUnitPrice(index);
        String totalPriceText = getTotalPrice(index);
        int quantity = getQuantity(index);

        int unitPrice = (int) Double.parseDouble(unitPriceText.replace("$", "").replace(",", ""));
        int totalPrice = (int) Double.parseDouble(totalPriceText.replace("$", "").replace(",", ""));

        System.out.println("Unit Price: " + unitPrice + ", Quantity: " + quantity + ", Total Price: " + totalPrice);

        Assert.assertEquals(
                unitPrice * quantity,
                totalPrice,
                "Mismatch between calculated total and displayed total price for product at index " + index
        );
        return this;
    }

   public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


}
