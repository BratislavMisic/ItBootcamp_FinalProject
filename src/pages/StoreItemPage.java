package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreItemPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public StoreItemPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public WebElement addToCartBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("add_to_cart_btn"))); 
	}
	public void sendToCart() {
		this.addToCartBtn().click();
	}
	
	public WebElement removeBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("remove_btn")));
	}
	
	public boolean sentToCart() {
		boolean inCart = false;
		if (this.removeBtn().isDisplayed()) {
			inCart = true;
		}
		return inCart;
	}
}
