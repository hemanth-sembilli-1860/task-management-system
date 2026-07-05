# Task Management System

A secure backend Task Management System built using Spring Boot, Spring Security, JWT, MySQL, and JPA.  
This project allows users to manage their tasks securely with authentication, authorization, and ownership-based access control.

---

## Features

### Authentication & Security
- User registration with encrypted passwords (BCrypt)
- User login with JWT token generation
- Spring Security integration
- Protected APIs using Bearer token authentication

### Task Management
- Create tasks
- View all personal tasks
- Get task by ID
- Update task details
- Delete tasks
- Mark tasks as completed

### Filtering
- Filter tasks by status (PENDING, IN_PROGRESS, COMPLETED)
- Filter tasks by priority (LOW, MEDIUM, HIGH)

### Ownership Authorization
- Only task creator can:
  - View task details
  - Update task
  - Delete task
  - Mark task as completed
- Other users are denied access

### Exception Handling
- Custom access denied exception
- Global exception handler for cleaner API responses

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## Project Structure

src/main/java/com/taskmanager
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util

---

## API Endpoints

### User APIs
- POST `/users/register`
- POST `/users/login`

### Task APIs
- POST `/tasks`
- GET `/tasks`
- GET `/tasks/{id}`
- PUT `/tasks/{id}`
- DELETE `/tasks/{id}`
- PATCH `/tasks/{id}/complete`

### Filter APIs
- GET `/tasks/status/{status}`
- GET `/tasks/priority/{priority}`

---

## Security Flow

1. Register user
2. Login and receive JWT token
3. Pass token in Authorization header:

Bearer <your_token>

4. Access secured task APIs

---

## Future Improvements

- Swagger API documentation
- Role-based authorization (ADMIN/USER)
- Cloud deployment (Render/Railway)
- Email notifications
- Task deadlines reminder

---

## Author

Hemanth
