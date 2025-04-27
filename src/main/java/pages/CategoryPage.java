package pages;

import com.github.javafaker.Cat;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static utilities.ElementActions.*;
import static utilities.ElementUtils.hover;
import static utilities.WaitUtils.fluentWait;


public class CategoryPage extends BasePage<CategoryPage> {

    int max = 2000;
    int min = 98;
    By maxSlider = By.cssSelector("#mz-filter-panel-0-0 [style = \"left: 100%;\"]");
    By minSlider = By.cssSelector("#mz-filter-panel-0-0 [style = \"left: 0%;\"]");
    By minPrice = By.cssSelector("#mz-filter-panel-0-0 [name=\"mz_fp[min]\"]");
    By maxPrice = By.cssSelector("#mz-filter-panel-0-0 [name=\"mz_fp[max]\"]");
    String manfuctureName = "Apple";
    By manufactureSelector;
    By searchText = By.cssSelector("#mz-filter-panel-0-3  [aria-label=\"Search\"][class=\"form-control\"]");
    String colorName = "Green";
    By color;
    String sizeName = "L";
    By size;
    int discount = 10;
    By discountSelect = By.cssSelector("#mz-fd-1-" + discount);
    int rate = 1;
    By rating = By.cssSelector("#rating-" + rate);
    String catogyName = "Desktop";
    By category = By.xpath("//div[@id=\"entry_212412\"]//*[contains(text()," + catogyName + ")]");

    String productName = "iMac";
    By addToCartBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Add to Cart\"]");
    By viewProductBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Quick view\"]");
    By addToWishListBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Add to Wish List\"]");
    By comporareBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Compare this Product\"]");

    By product = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]");

    By name = By.cssSelector(".product-thumb .caption .title");
    By price = By.cssSelector(".product-thumb .caption .price");


    By numberOfItem = By.cssSelector("#entry_212398 #input-limit-212402");
    By sortSDropDown = By.cssSelector("#entry_212398 #input-sort-212403");
    By productsCompre = By.cssSelector("#entry_212398 #entry_212400 a");
    By listBtn = By.cssSelector("#entry_212398 #list-view");
    By gridtBtn = By.cssSelector("#entry_212398 #grid-view");
    String componentName = "Monitors";
    By componentCategoryBtn = By.xpath("//figcaption[contains(text()," + componentName + ")]/ancestor::a");
    By invalidProductSearch = By.xpath("//*[@class=\"buttons clearfix mb-3\"]/.. //p");

    By inStockAailbilty = By.xpath("//*[@id = \"mz-fss-0--1\"]/..");

    private By brand = By.xpath("(//*[contains(text(),\"Brand\")]/ancestor::li//a)");
    private By availability = By.xpath("(//*[contains(text(),\"Availability\")]/ancestor::li//span)[2]");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }


    public void setManufactureName(String s) {
        manfuctureName = "Apple";
        manufactureSelector = By.xpath("//*[@class=\"custom-control custom-checkbox\"]//img[@alt=\"" + manfuctureName + "\"]/..");
    }

    public void setColorName(String s) {
        this.colorName = s;
        color = By.xpath("//*[@id=\"entry_212411\"]//*[contains(@alt,\"" + colorName + "\")]");
    }

    public void setSizeName(String s) {
        this.sizeName = s;
        size = By.xpath("//*[@id=\"mz-filter-panel-0-6\"]//label[normalize-space(text())=\"" + sizeName + "\"]");
    }

    public void setRate(int i) {
        this.rate = i;
        rating = By.cssSelector("//*[@id=\"mz-fr-0-" + rate + "\"]/..");
    }

    public void setCatogyName(String s) {
        this.catogyName = s;
        category = By.xpath("//div[@id=\"entry_212412\"]//*[contains(text(),\"" + catogyName + "\")]");
    }

    public void setProductName(String s) {
        this.productName = s;

        product = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]");
        addToCartBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Add to Cart\"]");
        viewProductBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Quick view\"]");
        addToWishListBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Add to Wish List\"]");
        comporareBtn = By.xpath("//*[contains(text(),\"" + productName + "\")]/ancestor::div[@class=\"product-thumb\"]//button[@title=\"Compare this Product\"]");
    }

    public void setDiscount(int i) {
        discount = i;
        discountSelect = By.xpath("//*[@id =\"mz-fd-0-\"" + discount + "\"]/..");

    }

    public void setComponentName(String s) {
        this.componentName = s;
        componentCategoryBtn = By.xpath("//figcaption[contains(text(),\"" + componentName + "\")]/ancestor::a");
    }


    @Step("Clicking the maximum slider")
    public void clickMaxSlider() {
        clickButton(driver, maxSlider);
    }

    @Step("Clicking the minimum slider")
    public void clickMinSlider() {
        clickButton(driver, minSlider);
    }

    @Step("Entering minimum price: {0}")
    public CategoryPage enterMinPrice(String s) {
        clearText(driver, minPrice);
        enterTextAndEnter(driver, minPrice, s);
        return this;
    }

    @Step("Getting the minimum price")
    public String getMinPrice() {
        return driver.findElement(minPrice).getAttribute("value");
    }

    @Step("Entering maximum price: {0}")
    public CategoryPage enterMaxPrice(String s)  {
        clearText(driver, maxPrice);
        enterTextAndEnter(driver, maxPrice, s);

        return this;
    }

    @Step("Getting the maximum price")
    public String getMaxPrice() {
        return driver.findElement(maxPrice).getAttribute("value");
    }

    @Step("Clicking the manufacturer selector: {0}")
    public CategoryPage clickManufactureSelector(String s) {
        setManufactureName(s);
        clickButton(driver, manufactureSelector);
        return this;
    }

    @Step("Entering search text: {0}")
    public CategoryPage enterSearchText(String s) {
        enterTextAndEnter(driver, searchText, s);
        return this;
    }

    @Step("Clicking the color: {0}")
    public CategoryPage clickColor(String s) {
        setColorName(s);
        clickButton(driver, color);
        return new CategoryPage(driver);
    }

    @Step("Clicking the size: {0}")
    public CategoryPage clickSize(String s) {
        setSizeName(s);
        clickButton(driver, size);
        return new CategoryPage(driver);
    }

    @Step("Changing the maximum value to: {0}")
    public CategoryPage changeMax(int max) {
        clickAndMove(driver, maxSlider, max - this.max);
        this.max = max;
        return this;
    }

    @Step("Changing the minimum value to: {0}")
    public CategoryPage changMin(int min) {
        clickAndMove(driver, minSlider, min - this.min);
        this.min = min;
        return this;
    }

    @Step("Clicking the discount selector: {0}")
    public CategoryPage clickDiscountSelect(int i) {
        setDiscount(i);
        clickButton(driver, discountSelect);
        return new CategoryPage(driver);
    }

    @Step("Clicking the rating: {0}")
    public CategoryPage clickRating(int i) {
        setRate(i);
        clickButton(driver, rating);
        return new CategoryPage(driver);
    }
    @Step("click in stock availabilty")
    public CategoryPage clickinStock(){
        clickButton(driver,inStockAailbilty);
        return this;
    }
    @Step("Clicking the category: {0}")
    public CategoryPage clickCategory(String s) {
        setCatogyName(s);
        clickButton(driver, category);
        return new CategoryPage(driver);
    }
    
        @Step("Adding product to cart: {0}")
        public CategoryPage clickAddToCartBtn(String s) {
            setProductName(s);
            hover(driver, product);
            clickButtonWithoutScroll(driver, addToCartBtn);
            return this;
        }
    
        @Step("Viewing product details: {0}")
        public CategoryPage clickViewProductBtn(String s) {
            setProductName(s);
            hover(driver, product);
            clickButtonWithoutScroll(driver, viewProductBtn);
            return this;
        }
    
        @Step("Adding product to wishlist: {0}")
        public CategoryPage clickAddToWishListBtn(String s) {
        driver.navigate().refresh();
            setProductName(s);
            hover(driver, product);
            clickButtonWithoutScroll(driver, addToWishListBtn);
            System.out.println(addToWishListBtn.toString());
            return this;
        }
    
        @Step("Comparing product: {0}")
        public CategoryPage clickComporareBtn(String s) {
            setProductName(s);
            hover(driver, product);
            clickButtonWithoutScroll(driver, comporareBtn);
            return this;
        }
    
        @Step("Navigating to product details page: {0}")
        public SpecificProductPage clickProduct(String s) {
            setProductName(s);
            clickButton(driver, product);
            System.out.println(product.toString());
            return new SpecificProductPage(driver);
        }
    
        @Step("Selecting number of items per page")
        public void clickNumberOfItem() {
            clickButton(driver, numberOfItem);
        }
    
        @Step("Sorting products by index: {0}")
        public void clickSortSDropDown(int index) {
            selectDropListElement(driver, sortSDropDown, index);
        }
    
        @Step("Comparing products")
        public void clickProductsCompre() {
            clickButton(driver, productsCompre);
        }
    
        @Step("Switching to list view")
        public void clickListBtn() {
            clickButton(driver, listBtn);
        }
    
        @Step("Switching to grid view")
        public void clickGridtBtn() {
            clickButton(driver, gridtBtn);
        }
    
        @Step("Navigating to component category: {0}")
        public void clickComponentCategoryBtn(String s) {
            setComponentName(s);
            clickButton(driver, componentCategoryBtn);
        }


    @Step("Get the Brand of the product")
    public List<String> getBrand() {
        List<WebElement> list = findElements(driver,brand);
        List<String> brands = new ArrayList<>();

        for (WebElement element : list) {
            brands.add(element.getText());
        }
        return brands;
    }


    @Step("Get the availability status of the product")
    public List<String> getAvailabilities() {
       fluentWait(driver,availability,20000).until(ExpectedConditions.elementToBeClickable(availability));
        List<WebElement> list = findElements(driver,availability);
        List<String> availabilities = new ArrayList<>();

        for (WebElement element : list) {
            availabilities.add(element.getText());
        }
        return availabilities;
    }
    
        @Step("Getting the names of all products")
        public List<String> getProductsName() {
            driver.navigate().refresh();
            List<WebElement> list = findElements(driver,name);
            List<String> names = new ArrayList<>();
    
            for (WebElement element : list) {
                names.add(element.getText());
            }
            return names;
        }

        @Step("Getting the prices of all products")
        public List<Integer> getProductsPrice() {
            List<WebElement> list = findElements(driver,price);
            List<Integer> prices = new ArrayList<>();
            for (WebElement element : list) {
                String stringNumber = element.getText().replace("$", "");
                stringNumber = stringNumber.replace(",", "");
                double d = Double.parseDouble(stringNumber);
                prices.add((int) d);
            }
            return prices;
        }
    
        @Step("Getting the error message for invalid product search")
        public String getInvalidProductSearch() {
            driver.navigate().refresh();
            return getDataFromElement(driver, invalidProductSearch);
        }
}


