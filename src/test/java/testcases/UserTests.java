package testcases;

/**
 * UserTests.java
 * 
 * Description:
 * This TestNG test class contains automated test cases for verifying
 * the User module of the Fake Store API (https://fakestoreapi.com)
 * Purpose:
 * Validate core user-related endpoints of Fake Store API
 * Ensure each API method returns the expected status codes and response structures
 * Note:
 * Fake Store API is a mock API; POST, PUT, DELETE do not persist changes
 * Test data is generated dynamically to simulate real-world inputs
 * **/

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RedirectSpecification;
import payload.User;
import utilities.ExtentReportManager;

public class UserTests {
	
	Faker faker;
	User userPayload;
			
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		
	}
	
	@Test(priority=1)
	public void testcaseGetAllUsers()
	{
		Response response = UserEndPoints.getAllUsers();

			response.then().statusCode(200);
			response.then().log().body();
			String responseBody = response.getBody().asString();
			//This statement is saving the API response body into the current test result object so that your listener can retrieve it later and log it in Extent Reports.
			Reporter.getCurrentTestResult().setAttribute("responseBody", responseBody);
		
	}
	
	
	@Test(priority=2)
	public void testcasePostUser()
	
	{   
				
		System.out.println(userPayload.getId());
		System.out.println(userPayload.getUsername());
		System.out.println(userPayload.getEmail());
		System.out.println(userPayload.getPassword());
		String allInfo =
		         "Id: " + userPayload.getId() +
		         ", Username: " + userPayload.getUsername() +
		         ", Email: " + userPayload.getEmail() +
		         ", Password: " + userPayload.getPassword();
		Reporter.getCurrentTestResult().setAttribute("Newly Added User Informarion", allInfo);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
		//This statement is saving the API response body into the current test result object so that your listener can retrieve it later and log it in Extent Reports.		
		String newUser = response.getBody().asString();
		Reporter.getCurrentTestResult().setAttribute("new user", newUser);
		
					
	}
	
	
	@Test(priority=3)
	public void testcaseGetUserById()
	{
		//Response response=UserEndPoints.readUser(this.userPayload.getId());
		//response.then().log().all();
		Response response=UserEndPoints.readUser(10);
		response.then().log().body();
		System.out.println(response.then().log().body());
		Assert.assertEquals(response.getStatusCode(), 200);
		String getUserById = response.getBody().asString();
		Reporter.getCurrentTestResult().setAttribute("Get User Details By Id", getUserById);
		
	}
	
	@Test(priority=4)
	public void testcaseUpdateUserById()
	{
		// update user email by using payload
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response=UserEndPoints.updateUser(this.userPayload.getId(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
        		
		String updatedUser = response.getBody().asString();
		Reporter.getCurrentTestResult().setAttribute("Updated user email", updatedUser);
		
		// Check the user email is updated
		Response updatedresponse=UserEndPoints.readUser(this.userPayload.getId());
		Assert.assertEquals(updatedresponse.getStatusCode(), 200);
		
					
	}
	
	@Test(priority=5)
	public void deleteUser()
	{
		Response response=UserEndPoints.deleteUser(this.userPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
	} 
	
}
