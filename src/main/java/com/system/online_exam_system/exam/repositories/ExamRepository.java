package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.dtos.AvailableExamDto;
import com.system.online_exam_system.exam.dtos.PastExamDto;
import com.system.online_exam_system.exam.entites.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExamRepository  extends JpaRepository<Exam,Long> {
    Page<Exam> findAllByInstructor_Id(Long instructorId, Pageable pageable);

    @Query("""
    SELECT new com.system.online_exam_system.exam.dtos.AvailableExamDto(
        e.id,
        e.title,
        e.startTime,
        e.duration,
        i.name
    )
    FROM Exam e
    JOIN e.instructor i
    WHERE e.gradeLevel = :grade
      AND e.endTime > :now
      AND NOT EXISTS (
        SELECT 1 FROM ExamAttempt a
        WHERE a.exam.id = e.id AND a.student.id = :studentId
      )
""")
    Page<AvailableExamDto> findAvailableExamDtos(
            @Param("grade") int grade,
            @Param("studentId") Long studentId,
            @Param("now") LocalDateTime now,
            Pageable pageable
    );




}
