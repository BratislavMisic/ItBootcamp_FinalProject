package test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class PetStoreMenuPageTest {

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
	public void validUrlTest() {
		this.driver.navigate().to(this.locators.getProperty("menu_page_url"));
		SoftAssert sa = new SoftAssert();
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		
		sa.assertTrue(psmp.leftNavBarLinksActive());
		sa.assertTrue(psmp.topMenuLinksActive());
		sa.assertTrue(psmp.photoMenuLinksActive());
		sa.assertTrue(psmp.headMenuLinksActive());
		
		sa.assertTrue(psmp.leftNavBarLinksPath());
		sa.assertTrue(psmp.topMenuLinksPath());
		sa.assertTrue(psmp.photoMenuLinksPath());
		sa.assertTrue(psmp.signInButtonPath());
	}
	
//	@Test
//	public void validPathTest() {
//		this.driver.navigate().to(this.locators.getProperty("menu_page_url"));
//		SoftAssert sa1 = new SoftAssert();
//		PetStoreMenuPage psmp1 = new PetStoreMenuPage(driver, locators, waiter);
//		
//		sa1.assertTrue(psmp1.leftNavBarLinksPath());
//		sa1.assertTrue(psmp1.topMenuLinksPath());
//		sa1.assertTrue(psmp1.photoMenuLinksPath());
//		sa1.assertTrue(psmp1.signInButtonPath());
//	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
