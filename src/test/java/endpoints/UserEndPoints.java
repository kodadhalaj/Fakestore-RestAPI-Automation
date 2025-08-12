package endpoints;
/*
**
*File:  UserEndPoints.java
* 
* Description:
* This class contains reusable REST-assured methods that perform 
* CRUD (Create, Read, Update, Delete) operations for the User module 
* of the Fake Store API (https://fakestoreapi.com).
* Actions: Performs following
*  getAllUsers()            → Sends GET request to fetch all users
*  createUser(User payload) → Sends POST request to create a new user
*  readUser(int id)         → Sends GET request to retrieve user details by ID
*  updateUser(int id, User payload) → Sends PUT request to update user details
*  deleteUser(int id)               → Sends DELETE request to remove a user by ID
 */

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;



public class UserEndPoints {
	
	// create a method called createUser to add a user 
	public static Response createUser(User payload)
	{		
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		
		.when()
		    .post(Routes.post_url);
		
		return response;		
	}

	
	// create a method called readUser to retrieve user 
	public static Response readUser(int id)
	{		
		Response response=given()
				.pathParam("id", id)
		  
		.when()
		    .get(Routes.get_url);
		
		return response;		
	}
	
		
	
	// create a method called updateUser to update user information
		public static Response updateUser(int id, User payload)
		{		
			Response response=given()
			   .contentType(ContentType.JSON)
			   .accept(ContentType.JSON)
			   .pathParam("id", id)
			   .body(payload)
			
			.when()
			    .put(Routes.put_url);
			
			return response;		
		}
		
		// create a method called deleteUser to delete user 
		public static Response deleteUser(int id)
		{		
			Response response=given()
					.pathParam("id", id)
			  
			.when()
			    .delete(Routes.delete_url);
			
			return response;		
		}
		
	
	 public static Response getAllUsers()
	 {
		 Response response = RestAssured
				    .given()
				    .when()
				    .get(Routes.getall_url);
		 return response;
	 }
}
