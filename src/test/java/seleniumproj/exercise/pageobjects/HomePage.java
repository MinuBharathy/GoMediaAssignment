package seleniumproj.exercise.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(linkText = "iPad 4 Mini")
    private WebElement product_1;
    
    @FindBy(linkText = "H&M T-Shirt White")
    private WebElement product_2;
    
    @FindBy(linkText = "Charli XCX - Sucker CD")
    private WebElement product_3;

    @FindBy(xpath = "//a[@class='title']")
    private List<WebElement> products;
    
    @FindBy(xpath = "//span[@class='price']")
    private List<WebElement> prices;

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void loadHomePage() {
        driver.get("https://skyronic.github.io/vue-spa/#/");
        
        WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
    }
    
    public String clickProduct(String name) {
    	if (name.equalsIgnoreCase("iPad 4 Mini")) {
    		product_1.click();
    	} else if (name.equalsIgnoreCase("H&M T-Shirt White")) {
    		product_2.click();
    	} else {
    		product_3.click();
    	}
    	
    	return driver.getCurrentUrl();
    }
    
    public ArrayList<String> getProducts() {
    	ArrayList<String> productList = new ArrayList<String>();
    	for (WebElement product : products) {
    		productList.add(product.getText());
    	}
    	return productList;
    }
		
	public ArrayList<String> getPrices() {
		ArrayList<String> priceList = new ArrayList<String>();
    	for (WebElement price : prices) {
    		priceList.add(price.getText());
    	}
    	return priceList;
    }

}
