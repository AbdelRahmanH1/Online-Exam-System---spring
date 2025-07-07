package com.system.online_exam_system.exam.repositories;

import com.system.online_exam_system.exam.entites.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository  extends JpaRepository<Exam,Long> {
}
