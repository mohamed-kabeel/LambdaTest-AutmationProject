package pages;

import com.github.javafaker.Cat;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v130.fedcm.model.Account;
import org.testng.asserts.SoftAssert;


import static utilities.ElementActions.clickButton;
import static utilities.ElementActions.enterText;
import static utilities.ElementUtils.hover;
public class BasePage<T extends BasePage<T>>{
    public WebDriver driver;
    public static SoftAssert softAssert = new SoftAssert();
    protected By searchCategory = By.cssSelector("#entry_217822 [class = \"dropdown search-category\"]");
    protected String searchSubCat = "Desktops";
    protected By SearhcspecificCategory = By.xpath("//*[@class=\"dropdown-menu dropdown-menu-left show\"]//a[contains(text(),\""+searchSubCat+"\")]");
    protected By searchText = By.cssSelector("#entry_217822 input[name=\"search\"]");
    protected By searchBtn = By.cssSelector("#entry_217822 .search-button");
    protected By compareBtn = By.cssSelector("#entry_217823 svg");
    protected By wishlistBtn = By.cssSelector("#entry_217824 svg");
    protected By cartBtn = By.cssSelector("#entry_217825 svg");

    protected By topCatogries = By.cssSelector("#entry_217832 a");

    protected By homeBtn= By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"Home\"]");
    protected By specialBtn = By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"Special\"]");
    protected By blogBtn = By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"Blog\"]");
    protected By megaMenuBtn = By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"Mega Menu\"]");
    protected By addOnBtn = By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"AddOns\"]");
    protected By myAccountBtn = By.xpath("//*[@id=\"entry_217834\"]//*[normalize-space(text()) = \"My account\"]");
    String baseBtnName;
    String specificBtnName;
    protected By specificBtn = By.xpath("//*[@id=\"entry_217834\"]//*[contains(text(),\""+baseBtnName+"\")]/ancestor::li//*[contains(text(),\""+specificBtnName+"\")]");

    private By primaryBtn = By.cssSelector("[class=\"btn btn-primary btn-block\"]");
    private By secondryBtn = By.cssSelector("[class=\"btn btn-secondary btn-block\"]");
    private By closeAlertBtn = By.cssSelector("[class =\"ml-2 mb-1 close\"]");
    public BasePage(WebDriver driver){
        this.driver=driver;
    }
    void setSearchSubCat(String s){
        searchSubCat = "Desktops";
      SearhcspecificCategory = By.xpath("//*[@class=\"dropdown-menu dropdown-menu-left show\"]//a[contains(text(),\""+searchSubCat+"\")]");
    }
    void setSpecificBtn(String base,String specific){
        baseBtnName = base;
        specificBtnName = specific;
        specificBtn = By.xpath("//*[@id=\"entry_217834\"]//*[contains(text(),\""+baseBtnName+"\")]/ancestor::li//*[contains(text(),\""+specificBtnName+"\")]");
    }
    public HomePage clickHomeBtn(){
        clickButton(driver,homeBtn);
        return new HomePage(driver);
    }
    public T clickSpecialBtn(){
        clickButton(driver,specialBtn);
        return (T)this;
    }
    public T clickBlogBtn(){
        clickButton(driver,blogBtn);
        return (T)this;
    }
    public T clickMegaMenuBtn(){
        clickButton(driver,megaMenuBtn);
        return (T)this;
    }
    public T clickAddOnBtn(){
        clickButton(driver,addOnBtn);
        return (T)this;
    }
    public T clickMyAccountBtnBase(){
        clickButton(driver,myAccountBtn);
        return (T)this;
    }
    public CategoryPage clickSpecificBtn(String base, String specific){
        setSpecificBtn(base,specific);
        clickButton(driver,specificBtn);
        return new CategoryPage(driver);
    }
    public T enterSearchText(String s){
        enterText(driver,searchText,s);
        return (T)this;
    }
    public CategoryPage clickSearchBtn(){
        clickButton(driver,searchBtn);
        return new CategoryPage(driver);
    }
    public T clickCompareBtn(){
        clickButton(driver,compareBtn);
        return (T)this;
    }
    public T clickWishlistBtn(){
        clickButton(driver,wishlistBtn);
        return (T)this;
    }
    public T clickCartBtn(){
        clickButton(driver,cartBtn);
        return (T)this;
    }
    public T clickSearchCategory(){

        clickButton(driver,searchCategory);
        return (T)this;
    }
    public T clickSearhcspecificCategory(String s){
        setSearchSubCat(s);
        clickButton(driver,SearhcspecificCategory);
        return (T)this;
    }
    public T clickTopCatogries(){
        clickButton(driver,topCatogries);
        return (T)this;
    }
    public void  setBaseBtnName(String s){
        baseBtnName=s;

    }

    public T clickeSpecialBtn(){
        clickButton(driver,specialBtn);
        return (T)this;
    }
    public T hoverMegMenu(){
        hover(driver,megaMenuBtn);
        return (T)this;

    }
    public T hoverMyAcount(){
        hover(driver,myAccountBtn);
        return (T)this;

    }
    @Step("Navigate to the Shopping Cart page")
    public ShoppingCartPage clickAlertViewCart() {
        clickButton(driver, primaryBtn);
        return new ShoppingCartPage(driver);
    }

    @Step("Navigate to the Checkout page")
    public CheckoutPage clickAlertCheckoutBtn() {
        clickButton(driver, secondryBtn);
        return new CheckoutPage(driver);
    }
    @Step("Navigate to the WishListPage page")
    public WishListPage clickAlertWishListBtn() {
        clickButton(driver, secondryBtn);
        return new WishListPage(driver);
    }

    @Step("Navigate to the Compare page")
    public ComparePage clickAlertCompareBtn() {
        clickButton(driver, secondryBtn);
        return new ComparePage(driver);
    }

    public T clickAlertCloseBtn(){
        clickButton(driver,closeAlertBtn);
        return (T)this;
    }



}
