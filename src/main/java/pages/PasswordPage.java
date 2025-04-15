package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static utilities.ElementActions.*;
import static utilities.ElementUtils.checkElementVisibility;
public class PasswordPage extends AccountBase {
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");
    private By continueBtn = By.cssSelector("[class=\"btn btn-primary\"]");
    private By passwordError = By.xpath("//input[@id=\"input-password\"]/..//div");
    private By confirmPasswordError = By.xpath("//input[@id=\"input-confirm\"]/..//div");
    private By successafulAlert = By.cssSelector("[class = \"alert alert-success alert-dismissible\"]");
    public PasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter password: {0}")
    public PasswordPage enterPassword(String s){
        enterText(driver,password,s);
        return this;
    }
    
    @Step("Enter confirm password: {0}")
    public PasswordPage enterConfirmPassword(String s){
        enterText(driver,confirmPassword,s);
        return this;
    }
    
    @Step("Click continue button")
    public PasswordPage clickContinueBtn(){
        clickButton(driver,continueBtn);
        return this;
    }
    public String getAlertText(){
        return getDataFromElement(driver,successafulAlert);
    }
    
    @Step("Verify password error message is displayed")
    public void verifyPasswordError(){
        Assert.assertTrue(checkElementVisibility(driver,passwordError));
    }
    
    @Step("Verify confirm password error message is displayed")
    public void verifyConfirmPasswordError(){
        Assert.assertTrue(checkElementVisibility(driver,confirmPasswordError));
    }
    public void  verifysuccessifulAlert(){
        Assert.assertTrue(getAlertText().contains("Success: Your password has been successfully updated."));
    }


}
