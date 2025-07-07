package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository  extends JpaRepository<Exam,Long> {
    Page<Exam> findAllByInstructor_Id(Long instructorId, Pageable pageable);
}
