package workspace;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	
	protected  WebDriver driver;
	public ExtentTest parent;
	protected static ExtentReports extent;
	public ExtentTest test ;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm");
	private Date date = new Date();
	protected String currentTimeStamp = dateFormat.format(date);
	public static final String URL = "https://www.amazon.in/";
	protected String REPORT_PATH = System.getProperty("user.dir") + "/reports/"+"Amazon_demo_"+currentTimeStamp+".html";
	public final static long DEFAULT_SLEEP_TIMEOUT = 500;
	// enter your credentials
	public String LOGIN_USERNAME="9872911160";
	public String LOGIN_PASSWORD="Nik0712@";
	public String PRODUCT="IPHONE";
	
	@BeforeSuite
	public void SuiteSetUp1() {
		extent = new ExtentReports();
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));         
		extent.setSystemInfo("platformName", "Desktop");
		extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		extent.attachReporter(getHtmlReporter(REPORT_PATH, "abcd"));
	}
	
	private static ExtentHtmlReporter getHtmlReporter(String filePath, String reportName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);  
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().getLevel();                                      
		htmlReporter.config().setCSS("css-string");
		htmlReporter.config().setJS("js-string");     
		return htmlReporter;
	}
	
	@BeforeMethod
	public void suiteSetup() throws Exception {
		try {
			driver = launchDriver();
			driver.manage().window().maximize();
			driver.navigate().to(URL);
		} catch (Exception e) {
			test.log(Status.FAIL, e);
			this.suiteTearDown();
		}
	}
	
	@AfterMethod
	public void suiteTearDown() {
		if(driver != null ) {
			driver.quit();
		}
		extent.flush();
	}
	
	public WebDriver launchDriver() {
		WebDriver driver = null;
		try {
			String driverLoc = System.getProperty("user.dir")+"\\drivers\\windows\\chromedriver.exe";
			System.out.println(driverLoc);
			System.setProperty("webdriver.chrome.driver", driverLoc);
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
