package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getItemId() {
		return this.driver.findElements(By.xpath(locators.getProperty("item_id")));
	}

	public WebElement getSumTotal() {
		return this.driver.findElement(By.xpath(locators.getProperty("sum_total")));
	}

	
	public int getSum() {
		String subTotal = this.getSumTotal().getText().substring(12);
		double total = Double.parseDouble(subTotal);
                return (int) (total * 100);
	}

	public List<WebElement> getAllItemPriceTotal() {
		return this.driver.findElements(By.xpath(locators.getProperty("price_total_1")));
	}

	public boolean isItemAddedToCart(String id) {
		List<WebElement> itemsId = this.getItemId();
		for (int i = 0; i < itemsId.size(); i++) {
			String itemId = itemsId.get(i).getText();
			if (itemId.contentEquals(id)) {
				return true;
			}
		}
		return false;
	}

	public void deleteCookies() {
		this.driver.manage().deleteAllCookies();
	}

	public boolean CartEmpty() {
		try {
			this.driver.findElement(By.xpath(locators.getProperty("empty_cart")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getTotalItemSum() {
		List<WebElement> allItemPrice = this.getAllItemPriceTotal();
		int sum = 0;
		for (int i = 0; i < allItemPrice.size(); i++) {
			String itemPrice = allItemPrice.get(i).getText().substring(1,5);
			double price = Double.parseDouble(itemPrice);
			sum += (int) (price * 100);
		}
		return sum;
	}

	public boolean verifyPrices() {
		return this.getSum() == this.getTotalItemSum();
	}

}

