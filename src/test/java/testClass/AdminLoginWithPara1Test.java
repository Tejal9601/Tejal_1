package testClass;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pom.AdminLoginWithPara1;

public class AdminLoginWithPara1Test extends BaseClass {
	AdminLoginWithPara1 logins;
	
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		launchApp();
	}
	@Parameters ({"username","password"})
	@Test
	public void aLogins(String user, String pass) {
		logins= new AdminLoginWithPara1() ;
		logins.aLogin(user, pass);

	}
	@AfterMethod 
	public void tearDown() {
		driver.quit();
	}
}
