package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {
	
	private WebDriverWait wait;
	
	public void newWait(int sec, WebDriver driver) {
		wait = new WebDriverWait(driver, sec);
	}

	public void waitForClickability(WebElement element, WebDriver driver, int waitTime) {
		newWait(waitTime, driver);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForVisibility(WebElement element, WebDriver driver, int waitTime) {
		newWait(waitTime, driver);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForInvisability(WebElement element, WebDriver driver, int waitTime) {
		newWait(waitTime, driver);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
}