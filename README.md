
---
# FakestoreAPI Test Automation

## 📌 Objective  
This project demonstrates an **API Automation Framework** for performing CRUD (Create, Read, Update, Delete) operations on the **Fakestore API Users endpoint**.

API Under Test: [https://fakestoreapi.com/users](https://fakestoreapi.com/users)

---

## 🛠 Tools & Frameworks  
- **Eclipse** – Open-source IDE for development.  
- **Maven** – Dependency management & build automation.  
- **Rest Assured** – Java library for REST API automation.  
- **TestNG** – Test framework for structuring and managing test cases.  
- **Extent Reports** – For generating interactive execution reports.  


### 🔹 Rest Assured  
- Java library for REST API automation.  
- Provides methods to send HTTP requests & validate responses.  

### 🔹 TestNG  
- Provides annotations for test flow control.  
  - `@Test` → Define test cases.  
  - `@BeforeClass` → Setup tasks (payloads, authentication, etc).  
- Includes **assertions** to validate API responses.  

### 🔹 Extent Reports  
- Generates interactive HTML reports.  
- Integrates with **TestNG listeners** for automatic report generation.  
- Captures request/response logs with **pass/fail status** for each test case.  

---
### ✅  Files 
- **Routes.java** – Centralized repository for all API endpoint URLs, promoting maintainability and reducing code duplication.
- **User.java** – Plain Old Java Object (POJO) representing the User entity with proper encapsulation and JSON mapping annotations.
- **UserEndPoints.java** – Contains all HTTP method implementations (GET, POST, PUT, DELETE) with Rest Assured configurations and response handling. 
- **UserTests.java** – TestNG test class containing all test cases with proper annotations, assertions, and test data management.
- **ExtentReportsManager.java** – Utility class for generating comprehensive HTML reports with request/response logging and test execution details.


## 🌐 Fakestore API Endpoints  

**Base URL:** `https://fakestoreapi.com/users`  

| Method | Endpoint        | Description                  |
|--------|-----------------|------------------------------|
| GET    | /users          | Retrieve all users           |
| GET    | /users/{id}     | Retrieve a single user by ID |
| POST   | /users          | Add a new user               |
| PUT    | /users/{id}     | Update an existing user      |
| DELETE | /users/{id}     | Delete a user                |

✅ **Key Highlights**

Modular test design with separate routes, payloads, and endpoints.

Automated CRUD API testing with validations.

Integrated HTML reports with detailed execution logs.
