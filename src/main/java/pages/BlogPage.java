package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.ElementActions.clickButton;
import static utilities.ElementActions.getDataFromElement;
public class BlogPage extends BasePage {
     private int  lastedBlogNum = 1;
    String lastArticle = "swiper-wrapper-f9b891315a511436";
    String mostViewdArticle = "mz-article-listing-77210961";
    private By lastedBlogAuthor ;
    private By lastedBlogcomment ;
    private By lastedBlogViewed ;
    private By lastedBlogTimespamed ;
    private By lastedBlogNameAndLink ;

    private By mostviewedBlogAuthor ;
    private By MostViewedBlogcomment ;
    private By MostViewedlogViewed ;
    private By MostViewedBlogTimespamed ;
    private By MostViewedBlogNameAndLink ;

    void setLastViewd(int num){
        lastedBlogNum = num;
         lastedBlogAuthor = By.cssSelector("#" +lastArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .author");
         lastedBlogcomment = By.cssSelector("#" +lastArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .comment");
         lastedBlogViewed = By.cssSelector("#" +lastArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .viewed");
         lastedBlogTimespamed = By.cssSelector("#" +lastArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .timestamp");
         lastedBlogNameAndLink = By.cssSelector("#" +lastArticle+ " div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .title a");
    }
    void setMostViewd(int num){
        lastedBlogNum = num;
        mostviewedBlogAuthor = By.cssSelector("#" +mostViewdArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .author");
        MostViewedBlogcomment = By.cssSelector("#" +mostViewdArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .comment");
        MostViewedlogViewed = By.cssSelector("#" +mostViewdArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .viewed");
        MostViewedBlogTimespamed = By.cssSelector("#" +mostViewdArticle+" div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .timestamp");
        MostViewedBlogNameAndLink = By.cssSelector("#" +mostViewdArticle+ " div[aria-label=\""+ lastedBlogNum+" / 10\"] div[class =\"article-thumb image-top\"] .title a");
    }

    public void clickLastedBlogsAuthor(int num){
        setLastViewd(num);
        clickButton(driver,lastedBlogAuthor);
    }
    public String getComment(){
        setLastViewd(lastedBlogNum);
        return getDataFromElement(driver,lastedBlogcomment);
    }
    public String getViewed(){
        setLastViewd(lastedBlogNum);
        return getDataFromElement(driver,lastedBlogViewed);
    }
    public String getTimespamed(){
        setLastViewd(lastedBlogNum);
        return getDataFromElement(driver,lastedBlogTimespamed);
    }
    public String getLink(){
        setLastViewd(lastedBlogNum);
        return getDataFromElement(driver,lastedBlogNameAndLink);
    }
    public void clickMostViewdBlogsAuthor(int num){
        setMostViewd(num);
        clickButton(driver,mostviewedBlogAuthor);
    }
    public String getMostViewdComment(){
        setMostViewd(lastedBlogNum);
        return getDataFromElement(driver,MostViewedBlogcomment);
    }

    public String getMostViewdViewed(){
        setMostViewd(lastedBlogNum);
        return getDataFromElement(driver,MostViewedlogViewed);
    }
    public String getMostViewdTimespamed(){
        setMostViewd(lastedBlogNum);
        return getDataFromElement(driver,MostViewedBlogTimespamed);
    }
    public String getMostViewdLink(int num){
        setMostViewd(num);
        return getDataFromElement(driver,MostViewedBlogNameAndLink);
    }
    public SpecificBlogPage clickMostViewdLink(int num){
        setMostViewd(num);
        System.out.println(MostViewedBlogNameAndLink);
        clickButton(driver,MostViewedBlogNameAndLink);
        return new SpecificBlogPage(driver);
    }


    public BlogPage(WebDriver driver) {
        super(driver);
    }
}
