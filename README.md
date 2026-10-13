# Employee CRUD REST API

A RESTful Employee Management application built using **Java and Spring Boot** that provides APIs to create, retrieve, update, and delete employee records.

## 🚀 Features

- **Create Employee:** Add new employee records.
- **Get All Employees:** Retrieve the list of employees.
- **Get Employee by ID:** Fetch an employee using their unique ID.
- **Update Employee:** Modify existing employee details.
- **Delete Employee:** Remove an employee record.
- **RESTful APIs:** Perform CRUD operations through HTTP requests.

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Database:** Configure according to your project (MySQL, PostgreSQL, H2, etc.)
- **API Testing:** Postman
- **Version Control:** Git and GitHub

## 📁 Project Structure

```text
employee/
├── src/
│   ├── main/
│   │   ├── java/com/example/employee/
│   │   │   └── EmployeeApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## ⚙️ Prerequisites

Install the following tools before running the project:

- Java JDK compatible with the version specified in `pom.xml`
- Maven (optional if using the included Maven Wrapper)
- A database configured in `application.properties`, if required

## 📥 Installation and Setup

**1. Clone the repository**

```bash
git clone https://github.com/Pavankumarpsp/spring-boot-employee-crud.git
```

**2. Navigate to the project directory**

```bash
cd spring-boot-employee-crud
```

**3. Configure the database**

Open `src/main/resources/application.properties` and configure your database connection, credentials, and other required settings.

Do not commit real passwords, API keys, or other secrets.

**4. Run the application**

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will run on the configured server port, commonly `8080`.

## 🔗 API Endpoints

Update these example endpoints to match your controller mappings.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/employees` | Create an employee |
| GET | `/employees` | Retrieve all employees |
| GET | `/employees/{id}` | Retrieve an employee by ID |
| PUT | `/employees/{id}` | Update an employee |
| DELETE | `/employees/{id}` | Delete an employee |

### Example: Create Employee

**Request**

```http
POST /employees
Content-Type: application/json
```

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "department": "Engineering"
}
```

The JSON fields are illustrative. Use the actual field names defined in your Employee model.

## 🧪 Testing

You can test the API using Postman or another REST client.

1. Start the Spring Boot application.
2. Send requests to the configured API endpoints.
3. Verify the HTTP status codes and response data.
4. Run the automated tests using:

```powershell
.\mvnw.cmd test
```

## 🎯 Learning Objectives

This project demonstrates practical experience with:

- Spring Boot application development
- REST API design and HTTP methods
- CRUD operations
- Maven project management
- Database connectivity and persistence, if configured
- API testing using Postman

## 🔮 Future Enhancements

- Add request validation and global exception handling.
- Implement pagination and sorting.
- Add Spring Security and role-based authorization.
- Add Swagger/OpenAPI documentation.
- Implement comprehensive unit and integration tests.

## 👨‍💻 Author

**Pavankumar P S**

- GitHub: https://github.com/Pavankumarpsp
- LinkedIn: https://linkedin.com/in/pavankumar-p-s

