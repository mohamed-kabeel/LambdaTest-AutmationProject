package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.Log;

import static utilities.ElementActions.clickButton;
import static utilities.ElementActions.enterText;
import static utilities.ElementUtils.checkElementVisibility;

public class SingUpPage extends AccountBase<SingUpPage> {
    private By firtName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telphone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By subscribeYes = By.id("input-newsletter-yes");
    private By subscribeNo = By.id("input-newsletter-no");
    private By inputAgree = By.xpath("//*[@id =\"input-agree\"]/..");
    private By continueBtn = By.cssSelector("[class=\"btn btn-primary\"]");

    private By firstNameError = By.xpath("//input[@id=\"input-firstname\"]/..//div");
    private By lastNameError = By.xpath("//input[@id=\"input-lastname\"]/..//div");
    private By emailError = By.xpath("//input[@id=\"input-email\"]/..//div");
    private By telphoneError = By.xpath("//input[@id=\"input-telephone\"]/..//div");
    private By passwordError = By.xpath("//input[@id=\"input-password\"]/..//div");
    private By confirmPasswordError = By.xpath("//input[@id=\"input-confirm\"]/..//div");
    private By agreeError = By.xpath("//input[@id=\"input-agree\"]/..//div");



    public SingUpPage(WebDriver driver) {
        super(driver);
    }


        @Step("Enter First Name: {0}")
        public SingUpPage enterFirstName(String s){
            enterText(driver,firtName,s);
            return this;
        }

        @Step("Enter Last Name: {0}")
        public SingUpPage enterLastName(String s){
            enterText(driver,lastName,s);
            return this;
        }

        @Step("Enter Email: {0}")
        public SingUpPage enterEmail(String s){
            enterText(driver,email,s);
            return this;
        }

        @Step("Enter Telephone: {0}")
        public SingUpPage enterTelphone(String s){
            enterText(driver,telphone,s);
            return this;
        }

        @Step("Enter Password: {0}")
        public SingUpPage enterPassword(String s){
            enterText(driver,password,s);
            return this;
        }

        @Step("Enter Confirm Password: {0}")
        public SingUpPage enterConfirmPassword(String s){
            enterText(driver,confirmPassword,s);
            return this;
        }

        @Step("Click Subscribe Yes")
        public SingUpPage clickSubscribeYes(){
            clickButton(driver,subscribeYes);
            return this;
        }

        @Step("Click Subscribe No")
        public SingUpPage clickSubscribeNo(){
            clickButton(driver,subscribeNo);
            return this;
        }

        @Step("Click Input Agree")
        public SingUpPage clickInputAgree(){
            clickButton(driver,inputAgree);
            return this;
        }

        @Step("Click Continue Button")
        public SingUpPage clickContinueBtn(){
            clickButton(driver,continueBtn);
            return this;
        }

        //@Step("Verify First Name Error")
        /*public SingUpPage verifyFirstNameError(){
            softAssert.assertTrue(checkElementVisibility(driver,firstNameError));
            return this;
        }

        //@Step("Verify Last Name Error")
        public SingUpPage verifyLastNameError(){
            Log.info("ver"+checkElementVisibility(driver,lastNameError));
            softAssert.assertTrue(checkElementVisibility(driver,lastNameError));
            return this;
        }

        //@Step("Verify Email Error")
        public SingUpPage verifyEmailError(){
            softAssert.assertTrue(checkElementVisibility(driver,emailError));
            return this;
        }

        //@Step("Verify Telephone Error")
        public SingUpPage verifyTelphoneError(){
            softAssert.assertTrue(checkElementVisibility(driver,telphoneError));
            return this;
        }

        //@Step("Verify Password Error")
        public SingUpPage verifyPasswordError(){
            softAssert.assertTrue(checkElementVisibility(driver,passwordError));
            return this;
        }

        //@Step("Verify Confirm Password Error")
        public SingUpPage verifyConfirmPasswordError(){
            softAssert.assertTrue(checkElementVisibility(driver,confirmPasswordError));
            return this;
        }

        //@Step("Verify Agree Error")
        public SingUpPage verifyAgreeError(){
            softAssert.assertTrue(checkElementVisibility(driver,agreeError));
            return this;
        }*/

    public SingUpPage verifyFirstNameError() {
        boolean visible = checkElementVisibility(driver, firstNameError);
        Log.info("Verifying First Name error visibility: " + visible);
        softAssert.assertTrue(visible, "First Name error message should be visible.");
        return this;
    }

    public SingUpPage verifyLastNameError() {
        boolean visible = checkElementVisibility(driver, lastNameError);
        Log.info("Verifying Last Name error visibility: " + visible);
        softAssert.assertTrue(visible, "Last Name error message should be visible.");
        return this;
    }

    public SingUpPage verifyEmailError() {
        boolean visible = checkElementVisibility(driver, emailError);
        Log.info("Verifying Email error visibility: " + visible);
        softAssert.assertTrue(visible, "Email error message should be visible.");
        return this;
    }

    public SingUpPage verifyTelphoneError() {
        boolean visible = checkElementVisibility(driver, telphoneError);
        Log.info("Verifying Telephone error visibility: " + visible);
        softAssert.assertTrue(visible, "Telephone error message should be visible.");
        return this;
    }

    public SingUpPage verifyPasswordError() {
        boolean visible = checkElementVisibility(driver, passwordError);
        Log.info("Verifying Password error visibility: " + visible);
        softAssert.assertTrue(visible, "Password error message should be visible.");
        return this;
    }

    public SingUpPage verifyConfirmPasswordError() {
        boolean visible = checkElementVisibility(driver, confirmPasswordError);
        Log.info("Verifying Confirm Password error visibility: " + visible);
        softAssert.assertTrue(visible, "Confirm Password error message should be visible.");
        return this;
    }

    public SingUpPage verifyAgreeError() {
        boolean visible = checkElementVisibility(driver, agreeError);
        Log.info("Verifying Agree checkbox error visibility: " + visible);
        softAssert.assertTrue(visible, "Agree checkbox error message should be visible.");
        return this;
    }
}



