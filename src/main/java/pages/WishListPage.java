package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utilities.ElementActions.*;

public class WishListPage extends BasePage<WishListPage>{
    private By addToCartBtn = By.xpath("//*[@class = \"btn btn-light btn-sm\"]");
    private  By removeFromWithListBtn = By.xpath("//*[@class = \"fa fa-times\"]");
    private  By productName = By.cssSelector(".text-left a");
    private By continueBtn = By.cssSelector("[class = \"btn btn-primary\"]");
    private By modifiedAlert = By.cssSelector("[class = \"alert alert-success alert-dismissible\"]");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public WishListPage clickAddCartBtn(int index){
        WebElement element = (findElements(driver,addToCartBtn)).get(index-1);
        clickOnWebElement(driver,element);
        return this;
    }
    public WishListPage clickContinueBtn(){
        clickButton(driver,continueBtn);
        return this;
    }
    public WishListPage getProductName(int index){
        WebElement element = (findElements(driver,productName)).get(index-1);
        clickOnWebElement(driver,element);
        return this;
    }

    public WishListPage clickRemoveBtn(int index){
        WebElement element = (findElements(driver,removeFromWithListBtn)).get(index-1);
        clickOnWebElement(driver,element);
        return this;
    }
    public String getModificatedAlert(){
        return getDataFromElement(driver,modifiedAlert);
    }

}