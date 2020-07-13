package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testCases.Base;

public class Reporting implements ITestListener{

	public static ExtentReports extent;
	public ExtentTest logger;
	public static String reportName;
	
	static {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "TestReport-"+timeStamp;
		String reportLocation = "./Reports/"+reportName+"/index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportLocation);
		reporter.loadXMLConfig("./extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);		
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");		
	}

	public void onTestSuccess(ITestResult result) {		
		logger = extent.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		logger.fail(result.getThrowable().toString());
		String screenshotPath = System.getProperty("user.dir")+"/Reports/"+reportName+"/img/"+result.getName()+".png";
		try {
			Base.captureScreen(screenshotPath);
			logger.fail("Screenshot is given below : "+logger.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
