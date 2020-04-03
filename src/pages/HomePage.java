package pages;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	public void mainPageEntrance() {
		 this.driver.findElement(By.xpath(locators.getProperty("enter_btn"))).click();
	}
	public boolean mainPageValid() {
		return this.driver.findElement(By.xpath(locators.getProperty("signin_btn"))).isDisplayed();
	}
}
