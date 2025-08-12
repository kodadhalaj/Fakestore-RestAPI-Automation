package utilities;

/**
 * ExtentReportManager.java
 * 
 * Description:
 * This class implements the TestNG ITestListener interface and integrates 
 * Extent Reports into the API test automation framework for the Fake Store API.
 * 
 * * Purpose:
 * - Provide a centralized reporting mechanism for all TestNG-driven API tests
 * - Automatically generate a timestamped HTML report for each test run
 * */

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.markuputils.CodeLanguage;

public class ExtentReportManager implements ITestListener

{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date()); //time stamp
		String repName="Test Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\Reports\\"+repName); //specify location of the report
		
		sparkReporter.config().setDocumentTitle("RESTAPI Automation Report");
		sparkReporter.config().setReportName("Fake Store User API");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application",  "Fake Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User name", System.getenv("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Joseph Kodadhala");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		String responseBody = (String) result.getAttribute("responseBody");
		if (responseBody != null) {
			test.log(Status.INFO, "Response of Get all users API call");
		    test.log(Status.INFO, "Response Body: " + responseBody);
		    //test.log(Status.INFO, MarkupHelper.createCodeBlock(responseBody, CodeLanguage.JSON));
		}
		
		String allInfo = (String) result.getAttribute("Newly Added User Informarion");
		if (allInfo != null) {
		test.log(Status.INFO, "Newly registered user details are as follows:");
		test.log(Status.INFO, allInfo);
		}
		
		
		String newUser = (String) result.getAttribute("new user");
		test.log(Status.INFO, newUser);
		
		String getUserById = (String) result.getAttribute("Get User Details By Id");
		if (getUserById != null) {
			test.log(Status.INFO, "User details response by Id");
			}
		test.log(Status.INFO, getUserById);
		//test.log(Status.INFO, MarkupHelper.createCodeBlock(getUserById, CodeLanguage.JSON));
		
		String updatedUser = (String) result.getAttribute("Updated user email");
		if (updatedUser != null) {
			test.log(Status.INFO, "User email information is updated");
			}
		test.log(Status.INFO, updatedUser);
		//test.log(Status.INFO, MarkupHelper.createCodeBlock(updatedUser, CodeLanguage.JSON));
								
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
				
	}
		
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.FAIL, result.getThrowable().getMessage());
				
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
							
	}
	

}
