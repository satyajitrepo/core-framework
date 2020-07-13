package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public JavascriptExecutor js; 
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		
		if(browser.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			
			DesiredCapabilities ch = DesiredCapabilities.chrome();
			ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			ChromeOptions options = new ChromeOptions();
			options.merge(ch);

			driver = new ChromeDriver(options);
		}
		else if(browser.contentEquals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(browser.contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIePath());
			driver = new InternetExplorerDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseURL);
		js = (JavascriptExecutor) driver;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public static void captureScreen(String path) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(path);
		
		FileUtils.copyFile(source, target);
	}
}
