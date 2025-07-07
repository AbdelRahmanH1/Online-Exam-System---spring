package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.Exam;
import com.system.online_exam_system.exam.entites.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    boolean existsByExamIdAndQuestionText(Long exam, String questionText);
}

