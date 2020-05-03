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
import utils.ExcelUtils;

public class RegistrationPageTest {
	
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
	public void registrationTest() {
		
		
		SoftAssert sa = new SoftAssert();
		
		RegistrationPage rp = new RegistrationPage(driver, locators, waiter);
		
		ExcelUtils excelOption = new ExcelUtils();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			this.driver.navigate().to(this.locators.getProperty("registration_url"));
			
			int idn = ExcelUtils.getRandomId(1000,1);
			String id = Integer.toString(idn);
			String password = ExcelUtils.getDataAt(i, 1);
			String repeatPassword = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String email = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAt(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			
			rp.setNewRegistration(id, password, repeatPassword, firstName, lastName, email, phone, 
									address1, address2, city, state, zip, country);
			rp.setLanguage("English");
			rp.setCategory("BIRD");
			
			rp.enableList();
			rp.enableBanner();
			
			rp.saveData();
			
			sa.assertTrue(rp.savedRegistration());
		}
		sa.assertAll();
	}
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
