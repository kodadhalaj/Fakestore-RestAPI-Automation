package endpoints;

/**
 * Routes.java
 * 
 * Description:
 * This class defines the base URL and all endpoint routes for the User module 
 * of the Fake Store API (https://fakestoreapi.com). 

/*
 * Fakestore url:  https://fakestoreapi.com
 * 
 * Create user (POST): https://fakestoreapi.com/users
 * Get user (GET): https://fakestoreapi.com/users/{userid}
 * Update User (PUT): https://fakestoreapi.com/users/{userid}
 * Delete User (DELETE): https://fakestoreapi.com/users/{userid}
 */

public class Routes {
	
	public static String homeurl = "https://fakestoreapi.com";
	
	// user module urls
	public static String post_url = homeurl+"/users";
	public static String getall_url = homeurl+"/users";
	public static String get_url = homeurl+"/users/{id}";
	public static String put_url = homeurl+"/users/{id}";
	public static String delete_url = homeurl+"/users/{id}";
	
}
