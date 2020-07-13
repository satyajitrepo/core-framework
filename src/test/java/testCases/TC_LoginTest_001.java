package testCases;

import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_LoginTest_001 extends Base{
	
	@Test()
	public void LoginTest() {
		 LoginPage lp = new LoginPage(driver);
		 lp.setUserName(userName);
		 lp.setPassword(password);
		 lp.clickSubmit().clickLogout();
	}
}
