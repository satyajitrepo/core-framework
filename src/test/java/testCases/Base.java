package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {
	public String baseURL = "https://demo.guru99.com/V4/index.php";
	public String userName = "mngr270942";
	public String password = "rupeqYn";
	public WebDriver driver;
	public JavascriptExecutor js;
	public String mainDir = System.getProperty("user.dir");
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", mainDir + "\\Drivers\\chromedriver.exe");
		DesiredCapabilities ch = DesiredCapabilities.chrome();
		ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		ChromeOptions options = new ChromeOptions();
		options.merge(ch);

		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseURL);
		js = (JavascriptExecutor) driver;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
