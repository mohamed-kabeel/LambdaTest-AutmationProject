package pages;

import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import static utilities.ElementActions.clickButton;
import static utilities.ElementActions.enterText;
import static utilities.ElementUtils.checkElementVisibility;
import static utilities.WaitUtils.fluentWait;

public class LoginPage extends AccountBase<LoginPage> {
    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.cssSelector("[class=\"btn btn-primary\"][type=\"submit\"]");
    private By loginError = By.cssSelector("[class=\"alert alert-danger alert-dismissible\"]");
    private By forgotPassword = By.xpath("//*[@class=\"form-group\"]//a[contains(text(),\"Forgotten Password\")]");
    private By register = By.xpath("//*[@class=\"card-body p-4\"]//a[contains(@href,\"register\")]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("enter email address")
    public LoginPage enterEmail(String s) {
        enterText(driver, email, s);
        return this;
    }

    @Step("enter password")
    public LoginPage enterPassword(String s) {
        enterText(driver, password, s);
        return this;
    }

    @Step("click login button")
    public LoginPage clickLoginBtn() {
        clickButton(driver, loginBtn);
        return this;
    }

    public LoginPage waitForNextPage() {
        fluentWait(driver, specialBtn, 3000)
                .until(x -> {
                    if (driver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=account/account"))
                        return true;
                    return false;
                });
        return this;
    }


    public void verifyLoginError(){
        Assert.assertTrue(checkElementVisibility(driver,loginError),"login error should be appear");
    }
}
