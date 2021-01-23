package workspace;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BasicLogic {
	WebDriver driver;
	PageObjectRepository obj;
	CommonFunctions func;

	public BasicLogic(WebDriver driver) {
		this.driver = driver;
		obj = new PageObjectRepository(driver);
		func = new CommonFunctions(driver);
	}

	public String LoginFlow(ExtentTest test, String username, String password) {
		String var = "false";
		if (obj.isLoginLinkDisplayed()) {
			test.log(Status.PASS, "Login Link is displayed");
			func.verifyText(obj.getLoginText(), "Hello, Sign in", "Login Link Text", test);
			obj.clickOnLoginLink();
			if (obj.isLoginFormDisplayed()) {
				test.log(Status.PASS, "Login Form is displayed");
				obj.setEmailId(username);
				obj.clickOnSubmitButton();
				if (obj.isCaptchaDisplayed()) {
					test.log(Status.INFO, "Captcha OR OTP Screen appeared so cannot proceed further");
					var = "captcha";
				} else if (obj.isPasswordScreenDisplayed()) {
					test.log(Status.PASS, "Password screen is displayed");
					func.verifyText(obj.getUsernameFromPasswordScreen(), "+91" + username,
							"Username on Password Screen", test);
					obj.setPassword(password);
					obj.clickOnSubmitButton();
					if (obj.isCaptchaDisplayed()) {
						test.log(Status.INFO, "Captcha OR OTP Screen appeared so cannot proceed further");
						var = "captcha";
					} else if (obj.isUserLoggedIn()) {
						test.log(Status.PASS, "User is successfully logged in");
						// username to be changed as per account
						func.verifyText(obj.getLoginText(), "Hello, Nikhil", "Username displayed after user logged in",
								test);
						var = "true";
					} else {
						test.log(Status.FAIL, "User is not logged in");
					}
				} else {
					test.log(Status.FAIL, "Password screen is not displayed");
				}
			} else {
				test.log(Status.FAIL, "Login Form is not displayed");
			}
		} else {
			test.log(Status.FAIL, "Login Link is not displayed");
		}
		return var;
	}

	public void LogOutFlow(ExtentTest test, String username, String password) {
		String var = LoginFlow(test, username, password);
		if (var.equalsIgnoreCase("true")) {
			obj.logout();
			if (obj.isLoginFormDisplayed()) {
				test.log(Status.PASS, "User is successfully logged out");
			} else {
				test.log(Status.FAIL, "User is not logged out");
			}
		} else if (var.equalsIgnoreCase("captcha")) {
			test.log(Status.INFO, "Logout not possible since log in process terminated");
		} else {
			test.log(Status.FAIL, "Logout not possible since log in process not completed successfully");
		}

	}

	public void Search(String product_name, ExtentTest test) {
		obj.setProductName(product_name);
		obj.clickOnSubmitButton();
		if (obj.isSearchPerformed(product_name)) {
			test.log(Status.PASS, "Search is performed successfully");
			test.log(Status.INFO, "Search Result Text: " + obj.getResultsText());
		} else {
			test.log(Status.FAIL, "Search is not performed");
		}
	}

	public void AddToCart(String product_name, ExtentTest test) {
		Search(product_name, test);
		obj.openProductDesc();
		func.switchToChildWindow();
		if (obj.isProductDescDisplayed()) {
			test.log(Status.PASS, "Product Description page opened successfully");
			obj.addToCart();
			if (obj.isCartUpdated()) {
				test.log(Status.PASS, "Product added to cart successfully");
				test.log(Status.INFO, "Cart final amount: " + obj.getValue());
			} else {
				test.log(Status.FAIL, "Product is not added to cart");
			}
		} else {
			test.log(Status.FAIL, "Product Description page did not appear");
		}
	}

}
