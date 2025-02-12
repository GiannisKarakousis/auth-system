# Auth System

This is an authentication system built using Spring Boot that supports user registration, login, role-based access control, JWT token generation, and more. It integrates with a MySQL database and uses Spring Security for authentication and authorization.

## Features

• User Registration: Users can register by providing a username, password, email, and role.  
• Role-Based Access Control: Each user is assigned a role, and roles define the permissions a user has within the system.  
• JWT Authentication: Once a user logs in, a JWT (JSON Web Token) is generated for secure API access.  
• Security: Implements security measures such as account locking after multiple failed login attempts and password hashing.  
• MySQL Database: Stores user and role information in a MySQL database.  

## API Endpoints

### 1. User Registration  
    POST /api/auth/register  
Registers a new user.  
Request Body:  
```json
{
  "firstname": "string",
  "lastname": "string",
  "username": "string",
  "password": "string",
  "email": "string",
  "roleId": 0,
  "enabled": true
}
```
Response:  
```json
{
  "id": 0,
  "roleId": 0,
  "firstname": "string",
  "lastname": "string",
  "email": "string",
  "enabled": true
}
```
### 2. User Login
    POST /api/auth/login  
Logs in a user and generates a JWT token.  
Request Body:  
```json
{
  "username": "string",
  "password": "string"
}
```
Response:  
```json
{
  "accessToken": "string",
  "type": "Bearer"
}
```
### 3. Get User by ID
    GET /api/users/{id} 
Retrieves user details by their ID.  
Response:  
```json
{
  "id": 0,
  "roleId": 0,
  "firstname": "string",
  "lastname": "string",
  "email": "string",
  "enabled": true
}
```
## Technologies Used

• Spring Boot: Framework for building the backend API.  
• Spring Security: Provides authentication and authorization mechanisms.  
• Spring Data JPA: For database access using Hibernate ORM.  
• MySQL: Database to store user and role information.  
• JWT (JSON Web Token): For secure token-based authentication.  
• ModelMapper: For converting between different DTOs and entities.  
• Maven: Build and dependency management.  
