package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.ElementActions.clickButton;
public class HomePage extends BasePage<HomePage> {
    String trandingcatogroyName = "Desktops";
    By trandingCategory = By.xpath("//*[@id=\"mz-category-wall74217970\"]//*[contains(text(),\""+trandingcatogroyName+"\")]/../..");

    String topProductName;
    By topAddToCartBtn ;
    By topViewProductBtn ;
    By topAddToWishListBtn ;
    By topComporareBtn ;
    By topProduct ;


    String collProductName;
    By collAddToCartBtn ;
    By collViewProductBtn ;
    By collAddToWishListBtn ;
    By collComporareBtn ;
    By collProduct ;
    int BlogNum;
    By BlogAuthor = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .author");
    By blogcomment = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .comment");
    By blogViewed = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .viewed");
    By blogTimespamed = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .timestamp");
    By blogNameAndLink = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .title a");

    void setTopProduct(String s){
        this.topProductName = s;
         topAddToCartBtn = By.xpath("//*[@id=\"entry_217979\"]//*[contains(text(),\""+topProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Add to Cart\"]");
         topViewProductBtn = By.xpath("//*[@id=\"entry_217979\"]//*[contains(text(),\""+topProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Quick view\"]");
         topAddToWishListBtn = By.xpath("//*[@id=\"entry_217979\"]//*[contains(text(),\""+topProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Add to Wish List\"]");
         topComporareBtn = By.xpath("//*[@id=\"entry_217979\"]//*[contains(text(),\""+topProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Compare this Product\"]");
         topProduct = By.xpath("//*[@id=\"entry_217979\"]//*[contains(text(),\""+topProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]");
    }
    void setCollProduct(String s){
        this.collProductName = s;
        collAddToCartBtn = By.xpath("//*[@id=\"entry_217984\"]//*[contains(text(),\""+collProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Add to Cart\"]");
        collViewProductBtn = By.xpath("//*[@id=\"entry_217984\"]//*[contains(text(),\""+collProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Quick view\"]");
        collAddToWishListBtn = By.xpath("//*[@id=\"entry_217984\"]//*[contains(text(),\""+collProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Add to Wish List\"]");
        collComporareBtn = By.xpath("//*[@id=\"entry_217984\"]//*[contains(text(),\""+collProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]//button[@title=\"Compare this Product\"]");
        collProduct = By.xpath("//*[@id=\"entry_217984\"]//*[contains(text(),\""+collProductName+"\")]/ancestor::div[contains(@class,\"product-thumb\")]");
    }
   public void setBlog(int i){
        this.BlogNum = i;
        BlogAuthor = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .author");
        blogcomment = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .comment");
        blogViewed = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .viewed");
        blogTimespamed = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .timestamp");
        blogNameAndLink = By.xpath("div[aria-label=\""+ BlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .title a");
    }
    public void setTrandingCategory(String s){
        this.trandingcatogroyName = s;
        trandingCategory = By.xpath("//*[@id=\"mz-category-wall74217970\"]//*[contains(text(),\""+trandingcatogroyName+"\")]/../..");
    }
    @Step("Click on the '{s}' tranding category")
    public void clickTrandinCategory(String s){
        setTrandingCategory(s);
        clickButton(driver,trandingCategory);
    }
    
    @Step("Add '{s}' product to cart from top products section")
    public void clickTopAdd(String s){
        setTopProduct(s);
        clickButton(driver,topAddToCartBtn);
    }
    
    @Step("View details of '{s}' product from top products section")
    public void clickTopView(String s){
        setTopProduct(s);
        clickButton(driver,topViewProductBtn);
    }
    
    @Step("Add '{s}' product to wishlist from top products section")
    public void clickTopWishList(String s){
        setTopProduct(s);
        clickButton(driver,topAddToWishListBtn);
    }
    
    @Step("Compare '{s}' product from top products section")
    public void clickTopCompare(String s){
        setTopProduct(s);
        clickButton(driver,topComporareBtn);
    }
    
    @Step("Click on '{s}' product from top products section")
    public SpecificProductPage clickTopProduct(String s){
        setTopProduct(s);
        clickButton(driver,topProduct);
        return new SpecificProductPage(driver);
    }
    
    @Step("Add '{s}' product to cart from collection section")
    public void clickCollAdd(String s){
        setCollProduct(s);
        clickButton(driver,collAddToCartBtn);
    }
    
    @Step("View details of '{s}' product from collection section")
    public void clickCollView(String s){
        setCollProduct(s);
        clickButton(driver,collViewProductBtn);
    }
    
    @Step("Add '{s}' product to wishlist from collection section")
    public void clickCollWishList(String s){
        setCollProduct(s);
        clickButton(driver,collAddToWishListBtn);
    }
    
    @Step("Compare '{s}' product from collection section")
    public void clickCollCompare(String s){
        setCollProduct(s);
        clickButton(driver,collComporareBtn);
    }
    
    @Step("Click on '{s}' product from collection section")
    public SpecificProductPage clickCollProduct(String s){
        setCollProduct(s);
        clickButton(driver,collProduct);
        return new SpecificProductPage(driver);
    }
    
    @Step("Click on author of blog number {i}")
    public void clickBlogAuthor(int i){
        setBlog(i);
        clickButton(driver,BlogAuthor);
    }
    
    @Step("Get comment count of blog number {i}")
    public void getBlogComment(int i){
        setBlog(i);
        clickButton(driver,blogcomment);
    }
    
    @Step("Get viewed count of blog number {i}")
    public void getBlogViewed(int i){
        setBlog(i);
        clickButton(driver,blogViewed);
    }
    
    @Step("Get timestamp of blog number {i}")
    public void getBlogTimeSpamed(int i){
        setBlog(i);
        clickButton(driver,blogTimespamed);
    }
    
    @Step("Click on blog name and link of blog number {i}")
    public void clickBlogNameAndLink(int i){
        setBlog(i);
        clickButton(driver,blogNameAndLink);
    }
    public HomePage(WebDriver driver) {
        super(driver);
    }
}
