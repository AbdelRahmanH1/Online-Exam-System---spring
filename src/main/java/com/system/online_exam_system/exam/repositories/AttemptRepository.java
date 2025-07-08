package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AttemptRepository extends JpaRepository<ExamAttempt, Long> {
    boolean existsByStudentIdAndExamId(Long studentId, Long examId);

    @Query("""
    SELECT a FROM ExamAttempt a
    JOIN FETCH a.exam
    JOIN FETCH a.student
    WHERE a.id = :attemptId AND a.student.id = :studentId
""")
    Optional<ExamAttempt> findByIdWithExamAndStudent(@Param("attemptId") Long attemptId,
                                                     @Param("studentId") Long studentId);
}
