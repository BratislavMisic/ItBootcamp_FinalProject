package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public List<WebElement> getLeftNavBarLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("left_menu_link")));
	}
	public boolean leftNavBarLinksActive() {
		List<WebElement> leftMenuLinks = this.getLeftNavBarLinks();
		boolean activeUrl = false;
		for (int i = 0; i < leftMenuLinks.size(); i++) {
			String link = leftMenuLinks.get(i).getAttribute("href");
			if (this.verifyURLStatus(link) < 400) {
				activeUrl = true;
			}
		}
		return activeUrl;
	}
	
	public boolean leftNavBarLinksPath() {
		List<WebElement> leftMenuLinks = this.getLeftNavBarLinks();
		boolean pathTrue = false;
		for (int i = 0; i < leftMenuLinks.size(); i++) {
			String link = leftMenuLinks.get(i).getAttribute("href");
			leftMenuLinks.get(i).click();
			WebElement pathLink = driver.findElement(By.xpath(locators.getProperty("h2_element")));
			String headLine = pathLink.getAttribute("h2");
			if (link.contains(headLine)) {
				pathTrue = true;
			}			
		}
		return pathTrue;
	}
	
	public List<WebElement> getTopMenuLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("top_menu_link")));
	}
	public boolean topMenuLinksActive() {
		List<WebElement> topMenuLinks = this.getTopMenuLinks();
		boolean activeUrl = false;
		for (int i = 0; i < topMenuLinks.size(); i++) {
			String link = topMenuLinks.get(i).getAttribute("href");
			if (this.verifyURLStatus(link) < 400) {
				activeUrl = true;
			}
		}
		return activeUrl;
	}
	
	public boolean topMenuLinksPath() {
		List<WebElement> topMenuLinks = this.getTopMenuLinks();
		boolean pathTrue = false;
		for (int i = 0; i < topMenuLinks.size(); i++) {
			String link = topMenuLinks.get(i).getAttribute("href");
			topMenuLinks.get(i).click();
			WebElement pathLink = driver.findElement(By.xpath(locators.getProperty("h2_element")));
			String headLine = pathLink.getAttribute("h2");
			if (link.contains(headLine)) {
				pathTrue = true;
			}			
		}
		return pathTrue;
	}
	
	public List<WebElement> getPhotoMenuLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("photo_menu_link")));
	}
	public boolean photoMenuLinksActive() {
		List<WebElement> photoMenuLinks = this.getPhotoMenuLinks();
		boolean activeUrl = false;
		for (int i = 0; i < photoMenuLinks.size(); i++) {
			String link = photoMenuLinks.get(i).getAttribute("href");
			if (this.verifyURLStatus(link) < 400) {
				activeUrl = true;
			}
		}
		return activeUrl;
	}

	public boolean photoMenuLinksPath() {
		List<WebElement> photoMenuLinks = this.getPhotoMenuLinks();
		boolean pathTrue = false;
		for (int i = 0; i < photoMenuLinks.size(); i++) {
			String link = photoMenuLinks.get(i).getAttribute("href");
			photoMenuLinks.get(i).click();
			WebElement pathLink = driver.findElement(By.xpath(locators.getProperty("h2_element")));
			String headLine = pathLink.getAttribute("h2");
			if (link.contains(headLine)) {
				pathTrue = true;
			}			
		}
		return pathTrue;
	}
	
	public List<WebElement> getHeadMenuLinks() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("head_menu_link")));
	}
	public boolean headMenuLinksActive() {
		List<WebElement> headMenuLinks = this.getHeadMenuLinks();
		boolean activeUrl = false;
		for (int i = 0; i < headMenuLinks.size(); i++) {
			String link = headMenuLinks.get(i).getAttribute("href");
			if (this.verifyURLStatus(link) < 400) {
				activeUrl = true;
			}
		}
		return activeUrl;
	}
	
	public void getSignInButton() {
		this.driver.findElement(By.xpath(locators.getProperty("sign_in_btn"))).click();
	}
	public boolean signInButtonPath() {
		return this.driver.findElement(By.xpath(locators.getProperty("login_btn"))).isDisplayed();
		}
	
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}
