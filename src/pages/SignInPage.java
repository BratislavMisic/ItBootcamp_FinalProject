package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public WebElement getUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("username"))); 
	}
	public void setUserName(String username) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("password")));
	}
	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}
	
	public WebElement getLogin() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("login_btn")));
	}
	public void setLogin() {
		this.getLogin().click();
	}
	
	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("sign_out_btn")));
	}
	public void signOut() {
		this.getSignOut().click();
	}
	
	public boolean logedIn() {
		boolean logedIn = false;
		if (this.getSignOut().isDisplayed()) {
			logedIn = true;
		}
		return logedIn;
	}
 }
