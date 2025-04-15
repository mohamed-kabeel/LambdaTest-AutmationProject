package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utilities.ElementActions.*;

public class CheckoutPage extends BasePage<CheckoutPage> {
    private By newBillingAddressBtn = By.cssSelector("[for=\"input-payment-address-new\"]");

    private By telephone = By.id("input-telephone");
    private By billingFirstName = By.id("input-payment-firstname");
    private By billingLastName = By.id("input-payment-lastname");
    private By billingCompany = By.id("input-payment-company");
    private By billingAddress1 = By.id("input-payment-address-1");
    private By billingAddress2 = By.id("input-payment-address-2");
    private By billingCity = By.id("input-payment-city");
    private By billingPostcode = By.id("input-payment-postcode");
    private By billingCountry = By.id("input-payment-country");
    private By billingState = By.id("input-payment-zone");
    private By billingComment = By.id("input-comment");
    private By billingAgreebtn = By.cssSelector("[for=\"input-agree\"]");
    private By agreementWarningMsg = By.cssSelector("[class=\"alert alert-warning alert-dismissible\"]");

    private By checkListForShipping = By.id("input-shipping-address-same");
    private By newShippingAddressBtn = By.cssSelector("[for=\"input-shipping-address-new\"]");

    private By shippingFirstName = By.id("input-shipping-firstname");
    private By shippingLastName = By.id("input-shipping-lastname");
    private By shippingCompany = By.id("input-shipping-company");
    private By shippingAddress1 = By.id("input-shipping-address-1");
    private By shippingAddress2 = By.id("input-shipping-address-2");
    private By shippingCity = By.id("input-shipping-city");
    private By shippingPostcode = By.id("input-shipping-postcode");
    private By shippingCountry = By.id("select-shipping-country");
    private By shippingState = By.id("select-shipping-zone");


    private By quantityText = By.cssSelector("[class=\"input-group input-group-sm flex-nowrap\"] .form-control");
    private By quantityUpdateBtn = By.xpath("//*[@class=\"input-group input-group-sm flex-nowrap\"]//*[@class=\"btn btn-primary\"]");
    private By removeBtn = By.cssSelector("[class=\"btn btn-danger\"]");
    private By elementPrice = By.xpath("//th[contains(text(),\"Unit Price\")]/ancestor::table//td[contains(text(),\"$\")]");

    private By subTotalPrice = By.xpath("//*[@id=\"checkout-total\"]//*[contains(text(),\"Sub-Total:\")]/following-sibling::td//strong[contains(text(),\"$\")]");
    private By flatShippingRate = By.xpath("//*[@id=\"checkout-total\"]//*[contains(text(),\"Flat Shipping Rate:\")]/following-sibling::td//strong[contains(text(),\"$\")]");
    private By ecoTax = By.xpath("//*[@id=\"checkout-total\"]//*[contains(text(),\"Eco Tax\")]/following-sibling::td//strong[contains(text(),\"$\")]");
    private By vat = By.xpath("//*[@id=\"checkout-total\"]//*[contains(text(),\"VAT \")]/following-sibling::td//strong[contains(text(),\"$\")]");
    private By total = By.xpath("(//*[@id=\"checkout-total\"]//*[contains(text(),\"Total:\")]/following-sibling::td//strong[contains(text(),\"$\")])[2]");


    private By ContinueBtn = By.cssSelector("button#button-save");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    @Step("Click 'New Billing Address' button")
    public CheckoutPage clickNewBillingAddressBtn(){
        clickButton(driver,newBillingAddressBtn);
        return this;
    }
    
    @Step("Click 'New Shipping Address' button")
    public CheckoutPage clicknewShippningBtn(){
        clickButton(driver,newShippingAddressBtn);
        return this;
    }
    
    @Step("Get agreement alert message")
    public String getAgreementAlert(){
        return getDataFromElement(driver,agreementWarningMsg);
    }
    
    @Step("Enter quantity for product at index {index} with value {s}")
    public CheckoutPage enterQuantity(String s,int index){
        WebElement element = (findElements(driver,quantityText)).get(index-1);
        clearTextInWebElement(element);
        enterTextInWebElement(driver,element,s);
        return this;
    }
    
    @Step("Click 'Update' button for product at index {index}")
    public CheckoutPage clickQuantityUpdateBtn(int index){
        WebElement element = (findElements(driver,quantityUpdateBtn)).get(index-1);
        clickOnWebElement(driver,element);
        return this;
    }
    
    @Step("Click 'Remove' button for product at index {index}")
    public CheckoutPage clickRemoveBtn(int index){
        WebElement element = (findElements(driver,removeBtn)).get(index-1);
        clickOnWebElement(driver,element);
        return this;
    }
    
    @Step("Get unit price for product at index {index}")
    public String getUnitPrice(int index){
        index = (index-1)*2;
        WebElement element = (findElements(driver,elementPrice)).get(index);
        return element.getText();
    }
    
    @Step("Get total price for product at index {index}")
    public String getTotalPrice(int index){
        index = (index-1)*2+1;
        WebElement element = (findElements(driver,elementPrice)).get(index);
        return element.getText();
    }
    
    @Step("Enter telephone number with value {s}")
    public CheckoutPage enterTelephone(String s){
        enterText(driver,telephone,s);
        return this;
    }
    
    @Step("Enter billing first name with value {s}")
    public CheckoutPage enterBillingFirstName(String s){
        enterText(driver,billingFirstName,s);
        return this;
    }
    
    @Step("Enter billing last name with value {s}")
    public CheckoutPage enterBillingLastName(String s){
        enterText(driver,billingLastName,s);
        return this;
    }
    
    @Step("Enter billing company with value {s}")
    public CheckoutPage enterBillingCompany(String s){
        enterText(driver,billingCompany,s);
        return this;
    }
    
    @Step("Enter billing address 1 with value {s}")
    public CheckoutPage enterBillingAddress1(String s){
        enterText(driver,billingAddress1,s);
        return this;
    }
    @Step("Enter billing address 2 with value {s}")
    public CheckoutPage enterBillingAddress2(String s){
        enterText(driver,billingAddress2,s);
        return this;
    }
    
    @Step("Enter billing city with value {s}")
    public CheckoutPage enterBillingCity(String s){
        enterText(driver,billingCity,s);
        return this;
    }
    
    @Step("Enter billing postcode with value {s}")
    public CheckoutPage enterBillingPostcode(String s){
        enterText(driver,billingPostcode,s);
        return this;
    }
    
    @Step("Select billing country at index {index}")
    public CheckoutPage selectBillingCountry(int index){
        selectDropListElement(driver,billingCountry,index);
        return this;
    }
    
    @Step("Select billing state at index {index}")
    public CheckoutPage selectBillingState(int index){
        selectDropListElement(driver,billingState,index);
        return this;
    }
    
    @Step("Enter billing comment with value {s}")
    public CheckoutPage enterBillingComment(String s){
        enterText(driver,billingComment,s);
        return this;
    }
    
    @Step("Click billing agreement checkbox")
    public CheckoutPage clickBillingAgreebtn(){
        clickButton(driver,billingAgreebtn);
        return this;
    }
    
    @Step("Click 'Check List For Shipping' checkbox")
    public CheckoutPage clickCheckListForShipping(){
        clickButton(driver,checkListForShipping);
        return this;
    }
    
    @Step("Enter shipping first name with value {s}")
    public CheckoutPage enterShippingFirstName(String s){
        enterText(driver,shippingFirstName,s);
        return this;
    }
    
    @Step("Enter shipping last name with value {s}")
    public CheckoutPage enterShippingLastName(String s){
        enterText(driver,shippingLastName,s);
        return this;
    }
    
    @Step("Enter shipping company with value {s}")
    public CheckoutPage enterShippingCompany(String s){
        enterText(driver,shippingCompany,s);
        return this;
    }
    
    @Step("Enter shipping address 1 with value {s}")
    public CheckoutPage enterShippingAddress1(String s){
        enterText(driver,shippingAddress1,s);
        return this;
    }
    
    @Step("Enter shipping address 2 with value {s}")
    public CheckoutPage enterShippingAddress2(String s){
        enterText(driver,shippingAddress2,s);
        return this;
    }
    
    @Step("Enter shipping city with value {s}")
    public CheckoutPage enterShippingCity(String s){
        enterText(driver,shippingCity,s);
        return this;
    }
    
    @Step("Enter shipping postcode with value {s}")
    public CheckoutPage enterShippingPostcode(String s){
        enterText(driver,shippingPostcode,s);
        return this;
    }
    
    @Step("Enter shipping country with value {s}")
    public CheckoutPage enterShippingCountry(String s){
        enterText(driver,shippingCountry,s);
        return this;
    }
    
    @Step("Enter shipping state with value {s}")
    public CheckoutPage enterShippingState(String s){
        enterText(driver,shippingState,s);
        return this;
    }
    
    @Step("Click 'Continue' button to proceed to the Confirm Order page")
    public ConfirmOrderPage clickContinueBtn(){
        clickButton(driver,ContinueBtn);
        return new ConfirmOrderPage(driver);
    }

    public int getNumberOfProudcts(){
        return findElements(driver,quantityText).size();
    }

    public String getQuatity() {
        return driver.findElement(quantityText).getAttribute("value");
    }

}
