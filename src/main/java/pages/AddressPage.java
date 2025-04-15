package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.ElementActions.*;
import static utilities.ElementUtils.checkElementVisibility;

public class AddressPage extends AccountBase<AddressPage> {
    private By firstname = By.id("input-firstname");
    private By lastname = By.id("input-lastname");
    private By company = By.id("input-company");
    private By address1 = By.id("input-address-1");
    private By address2 = By.id("input-address-2");
    private By city = By.id("input-city");
    private By zipcode = By.id("input-postcode");
    private By country = By.id("input-country");
    private By region = By.id("input-zone");
    private By defaultAddressYes = By.cssSelector(".form-check-input[value =\"1\"]");
    private By defaultAddressNo = By.id("input-default-address-no");
    private By continueBtn = By.cssSelector("[class = \"btn btn-primary\"]");

    private By firstNameError = By.cssSelector("//*[@id=\"input-firstname\"]/..//div");
    private By lastNameError = By.cssSelector("//*[@id=\"input-lastname\"]/..//div");
    private By companyError = By.cssSelector("//*[@id=\"input-company\"]/..//div");
    private By address1Error = By.cssSelector("//*[@id=\"input-address-1\"]/..//div");
    private By address2Error = By.cssSelector("//*[@id=\"input-address-2\"]/..//div");
    private By cityError = By.cssSelector("//*[@id=\"input-city\"]/..//div");
    private By zipcodeError = By.cssSelector("//*[@id=\"input-postcode\"]/..//div");
    private By countryError = By.cssSelector("//*[@id=\"input-country\"]/..//div");
    private By regionError = By.cssSelector("//*[@id=\"input-zone\"]/..//div");
    private By newAddressBtn = By.cssSelector("[class = \"btn btn-primary\"]");
    public AddressPage(WebDriver driver) {
        super(driver);
    }
    public AddressPage clickNewAddress(){
        clickButton(driver,newAddressBtn);
        return this;
    }
    @Step("Enter first name: {0}")
    public AddressPage enterFirstName(String s){
        enterText(driver,firstname,s);
        return this;
    }
    
    @Step("Enter last name: {0}")
    public AddressPage enterLastName(String s){
        enterText(driver,lastname,s);
        return this;
    }
    
    @Step("Enter company: {0}")
    public AddressPage enterCompany(String s){
        enterText(driver,company,s);
        return this;
    }
    
    @Step("Enter address 1: {0}")
    public AddressPage enterAddress1(String s){
        enterText(driver,address1,s);
        return this;
    }
    
    @Step("Enter address 2: {0}")
    public AddressPage enterAddress2(String s){
        enterText(driver,address2,s);
        return this;
    }
    
    @Step("Enter city: {0}")
    public AddressPage enterCity(String s){
        enterText(driver,city,s);
        return this;
    }
    
    @Step("Enter zipcode: {0}")
    public AddressPage enterZipcode(String s){
        enterText(driver,zipcode,s);
        return this;
    }
    
    @Step("Enter country: {0}")
    public AddressPage selectCountry(int index){
        selectDropListElement(driver,country,index);
        return this;
    }
    
    @Step("Enter region: {0}")
    public AddressPage selectRegion(int index){
        selectDropListElement(driver,region,index);
        return this;
    }
    
    @Step("Click default address: Yes")
    public AddressPage clickDefaultAddressYes(){
        clickButton(driver,defaultAddressYes);
        return this;
    }
    
    @Step("Click default address: No")
    public AddressPage clickDefaultAddressNo(){
        clickButton(driver,defaultAddressNo);
        return this;
    }
    
    @Step("Click continue button")
    public AddressPage clickContinueBtn(){
        clickButton(driver,continueBtn);
        return this;
    }
    
    @Step("Verify first name error is displayed")
    public AddressPage verifyFirstNameError(){
        softAssert.assertTrue(checkElementVisibility(driver,firstNameError));
        return this;
    }
    
    @Step("Verify last name error is displayed")
    public AddressPage verifyLastNameError(){
        softAssert.assertTrue(checkElementVisibility(driver,lastNameError));
        return this;
    }
    
    @Step("Verify company error is displayed")
    public AddressPage verifyCompanyError(){
        softAssert.assertTrue(checkElementVisibility(driver,companyError));
        return this;
    }
    
    @Step("Verify address 1 error is displayed")
    public AddressPage verifyAddress1Error(){
        softAssert.assertTrue(checkElementVisibility(driver,address1Error));
        return this;
    }
    
    @Step("Verify address 2 error is displayed")
    public AddressPage verifyAddress2Error(){
        softAssert.assertTrue(checkElementVisibility(driver,address2Error));
        return this;
    }
    @Step("Verify city error is displayed")
    public AddressPage verifyCityError(){
        softAssert.assertTrue(checkElementVisibility(driver,cityError));
        return this;
    }
    
    @Step("Verify zipcode error is displayed")
    public AddressPage verifyZipcodeError(){
        softAssert.assertTrue(checkElementVisibility(driver,zipcodeError));
        return this;
    }
    
    @Step("Verify country error is displayed")
    public AddressPage verifyCountryError(){
        softAssert.assertTrue(checkElementVisibility(driver,countryError));
        return this;
    }
    
    @Step("Verify region error is displayed")
    public AddressPage verifyRegionError(){
        softAssert.assertTrue(checkElementVisibility(driver,regionError));
        return this;
    }
}
