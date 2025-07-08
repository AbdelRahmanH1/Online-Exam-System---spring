# 🎯 Online Exam System (Backend)

A fully-featured, role-based backend system for managing online exams, built with Spring Boot, Spring Security, MySQL, JPA, and Lombok. This RESTful API is designed with clear separation of responsibilities between Admins, Instructors, and Students, offering secure access control and efficient exam handling workflows.


---

## ✅ Roles & Functionalities

### 🔐 Authentication
- Login with username and password
- JWT token generation and validation
- Role-based access control: `ADMIN`, `INSTRUCTOR`, `STUDENT`

---

### 🧑‍🎓 Student Features
- View **available exams** based on grade and time
- **Start an exam** (creates an attempt entry)
- **Submit answers** (auto-graded where applicable)
- View **past exam attempts** with scores and timestamps

---

### 👨‍🏫 Instructor Features
- Create exams with title, grade level, duration, and schedule
- Add questions with multiple choices and correct answers
- Edit or delete exams and questions
- View all exam details including questions and answers

---

### 🧑‍💼 Admin Features
- Create users (`STUDENT`, `INSTRUCTOR`, or `ADMIN`)
- View, update, search, or delete students
- Assign or update student grade levels
- View or delete any user

---

## ⚙️ Tech Stack

| Tool             | Purpose                        |
|------------------|--------------------------------|
| Java 17          | Main programming language      |
| Spring Boot      | Application framework          |
| Spring Security  | JWT-based role security        |
| JPA + Hibernate  | ORM for database operations    |
| MySQL            | Relational database            |
| Lombok           | Reduces boilerplate code       |
| Swagger          | API documentation              |
| Maven            | Dependency management          |

---

## 🔍 API Endpoints (Highlights)

### 🛡 Auth
- `POST /api/auth/login` – login with credentials

### 🧑‍💼 Admin
- `POST /api/users` – create user
- `GET /api/students` – list all students
- `PUT /api/students/{id}` – update student grade
- `DELETE /api/users/{id}` – delete user

### 👨‍🏫 Instructor
- `POST /api/exams` – create exam
- `PATCH /api/exams/{id}` – update exam
- `POST /api/questions/{examId}` – add question
- `POST /api/questions/{questionId}/choices` – add choices

### 🧑‍🎓 Student
- `GET /api/exams/available-exams` – list exams available to the student
- `POST /api/exams/attempts/{examId}/start` – start an exam
- `POST /api/exams/attempts/{attemptId}/submit` – submit attempt and receive score
- `GET /api/exams/past-exams` – view previous attempts and results

📘 **Swagger UI:**  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🚀 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/AbdelRahmanH1/Online-Exam-System---spring.git
   cd Online-Exam-System--spring
   ```
2. **Configure your `.evn`**
   ```bash
    DB_URL=jdbc:url
    DB_USERNAME=username
    DB_PASSWORD=password
    JWT_SECRET=your_jwt_secrect
   ```
3. Run the application
   ```bash
    ./mvnw spring-boot:run
   ```
---

## Security

- JWT tokens
- Role-based access via `@PreAuthorize`
- Secure endpoints for Admin, Instructor, and Student
- Unauthorized access handling

---

## Testing

`use swagger or Postman to test`
- login and obtain JWT
- Receive and use JWT token
- add jwt token to the `Authorization` header
  ```bash
    Authorization: Bearer your_token_here
  ```

---

## 🧑 Author

Made with 💻 & ☕ by [Abdelrahman Hossam](https://github.com/AbdelRahmanH1)

---

## 📌 License
This project is open-source and available under the [MIT License](LICENSE).


---
## 🔧 Optional Enhancements
- 🗺 Entity Relationship Diagram (ERD)
- 🧪 Preconfigured test users for Swagger/Postman
