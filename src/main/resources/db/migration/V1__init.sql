-- ========================
-- USERS (Base Table)
-- ========================
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       role ENUM('STUDENT', 'INSTRUCTOR','ADMIN') NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ========================
-- STUDENT (Extends User)
-- ========================
CREATE TABLE student (
                         id BIGINT PRIMARY KEY,
                         grade INT,
                         FOREIGN KEY (id) REFERENCES users(id)
);

-- ========================
-- INSTRUCTOR (Extends User)
-- ========================
CREATE TABLE instructor (
                            id BIGINT PRIMARY KEY,
                            department VARCHAR(100),
                            FOREIGN KEY (id) REFERENCES users(id)
);

-- ========================
-- EXAM
-- ========================
CREATE TABLE exam (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      start_time DATETIME NOT NULL,
                      end_time DATETIME NOT NULL,
                      duration_minutes INT NOT NULL,
                      created_by BIGINT NOT NULL,
                      grade_level INT,
                      allow_multiple_attempts BOOLEAN DEFAULT FALSE,
                      FOREIGN KEY (created_by) REFERENCES instructor(id)
);

-- ========================
-- QUESTION
-- ========================
CREATE TABLE question (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          text TEXT NOT NULL,
                          correct_answer VARCHAR(255) NOT NULL,
                          exam_id BIGINT NOT NULL,
                          FOREIGN KEY (exam_id) REFERENCES exam(id) ON DELETE CASCADE
);
-- ========================
-- QUESTION CHOICES
-- ========================
CREATE TABLE question_choices (
                                  question_id BIGINT NOT NULL,
                                  choice VARCHAR(255) NOT NULL,
                                  FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);
-- ========================
-- EXAM ATTEMPT
-- ========================
CREATE TABLE exam_attempt (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              student_id BIGINT NOT NULL,
                              exam_id BIGINT NOT NULL,
                              started_at DATETIME NOT NULL,
                              submitted_at DATETIME,
                              score DOUBLE,
                              FOREIGN KEY (student_id) REFERENCES student(id),
                              FOREIGN KEY (exam_id) REFERENCES exam(id)
);

-- ========================
-- STUDENT ANSWER
-- ========================
CREATE TABLE student_answer (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                answer TEXT NOT NULL,
                                score DOUBLE,
                                question_id BIGINT NOT NULL,
                                attempt_id BIGINT NOT NULL,
                                FOREIGN KEY (question_id) REFERENCES question(id),
                                FOREIGN KEY (attempt_id) REFERENCES exam_attempt(id)
);
