package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import utilities.Log;

import static utilities.ElementActions.clickButton;
public class AccountBase<T extends AccountBase<T>> extends BasePage<T> {

    //WebDriver driver;

    private By loginBtn = By.xpath("//a[@class='login']");
    private By registerBtn = By.xpath("//*[@class=\"fas fa-user-plus fa-fw mr-1\"]/..");
    private By forgotPasswordBtn = By.xpath("//*[@class=\"fas fa-key fa-fw mr-1\"]/..']");
    private By myAccountBtn = By.xpath("//*[@class=\"fas fa-user fa-fw mr-1\"]/..");
    private By orderHistoryBtn = By.xpath("fas fa-box-open fa-fw mr-1");
    private By rewardPointsBtn = By.xpath("//*[@class=\"fas fa-medal fa-fw mr-1\"]/..");
    private By downloadBtn = By.xpath("//*[@class=\"fas fa-download fa-fw mr-1\"]/..");
    private By recurringPaymentsBtn = By.xpath("//*[@class=\"fas fa-credit-card fa-fw mr-1\"]/..");
    private By returnsBtn = By.xpath("//*[@class=\"fas fa-undo fa-fw mr-1\"]/..");
    private By transactionBtn = By.xpath("//*[@class=\"fas fa-dollar-sign fa-fw mr-1\"]/..");
    private By newsletterBtn = By.xpath("//*[@class=\"fas fa-envelope fa-fw mr-1\"]/..");
    private By addressBtn = By.xpath("//*[@class=\"fas fa-address-book fa-fw mr-1\"]/..");
    private By passwordBtn = By.xpath("//*[@class = \"fas fa-key fa-fw mr-1\"]/..");
    public AccountBase(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginBtn() {
        clickButton(driver,loginBtn);
        return new LoginPage(driver);
    }
    public SingUpPage clickRegisterBtn() {
        clickButton(driver,registerBtn);
        return new SingUpPage(driver);
    }
    public T clickForgotPasswordBtn() {
        clickButton(driver,forgotPasswordBtn);
        return (T) this;
    }
    public T clickMyAccountBtn() {
        clickButton(driver,myAccountBtn);
        return (T) this;
    }
    public T clickOrderHistoryBtn() {
        clickButton(driver,orderHistoryBtn);
        return (T) this;
    }
    public AddressPage clickAddressBtn(){
        clickButton(driver,addressBtn);
        return new AddressPage(driver);
    }
    public T clickRewardPointsBtn() {
        clickButton(driver,rewardPointsBtn);
        return (T) this;
    }
    public T clickDownloadBtn() {
        clickButton(driver,downloadBtn);
        return (T)this;
    }
    public T clickRecurringPaymentsBtn() {
        clickButton(driver,recurringPaymentsBtn);
        return (T)this;
    }
    public T clickReturnsBtn() {
        clickButton(driver,returnsBtn);
        return (T)this;
    }
    public T clickTransactionBtn() {

        clickButton(driver,transactionBtn);
        return (T)this;
    }
    public T clickNewsletterBtn() {
        clickButton(driver,newsletterBtn);
        return (T)this;
    }
    public PasswordPage clickPasswrdBtn(){
        clickButton(driver,passwordBtn);
        return new PasswordPage(driver);
    }
}
