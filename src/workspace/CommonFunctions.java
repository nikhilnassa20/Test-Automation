package workspace;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CommonFunctions {
	private WebDriver driver = null;
	private static final Logger LOGGER = Logger.getLogger(CommonFunctions.class);
	
	public CommonFunctions(WebDriver driver) {
		this.driver=driver;
	}
	
	public void navigateTo(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			LOGGER.error("Exception occurred while navigating to URL - ".concat(url) + e);
		}
	}

	public String getCurrentUrl() {
		String currentUrl = "";
		try {
			currentUrl = driver.getCurrentUrl();
		} catch (Exception e) {
			LOGGER.error("Exception occurred while fetching current URL " + e);
		}
		return currentUrl;
	}
	
	public boolean waitForElement() {
		boolean isWaitApplied = false;
		try {
			Thread.sleep(2000);
			isWaitApplied = true;
		} catch (Exception e) {
			LOGGER.error("Exception occurred while waiting for elements " + e);
		}

		return isWaitApplied;
	}

	public boolean isDisplayed(WebElement element) {
		boolean isDisplayed = false;		
		try {
			isDisplayed = element.isDisplayed();
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: isDisplayed() " + e);
		}
		return isDisplayed;
	}

	public boolean click(WebElement element) {
		boolean isClicked = false;
		try {
			element.click();
			isClicked = true;
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: click() " + e);
		}
		return isClicked;
	}
	
	public void clearAndType(WebElement textBox, String text) {
		try {
			textBox.clear();
			textBox.sendKeys(text);
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: clearAndType() " + e);
		}
	}
	
	public  void verifyText(String actual, String expected, String key, ExtentTest test) {	
		try {		
			Assert.assertEquals(actual, expected);
			logResult(test, key, actual, expected, "PASS");
		} catch (Error e) {
			SoftAssert sfa = new SoftAssert();
		    sfa.fail("Test Failed due to following error   :", e);
			logResult(test, key, actual, expected, "FAIL");
			test.error(e);
		}
	}
	
	public  void logResult(ExtentTest test, String key, String actual, String expected, String result) {
		if (result.equalsIgnoreCase("PASS"))
			test.log(Status.PASS, "<font size = 3 color=\"#3ADF00\">" + key +"</font>" + " Actual: " + actual + " || Expected: " + expected);
		else 
			test.log(Status.FAIL, "<font size = 3 color=\"#FE2E2E\">" + key +"</font>" + " Actual: " + actual + " || Expected: " + expected);
	}
	
	public String getText(WebElement element) {
		String text = "";
		try {
			if (isDisplayed(element)) {
				text = element.getText();
			}else {
			}
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: getText() " + e);
		}
		return text;
	}

	public boolean mouseOver(WebElement element) {
		boolean isPerformed = false;
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
			isPerformed = true;
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: mouseOver() " + e);
		}
		return isPerformed;
	}

	public boolean mouseOverClick(WebElement element) {
		boolean isPerformed = false;
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(element).click().build().perform();
			isPerformed = true;
		} catch (Exception e) {
			LOGGER.error(" Thrown by method: mouseOverClick() " + e);
		}
		return isPerformed;
	}
	
	public void scrollToElementLocation(WebElement element, String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			int loc = element.getLocation().y;
			executor.executeScript("window.scrollTo(0," + (loc - 250) + ");");
		}
	}
	
	public void switchToChildWindow() {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for(String child : allWindows) {
				driver.switchTo().window(child);
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while switching to child window " + e);
		}
	}
}
