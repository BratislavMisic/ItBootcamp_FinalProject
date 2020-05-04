package test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartPageTest {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if(browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("google chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("mozzila")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		}
	    else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	}
	
	@BeforeClass
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/finalProject.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void addCartItems() {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);
			String itemLink = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(itemLink);
			sip.sendToCart();
			sa.assertTrue(cp.isItemAddedToCart(itemId));
		}
		sa.assertAll();
	}

	@Test
	public void sum() {
		CartPage cp = new CartPage(driver, locators, waiter);
		Assert.assertTrue(cp.verifyPrices());
	}

	@Test
	public void deletedCookies() {
		this.driver.navigate().to(locators.getProperty("cart_url"));
		CartPage cp = new CartPage(driver, locators, waiter);
		cp.deleteCookies();
		this.driver.navigate().refresh();
		Assert.assertTrue(cp.CartEmpty());
	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}
}
