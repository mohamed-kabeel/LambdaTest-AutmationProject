package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.ElementActions.clickButton;
import static utilities.WaitUtils.fluentWait;

public class ConfirmOrderPage extends BasePage {
    private By editBtn = By.cssSelector("[class=\"btn btn-primary\"]");
   private By confirmBtn = By.cssSelector("button#button-confirm");


    public ConfirmOrderPage(WebDriver driver) {
        super(driver);
    }
    @Step("Click Edit Button on Confirm Order Page")
    public CheckoutPage clickEditBtn(){
        clickButton(driver,editBtn);
        return new CheckoutPage(driver);
    }
    
    @Step("Click Confirm Button on Confirm Order Page")
    public ConfirmOrderPage clickConfirmBtn(){
        clickButton(driver,confirmBtn);
        return this;
    }
    public void waitForNextPage(){
        fluentWait(driver,homeBtn,10000)
                .until(x->{
                    if(driver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=extension/maza/checkout/confirm"))
                        return true;
                    return false;
                });
    }
}
