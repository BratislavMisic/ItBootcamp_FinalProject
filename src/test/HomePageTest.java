package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import pages.HomePage;

public class HomePageTest {

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
	public void testHomePage() {
		this.driver.navigate().to(this.locators.getProperty("main_url"));
		HomePage homePage = new HomePage(driver, locators, waiter);
		homePage.mainPageEntrance();
		
		Assert.assertTrue(homePage.mainPageValid());
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}