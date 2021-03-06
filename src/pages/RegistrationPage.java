package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("user_id")));
	}
	
	public void setUserId(String id) {
		this.getUserId().clear();
		this.getUserId().sendKeys(id);
	}
	
	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("new_password")));
	}
	
	public void setNewPassword(String password) {
		this.getNewPassword().clear();
		this.getNewPassword().sendKeys(password);
	}
	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeat_password")));
	}
	
	public void setRepeatPassword(String password) {
		this.getRepeatPassword().clear();
		this.getRepeatPassword().sendKeys(password);
	}
	
	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("first_name")));
	}
	
	public void setFirstName(String firstName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
	}
	
	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("last_name")));
	}
	
	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}
	
	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("email")));
	}
	
	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}
	
	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone")));
	}
	
	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}
	
	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_1")));
	}
	
	public void setAddress1(String address1) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address1);
	}
	
	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address_2")));
	}
	
	public void setAddress2(String address2) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address2);
	}
	
	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city")));
	}
	
	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}
	
	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state")));
	}
	
	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}
	
	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip")));
	}
	
	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}
	
	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country")));
	}
	
	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}
	
	public WebElement getLanguage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("language")));
	}
	public Select getLanguageSelection() {
		 return new Select(this.getLanguage());
	}
	public void setLanguage(String language) {
		this.getLanguageSelection().selectByValue(language);
	}
	
	public WebElement getCategory() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("category"))); 
	}
	public Select getCategorySelection() {
		return new Select(this.getCategory());
	}
	public void setCategory(String category) {
		this.getCategorySelection().selectByValue(category);
	}
	
	public WebElement getEnableList() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enable_list")));
	}
	public void enableList() {
		this.getEnableList().click();
	}
	
	public WebElement getEnableBanner() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enable_banner")));
	}
	public void enableBanner() {
		this.getEnableBanner().click();
	}
	
	public WebElement getSaveData() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("save_btn")));
	}
	public void saveData() {
		this.getSaveData().click();
	}
	
	public WebElement myAccountButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("my_account_btn")));
	}
	
	public boolean savedRegistration() {
		boolean registered = false;
		try {
			if (this.myAccountButton().isDisplayed());
				registered = true;
		} catch (Exception e) {
				registered = false;
		}
		return registered;
	}
	
	public void setNewRegistration(String id, String password, String repeatPassword, String firstName, 
						String lastName, String email, String phone, String address1,
						String address2, String city, String state, String zip, String country) {
		this.setUserId(id);
		this.setNewPassword(password);
		this.setRepeatPassword(repeatPassword);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setAddress1(address1);
		this.setAddress2(address2);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setCountry(country);
	}

	public static int getRandomId(int max, int min) {
		int random = (int)(Math.random() * ((max-min)+1)) + min;
		return random;
	}
}
