package seleniumproj.exercise.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {
    @FindBy(className = "inventory")
    private WebElement stockAvailable;
    
    @FindBy(className = "add-button")
    private WebElement addButton;
    
    @FindBy(partialLinkText = "Cart")
    private WebElement cartValue;
    
    WebDriver driver;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void loadHomePage() {
        driver.get("https://skyronic.github.io/vue-spa/#/");
    }
    
    public String getStockAvailable() {
    	return stockAvailable.getText();
    }
    
    public void clickAddButton() {
    	addButton.click();
    }
    
    public String getCartValue() {
    	return cartValue.getText();
    }
    
    public boolean induceOutOfStock() {
    	while(!stockAvailable.getText().equalsIgnoreCase("In Stock: 0")) {
    			addButton.click();
    	}
    		
    	return addButton.isEnabled();  
    } 
   
    public String getAddButtonText() {
    	return addButton.getText();
    }
    
    public String getAddButtonTagName() {
    	return addButton.getTagName();
    }
}
