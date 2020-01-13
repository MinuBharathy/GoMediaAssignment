package seleniumproj.exercise.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import seleniumproj.exercise.pageobjects.HomePage;
import seleniumproj.exercise.pageobjects.ProductDetailPage;

public class ShoppingTests {

	WebDriver driver;
	String driverPath = "C:\\gecko.exe";
	HomePage homePage;
	ProductDetailPage productDetailPage;

	@BeforeClass(alwaysRun=true)
	public void setup() {

		driver = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver", driverPath);
	}

	@Test(priority=0)
	public void testHomePage() {

		SoftAssert softAssert = new SoftAssert();
		homePage = new HomePage(driver);
		productDetailPage = new ProductDetailPage(driver);
		homePage.loadHomePage();

		ArrayList<String> productList = new ArrayList<String>();
		productList = homePage.getProducts();
		ArrayList<String> priceList = new ArrayList<String>();
		priceList = homePage.getPrices();

		softAssert.assertEquals(productList.get(0), "iPad 4 Mini", "");
		softAssert.assertEquals(productList.get(1), "H&M T-Shirt White", "");
		softAssert.assertEquals(productList.get(2), "Charli XCX - Sucker CD", "");

		softAssert.assertEquals(priceList.get(0), "$ 500.01", "");
		softAssert.assertEquals(priceList.get(1), "$ 10.99", "");
		softAssert.assertEquals(priceList.get(2), "$ 19.99", "");
		softAssert.assertAll();

	}

	@Test(priority=1)
	public void testAddToCart() {

		String productName = "iPad 4 Mini";
		String currentURL = homePage.clickProduct(productName);
		Assert.assertTrue(currentURL.contains("/product/1"), "Product detail page is not loaded");

		String totalStock = "In Stock: 2";
		String stockAvailable = productDetailPage.getStockAvailable();
		Assert.assertEquals(stockAvailable, totalStock, "Stock is not displayed on the product detail page");

		String addButtonText = productDetailPage.getAddButtonText();
		Assert.assertEquals(addButtonText, "Add to cart", "Add to cart button is not displayed");
		String addButtonTagName = productDetailPage.getAddButtonTagName();
		Assert.assertEquals(addButtonTagName, "button", "Add to cart is not a button");

	}

	@Test(priority=2)
	public void testCartUpdate() {

		productDetailPage.clickAddButton();
		String cartValue = productDetailPage.getCartValue();
		Assert.assertEquals(cartValue, "Cart (1)", "Cart is not updated");

	}

	@Test(priority=3)
	public void testOutOfStock() {

		boolean isAddButtonEnabled = productDetailPage.induceOutOfStock();
		Assert.assertFalse(isAddButtonEnabled, "Add to card button is still enabled");
		String addButtonText = productDetailPage.getAddButtonText();
		Assert.assertEquals(addButtonText, "Out Of Stock", "Out of Stock is not displayed");

	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}



