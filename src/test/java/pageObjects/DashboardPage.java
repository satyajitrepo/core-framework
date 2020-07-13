package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage {
	
	WebDriver driver;
	HashMap<String, Integer> genderList;
	
	@FindBy(linkText = "New Customer")
	WebElement lnkNewCustomer;
	
	@FindBy(linkText = "Log out")
	WebElement lnkLogout;
	
	@FindBy(name = "name")
	WebElement edtName;
	
	@FindBy(name = "dob")
	WebElement edtDOB;
	
	@FindBy(name = "addr")
	WebElement edtAddress;
	
	@FindBy(name = "city")
	WebElement edtCity;
	
	@FindBy(name = "state")
	WebElement edtState;
	
	@FindBy(name = "pinno")
	WebElement edtPin;
	
	@FindBy(name = "telephoneno")
	WebElement edtMobileNum;
	
	@FindBy(name = "emailid")
	WebElement edtEmaiId;
	
	@FindBy(name = "password")
	WebElement edtPassword;
	
	@FindBy(name = "sub")
	WebElement btnSubmit;
	
	@FindBy(xpath = "//table[@id='customer']//p")
	WebElement msgCustomerRegConfirmation;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		genderList = new HashMap<String, Integer>();
		genderList.put("male", 1);
		genderList.put("female", 2);
	}
	
	public void clickNewCustomer() {
		lnkNewCustomer.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public void fillNewCustomerData(String name, String gender, String dob, String addr, String city,
			String state, String pin, String phonenum, String email, String password) {
		edtName.sendKeys(name);
		
		driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td/input["+genderList.get(gender)+"]")).click();
		
		edtDOB.sendKeys(dob);
		edtAddress.sendKeys(addr);
		edtCity.sendKeys(city);
		edtState.sendKeys(state);
		edtPin.sendKeys(pin);
		edtMobileNum.sendKeys(phonenum);
		edtEmaiId.sendKeys(email);
		edtPassword.sendKeys(password);
	}
	
	public void clickSubmit() {
		btnSubmit.click();
	}
	
	public void validateCustomerRegistration(String expectedValue) {
		Assert.assertEquals(msgCustomerRegConfirmation.getText(), expectedValue);
	}
}
