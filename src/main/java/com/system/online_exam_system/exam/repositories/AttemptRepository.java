package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<ExamAttempt, Long> {
    boolean existsByStudentIdAndExamId(Long studentId, Long examId);
}
