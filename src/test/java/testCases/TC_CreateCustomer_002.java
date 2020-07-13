package testCases;

import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;

public class TC_CreateCustomer_002 extends Base{
	
	@Test
	public void CreateCustomerTest() {
		 LoginPage lp = new LoginPage(driver);
		 lp.setUserName(userName);
		 lp.setPassword(password);
		 DashboardPage dp = lp.clickSubmit();
		 dp.clickNewCustomer();
		 dp.fillNewCustomerData("Satyajit", "male", "31/10/1995", "Kolkata NDB", "Kolkata", "West Bengal", 
				 "700039", "9051433458", "satyajitbarman72@gmail.com", "1234");
		 dp.clickSubmit();
		 dp.validateCustomerRegistration("Customer Registered Successfully!!!");
	}
}
