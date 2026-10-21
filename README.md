# Student Management System (REST API)

A Spring Boot REST API to manage student records and the courses they are enrolled in. It supports full CRUD operations, search by name, filtering students by course, input validation, and centralised exception handling, with data stored in MySQL through Hibernate/JPA.

## Tech Stack
- Java 17
- Spring Boot 3 (Web, Data JPA, Validation)
- Hibernate / JPA
- MySQL
- Maven
- Postman (API testing)

## Features
- Create, view, update and delete students
- Search students by name (partial, case-insensitive)
- View all students enrolled in a given course
- Create and list courses
- Request validation (required fields, valid email) with clear error messages
- Duplicate email detection (409) and not-found handling (404) through a global exception handler

## Entities
| Entity | Fields |
|--------|--------|
| Course | id, name (unique), durationMonths |
| Student | id, name, email (unique), course (many students -> one course) |

## API Endpoints (9)

| # | Method | URL | Description |
|---|--------|-----|-------------|
| 1 | POST | `/api/courses` | Create a course |
| 2 | GET | `/api/courses` | List all courses |
| 3 | POST | `/api/students` | Add a student |
| 4 | GET | `/api/students` | List all students |
| 5 | GET | `/api/students/{id}` | Get a student by ID |
| 6 | PUT | `/api/students/{id}` | Update a student |
| 7 | DELETE | `/api/students/{id}` | Delete a student |
| 8 | GET | `/api/students/search?name=ash` | Search students by name |
| 9 | GET | `/api/students/course/{courseId}` | List students in a course |

## Project Structure
```
src/main/java/com/aishwarya/studentmanagement
├── controller   (StudentController, CourseController)
├── service      (StudentService, CourseService)
├── repository   (StudentRepository, CourseRepository)
├── model        (Student, Course)
├── dto          (StudentRequest)
└── exception    (custom exceptions + GlobalExceptionHandler)
```

## How to Run
1. Install Java 17+, Maven and MySQL.
2. Open `src/main/resources/application.properties` and set your MySQL `username` and `password`. The database `student_db` is created automatically.
3. From the project folder run:
   ```
   mvn spring-boot:run
   ```
4. The API starts at `http://localhost:8080`.

## Sample Requests

Create a course:
```
POST /api/courses
{ "name": "Java Full Stack", "durationMonths": 6 }
```

Add a student:
```
POST /api/students
{ "name": "Asha Patil", "email": "asha@example.com", "courseId": 1 }
```

Validation error example (missing email):
```
{
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": { "email": "Email is required" }
}
```

## Future Improvements
- Pagination and sorting
- Spring Security with JWT authentication
- Unit and integration tests (JUnit, Mockito)
- Docker support

## Author
Aishwarya Pidashetti - [GitHub](https://github.com/pidashettiaishwarya-art)
