package workspace;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class AmazonDemo extends BaseTest{

	@BeforeClass
	public void extent() {
		parent = extent.createTest("AMAZON: Basic User flow of Shopping");
	}
	
	@Test(priority=1)
	public void validateLogin() {
		try {
			test = parent.createNode("CHECK LOGIN FLOW");
			BasicLogic logic=new BasicLogic(driver);
			CommonFunctions func= new CommonFunctions(driver);
			
			String url = URL;
			func.navigateTo(url);
			test.log(Status.INFO, "<b>CURRENT URL : </b>"+func.getCurrentUrl());
			test.log(Status.INFO, "<b>LOGIN CREDENTIAL: USERNAME: </b>"+LOGIN_USERNAME);
			test.log(Status.INFO, "<b>LOGIN CREDENTIAL: PASSWORD: </b>"+LOGIN_PASSWORD);
			logic.LoginFlow(test,LOGIN_USERNAME,LOGIN_PASSWORD);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void validateLogout() {
		try {
			test = parent.createNode("CHECK LOGOUT FLOW");
			BasicLogic logic=new BasicLogic(driver);
			CommonFunctions func= new CommonFunctions(driver);
			
			String url = URL;
			func.navigateTo(url);
			test.log(Status.INFO, "<b>CURRENT URL : </b>"+func.getCurrentUrl());
			test.log(Status.INFO, "<b>LOGIN CREDENTIAL: USERNAME: </b>"+LOGIN_USERNAME);
			test.log(Status.INFO, "<b>LOGIN CREDENTIAL: PASSWORD: </b>"+LOGIN_PASSWORD);
			logic.LogOutFlow(test,LOGIN_USERNAME,LOGIN_PASSWORD);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=3)
	public void validateSearch() {
		try {
			test = parent.createNode("VALIDATE SEARCH FLOW");
			BasicLogic logic=new BasicLogic(driver);
			CommonFunctions func= new CommonFunctions(driver);
			
			String url = URL;
			func.navigateTo(url);
			test.log(Status.INFO, "<b>CURRENT URL : </b>"+func.getCurrentUrl());
			test.log(Status.INFO, "<b>PRODUCT SEARCHED: </b>"+PRODUCT);
			logic.Search(PRODUCT, test);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=4)
	public void validateAddToCart() {
		try {
			test = parent.createNode("CHECK ADD TO CART FLOW");
			BasicLogic logic=new BasicLogic(driver);
			CommonFunctions func= new CommonFunctions(driver);
			
			String url = URL;
			func.navigateTo(url);
			test.log(Status.INFO, "<b>CURRENT URL : </b>"+func.getCurrentUrl());
			test.log(Status.INFO, "<b>PRODUCT TO BE ADDED TO CART: </b>"+PRODUCT);
			logic.AddToCart(PRODUCT, test);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
