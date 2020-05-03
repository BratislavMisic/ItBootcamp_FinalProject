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

import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInPageTest {

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
	public void signInTest() {
		
		SoftAssert sa = new SoftAssert();
		
		SignInPage sip = new SignInPage(driver, locators, waiter);
		
		ExcelUtils excelOption = new ExcelUtils();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		
		this.driver.navigate().to(this.locators.getProperty("sign_in_url"));
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			
			sip.setUserName(username);
			sip.setPassword(password);
			sip.setLogin();
			sa.assertTrue(sip.logedIn());
			sip.signOut();
		}
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
