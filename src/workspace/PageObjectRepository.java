package workspace;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectRepository{
	WebDriver driver;
	CommonFunctions func;
	
	public PageObjectRepository(WebDriver driver) {
		this.driver = driver;
		func=new CommonFunctions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	private WebElement loginLink;
	
	@FindBy(xpath="//form[@name='signIn']")
	private WebElement loginForm;
	
	@FindBy(id="nav-flyout-ya-signin")
	private WebElement signInLink;
	
	@FindBy(id="ap_email")
	private WebElement usernameField;
	
	@FindAll({
		@FindBy(id="continue"),
		@FindBy(id="signInSubmit"),
		@FindBy(id="nav-search-submit-button")
	})
	private WebElement submitButton;
	
	@FindBy(id="auth-fpp-link-bottom")
	private WebElement passwordScreen;
	
	@FindBy(xpath="(//div[@class='a-box']//span)[1]")
	private WebElement usernameOnPasswordScreen;
	
	@FindBy(id="ap_password")
	private WebElement passwordField;
	
	@FindBy(id="nav-item-signout")
	private WebElement logoutButton;
	
	@FindBy(id="twotabsearchtextbox")
	private WebElement searchField;
	
	@FindBy(xpath="(//span[@cel_widget_id='UPPER-RESULT_INFO_BAR-0']//div)[4]")
	private WebElement results;
	
	@FindAll({
		@FindBy(xpath="//img[@alt='captcha']"),
		@FindBy(id="channelDetails")
	})
	private WebElement captcha;
	
	@FindBy(linkText="Resend OTP")
	private WebElement resendOtp;
	
	@FindBy(xpath="//div[@data-index='3']//a//div")
	private WebElement productDesc;
	
	@FindBy(id="add-to-cart-button")
	private WebElement addToCartButton;
	
	@FindBy(id="nav-cart-count")
	private WebElement cartValue;
	
	@FindBy(xpath="//div[@id='hlb-subcart']//span")
	private WebElement cartAmount;

	public boolean isLoginLinkDisplayed() {
		func.waitForElement();
		return func.isDisplayed(loginLink);
	}

	public void clickOnLoginLink() {
		func.mouseOver(loginLink);
		func.waitForElement();
		func.click(signInLink);
	}

	public String getLoginText() {
		return func.getText(loginLink);
	}

	public boolean isLoginFormDisplayed() {
		func.waitForElement();
		return func.isDisplayed(loginForm);
	}
	
	public void setEmailId(String username) {
		func.click(usernameField);
		func.clearAndType(usernameField, username);
	}

	public void clickOnSubmitButton() {
		func.click(submitButton);
	}

	public boolean isPasswordScreenDisplayed() {
		func.waitForElement();
		return func.isDisplayed(passwordScreen);
	}

	public String getUsernameFromPasswordScreen() {
		return func.getText(usernameOnPasswordScreen);
	}

	public void setPassword(String password) {
		func.click(passwordField);
		func.clearAndType(passwordField, password);
	}

	public boolean isUserLoggedIn() {
		func.waitForElement();
		return (isLoginLinkDisplayed()?!getLoginText().equalsIgnoreCase("Hello, Sign in"):false);
	}

	public void logout() {
		func.mouseOver(loginLink);
		func.waitForElement();
		func.click(logoutButton);
	}

	public void setProductName(String product_name) {
		func.click(searchField);
		func.clearAndType(searchField, product_name);
	}

	public boolean isSearchPerformed(String product_name) {
		func.waitForElement();
		return func.isDisplayed(results)&&func.getText(results).contains(product_name);
	}
	
	public String getResultsText() {
		return func.isDisplayed(results)?func.getText(results):"false";
	}

	public boolean isCaptchaDisplayed() {
		return func.isDisplayed(captcha)||func.isDisplayed(resendOtp);
	}

	public void openProductDesc() {
		func.scrollToElementLocation(productDesc, "CHROME");
		func.click(productDesc);
	}

	public boolean isProductDescDisplayed() {
		return func.isDisplayed(addToCartButton);
	}

	public void addToCart() {
		func.click(addToCartButton);
		func.waitForElement();
		func.waitForElement();
	}

	public boolean isCartUpdated() {
		return func.getText(cartValue).equalsIgnoreCase("1");
	}

	public String getValue() {
		return func.getText(cartAmount);
	}

}
