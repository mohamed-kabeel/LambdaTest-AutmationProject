package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static utilities.ElementActions.*;

public class SpecificBlogPage extends BasePage<SpecificBlogPage> {
    By blogName = By.cssSelector("[class=\"entry-content content-title \"] h1");
    By name = By.cssSelector("#entry_210911 #input-name");
    By email = By.cssSelector("#entry_210911 #input-email");
    By comment = By.cssSelector("#entry_210911 #input-comment");
    By submitBtn = By.cssSelector("#entry_210911 #button-comment");
    By nameError = By.xpath("//*[@id = \"input-name\"]/..//*[@class=\"invalid-feedback\"]");
    By emailError = By.xpath("//*[@id = \"input-email\"]/..//*[@class=\"invalid-feedback\"]");
    By commentError = By.xpath("//*[@id = \"input-comment\"]/..//*[@class=\"invalid-feedback\"]");
    By successfullAlert = By.cssSelector("[class=\"alert alert-success alert-dismissible\"]");

    public SpecificBlogPage(WebDriver driver) {
        super(driver);
    }
    public String getBlogName(){
        return getDataFromElement(driver,blogName);
    }
    public SpecificBlogPage enterName(String s){
        enterText(driver,name,s);
        return this;
    }

    public SpecificBlogPage enterEmail(String s){
        enterText(driver,email,s);
        return this;
    }
    public SpecificBlogPage enterComment(String s){
        enterText(driver,comment,s);
        return this;
    }
    public SpecificBlogPage clickSubmitBtn(){
        clickButton(driver,submitBtn);
        return this;
    }
    public void validateErrors(SoftAssert softAssert){
        softAssert.assertTrue(getDataFromElement(driver,nameError).contains("Warning: Comment Name must be between 3 and 25 characters!"));
        softAssert.assertTrue(getDataFromElement(driver,emailError).contains("Warning: Invalid email id!"));
        softAssert.assertTrue(getDataFromElement(driver,commentError).contains("Warning: Comment Text must be between 25 and 1000 characters!"));

    }
    public SpecificBlogPage validateSuccessfullComment(){
        Assert.assertTrue(getDataFromElement(driver,successfullAlert).contains("Thank you for your comment. It has been submitted to the webmaster for approval."));
        return this;
    }


}
