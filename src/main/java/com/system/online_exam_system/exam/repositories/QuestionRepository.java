package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    boolean existsByExamIdAndQuestionText(Long exam, String questionText);

    List<Question> findAllByExamId(Long examId);
}

